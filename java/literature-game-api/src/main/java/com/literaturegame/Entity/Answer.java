package com.literaturegame.Entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "answer")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_sentence;

    private String answer_text;

    @Column(nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean isCorrect;

    public Answer(){

    }

    public Answer(String answer_text, boolean isCorrect){
        this.answer_text = answer_text;
        this.isCorrect = isCorrect;
    }

    public void setId_sentence(int id_sentence) {
        this.id_sentence = id_sentence;
    }

    public void setAnswer_text(String answer_text) {
        this.answer_text = answer_text;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public int getId_sentence() {
        return id_sentence;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public String getAnswer_text() {
        return answer_text;
    }
}
