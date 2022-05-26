package com.literaturegame.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "risposte")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_risposta;

    private String risposta_testo;

    @Column(nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean corretta;

    @ManyToOne
    @JoinColumn(name = "id_domanda")
    @JsonIgnore
    private Sentence sentence;

    public Answer(){}

    public Answer(int id_risposta, String testo_risposta, boolean corretta, Sentence sentence) {
        this.id_risposta = id_risposta;
        this.risposta_testo = testo_risposta;
        this.corretta = corretta;
        this.sentence = sentence;
    }

    public int getId_risposta() {
        return id_risposta;
    }

    public String getTesto_risposta() {
        return risposta_testo;
    }

    public boolean isCorretta() {
        return corretta;
    }

    public Sentence getSentence() {
        return sentence;
    }

    public void setId_risposta(int id_risposta) {
        this.id_risposta = id_risposta;
    }

    public void setTesto_risposta(String testo_risposta) {
        this.risposta_testo = testo_risposta;
    }

    public void setCorretta(boolean corretta) {
        this.corretta = corretta;
    }

    public void setSentence(Sentence sentence) {
        this.sentence = sentence;
    }
}
