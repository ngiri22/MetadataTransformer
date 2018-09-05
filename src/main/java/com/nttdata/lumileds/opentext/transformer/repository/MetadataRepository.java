package com.nttdata.lumileds.opentext.transformer.repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.artesia.entity.TeamsIdentifier;
import com.artesia.metadata.MetadataElement;
import com.artesia.metadata.MetadataField;
import com.artesia.metadata.MetadataTableField;
import com.artesia.metadata.MetadataValue;
import com.nttdata.lumileds.opentext.transformer.utility.MetadataConstants;

public class MetadataRepository {

	private static final Log log = LogFactory.getLog(MetadataRepository.class);

	public List<MetadataTableField> processUsageRightsTable(HashMap<String, String> palMetadataMap) {

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

	public MetadataTableField[] processMediaTabularField(
			HashMap<String, String> palMetadataMap) {

		MetadataTableField regionTableField = new MetadataTableField(new TeamsIdentifier(MetadataConstants.REGION_FIELD));
		MetadataTableField countriesTableField = new MetadataTableField(new TeamsIdentifier(MetadataConstants.COUNTRIES_FIELD));


		String regionValues = palMetadataMap.get(MetadataConstants.REGION_FIELD);

		String countriesValues = palMetadataMap.get(MetadataConstants.COUNTRIES_FIELD);

		//if ( null != regionValues ) {

			if ( null == regionValues || regionValues.contains(MetadataConstants.ALL_STRING) ) {

				for (MetadataValue regionField : MetadataConstants.REGION_VALUES ) {

					log.debug("Region Field value: " + regionValues);
					log.debug("Countries Field value: " + countriesValues);

					regionTableField.addValue(regionField);
					countriesTableField.addValue(countriesValues);			
				}

			}
			else {

				for ( String region :  regionValues.split(MetadataConstants.COMMA)) {
					
					region = region.trim();

					if ( region.equals("APR") ) {
						region = "APAC";
					}

					log.debug("Region field value: " + region);

					regionTableField.addValue(region);
					countriesTableField.addValue(countriesValues);
				}

			//}
		}

		MetadataTableField[] mediaTabularFields = {regionTableField, countriesTableField};

		return mediaTabularFields ;
	}

	public List<MetadataField> processScalarFields
	(HashMap<String, String> palMetadataMap) {

		List<MetadataField> scalarFields = new ArrayList<MetadataField>();

		for ( String scalarField : MetadataConstants.SCALAR_FIELDS) {

			String palMetadataValue = palMetadataMap.get(scalarField);

			log.debug("Scalar Field : " + scalarField);

			log.debug("MetadataValue : " + palMetadataValue );

			MetadataField metadataField = new MetadataField(new TeamsIdentifier(scalarField));


			//Languages field
			if (scalarField.equals(MetadataConstants.SCALAR_FIELDS[0])) {

				String isoLanguagesCode = MetadataConstants.ISO_LANGUAGES_MAP.get(palMetadataValue);

				log.debug("ISO Language code: " + isoLanguagesCode);

				metadataField.setValue(isoLanguagesCode);
			}
			//Asset Type field
			else if (scalarField.equals(MetadataConstants.SCALAR_FIELDS[8])) {
				metadataField.setValue("Packaging Picture");
			}
			else {

				metadataField.setValue(palMetadataValue);

			}


			scalarFields.add(metadataField);



		}

		return scalarFields;
	}

	public List<MetadataTableField> processFileTabularFields(HashMap<String, String> palMetadataMap) {

		List<MetadataTableField> fileTabularFields = new ArrayList<MetadataTableField>();

		for ( String fileTabularFieldId : MetadataConstants.FILE_INFO_TAB_FIELDS) {

			log.debug("Processing File Tabular field: " + fileTabularFieldId);

			MetadataTableField fileInfoTableField = new MetadataTableField(new TeamsIdentifier(fileTabularFieldId));

			String metadataValue = palMetadataMap.get(fileTabularFieldId);

			log.debug("Usage Rights field value: " + metadataValue );

			if (null != metadataValue) {

				fileInfoTableField.addValue(metadataValue);

			}
//			else if ( fileTabularFieldId.equals(MetadataConstants.FILE_INFO_TAB_FIELDS[2]) ) {
//
//				log.debug("Processing Creator Owner Group: " + fileTabularFieldId );
//
//				fileInfoTableField.addValue(new MetadataValue("Agency^SGS"));
//			}

			fileTabularFields.add(fileInfoTableField);

		}


		return fileTabularFields;

	}

	public MetadataElement getAssetType(ResultSet assetNamePathSet) {
		// TODO Auto-generated method stub
		return null;
	}


}
