package com.nttdata.lumileds.opentext.transformer;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.artesia.asset.Asset;
import com.artesia.metadata.MetadataElement;
import com.artesia.metadata.MetadataField;
import com.artesia.metadata.MetadataTable;
import com.artesia.metadata.MetadataTableField;
import com.artesia.metadata.MetadataUtils;
import com.artesia.metadata.MetadataValue;
import com.artesia.server.metadata.transformer.ValueTransformer;
import com.artesia.server.storage.StorageContext;
import com.nttdata.lumileds.opentext.transformer.constants.MetadataConstants;
import com.nttdata.lumileds.opentext.transformer.utility.SqlUtility;

public class CustomMetadataTransformer implements ValueTransformer {

	private static final Log log = LogFactory.getLog(CustomMetadataTransformer.class);


	public void applyTransformation(List<MetadataElement> fields, final Asset[]
			assets, StorageContext context) {

		if( null == assets  || assets.length == 0){
			log.info("Asset list is null or zero length.");
			return;
		}

		/*
		
		SqlUtility sqlUtility = new SqlUtility(); 

		HashMap<String,String> PALMetadata = sqlUtility.getAssetMetadata(assets[0].getName());
		
		SqlUtility.closeConnection();

		String metadataFieldId = "";
		String metadataValue = "";

		if ( null != PALMetadata && PALMetadata.size() > 0)

			for (MetadataElement fieldElement : fields) {
				
				metadataFieldId = fieldElement.getId().toString();

				if ( PALMetadata.containsKey(metadataFieldId)) {

					metadataValue = PALMetadata.get( metadataFieldId);

					log.info("Key exists: " + PALMetadata.containsKey(metadataFieldId));

					log.info("Map value: "  + metadataValue );

				
					if ( fieldElement.isTabular()) {

						MetadataElement clonedField = MetadataUtils.createDeepClone(fieldElement);
						
						MetadataTableField tableField = (MetadataTableField) clonedField;
						
						MetadataTable table = tableField.getParentTable();
						
						log.info("Table Name: " + tableField.getId().getId());
						
						log.info("Row count: " + tableField.getRowCount());

						//((MetadataTableField) fieldElement).clearValues();
						
						tableField.clearValues();
						
						//int i = 0;

						if ( metadataValue.contains("All")  ) {
							
							//tableField.setSavedValuesBehaviorMode(MetadataTableField.ADD_VALUES);
							
							log.info("Here...");
							
							for (MetadataValue tabularValue : getTabularValues(tableField)) {
								
								log.info("Values: " + tabularValue);
								
								//if (i > 0) {
							
									log.info("Came here... : " + table.getRowCount());
									
									
									
									//table.addMetadataElement(fieldElement);
									
									tableField.addValue(tabularValue);
																		
									log.info("After adding row: " + table.getRowCount());
									
									//((MetadataTableField) fieldElement).addValue(tabularValue);
								
								
									
								//}else {
									
									//tableField.setValue(tabularValue);
								
								//	i += 1 ;
								
									log.info("Step 3...");
								//}

							}
							
							fields.remove(fieldElement);
							fields.add((MetadataElement) tableField);
							
						}
						else {
							
							log.info("what is wronfg..");
							tableField.setValue(metadataValue);;
							log.info("Success... ");
						}


					}
					else {
						//MetadataField field = (MetadataField) fieldElement;

						//log.info("Scalar field " + field.getId() + " - " + field.getName());

						//if (field.getId().toString() == "LUM.FIELD.TEST_HOUSE" ) {

						//	if ( fieldElement.getId().asString().equals("LUM.FIELD.TEST_HOUSE")) {

						log.info("Field Value is: " + ((MetadataField) fieldElement).getValue() );

						((MetadataField) fieldElement).setValue(metadataValue);
					}	
				}

				log.info( "Field Id: " + fieldElement.getId() );
				//+ " Field Value: " + ((MetadataField)fieldElement).getValue() );

			}
		
		*/

	}


	private MetadataValue[] getTabularValues(MetadataTableField tableField) {

		switch (tableField.getId().getId()) {

		case MetadataConstants.REGION_FIELD : return MetadataConstants.REGION_VALUES ;

		default: return null ;

		}		

	}
}
