package com.ssafy.webterview.repository;

import com.ssafy.webterview.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface GradeRepository extends JpaRepository<Grade, Integer> {
	@Query("select e.evaluationNo as eval, avg(g.gradeScore) as avg, e.evaluationType as type " +
			"from Grade g " +
			"join g.applicant a " +
			"join g.evaluation e " +
			"where a.applicantNo=?1 and e.evaluationType <> 3" +
			"group by e.evaluationNo")
	List<Map<String,Object>> getAvgScores(int applicantNo);

	@Query("select g.gradeText from Grade g where g.applicant.applicantNo=?1 and g.evaluation.evaluationType = 3")
	List<String> getGradeText(int applicantNo);

	@Query("select a.applicantNo as no, a.applicantName as name, a.applicantEmail as email, e.evaluationNo as evalNo, e.evaluationType as type, avg(g.gradeScore) as avg " +
			"from Grade g " +
			"join g.applicant a " +
			"join g.evaluation e " +
			"join e.group gr " +
			"where gr.groupNo=?1 and e.evaluationType <> 3 " +
			"group by a.applicantNo, e.evaluationNo")
	List<Map<String,Object>> getAvgScoreList(int groupNo);

	@Query("select a.applicantNo as applicantNo, a.applicantName as name, a.applicantEmail as email, e.evaluationType as type, avg(g.gradeScore) as avg " +
			"from Grade g " +
			"join g.applicant a " +
			"join g.evaluation e " +
			"join e.group gr " +
			"where gr.groupNo=?1 and e.evaluationType <> 3 " +
			"group by a.applicantNo, e.evaluationType")
	List<Map<String,Object>> getRanking(int groupNo);

	List<Grade> findByRaterRaterNo(int raterNo);
}