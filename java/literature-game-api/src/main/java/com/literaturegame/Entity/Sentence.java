package com.literaturegame.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "domanda")
public class Sentence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_domanda;

    private String domanda_text;

    @OneToMany(mappedBy = "sentence")
    private List<Answer> answers;

    public Sentence(){

    }

    public Sentence(String testo_domanda){
        this.domanda_text = testo_domanda;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setId_domanda(int id_domanda) {
        this.id_domanda = id_domanda;
    }

    public int getId_domanda() {
        return id_domanda;
    }

    public void setTesto_domanda(String testo_domanda) {
        this.domanda_text = testo_domanda;
    }

    public String getTesto_domanda() {
        return domanda_text;
    }
}
