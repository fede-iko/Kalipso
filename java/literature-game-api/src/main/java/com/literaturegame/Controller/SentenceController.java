package com.literaturegame.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.literaturegame.Entity.Sentence;
import com.literaturegame.Repository.SentenceRepository;

@RestController
@RequestMapping("/sentence")
public class SentenceController {
	
	@Autowired
    SentenceRepository sentenceRepository;
	
	@CrossOrigin
	@GetMapping
	public List<Sentence> getAllSentences(){
		return sentenceRepository.findAll();
	}
	
	@GetMapping("/{idSentence}")
	public Optional<Sentence> getSentenceById(@PathVariable int idSentence) {
		return sentenceRepository.findById(idSentence);
	}
	
}
