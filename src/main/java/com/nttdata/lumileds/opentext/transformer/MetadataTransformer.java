package com.nttdata.lumileds.opentext.transformer;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.artesia.asset.Asset;
import com.artesia.asset.metadata.services.AssetMetadataServices;
import com.artesia.common.exception.BaseTeamsException;
import com.artesia.metadata.MetadataCollection;
import com.artesia.metadata.MetadataField;
import com.artesia.metadata.MetadataTableField;
import com.artesia.security.SecuritySession;
import com.artesia.server.asset.imprt.AssetImportInterceptor;
import com.artesia.server.storage.StorageContext;
import com.nttdata.lumileds.opentext.transformer.repository.MetadataRepository;
import com.nttdata.lumileds.opentext.transformer.repository.SQLRepository;
import com.nttdata.lumileds.opentext.transformer.utility.MetadataConstants;

public class MetadataTransformer implements AssetImportInterceptor {

	private static final Log log = LogFactory.getLog(MetadataTransformer.class);


	@Override
	public void examineAssets(List<Asset> assets, 
			StorageContext context, SecuritySession session) 
					throws BaseTeamsException {

		for (Asset asset : assets ) {

			MetadataCollection assetMetadata = 
					AssetMetadataServices.getInstance().
					retrieveMetadataForAsset
					(
							asset.getAssetId(),
							MetadataConstants.FIELD_IDS,
							session
							);

			SQLRepository sQLRepository = new SQLRepository(); 

			String assetNameWithExtension = asset.getName();

			log.debug("Asset Name with Extension: " + assetNameWithExtension);

			String[] assetPALId = assetNameWithExtension.split(MetadataConstants.DOT);

			log.debug("Asset PAL ID: " + assetPALId[0]);

			HashMap<String,String> palMetadataMap = sQLRepository.getAssetMetadata
					(assetPALId[0], context.getJDBCConnection());

			MetadataRepository metadataRepository = new MetadataRepository();

			for (MetadataField scalarField : 
				metadataRepository.processScalarFields(palMetadataMap) ) {

				assetMetadata.replaceElement(scalarField, true);

			}

			for ( MetadataTableField processedMediaTabularField : 
				metadataRepository.processMediaTabularField(palMetadataMap) )
			{
				assetMetadata.replaceElement(processedMediaTabularField, true);

			}

			for (MetadataTableField processedRightsTableField : 
				metadataRepository.processUsageRightsTable(palMetadataMap) ) {

				assetMetadata.replaceElement(processedRightsTableField, true);

			}


			for ( MetadataTableField processedFileTableField : 
				metadataRepository.processFileTabularFields(palMetadataMap) )
			{
				assetMetadata.replaceElement(processedFileTableField, true);

			}


			//Get AssetType
			
			ResultSet assetNamePathSet = sQLRepository.getAssetNamePath
					(assetPALId[0], context.getJDBCConnection());

			if ( null != assetNamePathSet) {

				assetMetadata.replaceElement(metadataRepository.getAssetType(assetNamePathSet), true);
			}


			asset.setMetadata(assetMetadata);

		}


	}


}
