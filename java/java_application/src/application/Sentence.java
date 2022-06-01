package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Sentence {

	private int idSentence;
	private String sentenceText;	
	private List<Answer> answers;
	
	public Sentence() {
		
	}

	public Sentence(int idSentence, String sentenceText) throws SQLException {
		this.idSentence = idSentence;
		this.sentenceText = sentenceText;
		answers = new ArrayList<Answer>();
		createAnswers();
	}
	
	public int getIdSentence() {
		return this.idSentence;		
	}
	
	public String getSentenceText() {
		return this.sentenceText;
	}
	
	public List<Answer> getAnswers(){
		return this.answers;
	}
	
	public void createAnswers() throws SQLException {
		
		DBConnection db = new DBConnection();		
		String query = "SELECT id_sentence,answer_text,is_correct FROM answer WHERE id_sentence = "+this.idSentence+";";		
		ResultSet rs = db.selectQuery(query);
		
		boolean goodSelect = false;
		
		while(rs.next()) {
			int idSentAnsw = rs.getInt("id_sentence");
			String ansText = rs.getString("answer_text");
			boolean correct = rs.getBoolean("is_correct");
			
			answers.add(new Answer(idSentAnsw,ansText,correct));
			goodSelect = true;
		}
		
		if(!goodSelect)
			throw new SQLException("Cannot create answers for the sentence {ID: "+this.idSentence+" }");
	}
	
	public boolean isCorrect(String userAnswer) {
		
		for(Answer a: this.answers) {			
			if(a.getIsCorrect()) {
				
				userAnswer = userAnswer.replaceAll("  ", " ").replaceAll("’","'").replaceAll("‘","'").toLowerCase();
						 
				String correctText = a.getAnswerText().replaceAll("  ", " ").replaceAll("’","'").replaceAll("‘","'").toLowerCase();
				
				return userAnswer.equals(correctText);
			}
		}
		
		return false;
	}
	
	public String getCorrectAnswer() {
		for(Answer a: this.answers) {
			if(a.getIsCorrect()) {
				return a.getAnswerText();
			}
		}
		return null;
	}
	
}
