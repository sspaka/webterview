package com.ssafy.webterview.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Data
@DynamicInsert
@Table(name = "grade")
public class Grade {
	@Id
	@Column(name = "GradeNo", nullable = false)
	private Integer gradeNo;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "RaterNo", nullable = false)
	private Rater rater;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "ApplicantNo", nullable = false)
	private Applicant applicant;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "EvaluationNo", nullable = false)
	private Evaluation evaluation;

	@Column(name = "GradeScore", nullable = false)
	private Integer gradeScore;

}