
/* ** FIELD TABLES ** */

/*
Media Information table
 */
CREATE TABLE LUM_MD_MEDIA_INFO(
	UOI_ID nvarchar(40) NOT NULL,
	MEDIA_TITLE nvarchar(250) NULL,
	MASTER_FILE_NAME nvarchar(250) NULL,
	ASSET_TYPE nvarchar(100) NULL,
	BRAND nvarchar(20) NULL,
	DESCRIPTION nvarchar(500) NULL,
	KEYWORDS nvarchar(1000) NULL,
	ALERT_COMMENT nvarchar(50) NULL,
	LEGEND nvarchar(100) NULL,
	LEGACY_ASSET_ID nvarchar(100) NULL
);

ALTER TABLE LUM_MD_MEDIA_INFO  
WITH CHECK ADD CONSTRAINT LUM_MD_MEDIA_INFO_FK FOREIGN KEY(UOI_ID)
REFERENCES UOIS (UOI_ID) ON DELETE CASCADE;

ALTER TABLE LUM_MD_MEDIA_INFO CHECK CONSTRAINT LUM_MD_MEDIA_INFO_FK;

INSERT INTO METADATA_TABLE_INCLUSIONS VALUES ('LUM_MD_MEDIA_INFO','N',NULL);



/*
Media Info Tabular table
*/
CREATE TABLE LUM_MD_MEDIA_INFO_TAB(
	UOI_ID nvarchar(40) NOT NULL,
	REGIONS nvarchar(20) NULL,
	COUNTRIES nvarchar(500) NULL
);

ALTER TABLE LUM_MD_MEDIA_INFO_TAB  
WITH CHECK ADD CONSTRAINT LUM_MD_MEDIA_INFO_TAB_FK FOREIGN KEY(UOI_ID)
REFERENCES UOIS (UOI_ID) ON DELETE CASCADE;

ALTER TABLE LUM_MD_MEDIA_INFO_TAB CHECK CONSTRAINT LUM_MD_MEDIA_INFO_TAB_FK;

INSERT INTO METADATA_TABLE_INCLUSIONS VALUES ('LUM_MD_MEDIA_INFO_TAB','Y',NULL);


/*

LANGUAGES Tabular TABLE

*/

CREATE TABLE LUM_MD_LANGUAGES_TAB(
	UOI_ID nvarchar(40) NOT NULL,
	LANGUAGES nvarchar(20) NULL
);

ALTER TABLE LUM_MD_LANGUAGES_TAB  
WITH CHECK ADD CONSTRAINT LUM_MD_LANGUAGES_TAB_FK FOREIGN KEY(UOI_ID)
REFERENCES UOIS (UOI_ID) ON DELETE CASCADE;

ALTER TABLE LUM_MD_LANGUAGES_TAB CHECK CONSTRAINT LUM_MD_LANGUAGES_TAB_FK;

INSERT INTO METADATA_TABLE_INCLUSIONS VALUES ('LUM_MD_LANGUAGES_TAB','Y',NULL);


/*
Visual, Music, Approval Rights
*/

--Visual Rights
CREATE TABLE LUM_MD_VISUAL_COPYRIGHTS(
	UOI_ID nvarchar(40) NOT NULL,
	COPYRIGHT_VISUAL_APPLICABLE nvarchar(5) NULL,
	COPYRIGHT_TOUCHPOINTS nvarchar(100) NULL,
	COPYRIGHT_START_DATE datetime NULL,
	COPYRIGHT_END_DATE datetime NULL,
	COPYRIGHT_GEO_LOCATION nvarchar(100) NULL,
	COPYRIGHT_SUPPLIER nvarchar(100) NULL
);

ALTER TABLE LUM_MD_VISUAL_COPYRIGHTS
WITH CHECK ADD CONSTRAINT LUM_MD_VISUAL_COPYRIGHTS_FK FOREIGN KEY(UOI_ID)
REFERENCES UOIS (UOI_ID) ON DELETE CASCADE;

