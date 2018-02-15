package com.somos.poc.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

public class PocSomosRowMapper implements RowMapper<ListaSomos> {
	
	private static Logger logger = LoggerFactory.getLogger(PocSomosRowMapper.class);

	@Override
	public ListaSomos mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		logger.info(">>> mapRow");
		logger.debug(">>> ResultSet" + rs.toString());
		
		ListaSomos objectSomos = new ListaSomos();
		
		try {
			
			objectSomos.setPartition(rs.getString("Partition"));
			objectSomos.setComponent(rs.getString("Component"));
			objectSomos.setAction(rs.getString("action"));
			objectSomos.setInstance(rs.getString("instance"));
			objectSomos.setState(rs.getString("state"));
			objectSomos.setTempo(rs.getString("tempo"));
			
			
		
			
		} catch (Exception e) {
			logger.error("Fatal Error:" + e.getStackTrace());
		} 
		
		return objectSomos;
	}

}
