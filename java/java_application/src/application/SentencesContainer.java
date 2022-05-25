package application;

import java.util.Collections;
import java.util.List;

public class SentencesContainer {
	private List<Sentence> sentences;
	
	public SentencesContainer() {
		
	}
	
	public boolean addSentence(Sentence s) {
		if(s!=null) {
			this.sentences.add(s);
			return true;
		}else {
			return false;
		}
		
	}
	
	public boolean shuffleSentences() {
		
		if(this.sentences.size() >= 3) {
			Collections.shuffle(this.sentences);
			return true;
		}else {
			return false;
		}
	}
	
	public Sentence getSentence(int index) {
		Sentence sentence = new Sentence();
		try {
			sentence = this.sentences.get(index);
		} catch (IndexOutOfBoundsException e) {
			throw e;
		}
		return sentence;
		
	}
}