ALTER TABLE LUM_MD_VISUAL_COPYRIGHTS CHECK CONSTRAINT LUM_MD_VISUAL_COPYRIGHTS_FK;

INSERT INTO METADATA_TABLE_INCLUSIONS VALUES ('LUM_MD_VISUAL_COPYRIGHTS','N',NULL);

--Music Rights
CREATE TABLE LUM_MD_MUSIC_RIGHTS(
	UOI_ID nvarchar(40) NOT NULL,
	COPYRIGHT_MUSIC_APPLICABLE nvarchar(5) NULL, 
	COPYRIGHT_MUSIC_START_DATE datetime NULL,
	COPYRIGHT_MUSIC_END_DATE datetime NULL,
	COPYRIGHT_MUSIC_SUPPLIER nvarchar(100) NULL
);

ALTER TABLE LUM_MD_MUSIC_RIGHTS
WITH CHECK ADD CONSTRAINT LUM_MD_MUSIC_RIGHTS_FK FOREIGN KEY(UOI_ID)
REFERENCES UOIS (UOI_ID) ON DELETE CASCADE;

ALTER TABLE LUM_MD_MUSIC_RIGHTS CHECK CONSTRAINT LUM_MD_MUSIC_RIGHTS_FK;

INSERT INTO METADATA_TABLE_INCLUSIONS VALUES ('LUM_MD_MUSIC_RIGHTS','N',NULL);

--Approval Rights
CREATE TABLE LUM_MD_APPROVAL_RIGHTS_TAB(	
	UOI_ID nvarchar(40) NOT NULL,
	APPROVED_BY_BRAND nvarchar(100) NULL,
	APPROVER_NAME nvarchar(100) NULL,
	APPROVAL_DATE datetime NULL
);

ALTER TABLE LUM_MD_APPROVAL_RIGHTS_TAB
WITH CHECK ADD CONSTRAINT LUM_MD_APPROVAL_RIGHTS_TAB_FK FOREIGN KEY(UOI_ID)
REFERENCES UOIS (UOI_ID) ON DELETE CASCADE;

ALTER TABLE LUM_MD_APPROVAL_RIGHTS_TAB CHECK CONSTRAINT LUM_MD_APPROVAL_RIGHTS_TAB_FK;

INSERT INTO METADATA_TABLE_INCLUSIONS VALUES ('LUM_MD_APPROVAL_RIGHTS_TAB','Y',NULL);

/*
File Information
*/

CREATE TABLE LUM_MD_ASSET_INFO(
	UOI_ID nvarchar(40) NOT NULL,
	ACTIVATION_DATE datetime NULL,
	DEACTIVATION_DATE datetime NULL,
	PACKAGING_CLUSTERS nvarchar(50) NULL
);

ALTER TABLE LUM_MD_ASSET_INFO
WITH CHECK ADD CONSTRAINT LUM_MD_ASSET_INFO_FK FOREIGN KEY(UOI_ID)
REFERENCES UOIS (UOI_ID) ON DELETE CASCADE;

ALTER TABLE LUM_MD_ASSET_INFO CHECK CONSTRAINT LUM_MD_ASSET_INFO_FK;

INSERT INTO METADATA_TABLE_INCLUSIONS VALUES ('LUM_MD_ASSET_INFO','N',NULL);


CREATE TABLE LUM_MD_FILE_INFO(
	UOI_ID nvarchar(40) NOT NULL,
	CONTENT_EDITOR nvarchar(50) NULL,
	LEGACY_OWNER_EMAIL nvarchar(50) NULL,
	ASSET_OWNER nvarchar(200) NULL,
	FIRST_POINT_OF_CONTACT nvarchar(100) NULL
);


ALTER TABLE LUM_MD_FILE_INFO
WITH CHECK ADD CONSTRAINT LUM_MD_FILE_INFO_FK FOREIGN KEY(UOI_ID)
REFERENCES UOIS (UOI_ID) ON DELETE CASCADE;

