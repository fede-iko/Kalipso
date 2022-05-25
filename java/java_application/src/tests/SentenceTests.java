package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import application.Sentence;

public class SentenceTests {

	@Test
	public void create_answers() throws SQLException  {
		
		Sentence s = new Sentence(1,"Ei fu siccome immobile",0,0);						
			
		assertNotEquals(s.getAnswers().size(),0);
		
	}
	
	@Test
	public void correct_answer() throws SQLException {
		
		Sentence s = new Sentence(1,"Ei fu",0,0);		
		
		assertTrue(s.isCorrect("siccome immobile"));		
	}
	
	@Test
	public void wrong_answer() throws SQLException {
		Sentence s = new Sentence(1,"Ei fu",0,0);
		
		assertFalse(s.isCorrect("asadsa asdsada"));
	}
	
	@Test
	public void get_correct_answer() throws SQLException {
		Sentence s = new Sentence(1,"Ei fu",0,0);
		s.createAnswers();
		
		assertEquals(s.getCorrectAnswer(),"siccome immobile");
		
	}
}
