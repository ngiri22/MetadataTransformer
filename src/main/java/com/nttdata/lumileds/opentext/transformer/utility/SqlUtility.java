package com.nttdata.lumileds.opentext.transformer.utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SqlUtility {

	private static final Log log = LogFactory.getLog(SqlUtility.class);

	public HashMap<String, String> getMetadata(String name) {

		return null;
	}

	public HashMap<String, String> getAssetMetadata(String assetName, Connection conn) {

		HashMap<String, String> resultMap = new HashMap<String, String>();

		String assetMetadata = "select " + 
				"	a.OTMM_FIELD OT_FIELD," + 
				"	b.field PAL_VALUE" + 
				" from " + 
				"	LUMILEDS_MIGRATION_PAL_OTMM_MAPPING a, " + 
				"	lumileds_migration_pal_asset_metadata b " + 
				" where " + 
				"	a.pal_field = b.name and " + 
				"	b.id = ?";

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

}
