package com.nttdata.lumileds.opentext.transformer.utility;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SqlUtility {

	private static final Log log = LogFactory.getLog(SqlUtility.class);

	/*
	private static Connection conn = null;

	
	public SqlUtility() {

		String dbURL = "jdbc:sqlserver://10.80.132.90:21433;DatabaseName=OTMM_DEV";
		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String user = "otmm_devdba";
		String pass = "@ttmDevDBA01";

		try {
			Class.forName(driver).newInstance();

			conn = DriverManager.getConnection(dbURL, user, pass);
			if (null != conn) {
				DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
				log.debug("Driver name : " + dm.getDriverName());
				log.debug("Driver version : " + dm.getDriverVersion());
				log.debug("Product name : " + dm.getDatabaseProductName());
				log.debug("Product version : " + dm.getDatabaseProductVersion());
			}

		}
		catch (Exception ex) {
			log.error("Exception: {} ", ex);
		}

	}
	*/

	public HashMap<String, String> getMetadata(String name) {

		return null;
	}

	public HashMap<String, String> getAssetMetadata(String assetName, Connection conn) {

		HashMap<String, String> resultMap = new HashMap<String, String>();

		String assetMetadata = "select " + 
				"	a.OTMM_FIELD OT_FIELD," + 
				"	c.field PAL_VALUE" + 
				" from " + 
				"	LUMILEDS_MIGRATION_PAL_OTMM_MAPPING a, " + 
				"	lumileds_migration_pal_asset_master b, " + 
				"	lumileds_migration_pal_asset_metadata_processed c " + 
				" where " + 
				"	a.pal_field = c.name and " + 
				"	b.id = c.id and " +
				"	b.filename = ?";

		PreparedStatement assetMetadataSelectStatement;
		try {
			
			assetMetadataSelectStatement = conn.prepareStatement(assetMetadata);

			assetMetadataSelectStatement.setString(1, assetName);

			ResultSet rs = assetMetadataSelectStatement.executeQuery();
			
			 while (rs.next()) {
				 
		            resultMap.put(rs.getString(1), rs.getString(2));
			 }

		} catch (SQLException e) {
			log.info("Exception while fetching data: {} ", e);
		}

		return resultMap;

	}
	
	/*
	public static void closeConnection() {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException sqlEx) {
			log.error("SQLException: {} ", sqlEx);
		}
	}
	*/

}
