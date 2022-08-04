package com.ssafy.webterview.repository;

import com.ssafy.webterview.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface GradeRepository extends JpaRepository<Grade, Integer> {
	@Query("select e.evaluationNo as eval, avg(g.gradeScore) as avg " +
			"from Grade g " +
			"join g.applicant a " +
			"join g.evaluation e " +
			"where a.applicantNo=?1 " +
			"group by e.evaluationNo")
	List<Map<String,Object>> getAvgScores(int applicantNo);

//	List<Map<String,Object>> getAvgScoreList(int groupNo);
}