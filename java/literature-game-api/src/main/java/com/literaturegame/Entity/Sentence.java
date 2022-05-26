package com.literaturegame.Entity;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "sentence")
public class Sentence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_sentence;

    private String sentence_text;

    @OneToMany(mappedBy = "sentence")
    private List<Answer> answers;

    public Sentence(){

    }

    public Sentence(String sentence_text){
        this.sentence_text = sentence_text;
    }

	public int getId_sentence() {
		return id_sentence;
	}

	public void setId_sentence(int id_sentence) {
		this.id_sentence = id_sentence;
	}

	public String getSentence_text() {
		return sentence_text;
	}

	public void setSentence_text(String sentence_text) {
		this.sentence_text = sentence_text;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

    
}
