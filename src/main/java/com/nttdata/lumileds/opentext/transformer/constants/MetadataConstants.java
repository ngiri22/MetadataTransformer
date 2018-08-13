package com.nttdata.lumileds.opentext.transformer.constants;

import com.artesia.entity.TeamsIdentifier;
import com.artesia.metadata.MetadataValue;

public class MetadataConstants {

	public static final String ALL_STRING = "All";

	public static final String REGION_FIELD = "REGIONS";

	public static final String USAGE_RIGHTS_TABLE = "LUM.TABLE.USAGE_RIGHTS_TAB";

	public static MetadataValue[] REGION_VALUES = {
			new MetadataValue("EMEA"),
			new MetadataValue("APAC"),
			new MetadataValue("Greater China"),
			new MetadataValue("LATAM"),
			new MetadataValue("NAFTA")
	};

	public static final TeamsIdentifier REGION_ID_FIELD = new TeamsIdentifier(REGION_FIELD);

	public static final TeamsIdentifier USAGE_RIGHTS_TAB_FIELD = new TeamsIdentifier(USAGE_RIGHTS_TABLE);

	public static final String COMMA = ",";

	public static final String[] SCALAR_FIELDS = 
		{
				"LUM.FIELD.MEDIA_TITLE",
				"LUM.FIELD.BRAND",
				"LUM.FIELD.DESCRIPTION",
				"LUM.FIELD.KEYWORDS",  
				"LUM.FIELD.MASTER_FILE_NAME"
		};

	public static final TeamsIdentifier[] FIELD_IDS = 
		{
				new TeamsIdentifier(SCALAR_FIELDS[0]),
				new TeamsIdentifier(SCALAR_FIELDS[1]),
				new TeamsIdentifier(SCALAR_FIELDS[2]),
				new TeamsIdentifier(SCALAR_FIELDS[3]),
				new TeamsIdentifier(SCALAR_FIELDS[4]),
				REGION_ID_FIELD
		};

}
