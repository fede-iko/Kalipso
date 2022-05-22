package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.Sentence;

public class SentenceTests {

	@Test
	public void create_answers() {
		
		Sentence s = new Sentence(1,"Ei fu siccome immobile");
		
		assertEquals(s.getAnswers.size(), 0);
		
		s.createAnswers();
		
		assertNotEquals(s.getAnswers.size(),0);
	}
	
	@Test
	public void correct_answer() {
		
		Sentence s = new Sentence(1,"Ei fu");
		
		s.createAnswers();
		
		assertTrue(s.IsCorrect("siccome immobile"));		
	}
	
	@Test
	public void wrong_answer() {
		Sentence s = new Sentence(1,"Ei fu");
		
		s.createAnswers();
		
		assertFalse(s.IsCorrect("asadsa asdsada"));
	}
}
