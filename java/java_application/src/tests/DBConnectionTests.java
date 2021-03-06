package tests;


import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import application.DBConnection;


public class DBConnectionTests {	
	
	DBConnection db = null;

	public DBConnection getValidTestConnection() throws SQLException {		
		return new DBConnection();		
	}
	
	@BeforeEach
	void setUp() {
		try {
			db = getValidTestConnection();
			db.getConnection().createStatement().execute("START TRANSACTION;");
		} catch (SQLException e) {
			System.out.println("Cannot open connection!");
		}
	}
	
	@AfterEach
	void tearDown() {
		try {
			db.getConnection().createStatement().execute("ROLLBACK;");
			db.getConnection().close();
			db = null;
		} catch (SQLException e) {
			System.out.println("Cannot close connection!");
		}
	}
	
	@Test
	public void good_connection_to_database() throws SQLException  {
					
		getValidTestConnection();
		
	}
	
	@Test
	public void wrong_credentials_to_database() {		
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
	public void wrong_select_statement()  {
		String query = "SELECT * FROM wrong_table WHERE wrong_field = 1";
		
		assertThrows(SQLException.class,
				() ->{
					db.selectQuery(query);
				});
	}
	
	@Test
	public void good_update() throws SQLException {
		
		String query = "UPDATE sentence SET sentence_text = 'Franco' WHERE id_sentence = 1;";
		
		db.updateQuery(query);		
	}
	
	@Test
	public void wrong_update() {
		
		String query = "UPDATE flowers SET n_total = n_total + 1 WHERE id_sentence = 1;";		
		
		assertThrows(SQLException.class,
				()->{
					db.updateQuery(query);
				});		
	}
	
	@Test
	public void good_delete() throws SQLException {
		
		String query = "DELETE FROM answer WHERE id_sentence = 1;";		
		
		db.deleteQuery(query);		
	}
	
	@Test
	public void wrong_delete() {
		
		String query = "DELETE FROM roses WHERE id_sentence = 1213;";
		
		assertThrows(SQLException.class,
				()->{
					db.deleteQuery(query);	
				});
	}	
	
	@Test
	public void good_insert() throws SQLException {
		
		String query = "INSERT INTO sentence (sentence_text) VALUES('Ei fu test');";
		
		db.insertQuery(query);		
	}
	
	@Test
	public void wrong_insert() throws SQLException{
		
		String query = "INSERT INTO flowers (sentence_test) VALUES('aslkdja');";
		
		assertThrows(SQLException.class,
				()->{
					db.insertQuery(query);
				});
	}
	
	@Test
	public void multi_good_insert() throws SQLException{
		
		String query = "INSERT INTO sentence (sentence_text) VALUES"
				+ "('Ei fu test'),"
				+ "('Blabl bal?l'),"
				+ "('sadsalkj');";
		
		db.insertQuery(query);	
	}
	
}
