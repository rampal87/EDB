package com.acc.tools.ed.database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 
 * @author murali.k.gavarasana
 *
 */
@Service
final public class MicroSoftAccessDatabase {
	
	private final Logger log=LoggerFactory.getLogger(MicroSoftAccessDatabase.class);
	
	private static final String accessDBFile="EngagementDashboard.accdb"; 
	
	private Connection edbConnection;
	
	/**
	 * This method will copy the MS Access linked tables with sharepoint from application 
	 * to temporary directory  and open JDBC connection.
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 */
    public Connection getConnection() throws IOException, SQLException {
    	
    	if(this.edbConnection==null){
    	
			//Read ms access file from classpath
	    	InputStream dbfromJar = getClass().getResourceAsStream("/"+accessDBFile);
			
			//Delete MS Access database file from temp folder, if exists
	    	String path = System.getProperty("java.io.tmpdir");
			File localFile = new File(path + accessDBFile);
			localFile.deleteOnExit();
			
			//copy MS Access database file to temp folder
			FileOutputStream fileOutputStream = new FileOutputStream(localFile,true);
			int copyFile;		
			while ((copyFile = dbfromJar.read()) != -1) {
				fileOutputStream.write(copyFile);
			}
			fileOutputStream.close();
			log.debug("\t\tEngagementDashBoard Database created successfully!");
			
			String url = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ="+localFile.getAbsolutePath()+";";
	
			//MS Access datasource 
			
			this.edbConnection = DriverManager.getConnection(url);
			log.debug("\t\tEngagementDashBoard Database linked with sharepoint successfully!");
    	}
		return this.edbConnection;

    }

}
