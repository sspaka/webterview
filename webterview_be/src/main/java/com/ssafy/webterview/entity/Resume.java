package com.ssafy.webterview.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Data
@DynamicInsert
@Table(name = "Resume")
public class Resume {
	@Id
	@Column(name = "ResumeNo", nullable = false)
	private Integer resumeNo;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "ApplicantNo", nullable = false)
	private Applicant applicantNo;

	@Column(name = "ResumeQuestion", length = 45)
	private String resumeQuestion;

	@Column(name = "ResumeAnswer", length = 45)
	private String resumeAnswer;

}