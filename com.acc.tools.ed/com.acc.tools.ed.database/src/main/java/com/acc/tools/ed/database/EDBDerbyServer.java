package com.acc.tools.ed.database;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.derby.drda.NetworkServerControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
//@Configuration
public class EDBDerbyServer {
               
                private Properties edbProperties = new Properties();
 
                //@Autowired
                private DerbyServerConfig derbyServerConfig;
               
                private NetworkServerControl serverControl=null;
                private NetworkServerControl getDerbyServerInstance() throws Exception{
                                if(serverControl==null){
                                                InetAddress inetAdd=getIPAddress();
                                                Properties sysProp = System.getProperties();
                                                sysProp.put("derby.system.home", derbyServerConfig.getDbLocation());
                                                serverControl=new NetworkServerControl(inetAdd, derbyServerConfig.getPort(), derbyServerConfig.getDbUsername(), derbyServerConfig.getDbPassword());
                                                return serverControl;
                                } else {
                                                return serverControl;
                                }
                }
               
                private InetAddress getIPAddress() throws UnknownHostException{
                                InetAddress inetAdd=InetAddress.getLocalHost();
                                return inetAdd;
                }
               
                //@Bean
                public DataSource edDataSource() {
                                loadProperties();
                                System.out.println("****************************************** EBD DERBY SERVER DETAILS ******************************************");
                                String ipaddress=edbProperties.getProperty("EDB_DB_SERVER_IP");
                                logServierDetails(ipaddress);
                                final DataSource dataSource=getDataDource(ipaddress);
                                try {
                                                Connection con=dataSource.getConnection();
                                                con.close();
                                } catch (SQLException e) {
                                                System.out.println("\t\t\tERROR CONNECTING TO DERBY SERVER");
                                                try  {
                                                                FileOutputStream out = new FileOutputStream( derbyServerConfig.getDbLocation()+"//edb-database.properties");
                                                                edbProperties.setProperty("EDB_DB_SERVER_STATUS", "false");
                                                                edbProperties.setProperty("EDB_DB_SERVER_IP", "");
                                                                edbProperties.store(out, "PLEASE DON'T EDIT or DELETE PROPERTIES.\nAUTHOR:murali.k.gavarasana");
                                                                out.close();
                                                                System.out.println("\t\t\tRETRYING THE CONNECTION AGAIN!");
                                                                final boolean serverStatus=startDerbyServer();
                                                                if(serverStatus){
                                                                                try{
                                                                                                ipaddress=getIPAddress().getHostAddress();
                                                                                                final DataSource dataSource1=getDataDource(ipaddress);
                                                                                                Connection con=dataSource1.getConnection();
                                                                                                con.close();
                                                                                                logServierDetails(ipaddress);
                                                                                } catch (SQLException e1) {
                                                                                                System.out.println("\t\t\tRESTART APPLICATION/WEB SERVER!");
                                                                                }
                                                                }
                                                } catch (IOException e1) {
                                                                e1.printStackTrace();
                                                }
                                }
                                System.out.println("************************************************************************************************************");
                                return dataSource;
                }
               
                private void logServierDetails(String ipaddress){
                                System.out.println("\t\t\tDATABASE HOST:"+ipaddress);
                                System.out.println("\t\t\tDATABASE PORT:"+derbyServerConfig.getPort());
                                System.out.println("\t\t\tDATABASE NAME:"+derbyServerConfig.getDbName());
                }
 
                public boolean startDerbyServer(){
                                try{
                                                FileOutputStream out = new FileOutputStream( derbyServerConfig.getDbLocation()+"//edb-database.properties");
                                                NetworkServerControl serverControl=getDerbyServerInstance();
                                                serverControl.start(new PrintWriter(System.out,true));
                                                edbProperties.setProperty("EDB_DB_SERVER_STATUS", "true");
                                                InetAddress inetAdd=getIPAddress();
                                                edbProperties.setProperty("EDB_DB_SERVER_IP", inetAdd.getHostAddress());
                                                edbProperties.store(out, null);
                                                out.close();
                                               
                                } catch(Exception e){
                                                System.out.println("\t\t\tFAILD TO START EDB DATABASE SERVER:"+e);
                                                return false;
                                }
                                return true;
                }
               
                public boolean stopDerbyServer(){
                                try{
                                                NetworkServerControl serverControl=getDerbyServerInstance();
                                                serverControl.shutdown();
                                } catch(Exception e){
                                                System.out.println("\t\t\tFAILD TO STOP EDB DATABASE SERVER:"+e);
                                                return false;
                                }
                                return true;
                }
               
                private void loadProperties() {
 
                    try {
                        File file = new File( derbyServerConfig.getDbLocation()+"//edb-database.properties");
                        InputStream istrm = new FileInputStream( file );
                        edbProperties.load(istrm);
                    }
                    catch (Exception e ){
                                System.out.print("\t\t\tFAILED TO LOAD EDB DATABASE PROPERTIES:"+e);
                    }
                }
               
                private BasicDataSource getDataDource(String ipaddress){
                                String url="jdbc:derby://"+ipaddress+":"+ derbyServerConfig.getPort()+"/"+ derbyServerConfig.getDbName()+";create=true";
                                BasicDataSource dataSource = new BasicDataSource();
                                dataSource.setDriverClassName("org.apache.derby.jdbc.ClientDriver");
                                dataSource.setUrl(url);
                                dataSource.setUsername( derbyServerConfig.getDbUsername());
                                dataSource.setPassword( derbyServerConfig.getDbPassword());
                                return dataSource;
                }
 
 
                public Properties getEdbProperties() {
                                return edbProperties;
                }
 
                public void setEdbProperties(Properties edbProperties) {
                                this.edbProperties = edbProperties;
                }
 
}