package tests;


import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import application.DBConnection;

public class DBConnectionTests {	
	
	DBConnection db = null;

	public DBConnection getValidConnection() throws SQLException {		
		return new DBConnection();		
	}
	
	@BeforeEach
	void setUp() {
		try {
			db = getValidConnection();
		} catch (SQLException e) {
			System.out.println("Cannot open connection!");
		}
	}
	
	@AfterEach
	void tearDown() {
		try {
			db.getConnection().close();
			db = null;
		} catch (SQLException e) {
			System.out.println("Cannot close connection!");
		}
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
		
		db.selectQuery(query); //If the query is not valid then it will throws the exception
	}
	
	@Test
	public void wrong_select_statement() throws SQLException {
		String query = "SELECT * FROM wrong_table WHERE wrong_field = 1";
		
		assertThrows(SQLException.class,
				() ->{
					db.selectQuery(query);
				});
	}
	
	@Test
	public void good_update() throws SQLException{
		
		String query = "UPDATE sentence SET n_total = n_total + 1 WHERE id_sentence = 1;";
		
		assertTrue(db.updateQuery(query));		
	}
	
	@Test
	public void wrong_update() throws SQLException{
		
		String query = "UPDATE flowers SET n_total = n_total + 1 WHERE id_sentence = 1;";		
		
		assertFalse(db.updateQuery(query));		
	}
	
	@Test
	public void good_delete() throws SQLException{
		
		String query = "DELETE FROM sentence WHERE id_sentence = 1;";
		
		assertTrue(db.deleteQuery(query));		
	}
	
	@Test
	public void wrong_delete() throws SQLException{
		
		String query = "DELETE FROM roses WHERE id_sentence = 1213;";
		
		assertTrue(db.deleteQuery(query));
	}	
	
}