ALTER TABLE LUM_MD_FILE_INFO CHECK CONSTRAINT LUM_MD_FILE_INFO_FK;

INSERT INTO METADATA_TABLE_INCLUSIONS VALUES ('LUM_MD_FILE_INFO','N',NULL);

/*
Administrative Information
*/

CREATE TABLE LUM_MD_ADMIN_INFO(
	UOI_ID nvarchar(40) NOT NULL,
	MIGRATION_STATUS nvarchar(30) NULL, --* field length are assumed
	IMPORTED_DATE datetime NULL,
	LEGACY_CLASSIFICATION nvarchar(50) NULL
);

ALTER TABLE LUM_MD_ADMIN_INFO 
WITH CHECK ADD CONSTRAINT LUM_MD_ADMIN_INFO_FK FOREIGN KEY(UOI_ID)
REFERENCES UOIS (UOI_ID) ON DELETE CASCADE;

ALTER TABLE LUM_MD_ADMIN_INFO CHECK CONSTRAINT LUM_MD_ADMIN_INFO_FK;

INSERT INTO METADATA_TABLE_INCLUSIONS VALUES ('LUM_MD_ADMIN_INFO','N',NULL);

--PENDING SECURITY table

/*
 Industrial
*/
CREATE TABLE LUM_MD_INDUSTRIAL_INFO(
	UOI_ID nvarchar(40) NOT NULL,
	WCMS_CONFIDENTIALITY nvarchar(15) NULL,
	FACTORY nvarchar(50) NULL,
	REGULATION nvarchar(50) NULL,
	TEST_HOUSE nvarchar(50) NULL,
	MANUFACTURER nvarchar(50) NULL,
	AUTHORITY nvarchar(50) NULL,
	APPROVER_COUNTRY nvarchar(50) NULL,
	APPROVAL_NUMBER nvarchar(50) NULL,
	E_MARK nvarchar(50) NULL,
	EXTENSION_NUMBER nvarchar(10) NULL,
	INDUSTRIAL_STANDARD nvarchar(50) NULL,
	INDUSTRIAL_STANDARD_NUMBER nvarchar(50) NULL,
	YEAR_OF_RELEASE nvarchar(4) null,
	TYPE_TEST nvarchar(50) NULL,
	TEST_TIME_PERIOD nvarchar(8) NULL,
	PPAP_NUMBER nvarchar(15) NULL,
	PPAP_LEVEL nvarchar(10) NULL,
	PROJECT_NUMBER nvarchar(50) NULL,
	PCN_NUMBER nvarchar(50) NULL,
	SEGMENT nvarchar(30) NULL
);

ALTER TABLE LUM_MD_INDUSTRIAL_INFO
WITH CHECK ADD CONSTRAINT LUM_MD_INDUSTRIAL_INFO_FK FOREIGN KEY(UOI_ID)
REFERENCES UOIS (UOI_ID) ON DELETE CASCADE;

ALTER TABLE LUM_MD_INDUSTRIAL_INFO CHECK CONSTRAINT LUM_MD_INDUSTRIAL_INFO_FK;

INSERT INTO METADATA_TABLE_INCLUSIONS VALUES ('LUM_MD_INDUSTRIAL_INFO','N',NULL);

CREATE TABLE LUM_MD_INDUSTRIAL_TAB (
	UOI_ID nvarchar(40) NOT NULL,
	TECHNICAL_APPLICATION nvarchar(100) NULL
)

ALTER TABLE LUM_MD_INDUSTRIAL_TAB
WITH CHECK ADD CONSTRAINT LUM_MD_INDUSTRIAL_TAB_FK FOREIGN KEY(UOI_ID)
REFERENCES UOIS (UOI_ID) ON DELETE CASCADE;

