package com.ssafy.webterview.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Resume : 지원자 자기소개서 문항", description = "지원자의 자기소개서 정보를 나타낸다.")
public class Resume {
	@ApiModelProperty(value = "지원자 자기소개서 문항 번호")
	private int resumeNo;
	@ApiModelProperty(value = "지원자 번호")
	private int applicantNo;
	@ApiModelProperty(value = "질문내용")
	private String resumeQuestion;
	@ApiModelProperty(value = "질문 답변내용")
	private String resumeAnswer;
}
