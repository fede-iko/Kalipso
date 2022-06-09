package com.literaturegame.Controller;

import com.literaturegame.Entity.Answer;
import com.literaturegame.Repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    AnswerRepository answerRepository;

    @GetMapping
    public List<Answer> findAnswers(){
        return answerRepository.findAll();
    }

    @GetMapping("/{idAnswer}")
    public ResponseEntity<Answer> getAnswerById(@PathVariable int idAnswer){
    	Optional<Answer> a = answerRepository.findById(idAnswer);
		return new ResponseEntity<Answer>(a.isPresent() ? a.get() : null,a.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
    
}
