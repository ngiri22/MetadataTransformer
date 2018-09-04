package com.nttdata.lumileds.opentext.transformer.repository;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nttdata.lumileds.opentext.transformer.utility.DBConstants;

public class SQLDirectRepository {

	//private static final Log log = LogFactory.getLog(SQLDirectRepository.class);


	private static Connection conn = null;

	//public SQLDirectRepository() {

	public static Connection createConnection() {
		
		try {
			
			Class.forName(DBConstants.DB_DRIVER).newInstance();

			conn = DriverManager.getConnection
					(
						DBConstants.DB_URL,
						DBConstants.DB_USER,
						DBConstants.DB_PASSWORD
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

	public boolean isProductImage(String palId, Connection conn) {

		String assetTaxonomy = "select " + 
				" 1 " + 
				" from " + 
				" LUMILEDS_MIGRATION_PAL_ASSET_TAXONOMY " + 
				" where " +
				" NAMEPATH like '%" + 
				DBConstants.PRODUCT_STRING +
				"%' and " +
				" ID = ? ";

		PreparedStatement assetTaxonomyStatement;

		try {

			assetTaxonomyStatement = conn.prepareStatement(assetTaxonomy);

			assetTaxonomyStatement.setString(1, palId);

			ResultSet rs = assetTaxonomyStatement.executeQuery();
			
			if ( rs.next() ) {
				
				return true;
			}
			else {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {



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
