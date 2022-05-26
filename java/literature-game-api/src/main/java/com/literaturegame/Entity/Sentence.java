package com.literaturegame.Entity;

import javax.persistence.*;

@Entity
@Table(name = "sentence")
public class Sentence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_sentence;

    private String sentence_text;

    private int n_guessed;

    private int n_total;

    public Sentence(){

    }

    public Sentence(String sentence_text, int n_guessed, int n_total){
        this.sentence_text = sentence_text;
        this.n_guessed = n_guessed;
        this.n_total = n_total;
    }

    public int getId_sentence() {
        return id_sentence;
    }

    public String getSentence_text() {
        return sentence_text;
    }

    public int getN_guessed() {
        return n_guessed;
    }

    public int getN_total() {
        return n_total;
    }

    public void setId_sentence(int id_sentence) {
        this.id_sentence = id_sentence;
    }

    public void setSentence_text(String sentence_text) {
        this.sentence_text = sentence_text;
    }

    public void setN_guessed(int n_guessed) {
        this.n_guessed = n_guessed;
    }

    public void setN_total(int n_total) {
        this.n_total = n_total;
    }
}
