package com.literaturegame.Controller;

import com.literaturegame.Entity.Answer;
import com.literaturegame.Repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    AnswerRepository answerRepository;

    @CrossOrigin
    @GetMapping
    public List<Answer> findAnswers(){
        return answerRepository.findAll();
    }

    /*@CrossOrigin
    @GetMapping("/answer/{idSentence}")
    public Collection<Answer> getAnswersBySentenceId(@PathVariable int idSentence){
    	return answerRepository.findBySentenceId(idSentence);
    }*/

}
