package main.java.com.mejiasoft.systeminventario.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DataBaseConnection {
    
    private static Connection conn;
    
    private DataBaseConnection(){}
    
    public static Connection getConnectionDataBase()throws SQLException{
    if(conn == null || conn.isClosed()){
    conn = DriverManager.getConnection(Credentials.URL_DB, Credentials.USER_DB, Credentials.PASS_DB);
    }
    return conn;
    }
}
