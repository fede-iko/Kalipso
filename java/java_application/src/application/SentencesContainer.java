package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SentencesContainer {
	private List<Sentence> sentences;
	
	public SentencesContainer() throws SQLException {
		sentences = new ArrayList<Sentence>();		
	}
	
	public List<Sentence> getSentences(){
		return this.sentences;
	}
	
	public void createSentences() throws SQLException {
		
		DBConnection db = new DBConnection();
		String query = "SELECT id_sentence,sentence_text FROM sentence;";		
		ResultSet rs = db.selectQuery(query);
		
		while(rs.next()) {
			int idSentence = rs.getInt("id_sentence");
			String sentenceText = rs.getString("sentence_text");			
			sentences.add(new Sentence(idSentence,sentenceText));
		}
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
