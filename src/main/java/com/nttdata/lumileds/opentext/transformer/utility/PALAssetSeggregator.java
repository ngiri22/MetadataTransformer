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

		final File inputFolder = new File(baseFolderLocation + "/" + SeggregatorConstants.SOURCE_FOLDER);

		seggragateAssets(baseFolderLocation, inputFolder);
	}

	private static void seggragateAssets(String baseFolderLocation, File inputFolder) {

		SQLDirectRepository sqlRepository = new SQLDirectRepository();

		Connection conn = SQLDirectRepository.createConnection();

		for (final File fileEntry : inputFolder.listFiles()) {

			String fileName =  fileEntry.getName();


			System.out.println("File Name is : " + fileName);


			String palId = fileName.split(MetadataConstants.DOT)[0];

			String namePath = sqlRepository.getNamePath(palId,conn);
			
			Boolean allORAPRRegions = sqlRepository.checkALLORAPRRegions(palId,conn);

			//			try {

			//				ResultSet namePathSet = sqlRepository.getNamePath(palId,conn);

			String destinationPath;

			if ( null == namePath) {

				System.out.println("No value returned from the db, doing nothing");
			}
			else {

				if (SeggregatorConstants.pccList.contains(namePath)) {

					

					if (allORAPRRegions) {
						
						System.out.println("Going to PCC/Regional Specification folder");

						destinationPath = baseFolderLocation + "/" + 
								SeggregatorConstants.PCC_FOLDER + "/" + 
								SeggregatorConstants.REGIONAL_SPECIFICATION + "/" +
								fileName;
					}
					else {
						
						System.out.println("Going to PCC folder");
						
						destinationPath = baseFolderLocation + "/" + 
								SeggregatorConstants.PCC_FOLDER + "/" +
								fileName;

					}
					
					copyFiles(
							fileEntry.toPath(), destinationPath
							);


				} else if (SeggregatorConstants.marcomList.contains(namePath)) {

					
					if (allORAPRRegions) {
						
						System.out.println("Going to Marcom/Regional Specification folder");


						destinationPath = baseFolderLocation + "/" + 
								SeggregatorConstants.MARCOM_FOLDER + "/" +
								SeggregatorConstants.REGIONAL_SPECIFICATION + "/" +
								fileName;
					}
					else {
						
						System.out.println("Going to Marcom folder");

						destinationPath = baseFolderLocation + "/" + 
								SeggregatorConstants.MARCOM_FOLDER + "/" +
								fileName;

					}
					
					copyFiles(
							fileEntry.toPath(), destinationPath
							);


				} else if (SeggregatorConstants.brandElementsList.contains(namePath)) {

					if (allORAPRRegions) {
						
						System.out.println("Going to Brand Elements/Regional Specification folder");


						destinationPath = baseFolderLocation + "/" + 
								SeggregatorConstants.BRAND_ELEMENTS_FOLDER + "/" +
								SeggregatorConstants.REGIONAL_SPECIFICATION + "/" +
								fileName;
					}
					else {
						
						System.out.println("Going to Brand Elements folder");

						destinationPath = baseFolderLocation + "/" + 
								SeggregatorConstants.BRAND_ELEMENTS_FOLDER + "/" +
								fileName;

					}
					copyFiles(
							fileEntry.toPath(), destinationPath
							);

				} else if (SeggregatorConstants.amUndefinedList.contains(namePath)) {

					System.out.println("Going to AM/Undefined folder");

					destinationPath = baseFolderLocation + "/" + 
							SeggregatorConstants.AM_UNDEFINED_FOLDER + "/" +
							fileName;
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
