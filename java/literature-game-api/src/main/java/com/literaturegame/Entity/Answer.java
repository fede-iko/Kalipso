package com.literaturegame.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "answer")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_answer;

    private String answer_text;

    @Column(nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean is_correct;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_sentence")
    @JsonIgnore    
    private Sentence sentence;

    public Answer(){}

    public Answer(int id_answer, String answer_text, boolean isCorrect, Sentence sentence) {
        this.id_answer = id_answer;
        this.answer_text = answer_text;
        this.is_correct = isCorrect;
        this.sentence = sentence;
    }

	public int getId_answer() {
		return id_answer;
	}

	public void setId_answer(int id_answer) {
		this.id_answer = id_answer;
	}

	public String getAnswer_text() {
		return answer_text;
	}

	public void setAnswer_text(String answer_text) {
		this.answer_text = answer_text;
	}

	public boolean isCorrect() {
		return is_correct;
	}

	public void setCorrect(boolean isCorrect) {
		this.is_correct = isCorrect;
	}

	public Sentence getSentence() {
		return sentence;
	}

	public void setSentence(Sentence sentence) {
		this.sentence = sentence;
	}

    
}
