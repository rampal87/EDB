package com.acc.tools.ed.database;

public class DerbyServerConfig {
	
	private String dbLocation;
	private String dbUsername;
	private String dbPassword;
	private int port;
	private String dbName;
	public String getDbLocation() {
		return dbLocation;
	}
	public void setDbLocation(String dbLocation) {
		this.dbLocation = dbLocation;
	}
	public String getDbUsername() {
		return dbUsername;
	}
	public void setDbUsername(String dbUsername) {
		this.dbUsername = dbUsername;
	}
	public String getDbPassword() {
		return dbPassword;
	}
	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

}
