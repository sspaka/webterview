package com.ssafy.webterview.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Data
@DynamicInsert
@Table(name = "evaluation")
public class EvaluationEntity {
	@Id
	@Column(name = "EvaluationNo", nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "GroupNo", nullable = false)
	private GroupEntity groupNo;

	@Column(name = "EvaluationQuestion", length = 150)
	private String evaluationQuestion;

	@Column(name = "EvaluationMaxScore", length = 45)
	private String evaluationMaxScore;

}