package com.literaturegame.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.literaturegame.Entity.Sentence;
import com.literaturegame.Repository.SentenceRepository;

@CrossOrigin
@RestController
@RequestMapping("/sentence")
public class SentenceController {
	
	@Autowired
    SentenceRepository sentenceRepository;
	
	@GetMapping
	public List<Sentence> getAllSentences(){
		return sentenceRepository.findAll();
	}
	
	@GetMapping("/{idSentence}")
	public ResponseEntity<Sentence> getSentenceById(@PathVariable int idSentence) {
		Optional<Sentence> s = sentenceRepository.findById(idSentence);
		return new ResponseEntity<Sentence>(s.isPresent() ? s.get() : null,s.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);		
	}	
}
