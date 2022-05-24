package tests;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import application.Sentence;
import application.SentencesContainer;
import exceptions.SentenceIndexNotValidException;

public class SentencesContainerTests {

	@Test
	public void add_sentence() {
		
		SentencesContainer sc = new SentencesContainer();
		
		assertTrue(sc.addSentence(new Sentence(1,"Ei fu")));
	}
	
	@Test
	public void good_get_sentence() {
		SentencesContainer sc = new SentencesContainer();
		
		assertTrue(sc.addSentence(new Sentence(1,"Ei fu")));
		
		Sentence s = sc.getSentence(0);
		
		assertEqual(s.getId,0);
		assertEqual(s.getSentenceText,"Ei fu");
	}
	
	@Test
	public void wrong_get_sentence() {
		
		SentencesContainer sc = new SentencesContainer();
		
		assertTrue(sc.addSentence(new Sentence(1,"Ei fu")));
		
		assertThrows(SentenceIndexNotValidException.class,sc.getSentence(15));		
	}
	
	@Test
	public void shuffle_sentence() {
		SentencesContainer sc = new SentencesContainer();
		
		assertTrue(sc.addSentence(new Sentence(1,"Ei fu")));
		assertTrue(sc.addSentence(new Sentence(2,"La donzelletta")));
		assertTrue(sc.addSentence(new Sentence(3,"BLa bla"))); 
		//TODO ADD MORE VALID SENTENCE
		
		assertTrue(sc.shuffleSentences);	
		
	}
	
}
