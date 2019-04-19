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

	public Connection createConnection() {

		try {
			
			System.out.println("DB URL is : " + SeggregatorConstants.DB_DRIVER);

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

	public boolean checkAssetArchived(String palId, Connection conn) {

		String deactivationDates = "SELECT 1 FROM "
				+ " LUMILEDS_MIGRATION_PAL_ASSET_METADATA "
				+ " WHERE ID = ? AND"
				+ " NAME = ? AND"
				+ " CONVERT(DATE,FIELD,103) < GETDATE()";
		
		try {

			PreparedStatement copyRightDeactivationDateStatement = 
					conn.prepareStatement(deactivationDates);

			copyRightDeactivationDateStatement.setString(1, palId);
			copyRightDeactivationDateStatement.setString(2, 
					SeggregatorConstants.COPYRIGHT_END_DATE_FIELD);


			if  (copyRightDeactivationDateStatement.executeQuery().next()) {
				
				return true;
				
			}
			
			PreparedStatement deactivationDateStatement = 
					conn.prepareStatement(deactivationDates);

			deactivationDateStatement.setString(1, palId);
			deactivationDateStatement.setString(2, 
					SeggregatorConstants.DEACTIVATION_DATE);


			if  (deactivationDateStatement.executeQuery().next()) {
				
				return true;
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}


		return false;
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

	// Update valid Filename back to DB
	public void updateValidFileName (
			Connection conn,
			String assetFileName,
			String palId,
			String extension
			)
	{
		try
		{

			String updateFileName = " UPDATE "
					+ " LUMILEDS_MIGRATION_PAL_FILE_NAME_MAPPING"
					+ " SET "
					+ " FILE_NAME = ? ,"
					+ " EXTENSION = ? "
					+ " WHERE PAL_ID = ? ";

			// create our java prepared statement using a sql update query
			PreparedStatement updateFileNameStatement = 
					conn.prepareStatement(updateFileName);

			// set the prepared statement parameters
			updateFileNameStatement.setString(1,assetFileName);
			updateFileNameStatement.setString(2,extension);
			updateFileNameStatement.setString(3,palId);

			// call executeUpdate to execute our sql update statement
			updateFileNameStatement.executeUpdate();
			updateFileNameStatement.close();
		}
		catch (SQLException sqlEx)
		{
			sqlEx.printStackTrace();
		}
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
