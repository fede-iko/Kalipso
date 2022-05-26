package com.literaturegame.literaturegame;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sentence/")
public class SentenceController {

	@GetMapping("/{name}")
	@ResponseBody
	public ResponseEntity getSentence(@PathVariable String name) {
		return new ResponseEntity("Bentornato "+name,HttpStatus.OK);
	}
	
}
