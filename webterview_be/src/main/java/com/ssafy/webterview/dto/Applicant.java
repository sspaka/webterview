package com.ssafy.webterview.dto;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Applicant : 지원자 정보", description = "지원자의 상세 정보를 나타낸다.")
public class Applicant {
	@ApiModelProperty(value = "지원자 번호")
	private int applicantNo;
	@ApiModelProperty(value = "지원자가 속한 방 번호")
	private int roomNo;
	@ApiModelProperty(value = "지원자 이름")
	private String applicantName;
	@ApiModelProperty(value = "지원자 나이")
	private int applicantAge;
	@ApiModelProperty(value = "지원자 대학")
	private String applicantUniv;
	@ApiModelProperty(value = "지원자 학점")
	private double applicantGPA;
	@ApiModelProperty(value = "지원자 자격증")
	private String applicantLicense;
	@ApiModelProperty(value = "지원자 어학성적")
	private String applicantLang;
	@ApiModelProperty(value = "지원자 특이사항")
	private String applicantUnique;
	@ApiModelProperty(value = "지원자 면접날짜")
	private Date applicantDate;
	@ApiModelProperty(value = "지원자 순위")
	private int ApplicantRank;
	
}
