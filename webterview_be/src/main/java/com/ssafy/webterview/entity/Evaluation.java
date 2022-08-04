package com.ssafy.webterview.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Data
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Evaluation")
public class Evaluation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EvaluationNo", nullable = false)
	private Integer evaluationNo;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "GroupNo", nullable = false)
	private Group group;

	@Column(name = "EvaluationQuestion", length = 150)
	private String evaluationQuestion;

	@Column(name = "EvaluationType")
	private Integer evaluationType;

}