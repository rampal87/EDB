package com.acc.tools.ed.integration.test;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestSharePointDB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        try
        {
            System.out.println("filename");
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String database = 
                      "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=C:\\Users\\murali.k.gavarasana\\Desktop\\Database21.mdb";
            Connection conn = DriverManager.getConnection(database, "", "");
        } catch(Exception ex) {
            ex.printStackTrace();
        }

	}

}
