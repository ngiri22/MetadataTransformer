package com.nttdata.lumileds.opentext.transformer.utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;

import com.nttdata.lumileds.opentext.transformer.repository.SQLDirectRepository;

public class PALAssetSeggregator {

	public static void main(String[] args) {

		final String baseFolderLocation = args[0];
		
//		String baseFolderLocation = "hello.one   	two\\three/four";
//		
//		baseFolderLocation = baseFolderLocation.replaceAll("\\.", "_");
//		
//		System.out.println("After dot: " + baseFolderLocation);
//		
//		baseFolderLocation = baseFolderLocation.replaceAll("\\\\", "_");
//		
//		System.out.println("After backslash: " + baseFolderLocation);
//		
//		baseFolderLocation = baseFolderLocation.replaceAll("\\s+", "_");
//		
//		System.out.println("After space: " + baseFolderLocation);
//		
//		baseFolderLocation = baseFolderLocation.replaceAll("/", "_");
//		
//		System.out.println("After front slash: " + baseFolderLocation);
//		
//		System.exit(0);

		final File inputFolder = 
				new File(baseFolderLocation + "/" + SeggregatorConstants.SOURCE_FOLDER);

		System.out.println("Input folder location: " + inputFolder.toPath().toString());
		
		seggragateAssets(baseFolderLocation, inputFolder);
	}

