package com.nttdata.lumileds.opentext.transformer.utility;

import java.util.Arrays;
import java.util.List;

public class SeggregatorConstants {

	public static final String DB_URL = "jdbc:sqlserver://10.80.132.90:21433;DatabaseName=OTMM_DEV";
	public static final String DB_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static final String DB_USER = "otmm_devdba";
	public static final String DB_PASSWORD = "@ttmDevDBA01";


	public static final String SOURCE_FOLDER = "SOURCE";

	public static final String AM_UNDEFINED_FOLDER = "AM/Undefined";
	public static final String PCC_FOLDER = "AM/PCC";
	public static final String BRAND_ELEMENTS_FOLDER = "Corporate/Brand Elements";
	public static final String MARCOM_FOLDER = "AM/MARCOM";
	public static final String AUTOMOTIVE_REGION = "Asset_Automotive_Region";
	public static final String ALL_STRING = "%All%";
	public static final String APR_STRING = "APR";
	public static final String REGIONAL_SPECIFICATION = "Regional Specification";

	public static final String PRODUCT_QUERY_INITIAL_TERM = "/Assets/PhilipsAssetType/%";

	public static final List<String> amUndefinedList = 
			Arrays.asList(
					"/Assets/PhilipsAssetType/DataMigrationUnknown", 
					"/Assets/PhilipsAssetType/Document",
					"/Assets/PhilipsAssetType/Online",
					"/Assets/PhilipsAssetType/Online/Website"
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
					"/Assets/PhilipsAssetType/Product/Product videos/Product movie"
					);

	public static final List<String> brandElementsList = 
			Arrays.asList(
					"/Assets/PhilipsAssetType/Corporate/Icon-Graphic",
					"/Assets/PhilipsAssetType/Corporate/Logo",
					"/Assets/PhilipsAssetType/Philips Product Library/System Logo"
					);
	

}
