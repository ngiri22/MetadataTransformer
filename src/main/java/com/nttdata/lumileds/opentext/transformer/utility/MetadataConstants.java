package com.nttdata.lumileds.opentext.transformer.utility;

import java.util.HashMap;

import com.artesia.entity.TeamsIdentifier;
import com.artesia.metadata.MetadataValue;

public class MetadataConstants {

	public static final String ALL_STRING = "All";

	public static final String COMMA = ",";

	public static final String YES_CODE = "Y";

	public static final String NO_CODE = "N";

	public static final String DOT = "\\.";

	public static final String REGION_FIELD = "REGIONS";
	public static final String COUNTRIES_FIELD = "COUNTRIES";

	public static MetadataValue[] REGION_VALUES = 
		{
				new MetadataValue("EMEA"),
				new MetadataValue("APAC"),
				new MetadataValue("Greater China"),
				new MetadataValue("LATAM"),
				new MetadataValue("NAFTA")
		};


	public static final String[] SCALAR_FIELDS = 
		{
				"LUM.FIELD.LANGUAGES",
				"LUM.FIELD.MEDIA_TITLE",
				"LUM.FIELD.BRAND",
				"LUM.FIELD.DESCRIPTION",
				"LUM.FIELD.KEYWORDS",
				"LUM.FIELD.MASTER_FILE_NAME",
				"LUM.FIELD.CONTENT_EDITOR",
				"LUM.FIELD.LEGACY_OWNER_EMAIL",
				"LUM.FIELD.ASSET_TYPE"
		};

	public static final String[] USAGE_RIGHTS_FIELDS = 
		{
				"COPYRIGHT_VISUAL_APPLICABLE",
				"COPYRIGHT_MUSIC_APPLICABLE",
				"COPYRIGHT_GEO_LOCATION",
				"COPYRIGHT_TOUCHPOINTS",
				"COPYRIGHT_START_DATE",
				"COPYRIGHT_END_DATE"
		};

	public static final String[] FILE_INFO_TAB_FIELDS = 
		{
				"DEACTIVATION_DATE",
				"ACTIVATION_DATE"
		};

	public static final TeamsIdentifier[] FIELD_IDS = 
		{
				new TeamsIdentifier(SCALAR_FIELDS[0]),
				new TeamsIdentifier(SCALAR_FIELDS[1]),
				new TeamsIdentifier(SCALAR_FIELDS[2]),
				new TeamsIdentifier(SCALAR_FIELDS[3]),
				new TeamsIdentifier(SCALAR_FIELDS[4]),
				new TeamsIdentifier(SCALAR_FIELDS[5]),
				new TeamsIdentifier(SCALAR_FIELDS[6]),
				new TeamsIdentifier(SCALAR_FIELDS[7]),
				new TeamsIdentifier(SCALAR_FIELDS[8]),
				new TeamsIdentifier(REGION_FIELD),
				new TeamsIdentifier(COUNTRIES_FIELD),
				new TeamsIdentifier(USAGE_RIGHTS_FIELDS[0]),
				new TeamsIdentifier(USAGE_RIGHTS_FIELDS[1]),
				new TeamsIdentifier(USAGE_RIGHTS_FIELDS[2]),
				new TeamsIdentifier(USAGE_RIGHTS_FIELDS[3]),
				new TeamsIdentifier(USAGE_RIGHTS_FIELDS[4]),
				new TeamsIdentifier(USAGE_RIGHTS_FIELDS[5]),
				new TeamsIdentifier(FILE_INFO_TAB_FIELDS[0]),
				new TeamsIdentifier(FILE_INFO_TAB_FIELDS[1])
		};

	public static final HashMap<String, String> ISO_LANGUAGES_MAP = new HashMap<String, String>();


	static {
		ISO_LANGUAGES_MAP.put("Chinese (Simplified)","chi");
		ISO_LANGUAGES_MAP.put("Chinese (Taiwan)","chi");
		ISO_LANGUAGES_MAP.put("Czech","cze");
		ISO_LANGUAGES_MAP.put("Danish","dan");
		ISO_LANGUAGES_MAP.put("Dutch","nld");
		ISO_LANGUAGES_MAP.put("English","en");
		ISO_LANGUAGES_MAP.put("English (United States)","en_us");
		ISO_LANGUAGES_MAP.put("Finnish","fin");
		ISO_LANGUAGES_MAP.put("French","fre");
		ISO_LANGUAGES_MAP.put("French (France)","fre");
		ISO_LANGUAGES_MAP.put("German","ger");
		ISO_LANGUAGES_MAP.put("Global Language","en");
		ISO_LANGUAGES_MAP.put("Hungarian","hun");
		ISO_LANGUAGES_MAP.put("International English","en");
		ISO_LANGUAGES_MAP.put("International English, Japanese","en");
		ISO_LANGUAGES_MAP.put("Italian","ita");
		ISO_LANGUAGES_MAP.put("Norwegian","nor");
		ISO_LANGUAGES_MAP.put("Polish","pol");
		ISO_LANGUAGES_MAP.put("Portuguese","por");
		ISO_LANGUAGES_MAP.put("Romanian","ron");
		ISO_LANGUAGES_MAP.put("Russian","rus");
		ISO_LANGUAGES_MAP.put("Spanish","spa");
		ISO_LANGUAGES_MAP.put("Swedish","swe");

	}
	
