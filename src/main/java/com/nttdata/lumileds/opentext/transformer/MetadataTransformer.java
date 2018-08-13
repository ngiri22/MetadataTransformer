package com.nttdata.lumileds.opentext.transformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.artesia.asset.Asset;
import com.artesia.asset.metadata.services.AssetMetadataServices;
import com.artesia.common.exception.BaseTeamsException;
import com.artesia.entity.TeamsIdentifier;
import com.artesia.metadata.MetadataCollection;
import com.artesia.metadata.MetadataField;
import com.artesia.metadata.MetadataTable;
import com.artesia.metadata.MetadataTableField;
import com.artesia.metadata.MetadataValue;
import com.artesia.security.SecuritySession;
import com.artesia.server.asset.imprt.AssetImportInterceptor;
import com.artesia.server.storage.StorageContext;
import com.nttdata.lumileds.opentext.transformer.constants.MetadataConstants;
import com.nttdata.lumileds.opentext.transformer.utility.SqlUtility;

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

			//MetadataTableField regionTableField = (MetadataTableField) 
			//assetMetadata.findElementById(MetadataConstants.REGION_ID_FIELD);

			SqlUtility sqlUtility = new SqlUtility(); 

			HashMap<String,String> palMetadataMap = sqlUtility.getAssetMetadata
					(asset.getName(), context.getJDBCConnection());

			MetadataTableField processedRegionField = processRegionTabularField(palMetadataMap);

			assetMetadata.replaceElement(processedRegionField, true);

			for (MetadataField scalarField : processScalarFields(palMetadataMap) ) {

				assetMetadata.replaceElement(scalarField, true);

			}
			
			MetadataTable processedUsageRightsTable = processUsageRightsTable(palMetadataMap);
			
			assetMetadata.replaceElement(processedUsageRightsTable, true);

			asset.setMetadata(assetMetadata);

		}


	}

	private MetadataTable processUsageRightsTable(HashMap<String, String> palMetadataMap) {
		
		MetadataTable usageRightsTable = new MetadataTable(MetadataConstants.USAGE_RIGHTS_TABLE);
		
		String usageRightsValues = palMetadataMap.get(arg0)
		
		return usageRightsTable;
		
	}

	private MetadataTableField processRegionTabularField(
			HashMap<String, String> palMetadataMap) {

		MetadataTableField regionTableField = new MetadataTableField(MetadataConstants.REGION_ID_FIELD);

		String regionValues = palMetadataMap.get(MetadataConstants.REGION_FIELD);

		if ( null != regionValues ) {

			if ( regionValues.contains(MetadataConstants.ALL_STRING) ) {

				for (MetadataValue regionField : MetadataConstants.REGION_VALUES ) {

					regionTableField.addValue(regionField);

				}

			}
			else {

				for ( String region :  regionValues.split(MetadataConstants.COMMA)) {

					if ( region.equals("APR") ) {
						region = "APAC";
					}

					log.debug("Region field value: " + region);

					regionTableField.addValue(new MetadataValue ( region ));
				}

			}
		}

		return regionTableField;
	}

	private List<MetadataField> processScalarFields
	(HashMap<String, String> palMetadataMap) {

		List<MetadataField> scalarFields = new ArrayList<MetadataField>();

		for ( String scalarField : MetadataConstants.SCALAR_FIELDS) {

			String palMetadataValue = palMetadataMap.get(scalarField);
			
			log.debug("Scalar Field : " + scalarField);
			
			log.debug("MetadataValue : " + palMetadataValue );
			
			MetadataField metadataField = new MetadataField(new TeamsIdentifier(scalarField));

			metadataField.setValue(palMetadataValue);

			scalarFields.add(metadataField);
		}

		return scalarFields;
	}

}
