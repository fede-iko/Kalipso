package tests;


import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;


import application.DBConnection;

public class DBConnectionTests {	
	
	DBConnection db = null;

	public DBConnection getValidConnection() throws SQLException {		
		return new DBConnection();
	}
	
	@Test
	public void good_credentials_to_database() throws SQLException {
					
		getValidConnection();
		
	}
	
	@Test
	public void wrong_credentials_to_database() throws SQLException {		
		String user = "wrongUser";
		String pwd = "wrongPwd";		
		assertThrows(SQLException.class,
				() ->{
					new DBConnection(user,pwd);
				});		
	}
	
	@Test
	public void good_select_statement() throws SQLException {
		String query = "SELECT * FROM sentence";
		
		DBConnection db1 = getValidConnection();
		
		db1.selectQuery(query); //If the query is not valid then it will throws the exception
	}
	
	@Test
	public void wrong_select_statement() throws SQLException {
		String query = "SELECT * FROM wrong_table WHERE wrong_field = 1";
		
		DBConnection db = getValidConnection();
		assertThrows(SQLException.class,
				() ->{
					db.selectQuery(query);
				});
	}
	
	protected void tearDown() {
		try {
			db.getConnection().close();
		} catch (SQLException e) {
			System.out.println("Cannot close connection!");
		}
	}
	
}
