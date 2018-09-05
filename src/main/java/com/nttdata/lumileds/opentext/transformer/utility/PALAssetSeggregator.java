package com.nttdata.lumileds.opentext.transformer.utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;

import com.nttdata.lumileds.opentext.transformer.repository.SQLDirectRepository;

public class PALAssetSeggregator {

	public static void main(String[] args) {

		final File inputFolder = new File(args[0]);
		
		final String pccFolder = args[1];
		
		final String marcomFolder = args[2];

		seggragateAssets(inputFolder, pccFolder, marcomFolder);
	}

	private static void seggragateAssets(File inputFolder,
			String pccFolderLocation, String marcomFolderLocation) {

		SQLDirectRepository sqlRepository = new SQLDirectRepository();

		Connection conn = SQLDirectRepository.createConnection();

		for (final File fileEntry : inputFolder.listFiles()) {

			String fileName =  fileEntry.getName();


			System.out.println("File Name is : " + fileName);


			String palId = fileName.split(MetadataConstants.DOT)[0];

			try {

				if (sqlRepository.isProductImage( palId,
						conn)) {

					System.out.println("Copying to PCC folder: " + fileEntry.toPath());


					Files.copy(
							fileEntry.toPath() , 
							new File(pccFolderLocation + "/" + fileName).toPath() ,
							StandardCopyOption.REPLACE_EXISTING
							);


				}
				else {
					
					System.out.println("Copying to MARCOM folder: " + fileEntry.toPath());

					Files.copy(
							fileEntry.toPath() , 
							new File(marcomFolderLocation + "/" + fileName).toPath() ,
							StandardCopyOption.REPLACE_EXISTING
							);
					
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		SQLDirectRepository.closeConnection();

	}

}