	private static void seggragateAssets(String baseFolderLocation, File inputFolder) {

		SQLDirectRepository sqlRepository = new SQLDirectRepository();

		Connection conn = SQLDirectRepository.createConnection();

		for (final File fileEntry : inputFolder.listFiles()) {

			String palIdWithExtension =  fileEntry.getName();


			System.out.println("File Name is : " + palIdWithExtension);
			
			String[] palIdArray = palIdWithExtension.split(MetadataConstants.DOT);

			String palId = palIdArray[0];
			
			String namePath = sqlRepository.getNamePath(palId,conn);
			
			String assetFileName = sqlRepository.getFileName(palId, conn);
			
			assetFileName = assetFileName.replaceAll("\\.", "_");
			assetFileName = assetFileName.replaceAll("\\\\", "_");
			assetFileName = assetFileName.replaceAll("\\s+", "_");
			assetFileName = assetFileName.replaceAll("/", "_");
			
			String assetFileNameWithExtension;
			
			
			//There are few assets without any extension
			if (palIdArray.length > 1 ) {
			
				assetFileNameWithExtension = assetFileName + "." + palIdArray[1] ;
			}
			else {
				assetFileNameWithExtension = assetFileName;
			}

			
			
			Boolean allORAPRRegions = sqlRepository.checkALLORAPRRegions(palId,conn);

			//			try {

			//				ResultSet namePathSet = sqlRepository.getNamePath(palId,conn);

			String destinationPath;

			if ( null == namePath || null == assetFileName) {

				System.out.println("No value returned from the db, doing nothing");
			}
			else {

				if (SeggregatorConstants.pccList.contains(namePath)) {

					

					if (allORAPRRegions) {
						
						System.out.println("Going to PCC/Regional Identification folder");

						destinationPath = baseFolderLocation + "/" + 
								SeggregatorConstants.PCC_FOLDER + "/" + 
								SeggregatorConstants.REGIONAL_IDENTIFICATION + "/" +
								assetFileNameWithExtension;
					}
					else {
						
						System.out.println("Going to PCC folder");
						
						destinationPath = baseFolderLocation + "/" + 
								SeggregatorConstants.PCC_FOLDER + "/" +
								assetFileNameWithExtension;

					}
					
					copyFiles(
							fileEntry.toPath(), destinationPath
							);


				} else if (SeggregatorConstants.marcomList.contains(namePath)) {

					
					if (allORAPRRegions) {
						
						System.out.println("Going to Marcom/Regional Identification folder");


						destinationPath = baseFolderLocation + "/" + 
								SeggregatorConstants.MARCOM_FOLDER + "/" +
								SeggregatorConstants.REGIONAL_IDENTIFICATION + "/" +
								assetFileNameWithExtension;
					}
					else {
						
						System.out.println("Going to Marcom folder");

						destinationPath = baseFolderLocation + "/" + 
								SeggregatorConstants.MARCOM_FOLDER + "/" +
								assetFileNameWithExtension;

					}
					
					copyFiles(
							fileEntry.toPath(), destinationPath
							);


				} else if (SeggregatorConstants.brandElementsList.contains(namePath)) {

					if (allORAPRRegions) {
						
						System.out.println("Going to Brand Elements/Regional Identification folder");


						destinationPath = baseFolderLocation + "/" + 
								SeggregatorConstants.BRAND_ELEMENTS_FOLDER + "/" +
								SeggregatorConstants.REGIONAL_IDENTIFICATION + "/" +
								assetFileNameWithExtension;
					}
					else {
						
						System.out.println("Going to Brand Elements folder");

						destinationPath = baseFolderLocation + "/" + 
								SeggregatorConstants.BRAND_ELEMENTS_FOLDER + "/" +
								assetFileNameWithExtension;

					}
					copyFiles(
							fileEntry.toPath(), destinationPath
							);

				} else if (SeggregatorConstants.amUnidentifiedList.contains(namePath)) {

					System.out.println("Going to AM/Unidentified folder");

					destinationPath = baseFolderLocation + "/" + 
							SeggregatorConstants.AM_UNDEFINED_FOLDER + "/" +
							assetFileNameWithExtension;
					copyFiles(
							fileEntry.toPath(), destinationPath
							);
				}

			}

			//			if( namePath.contains(SeggregatorConstants.PACKAGE_IMAGE)) {
			//
			//				System.out.println("Copying to PCC folder: " + fileEntry.toPath());
			//
			//				copyFiles(
			//						fileEntry.toPath(), 
			//						baseFolderLocation + "/" + 
			//								SeggregatorConstants.PCC_FOLDER + "/" +
			//								fileName
			//						);
			//
			//			}
			//			else if ( namePath.contains(SeggregatorConstants.PLANOGRAM_IMAGE)) {
			//
			//				System.out.println("Copying to Planogram folder: " + fileEntry.toPath());
			//
			//				copyFiles(
			//						fileEntry.toPath(), 
			//						baseFolderLocation + "/" + 
			//								SeggregatorConstants.PLANOGRAM_FOLDER + "/" +
			//								fileName
			//						);
			//
			//			}
			//			else if ( namePath.contains(SeggregatorConstants.THREE_D)) {
			//
			//				System.out.println("Copying to 3D folder: " + fileEntry.toPath());
			//
			//				copyFiles(
			//						fileEntry.toPath(), 
			//						baseFolderLocation + "/" + 
			//								SeggregatorConstants.THREE_D_FOLDER + "/" +
			//								fileName
			//						);
			//
			//			}
			//			else if ( namePath.contains(SeggregatorConstants.ADDITIONAL_PRODUCT_PICTURE)) {
			//
			//				System.out.println("Copying to Additional Product Picture folder: " + fileEntry.toPath());
			//
			//				copyFiles(
			//						fileEntry.toPath(), 
			//						baseFolderLocation + "/" + 
			//								SeggregatorConstants.ADDITIONAL_FOLDER + "/" +
			//								fileName
			//						);
			//
			//			}
			//			else {
			//
			//				System.out.println("Copying to Marcom folder: " + fileEntry.toPath());
			//
			//				copyFiles(
			//						fileEntry.toPath(), 
			//						baseFolderLocation + "/" + 
			//								SeggregatorConstants.MARCOM_FOLDER + "/" +
			//								fileName
			//						);
			//
			//			}
		}

		SQLDirectRepository.closeConnection();

	}

	private static void copyFiles(Path sourcePath, String absDestinationFileName) {
		try {

			Files.copy(
					sourcePath , 
					new File(absDestinationFileName).toPath() ,
					StandardCopyOption.REPLACE_EXISTING
					);


		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
