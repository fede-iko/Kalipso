package com.literaturegame.Repository;

import com.literaturegame.Entity.Answer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
	
	/*@Query(value="SELECT a FROM Answer a WHERE a.id_sentence = ?1",nativeQuery = false)
	Collection<Answer> findBySentenceId(long id);*/
	
}