	public static final HashMap<String, String> ASSET_TYPE_MAP = new HashMap<String, String>();

	static {
		ASSET_TYPE_MAP.put("/Assets/PhilipsAssetType/ATL/Print/Brochure", "Catalog");
		ASSET_TYPE_MAP.put("/Assets/PhilipsAssetType/ATL/Print/Catalogue", "Catalog");
		ASSET_TYPE_MAP.put("/Assets/PhilipsAssetType/Audio and Video", "Lifestyle Video");
		ASSET_TYPE_MAP.put("/Assets/PhilipsAssetType/Audio and Video/Video", "Lifestyle Video");
		ASSET_TYPE_MAP.put("/Assets/PhilipsAssetType/Audio and Video/Video/Inspirational", "Lifestyle Video");
		ASSET_TYPE_MAP.put("/Assets/PhilipsAssetType/Audio and Video/Video/Installation", "Tutorial Video");
		ASSET_TYPE_MAP.put("/Assets/PhilipsAssetType/Audio and Video/Video/Tutorial", "Tutorial Video");
		ASSET_TYPE_MAP.put("/Assets/PhilipsAssetType/BTL/POS/On Product Materials", "POS");
		ASSET_TYPE_MAP.put("/Assets/PhilipsAssetType/Case Study/Case Study Online", "Press Release/Whitepaper/Ministorie");
		ASSET_TYPE_MAP.put("/Assets/PhilipsAssetType/Case Study/Images","Product in use");
		ASSET_TYPE_MAP.put("/Assets/PhilipsAssetType/Case Study/Images/Key Visual","Lifestyle picture");
		ASSET_TYPE_MAP.put("/Assets/PhilipsAssetType/Corporate/Icon-Graphic","Icon");
		ASSET_TYPE_MAP.put("/Assets/PhilipsAssetType/Corporate/Logo","Logo");
		ASSET_TYPE_MAP.put("/Assets/PhilipsAssetType/Feature/Image","Glossary feature");
		ASSET_TYPE_MAP.put("/Assets/PhilipsAssetType/PR","Press Release/Whitepaper/Ministorie");
		ASSET_TYPE_MAP.put("/Assets/PhilipsAssetType/Philips Product Library/Emotional Benefit Photo","Lifestyle picture");
		ASSET_TYPE_MAP.put("/Assets/PhilipsAssetType/Philips Product Library/Feature image","Lifestyle picture");
		ASSET_TYPE_MAP.put("/Assets/PhilipsAssetType/Philips Product Library/Feature movie","Product feature video");
		ASSET_TYPE_MAP.put("/Assets/PhilipsAssetType/Philips Product Library/Feature video","Product feature video");
		ASSET_TYPE_MAP.put("/Assets/PhilipsAssetType/Philips Product Library/Main in use photo","Product in use");
		ASSET_TYPE_MAP.put("/Assets/PhilipsAssetType/Philips Product Library/Packaging photograph","Packaging picture");
		ASSET_TYPE_MAP.put("/Assets/PhilipsAssetType/Philips Product Library/Product 3D","Product picture");
		ASSET_TYPE_MAP.put("/Assets/PhilipsAssetType/Philips Product Library/Product In Use Photo","Product in use");
		ASSET_TYPE_MAP.put("/Assets/PhilipsAssetType/Philips Product Library/Product Video","Product feature video");
		ASSET_TYPE_MAP.put("/Assets/PhilipsAssetType/Philips Product Library/System Logo","Icons");
		ASSET_TYPE_MAP.put("/Assets/PhilipsAssetType/Product/Packaging/Packaging Design","Packaging picture");
		ASSET_TYPE_MAP.put("/Assets/PhilipsAssetType/Product/Image","Product picture");
		ASSET_TYPE_MAP.put("/Assets/PhilipsAssetType/Product/Product images/3D","Product picture");
		ASSET_TYPE_MAP.put("/Assets/PhilipsAssetType/Product/Product images/Additional Product Picture","Product picture");
		ASSET_TYPE_MAP.put("/Assets/PhilipsAssetType/Product/Product images/Detail Photo","Product picture");
		ASSET_TYPE_MAP.put("/Assets/PhilipsAssetType/Product/Product images/Lifestyle Image","Lifestyle picture");
		ASSET_TYPE_MAP.put("/Assets/PhilipsAssetType/Product/Product images/Packaging Image","Packaging picture");
		ASSET_TYPE_MAP.put("/Assets/PhilipsAssetType/Product/Product images/Planogram Image","POS");
		ASSET_TYPE_MAP.put("/Assets/PhilipsAssetType/Product/Product images/Product Stand Alone Photo","Product picture");
		ASSET_TYPE_MAP.put("/Assets/PhilipsAssetType/Product/Product videos/Product movie","Product feature video");
	}

}
