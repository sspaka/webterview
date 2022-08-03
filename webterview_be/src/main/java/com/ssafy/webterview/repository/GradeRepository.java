package com.ssafy.webterview.repository;

import com.ssafy.webterview.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Integer> {
//	@Query("select avg(GradeScore) " +
//			"from Grade " +
//			"where ApplicantNo=?1" +
//			"group by EvaluationNo")
//	List<Integer> getAvgScores(int applicantNo);
}