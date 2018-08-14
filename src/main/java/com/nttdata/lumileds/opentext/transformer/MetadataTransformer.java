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
import com.artesia.metadata.MetadataTableField;
import com.artesia.metadata.MetadataValue;
import com.artesia.security.SecuritySession;
import com.artesia.server.asset.imprt.AssetImportInterceptor;
import com.artesia.server.storage.StorageContext;
import com.nttdata.lumileds.opentext.transformer.utility.MetadataConstants;
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

			SqlUtility sqlUtility = new SqlUtility(); 

			String assetNameWithExtension = asset.getName();

			log.debug("Asset Name with Extension: " + assetNameWithExtension);

			String[] assetPALId = assetNameWithExtension.split(MetadataConstants.DOT);

			log.debug("Asset PAL ID: " + assetPALId[0]);

			HashMap<String,String> palMetadataMap = sqlUtility.getAssetMetadata
					(assetPALId[0], context.getJDBCConnection());

			MetadataTableField processedRegionField = processRegionTabularField(palMetadataMap);

			assetMetadata.replaceElement(processedRegionField, true);

			for (MetadataField scalarField : processScalarFields(palMetadataMap) ) {

				assetMetadata.replaceElement(scalarField, true);

			}

			for (MetadataTableField processedTableField : processUsageRightsTable(palMetadataMap)) {

				assetMetadata.replaceElement(processedTableField, true);

			}

			asset.setMetadata(assetMetadata);

		}


	}

	private List<MetadataTableField> processUsageRightsTable(HashMap<String, String> palMetadataMap) {

		List<MetadataTableField> usageRightsTableFields = new ArrayList<MetadataTableField>();

		for ( String usageRightsFieldId : MetadataConstants.USAGE_RIGHTS_FIELDS) {

			log.debug("Processing usagerights field: " + usageRightsFieldId);

			MetadataTableField usageRightsTableField = new MetadataTableField(new TeamsIdentifier(usageRightsFieldId));

			String metadataValue = palMetadataMap.get(usageRightsFieldId);

			log.debug("Usage Rights field value: " + metadataValue );

			if (null != metadataValue) {

				if ( usageRightsFieldId.equals(MetadataConstants.USAGE_RIGHTS_FIELDS[0]) ||
						usageRightsFieldId.equals(MetadataConstants.USAGE_RIGHTS_FIELDS[1]) ) {

					log.debug("Yes/No domain entry for field: " + usageRightsFieldId );

					if(metadataValue.equals("Yes")) {

						usageRightsTableField.addValue(MetadataConstants.YES_CODE);
					}
					else {

						usageRightsTableField.addValue(MetadataConstants.NO_CODE);
					}

				}
				else {

					usageRightsTableField.addValue(metadataValue);
				}

				usageRightsTableFields.add(usageRightsTableField);
			}

		}

		return usageRightsTableFields;

	}

	private MetadataTableField processRegionTabularField(
			HashMap<String, String> palMetadataMap) {

		MetadataTableField regionTableField = new MetadataTableField(new TeamsIdentifier(MetadataConstants.REGION_FIELD));

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


			if (scalarField.equals(MetadataConstants.SCALAR_FIELDS[0])) {

				String isoLanguagesCode = MetadataConstants.ISO_LANGUAGES_MAP.get(palMetadataValue);

				log.debug("ISO Language code: " + isoLanguagesCode);

				metadataField.setValue(isoLanguagesCode);
			}
			else {

				metadataField.setValue(palMetadataValue);

			}


			scalarFields.add(metadataField);



		}

		return scalarFields;
	}

}
