package com.ssafy.webterview.repository;

import com.ssafy.webterview.entity.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation, Integer> {
	void deleteByGroupGroupNo(int groupNo);

	List<Evaluation> findByGroupGroupNo(int groupNo);
}