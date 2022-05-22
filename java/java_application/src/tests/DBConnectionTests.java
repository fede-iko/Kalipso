package tests;


import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;

import org.junit.jupiter.api.Test;


import application.DBConnection;

public class DBConnectionTests {	

	public DBConnection getConnection() {
		String connectionString = "complete_sentence_game.mysql"; //SET A GOOD URL FOR THE CONNECTION TO THE DB
		String user = "root";
		String pwd = "";
		
			
		return new DBConnection(connectionString,user,pwd);
	}
	
	@Test
	public void good_connection_to_database() {
					
		DBConnection db = getConnection();
		
		assertEqual(db.connectionStatus,true);
	}
	
	@Test
	public void wrong_connection_to_database() {
		
		String connectionString = "wrong_url"; //SET A GOOD URL FOR THE CONNECTION TO THE DB
		String user = "root";
		String pwd = "";		
		DBConnection db = new DBConnection(connectionString,user,pwd);
		
		assertEqual(db.connectionStatus,false);
	}
	
	@Test
	public void good_select_statement() {
		String stmt = "SELECT * FROM sentence WHERE sentence_id = 1";
		
		DBConnection db = getConnection();
		
		ResultSet rs = db.select(stmt);
		
		assertTrue(rs.getFetchSize()!=0); //CHANGE IT SO IT CHECKS IF THE RESULT IS NOT EMPTY
	}
	
	@Test
	public void wrong_select_statement() {
		String stmt = "SELECT * FROM wrong_table WHERE wrong_field = 1";
		
		DBConnection db = getConnection();
		
		ResultSet rs = db.select(stmt);
		
		assertTrue(rs.getFetchSize()==0); //CHANGE IT SO IT CHECKS IF THE RESULT IS NOT EMPTY
	}
	
}
