package com.ssafy.webterview.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Applicant")
@Data
@DynamicInsert
public class Applicant {
	@Id
	@Column(name = "ApplicantNo", nullable = false)
	private Integer applicantNo;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "RoomNo", nullable = false)
	private Room room;

//	@Column(name = "ApplicantOrder", nullable = false)
//	private Integer applicantOrder;

	@Column(name = "ApplicantName", nullable = false, length = 45)
	private String applicantName;

	@Column(name = "ApplicantEmail", nullable = false, length = 45)
	private String applicantEmail;

	@Column(name = "ApplicantAge")
	private Integer applicantAge;

	@Column(name = "ApplicantUniv", length = 45)
	private String applicantUniv;

	@Column(name = "ApplicantGPA")
	private Double applicantGPA;

	@Column(name = "ApplicantLicense", length = 100)
	private String applicantLicense;

	@Column(name = "ApplicantLang", length = 100)
	private String applicantLang;

	@Column(name = "ApplicantUnique", length = 45)
	private String applicantUnique;

	@Column(name = "ApplicantDate")
	private Instant applicantDate;

	@Column(name = "ApplicantRank", nullable = false)
	private Integer applicantRank;

	@Column(name = "ApplicantPhone", nullable = false, length = 15)
	private String applicantPhone;

	@OneToMany(mappedBy = "applicantNo")
	private List<Resume> resumes = new ArrayList<>();

}