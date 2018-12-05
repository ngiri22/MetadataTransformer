package com.nttdata.lumileds.opentext.transformer.repository;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nttdata.lumileds.opentext.transformer.utility.SeggregatorConstants;

public class SQLDirectRepository {

	//private static final Log log = LogFactory.getLog(SQLDirectRepository.class);


	private static Connection conn = null;

	//public SQLDirectRepository() {

	public static Connection createConnection() {
		
		try {
			
			Class.forName(SeggregatorConstants.DB_DRIVER).newInstance();

			conn = DriverManager.getConnection
					(
						SeggregatorConstants.DB_URL,
						SeggregatorConstants.DB_USER,
						SeggregatorConstants.DB_PASSWORD
					);

			if (null != conn) {
			
				DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
				System.out.println("Driver name : " + dm.getDriverName());
				System.out.println("Driver version : " + dm.getDriverVersion());
				System.out.println("Product name : " + dm.getDatabaseProductName());
				System.out.println("Product version : " + dm.getDatabaseProductVersion());
			
			}
			
			return conn;

		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return conn;

	}
	
	public String getFileName(String palId, Connection conn ) {
	
		String assetName = "SELECT FILE_NAME FROM "
				+ "LUMILEDS_MIGRATION_PAL_FILE_NAME_MAPPING "
				+ " WHERE PAL_ID = ?";
		
		PreparedStatement assetNameStatement;
		
		try {
			
			assetNameStatement = conn.prepareStatement(assetName);
			
			assetNameStatement.setString(1, palId);
			
			ResultSet rs = assetNameStatement.executeQuery();
			
			while (rs.next()) {
				return rs.getString(1);
			}
			
		}
		catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
		return null;
		
	}

	public String getNamePath(String palId, Connection conn) {

		String assetTaxonomy = "select " + 
				"	 namepath " + 
				" from " + 
				" 	LUMILEDS_MIGRATION_PAL_ASSET_TAXONOMY " + 
				" where " +
				" 	NAMEPATH like ? and " + 
				" 	ID = ? ";

		PreparedStatement assetTaxonomyStatement;
		
		try {

			assetTaxonomyStatement = conn.prepareStatement(assetTaxonomy);

			assetTaxonomyStatement.setString(1, SeggregatorConstants.PRODUCT_QUERY_INITIAL_TERM);
			assetTaxonomyStatement.setString(2, palId);

			ResultSet rs = assetTaxonomyStatement.executeQuery();
			
			while ( rs.next() ) {
				return rs.getString(1);
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null ;
	}
	
	public Boolean checkALLORAPRRegions(String palId, Connection conn) {
		
		String checkRegion = " Select 1 from " +
				" lumileds_migration_pal_asset_metadata "
				+ " where "
				+ " ID = ? and "
				+ " NAME = ? and "
				+ " ( FIELD LIKE ? OR FIELD = ? )";
		
		PreparedStatement checkRegionStatement;
		
		try {
			checkRegionStatement = conn.prepareStatement(checkRegion);
			
			checkRegionStatement.setString(1, palId);
			checkRegionStatement.setString(2, SeggregatorConstants.AUTOMOTIVE_REGION);
			checkRegionStatement.setString(3, SeggregatorConstants.ALL_STRING);
			checkRegionStatement.setString(4, SeggregatorConstants.APR_STRING);
			
			if ( checkRegionStatement.executeQuery().next()) {
			
				return true;
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	public static void closeConnection() {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
	}

	

}