ALTER TABLE LUM_MD_INDUSTRIAL_TAB CHECK CONSTRAINT LUM_MD_INDUSTRIAL_TAB_FK;

INSERT INTO METADATA_TABLE_INCLUSIONS VALUES ('LUM_MD_INDUSTRIAL_TAB','Y',NULL);
/*
Product Information - not present currently


CREATE TABLE LUM_MD_PRODUCT_INFO(
	UOI_ID nvarchar(40) NOT NULL,
	PRODUCT_TYPE nvarchar(30) NULL
);

ALTER TABLE LUM_MD_PRODUCT_INFO
WITH CHECK ADD CONSTRAINT LUM_MD_PRODUCT_INFO_FK FOREIGN KEY(UOI_ID)
REFERENCES UOIS (UOI_ID) ON DELETE CASCADE;

ALTER TABLE LUM_MD_PRODUCT_INFO CHECK CONSTRAINT LUM_MD_PRODUCT_INFO_FK;

INSERT INTO METADATA_TABLE_INCLUSIONS VALUES ('LUM_MD_PRODUCT_INFO','N',NULL);
*/

/*
Customer Information
*/

CREATE TABLE LUM_MD_CUSTOMER_INFO_TAB(
	UOI_ID nvarchar(40) NOT NULL,
	SOLD_TO_CUSTOMER nvarchar(500) NULL
);

ALTER TABLE LUM_MD_CUSTOMER_INFO_TAB
WITH CHECK ADD CONSTRAINT LUM_MD_CUSTOMER_INFO_TAB_FK FOREIGN KEY(UOI_ID)
REFERENCES UOIS (UOI_ID) ON DELETE CASCADE;

ALTER TABLE LUM_MD_CUSTOMER_INFO_TAB CHECK CONSTRAINT LUM_MD_CUSTOMER_INFO_TAB_FK;

INSERT INTO METADATA_TABLE_INCLUSIONS VALUES ('LUM_MD_CUSTOMER_INFO_TAB','Y',NULL);

/*
 * SAP PRODUCTS TABULAR TABLE
 */

CREATE TABLE LUM_MD_SAP_PRODUCTS(
	UOI_ID nvarchar(40) NOT NULL,
	SAP_PRODUCT_ID nvarchar(100) NULL,
	IS_ASSET_ASSIGNED numeric(1, 0) NULL,
	SAP_ATTRIBUTE_ID nvarchar(100) NULL
);

ALTER TABLE LUM_MD_SAP_PRODUCTS  
WITH CHECK ADD CONSTRAINT LUM_MD_SAP_PRODUCTS_FK FOREIGN KEY(UOI_ID)
REFERENCES UOIS (UOI_ID) ON DELETE CASCADE;

ALTER TABLE LUM_MD_SAP_PRODUCTS CHECK CONSTRAINT LUM_MD_SAP_PRODUCTS_FK;

INSERT INTO METADATA_TABLE_INCLUSIONS VALUES ('LUM_MD_SAP_PRODUCTS','Y',NULL);


/* ***********
*
*
 LOOKUP TABLES
*
*
*
 *************/
