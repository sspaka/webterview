package com.ssafy.webterview.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Grade : 평가점수", description = "평가문항 별 점수의 정보를 나타낸다.")
public class Grade {
	@ApiModelProperty(value="평가점수 번호")
	private int gradeNo;
	@ApiModelProperty(value="면접관 번호")
	private int raterNo;
	@ApiModelProperty(value="지원자 번호")
	private int applicantNo;
	@ApiModelProperty(value="평가문항 번호")
	private int evaluationNo;
	@ApiModelProperty(value="평가점수")
	private int gradeScore;
}
