package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Sentence {

	private int idSentence;
	private String sentenceText;
	private int nGuessed;
	private int nTotal;
	private List<Answer> answers;
	
	public Sentence() {
		
	}

	public Sentence(int idSentence, String sentenceText, int nGuessed, int nTotal) throws SQLException {
		this.idSentence = idSentence;
		this.sentenceText = sentenceText;
		this.nGuessed = nGuessed;
		this.nTotal = nTotal;
		answers = new ArrayList<Answer>();
		createAnswers();
	}
	
	public int getIdSentence() {
		return this.idSentence;		
	}
	
	public String getSentenceText() {
		return this.sentenceText;
	}
	
	public int getNGuessed() {
		return this.nGuessed;
	}
	
	public int getNTotal() {
		return this.nTotal;
	}
	
	public List<Answer> getAnswers(){
		return this.answers;
	}
	
	public void createAnswers() throws SQLException {
		
		DBConnection db = new DBConnection();		
		String query = "SELECT * FROM answer WHERE id_sentence = "+this.idSentence+";";		
		ResultSet rs = db.selectQuery(query);
		
		boolean goodSelect = false;
		
		while(rs.next()) {
			int idSentAnsw = rs.getInt(1);
			String ansText = rs.getString(2);
			boolean correct = rs.getBoolean(3);
			
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
