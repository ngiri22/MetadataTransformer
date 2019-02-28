package com.nttdata.lumileds.opentext.transformer.utility;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;

import com.nttdata.lumileds.opentext.transformer.repository.SQLDirectRepository;

public class PALAssetSeggregator {

	public static void main(String[] args) {

		final String baseFolderLocation = args[0];
//
//						String baseFolderLocation = "hello_	one   	two\\three/four		five	";
//						
//						baseFolderLocation = baseFolderLocation.replaceAll("\\.", "_");
//						
//						System.out.println("After dot: " + baseFolderLocation);
//						
//						baseFolderLocation = baseFolderLocation.replaceAll("\\\\", "_");
//						
//						System.out.println("After backslash: " + baseFolderLocation);
//						
//						baseFolderLocation = baseFolderLocation.replaceAll("\\s{2,}", " ").trim();
//						
//						baseFolderLocation = baseFolderLocation.replaceAll("\t", " ").trim();
//						
//						System.out.println("After space: " + baseFolderLocation + "check");
//						
//						baseFolderLocation = baseFolderLocation.replaceAll("/", "_");
//						
//						System.out.println("After front slash: " + baseFolderLocation);
//						
//						System.exit(0);

		final File inputFolder = 
				new File(baseFolderLocation + "/" + SeggregatorConstants.SOURCE_FOLDER);

		System.out.println("Input folder location: " + inputFolder.toPath().toString());

		seggragateAssets(baseFolderLocation, inputFolder);
	}

	private static void seggragateAssets(String baseFolderLocation, File inputFolder) {

		SQLDirectRepository sqlRepository = new SQLDirectRepository();

		Connection conn = sqlRepository.createConnection();

		for (final File fileEntry : inputFolder.listFiles()) {

			String palIdWithExtension =  fileEntry.getName();


			System.out.println("Initial PAL ID file name : " + palIdWithExtension);

			String[] palIdArray = palIdWithExtension.split(MetadataConstants.DOT);

			String palId = palIdArray[0];

			String namePath = sqlRepository.getNamePath(palId,conn);

			String assetFileName = sqlRepository.getFileName(palId, conn);
			
			if ( null == namePath || null == assetFileName) {

				System.out.println("No value returned from the db, doing nothing");

				continue;

			}
			
			assetFileName = assetFileName.replaceAll("\\.", "_");
			assetFileName = assetFileName.replaceAll("\\\\", "_");
			assetFileName = assetFileName.replaceAll("\\s{2,}", " ");
			assetFileName = assetFileName.replaceAll("\t", " ");
			assetFileName = assetFileName.replaceAll("/", "_");
			assetFileName = assetFileName.replaceAll(":", "_").trim();

			String assetFileNameWithExtension;


			//There are few assets without any extension
			if (palIdArray.length > 1 ) {

				assetFileNameWithExtension = assetFileName + "." + palIdArray[1] ;
			}
			else {
				assetFileNameWithExtension = assetFileName;
			}

			System.out.println("Asset File name : " + palIdWithExtension);

			Boolean allORAPRRegions = sqlRepository.checkALLORAPRRegions(palId,conn);

			// Initializing the default folder to Base location
			String destinationFolderPath = baseFolderLocation;

			boolean copyFlag = false ;

			if ( sqlRepository.checkAssetArchived(palId, conn)) {

				copyFlag = true;

				System.out.println("Going to Archive folder");

				destinationFolderPath = baseFolderLocation + "/" + 
						SeggregatorConstants.ARCHIVE_FOLDER;

			}
			else {

				copyFlag = true;

				if (SeggregatorConstants.pccList.contains(namePath)) {

					if (allORAPRRegions) {

						System.out.println("Going to PCC/Regional Identification folder");

						destinationFolderPath = baseFolderLocation + "/" + 
								SeggregatorConstants.PCC_FOLDER + "/" + 
								SeggregatorConstants.REGIONAL_IDENTIFICATION;
					}
					else {

						System.out.println("Going to PCC folder");

						destinationFolderPath = baseFolderLocation + "/" + 
								SeggregatorConstants.PCC_FOLDER;

					}

				} else if (SeggregatorConstants.marcomList.contains(namePath)) {

					if (allORAPRRegions) {

						System.out.println("Going to Marcom/Regional Identification folder");


						destinationFolderPath = baseFolderLocation + "/" + 
								SeggregatorConstants.MARCOM_FOLDER + "/" +
								SeggregatorConstants.REGIONAL_IDENTIFICATION;
					}
					else {

						System.out.println("Going to Marcom folder");

						destinationFolderPath = baseFolderLocation + "/" + 
								SeggregatorConstants.MARCOM_FOLDER;
					}

				} else if (SeggregatorConstants.brandElementsList.contains(namePath)) {

					if (allORAPRRegions) {

						System.out.println("Going to Brand Elements/Regional Identification folder");


						destinationFolderPath = baseFolderLocation + "/" + 
								SeggregatorConstants.BRAND_ELEMENTS_FOLDER + "/" +
								SeggregatorConstants.REGIONAL_IDENTIFICATION;
					}
					else {

						System.out.println("Going to Brand Elements folder");

						destinationFolderPath = baseFolderLocation + "/" + 
								SeggregatorConstants.BRAND_ELEMENTS_FOLDER;

					}

				} else if (SeggregatorConstants.amUnidentifiedList.contains(namePath)) {

					System.out.println("Going to AM/Unidentified folder");

					destinationFolderPath = baseFolderLocation + "/" + 
							SeggregatorConstants.AM_UNDEFINED_FOLDER;
				}


			}

			if (copyFlag) {
				copyFiles(
						conn,
						fileEntry.toPath(),
						destinationFolderPath,
						assetFileNameWithExtension,
						palId
						);
			}

		}

		SQLDirectRepository.closeConnection();

	}

	private static void copyFiles(
			Connection conn,
			Path sourcePath, 
			String destinationFolderPath,
			String assetFileNameWithExtension,
			String palId) {

		try {

			SQLDirectRepository sqlRepository = new SQLDirectRepository();


			String absDestinationFileName = 
					destinationFolderPath + "/" +
							assetFileNameWithExtension ;

			System.out.println("Destination Folder path :" + destinationFolderPath);


			int i = 0;

			String fileNameWithoutExtension = null;

			String extension = "";
			
			String[] fileArray = 
					assetFileNameWithExtension.split(MetadataConstants.DOT);
			
			fileNameWithoutExtension = fileArray[0];
			
			if (fileArray.length > 1) {
				extension = fileArray[1];
			}

			while ( new File(absDestinationFileName).isFile() ) {

				i += 1 ;

				fileNameWithoutExtension = fileArray[0] + 
						"_" + SeggregatorConstants.DUPLICATE_STRING +  
						"_" + i;

				if (fileArray.length > 1) {
					
					absDestinationFileName = destinationFolderPath + "/" 
							+ fileNameWithoutExtension + "." + extension;
					
				}
				else {
					absDestinationFileName = destinationFolderPath + "/" 
							+ fileNameWithoutExtension ;
				}
			}

			System.out.println("File Name: " + fileNameWithoutExtension);

			System.out.println("Extension: " + extension);

			sqlRepository.updateValidFileName(conn, 
					fileNameWithoutExtension, palId, extension);

			System.out.println("Modified Asset file name : " + absDestinationFileName);

			
			Files.copy(
					sourcePath , 
					new File(absDestinationFileName).toPath()
					);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
