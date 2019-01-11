package com.nttdata.lumileds.opentext.transformer.utility;

import java.util.Arrays;
import java.util.List;

public class SeggregatorConstants {

//	public static final String DB_URL = "jdbc:sqlserver://10.80.132.90:21433;DatabaseName=OTMM_DEV";
//	public static final String DB_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//	public static final String DB_USER = "otmm_devdba";
//	public static final String DB_PASSWORD = "@ttmDevDBA01";

	
//	public static final String DB_URL = "jdbc:sqlserver://10.80.132.102:1433;DatabaseName=OTMM_QA";
//	public static final String DB_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//	public static final String DB_USER = "otmm_dba";
//	public static final String DB_PASSWORD = "@tmmDBA01";

	public static final String DB_URL = "jdbc:sqlserver://10.80.134.81:1433;DatabaseName=OTMM_PROD";
	public static final String DB_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static final String DB_USER = "otmm_dba";
	public static final String DB_PASSWORD = "@tmmDBA321";

	
	public static final String SOURCE_FOLDER = "SOURCE";

	public static final String AM_UNDEFINED_FOLDER = "AM/Unidentified";
	public static final String PCC_FOLDER = "AM/PCC";
	public static final String BRAND_ELEMENTS_FOLDER = "Corporate/Brand Element";
	public static final String MARCOM_FOLDER = "AM/MARCOM";
	public static final String AUTOMOTIVE_REGION = "Asset_Automotive_Region";
	public static final String ALL_STRING = "%All%";
	public static final String APR_STRING = "APR";
	public static final String REGIONAL_IDENTIFICATION = "Regional Identification";
	public static final String COPYRIGHT_END_DATE_FIELD = "Asset_Rights_Copyright_Date_End";
	public static final String DEACTIVATION_DATE = "Asset_Date_Deactivation";
	public static final String ARCHIVE_FOLDER = "Archive";
	

	public static final String PRODUCT_QUERY_INITIAL_TERM = "/Assets/PhilipsAssetType/%";

	public static final List<String> amUnidentifiedList = 
			Arrays.asList(
					"/Assets/PhilipsAssetType/DataMigrationUnknown", 
					"/Assets/PhilipsAssetType/Document",
					"/Assets/PhilipsAssetType/Online",
					"/Assets/PhilipsAssetType/Online/Website",
					"/Assets/PhilipsAssetType/Online/Applications",
					"/Assets/PhilipsAssetType/Online/Banners",
					"/Assets/PhilipsAssetType/Online/E-mail",
					"/Assets/PhilipsAssetType/Online/Social Media"
					);

	public static final List<String> pccList = 
			Arrays.asList(
					"/Assets/PhilipsAssetType/BTL/POS/On Product Materials",
					"/Assets/PhilipsAssetType/Philips Product Library/Packaging photograph",
					"/Assets/PhilipsAssetType/Product/Packaging",
					"/Assets/PhilipsAssetType/Product/Packaging/Packaging Design",
					"/Assets/PhilipsAssetType/Product/Product images/Packaging Image",
					"/Assets/PhilipsAssetType/Product/Product images/Planogram Image"
					);
	
	
	
	
	
	
	public static final List<String> marcomList = 
			Arrays.asList(
					"/Assets/PhilipsAssetType/ATL/Print/Brochure",
					"/Assets/PhilipsAssetType/ATL/Print/Catalogue",
					"/Assets/PhilipsAssetType/Audio and Video/Video/Inspirational",
					"/Assets/PhilipsAssetType/Audio and Video/Video/Installation",
					"/Assets/PhilipsAssetType/Audio and Video/Video/Tutorial",
					"/Assets/PhilipsAssetType/Case Study/Case Study Online",
					"/Assets/PhilipsAssetType/Case Study/Images",
					"/Assets/PhilipsAssetType/Case Study/Images/Key Visual",
					"/Assets/PhilipsAssetType/Feature/Image",
					"/Assets/PhilipsAssetType/PR",
					"/Assets/PhilipsAssetType/PR/Advertorial",
					"/Assets/PhilipsAssetType/Philips Product Library/Emotional Benefit Photo",
					"/Assets/PhilipsAssetType/Philips Product Library/Feature image",
					"/Assets/PhilipsAssetType/Philips Product Library/Feature movie",
					"/Assets/PhilipsAssetType/Philips Product Library/Feature video",
					"/Assets/PhilipsAssetType/Philips Product Library/Main in use photo",
					"/Assets/PhilipsAssetType/Philips Product Library/Product 3D",
					"/Assets/PhilipsAssetType/Philips Product Library/Product In Use Photo",
					"/Assets/PhilipsAssetType/Philips Product Library/Product Video",
					"/Assets/PhilipsAssetType/Philips Product Library/Product with stand photo",
					"/Assets/PhilipsAssetType/Product",
					"/Assets/PhilipsAssetType/Product/Image",
					"/Assets/PhilipsAssetType/Product/Product images",
					"/Assets/PhilipsAssetType/Product/Product images/3D",
					"/Assets/PhilipsAssetType/Product/Product images/Additional Product Picture",
					"/Assets/PhilipsAssetType/Product/Product images/Detail Photo",
					"/Assets/PhilipsAssetType/Product/Product images/Lifestyle Image",
					"/Assets/PhilipsAssetType/Product/Product images/Product Stand Alone Photo",
					"/Assets/PhilipsAssetType/Product/Product videos/Product movie",
					"/Assets/PhilipsAssetType/Audio and Video",
					"/Assets/PhilipsAssetType/Audio and Video/Video",
					"/Assets/PhilipsAssetType/Product/Product videos",
					"/Assets/PhilipsAssetType/Product/Video",
					"/Assets/PhilipsAssetType/Audio and Video/Video/Explanation",
					"/Assets/PhilipsAssetType/Briefs and Guidelines/Briefs",
					"/Assets/PhilipsAssetType/Product/Award"					
					);

	public static final List<String> brandElementsList = 
			Arrays.asList(
					"/Assets/PhilipsAssetType/Corporate/Icon-Graphic",
					"/Assets/PhilipsAssetType/Corporate/Logo",
					"/Assets/PhilipsAssetType/Philips Product Library/System Logo"
					);
		

}
