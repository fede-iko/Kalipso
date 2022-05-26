package application;

public class Answer {

	private int id_sentence;
	private String answer_text;
	private boolean isCorrect;
	
	public Answer(int id_sentence,String answer_text,boolean isCorrect) {
		this.id_sentence = id_sentence;
		this.answer_text = answer_text;
		this.isCorrect = isCorrect;
	}
	
	public int getIdSentence() {
		return this.id_sentence;
	}
	
	public String getAnswerText() {
		return this.answer_text;
	}
	
	public boolean getIsCorrect() {
		return this.isCorrect;
	}
	
	
	
	
}
