package com.literaturegame.Controller;

import com.literaturegame.Entity.Answer;
import com.literaturegame.Repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnswerController {

    @Autowired
    AnswerRepository answerRepository;

    @CrossOrigin
    @GetMapping("/answer")
    public List<Answer> findAnswers(){
        return answerRepository.findAll();
    }

}
