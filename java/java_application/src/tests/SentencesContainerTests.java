package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import application.Sentence;
import application.SentencesContainer;

public class SentencesContainerTests {

	@Test
	public void good_sentences_container() throws SQLException {
		
		SentencesContainer sc = new SentencesContainer();
		
		assertTrue(sc.getSentences().size()==0);
		
	}
	
	@Test
	public void add_sentence() throws SQLException {
		
		SentencesContainer sc = new SentencesContainer();
		
		assertTrue(sc.addSentence(new Sentence(1,"Ei fu",0,0)));
		
		assertEquals(sc.getSentences().size(),1);
	}
	
	@Test
	public void good_get_sentence() throws SQLException {
		SentencesContainer sc = new SentencesContainer();
		
		assertTrue(sc.addSentence(new Sentence(1,"Ei fu",0,0)));
		
		Sentence s = sc.getSentence(0);
		
		assertEquals(s.getIdSentence(),1);
		assertEquals(s.getSentenceText(),"Ei fu");
	}
	
	@Test
	public void wrong_get_sentence() throws SQLException {
		
		SentencesContainer sc = new SentencesContainer();
		
		assertTrue(sc.addSentence(new Sentence(1,"Ei fu",0,0)));
		
		assertThrows(IndexOutOfBoundsException.class,
				()->{
					sc.getSentence(15);	
				});
	}
	
	@Test
	public void shuffle_sentence() throws SQLException {
		SentencesContainer sc = new SentencesContainer();
		
		sc.createSentences();
		
		assertTrue(sc.shuffleSentences());	
		
	}
	
}
