package com.acc.tools.ed.integration.dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.acc.tools.ed.database.MicroSoftAccessDatabase;


public class AbstractEdbDao {
	
	Logger log=LoggerFactory.getLogger(AbstractEdbDao.class);
	
	@Resource
	private MicroSoftAccessDatabase msAccessDatabase;
	
	
	public Connection getConnection() throws IOException, SQLException{
		final Connection connection=msAccessDatabase.getConnection();
		if(connection==null){
			throw new RuntimeException("Connection to Sharepoint failed.Please restart the EngagementDashboard Tool.");
		}
		return connection;
	}

}
