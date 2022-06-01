package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import application.Sentence;

public class SentenceTests {

	@Test
	public void create_answers() throws SQLException  {
		
		Sentence s = new Sentence(1,"Ei fu siccome immobile");						
			
		assertNotEquals(s.getAnswers().size(),0);
		
	}
	
	@Test
	public void correct_answer() throws SQLException {
		
		Sentence s = new Sentence(1,"Ei fu");		
		
		assertTrue(s.isCorrect("siccome immobile"));		
	}
	
	@Test
	public void wrong_answer() throws SQLException {
		Sentence s = new Sentence(1,"Ei fu");
		
		assertFalse(s.isCorrect("asadsa asdsada"));
	}
	
	@Test
	public void get_correct_answer() throws SQLException {
		Sentence s = new Sentence(1,"Ei fu");
		s.createAnswers();
		
		assertEquals(s.getCorrectAnswer(),"siccome immobile");
		
	}
}