--ASSET TYPE
CREATE TABLE LUM_MD_ASSET_TYPE_LU(
	ID nvarchar(40) NOT NULL,
	VALUE nvarchar(40) NOT NULL,
	DESCRIPTION nvarchar(200) NULL,
	CONSTRAINT LUM_MD_ASSET_TYPE_LU_PK PRIMARY KEY CLUSTERED 
(
	ID ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
);
 
INSERT INTO LOOKUP_TABLE_INCLUSIONS values ('LUM_MD_ASSET_TYPE_LU');

-- BRAND
CREATE TABLE LUM_MD_BRAND_LU(
	ID nvarchar(40) NOT NULL,
	VALUE nvarchar(40) NOT NULL,
	DESCRIPTION nvarchar(200) NULL,
	CONSTRAINT LUM_MD_BRAND_LU_PK PRIMARY KEY CLUSTERED 
(
	ID ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
);

INSERT INTO LOOKUP_TABLE_INCLUSIONS values ('LUM_MD_BRAND_LU');

-- LANGUAGES
CREATE TABLE LUM_MD_LANGUAGES_LU(
	ID nvarchar(40) NOT NULL,
	VALUE nvarchar(40) NOT NULL,
	DESCRIPTION nvarchar(200) NULL,
	CONSTRAINT LUM_MD_LANGUAGES_LU_PK PRIMARY KEY CLUSTERED 
(
	ID ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
);

INSERT INTO LOOKUP_TABLE_INCLUSIONS values ('LUM_MD_LANGUAGES_LU');

-- REGIONS
CREATE TABLE LUM_MD_REGIONS_LU(
	ID nvarchar(40) NOT NULL,
	VALUE nvarchar(40) NOT NULL,
	DESCRIPTION nvarchar(200) NULL,
	CONSTRAINT LUM_MD_REGIONS_LU_PK PRIMARY KEY CLUSTERED 
(
	ID ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
);

INSERT INTO LOOKUP_TABLE_INCLUSIONS values ('LUM_MD_REGIONS_LU');

--MANUFACTURER
CREATE TABLE LUM_MD_MANUFACTURER_LU(
	ID nvarchar(40) NOT NULL,
	VALUE nvarchar(40) NOT NULL,
	DESCRIPTION nvarchar(200) NULL,
	CONSTRAINT LUM_MD_MANUFACTURER_LU_PK PRIMARY KEY CLUSTERED 
(
	ID ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
);

INSERT INTO LOOKUP_TABLE_INCLUSIONS values ('LUM_MD_MANUFACTURER_LU');

--WCMS Confidentiality
CREATE TABLE LUM_MD_WCMS_CNFDLTY_LU(
	ID nvarchar(40) NOT NULL,
	VALUE nvarchar(40) NOT NULL,
	DESCRIPTION nvarchar(200) NULL,
	CONSTRAINT LUM_MD_WCMS_CNFDLTY_LU_PK PRIMARY KEY CLUSTERED 
(
	ID ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
);

INSERT INTO LOOKUP_TABLE_INCLUSIONS values ('LUM_MD_WCMS_CNFDLTY_LU');



-- YES NO lookup for Copyright visual applicable and copyright music applicable.
-- More fields can make use of the OOB lookup if the lookup values are Yes/No only

--Creator Owner Cascading Group
CREATE TABLE LUM_MD_CREATOR_OWNER_LU1(
	ID nvarchar(40) NOT NULL,
	VALUE nvarchar(40) NOT NULL,
	DESCRIPTION nvarchar(200) NULL,
	CONSTRAINT LUM_MD_CREATOR_OWNER_LU1_PK PRIMARY KEY CLUSTERED 
(
	ID ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
);

INSERT INTO LOOKUP_TABLE_INCLUSIONS values ('LUM_MD_CREATOR_OWNER_LU1');

CREATE TABLE LUM_MD_CREATOR_OWNER_LU2(
	ID nvarchar(40) NOT NULL,
	VALUE nvarchar(40) NOT NULL,
	DESCRIPTION nvarchar(200) NULL,
	CONSTRAINT LUM_MD_CREATOR_OWNER_LU2_PK PRIMARY KEY CLUSTERED 
(
	ID ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
);

INSERT INTO LOOKUP_TABLE_INCLUSIONS values ('LUM_MD_CREATOR_OWNER_LU2');

CREATE TABLE LUM_MD_CREATOR_OWNER_CAS_MAP(
	CODE nvarchar(2000) NOT NULL,
	CREATOR_OWNER_LEVEL1 nvarchar(2000) NULL,
	CREATOR_OWNER_LEVEL2 nvarchar(2000) NULL,
 CONSTRAINT L_MD_CREATOR_OWNER_CAS_MAP_PK PRIMARY KEY CLUSTERED 
(
	CODE ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
);

--Asset Owner Cascading Group
CREATE TABLE LUM_MD_ASSET_OWNER_LU1(
	ID nvarchar(40) NOT NULL,
	VALUE nvarchar(40) NOT NULL,
	DESCRIPTION nvarchar(200) NULL,
	CONSTRAINT LUM_MD_ASSET_OWNER_LU1_PK PRIMARY KEY CLUSTERED 
(
	ID ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
);

INSERT INTO LOOKUP_TABLE_INCLUSIONS values ('LUM_MD_ASSET_OWNER_LU1');

CREATE TABLE LUM_MD_ASSET_OWNER_LU2(
	ID nvarchar(40) NOT NULL,
	VALUE nvarchar(40) NOT NULL,
	DESCRIPTION nvarchar(200) NULL,
	CONSTRAINT LUM_MD_ASSET_OWNER_LU2_PK PRIMARY KEY CLUSTERED 
(
	ID ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
);

INSERT INTO LOOKUP_TABLE_INCLUSIONS values ('LUM_MD_ASSET_OWNER_LU2');

CREATE TABLE LUM_MD_ASSET_OWNER_CAS_MAP(
	CODE nvarchar(2000) NOT NULL,
	ASSET_OWNER_LEVEL1 nvarchar(2000) NULL,
	ASSET_OWNER_LEVEL2 nvarchar(2000) NULL,
 CONSTRAINT L_MD_ASSET_OWNER_CAS_MAP_PK PRIMARY KEY CLUSTERED 
(
	CODE ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
);

----------------------------------

--First point of contact

CREATE TABLE LUM_MD_FIRST_POC_LU(
	ID nvarchar(40) NOT NULL,
	VALUE nvarchar(40) NOT NULL,
	DESCRIPTION nvarchar(200) NULL,
	CONSTRAINT LUM_MD_FIRST_POC_LU_PK PRIMARY KEY CLUSTERED 
(
	ID ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
);

INSERT INTO LOOKUP_TABLE_INCLUSIONS values ('LUM_MD_FIRST_POC_LU');


--Industrial Standard
CREATE TABLE LUM_MD_INDUSTRIAL_STANDARD_LU(
	ID nvarchar(40) NOT NULL,
	VALUE nvarchar(40) NOT NULL,
	DESCRIPTION nvarchar(200) NULL,
	CONSTRAINT LUM_MD_INDUSTRIAL_STANDARD_LU_PK PRIMARY KEY CLUSTERED 
(
	ID ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
);

INSERT INTO LOOKUP_TABLE_INCLUSIONS values ('LUM_MD_INDUSTRIAL_STANDARD_LU');

--SEGMENT
CREATE TABLE LUM_MD_INDUSTRIAL_SEGMENT_LU(
	ID nvarchar(40) NOT NULL,
	VALUE nvarchar(40) NOT NULL,
	DESCRIPTION nvarchar(200) NULL,
	CONSTRAINT LUM_MD_INDUSTRIAL_SEGMENT_LU_PK PRIMARY KEY CLUSTERED 
(
	ID ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
);

INSERT INTO LOOKUP_TABLE_INCLUSIONS values ('LUM_MD_INDUSTRIAL_SEGMENT_LU');


--Industrial Technical Application
CREATE TABLE LUM_MD_IND_TECH_APPN_LU(
	ID nvarchar(80) NOT NULL,
	VALUE nvarchar(80) NOT NULL,
	DESCRIPTION nvarchar(200) NULL,
	CONSTRAINT LUM_MD_IND_TECH_APPN_LU_PK PRIMARY KEY CLUSTERED 
(
	ID ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
);

INSERT INTO LOOKUP_TABLE_INCLUSIONS values ('LUM_MD_IND_TECH_APPN_LU');

--Product Grouping
CREATE TABLE LUM_MD_PRODUCT_GROUPING_LU(
	ID nvarchar(40) NOT NULL,
	VALUE nvarchar(40) NOT NULL,
	DESCRIPTION nvarchar(200) NULL,
	CONSTRAINT LUM_MD_PRODUCT_GROUPING_LU_PK PRIMARY KEY CLUSTERED 
(
	ID ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
);

INSERT INTO LOOKUP_TABLE_INCLUSIONS values ('LUM_MD_PRODUCT_GROUPING_LU');

--Type Test
CREATE TABLE LUM_MD_TYPE_TEST_LU(
	ID nvarchar(40) NOT NULL,
	VALUE nvarchar(40) NOT NULL,
	DESCRIPTION nvarchar(200) NULL,
	CONSTRAINT LUM_MD_TYPE_TEST_LU_PK PRIMARY KEY CLUSTERED 
(
	ID ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
);

INSERT INTO LOOKUP_TABLE_INCLUSIONS values ('LUM_MD_TYPE_TEST_LU');

--PPAP LEVEL
CREATE TABLE LUM_MD_PPAP_LEVEL_LU(
	ID nvarchar(40) NOT NULL,
	VALUE nvarchar(40) NOT NULL,
	DESCRIPTION nvarchar(200) NULL,
	CONSTRAINT LUM_MD_PPAP_LEVEL_LU_PK PRIMARY KEY CLUSTERED 
(
	ID ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
);

INSERT INTO LOOKUP_TABLE_INCLUSIONS values ('LUM_MD_PPAP_LEVEL_LU');

--Factory
CREATE TABLE LUM_MD_FACTORY_LU(
	ID nvarchar(40) NOT NULL,
	VALUE nvarchar(40) NOT NULL,
	DESCRIPTION nvarchar(200) NULL,
	CONSTRAINT LUM_MD_FACTORY_LU_PK PRIMARY KEY CLUSTERED 
(
	ID ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
);

INSERT INTO LOOKUP_TABLE_INCLUSIONS values ('LUM_MD_FACTORY_LU');

--Year of RELEASE
CREATE TABLE LUM_MD_YEAR_OF_REL_LU(
	ID nvarchar(40) NOT NULL,
	VALUE nvarchar(40) NOT NULL,
	DESCRIPTION nvarchar(200) NULL,
	CONSTRAINT LUM_MD_YEAR_OF_REL_LU_PK PRIMARY KEY CLUSTERED 
(
	ID ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
);

INSERT INTO LOOKUP_TABLE_INCLUSIONS values ('LUM_MD_YEAR_OF_REL_LU');

-- Packaging Clusters
CREATE TABLE LUM_MD_PKG_CLUSTER_LU1(
	ID nvarchar(40) NOT NULL,
	VALUE nvarchar(40) NOT NULL,
	DESCRIPTION nvarchar(200) NULL,
	CONSTRAINT LUM_MD_PKG_CLUSTER_LU1_PK PRIMARY KEY CLUSTERED 
(
	ID ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
);

INSERT INTO LOOKUP_TABLE_INCLUSIONS values ('LUM_MD_PKG_CLUSTER_LU1');

CREATE TABLE LUM_MD_PKG_CLUSTER_LU2(
	ID nvarchar(40) NOT NULL,
	VALUE nvarchar(40) NOT NULL,
	DESCRIPTION nvarchar(200) NULL,
	CONSTRAINT LUM_MD_PKG_CLUSTER_LU2_PK PRIMARY KEY CLUSTERED 
(
	ID ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
);

INSERT INTO LOOKUP_TABLE_INCLUSIONS values ('LUM_MD_PKG_CLUSTER_LU2');

CREATE TABLE LUM_MD_PKG_CLUSTER_CAS_MAP(
	CODE nvarchar(2000) NOT NULL,
	PKG_CLUSTER_LEVEL1 nvarchar(2000) NULL,
	PKG_CLUSTER_LEVEL2 nvarchar(2000) NULL,
 CONSTRAINT LUM_MD_PKG_CLUSTER_CAS_MAP_PK PRIMARY KEY CLUSTERED 
(
	CODE ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
);

/*
Test Time Period
*/

CREATE TABLE LUM_MD_TEST_TIME_LU1(
	ID nvarchar(40) NOT NULL,
	VALUE nvarchar(40) NOT NULL,
	DESCRIPTION nvarchar(200) NULL,
	CONSTRAINT LUM_MD_TEST_TIME_LU1_PK PRIMARY KEY CLUSTERED 
(
	ID ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
);

INSERT INTO LOOKUP_TABLE_INCLUSIONS values ('LUM_MD_TEST_TIME_LU1');

CREATE TABLE LUM_MD_TEST_TIME_LU2(
	ID nvarchar(40) NOT NULL,
	VALUE nvarchar(40) NOT NULL,
	DESCRIPTION nvarchar(200) NULL,
	CONSTRAINT LUM_MD_TEST_TIME_LU2_PK PRIMARY KEY CLUSTERED 
(
	ID ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
);

INSERT INTO LOOKUP_TABLE_INCLUSIONS values ('LUM_MD_TEST_TIME_LU2');

--Create Lookup domain from Metadata Editor

CREATE TABLE LUM_MD_TEST_TIME_CAS_MAP(
	CODE nvarchar(2000) NOT NULL,
	TEST_TIME_LEVEL1 nvarchar(2000) NULL,
	TEST_TIME_LEVEL2 nvarchar(2000) NULL,
 CONSTRAINT LUM_MD_TEST_TIME_CAS_MAP_PK PRIMARY KEY CLUSTERED 
(
	CODE ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
);

/*

SOLD TO CUSTOMER

*/

CREATE TABLE LUM_MD_SOLD_TO_BANNER_LU(
	ID nvarchar(40) NOT NULL,
	VALUE nvarchar(40) NOT NULL,
	DESCRIPTION nvarchar(200) NULL,
	CONSTRAINT LUM_MD_SOLD_TO_BANNER_LU_PK PRIMARY KEY CLUSTERED 
(
	ID ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
);

INSERT INTO LOOKUP_TABLE_INCLUSIONS values ('LUM_MD_SOLD_TO_BANNER_LU');

CREATE TABLE LUM_MD_SOLD_TO_PARTY_LU(
	ID nvarchar(80) NOT NULL,
	VALUE nvarchar(80) NOT NULL,
	DESCRIPTION nvarchar(200) NULL,
	CONSTRAINT LUM_MD_SOLD_TO_PARTY_LU_PK PRIMARY KEY CLUSTERED 
(
	ID ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
);

INSERT INTO LOOKUP_TABLE_INCLUSIONS values ('LUM_MD_SOLD_TO_PARTY_LU');

--Create Lookup domain from Metadata Editor

CREATE TABLE LUM_MD_SOLD_TO_CUS_CAS_MAP(
	CODE nvarchar(2000) NOT NULL,
	SOLD_TO_BANNER nvarchar(2000) NULL,
	SOLD_TO_PARTY nvarchar(2000) NULL,
 CONSTRAINT LUM_MD_SOLD_TO_CUS_CAS_MAP_PK PRIMARY KEY CLUSTERED 
(
	CODE ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)
);

create table LUMILEDS_MIGRATION_PAL_OTMM_MAPPING (
	PAL_FIELD nvarchar(50),
	OTMM_FIELD nvarchar(50)
);

create table LUMILEDS_MIGRATION_PAL_ASSET_TYPE_MAPPING (
	NAMEPATH nvarchar(200),
	ASSET_TYPE nvarchar(50)
);

create table LUMILEDS_MIGRATION_PAL_FILE_NAME_MAPPING (
	PAL_ID nvarchar(80),
	FILE_NAME nvarchar(200),
	EXTENSION nvarchar(10)
);
