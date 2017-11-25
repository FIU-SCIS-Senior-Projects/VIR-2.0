package com.vir.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vir.model.Word;

@Repository
public interface  WordRepository extends JpaRepository<Word, Long> {
	
	@Query("SELECT w FROM Word w WHERE w.value IN (:words)")
	List<Word> findAllIn(@Param("words") List<String> words);
	
	Word findFirstByValue(String value);
	
	@Transactional
    Long removeByValue(String value);

	Page<Word> findAllByCategory(Pageable pageable, String category);
}
	