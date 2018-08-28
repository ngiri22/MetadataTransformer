package com.nttdata.lumileds.opentext.transformer.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SQLRepository {

	private static final Log log = LogFactory.getLog(SQLRepository.class);

	public HashMap<String, String> getMetadata(String name) {

		return null;
	}

	public HashMap<String, String> getAssetMetadata(String palId, Connection conn) {

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

			assetMetadataSelectStatement.setString(1, palId);

			ResultSet rs = assetMetadataSelectStatement.executeQuery();

			while (rs.next()) {

				resultMap.put(rs.getString(1), rs.getString(2));
			}

		} catch (SQLException sqlEx) {
			log.error("Exception while fetching data: {} ", sqlEx);
		}

		return resultMap;

	}

	public ResultSet getAssetNamePath(String palId, Connection conn) {

		ResultSet rs = null;
		
		String assetNamePath = "select "
				+ "		NAMEPATH "
				+ " from"
				+ "		LUMILEDS_MIGRATION_PAL_ASSET_TAXONOMY "
				+ " where "
				+ "		id = ? ";

		PreparedStatement assetNamePathSelectStatement;

		try {

			assetNamePathSelectStatement = conn.prepareStatement(assetNamePath);

			assetNamePathSelectStatement.setString(1,palId);

			rs = assetNamePathSelectStatement.executeQuery();

		} catch (SQLException sqlEx) {
			log.error("Exception while fetching assetPath: {} ", sqlEx);
		}

		return rs;

	}

}
