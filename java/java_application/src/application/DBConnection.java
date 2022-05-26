package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
	
	String url = "jdbc:mysql://localhost/literature_game";
	String user;
	String pwd;
	Connection conn = null;
	
	public DBConnection() throws SQLException {		
		user = "root";
		pwd = null;			
		createConnection();
	}

	public DBConnection(String user,String pwd) throws SQLException {
		this.user = user;
		this.pwd = pwd;	
		createConnection();
	}
	
	private void createConnection() throws SQLException {		
		conn = DriverManager.getConnection(url,user,pwd);
	}
	
	public Connection getConnection() {		
		return this.conn;
	}
	
	
	public ResultSet selectQuery(String query) throws SQLException {					
		return conn.createStatement().executeQuery(query);
	}
	
	public void updateQuery(String query) throws SQLException {		
		conn.createStatement().executeUpdate(query);	
	}
	
	public void deleteQuery(String query) throws SQLException {
		conn.createStatement().executeUpdate(query);
	}
	
	public void insertQuery(String query) throws SQLException{   		
		if(conn.createStatement().executeUpdate(query)<1) {
			throw new SQLException();
		}		
	}
}
