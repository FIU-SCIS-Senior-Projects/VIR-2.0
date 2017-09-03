package com.vir.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vir.model.Word;

@Repository
public interface  WordRepository extends CrudRepository<Word, Long> {
	
	@Query("SELECT w FROM Word w WHERE w.value IN (:words)")
	List<Word> findAllIn(@Param("words") List<String> words);
	
	@Query(value="SELECT * FROM vir.word  WHERE word.value = :w LIMIT 1", nativeQuery=true)
	Word findMax1ByValue(@Param("w") String word);
}
