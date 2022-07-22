package com.ssafy.webterview.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Evaluation : 평가표 정보", description = "평가표의 상세 정보를 나타낸다.")
public class Evaluation {
	@ApiModelProperty(value = "평가표 점수 코드")
	private int EvaluationNo;
	@ApiModelProperty(value = "평가가 속한 그룹 번호")
	private int groupNo;
	@ApiModelProperty(value = "평가한 면접관 번호")
	private int raterNo;
	@ApiModelProperty(value = "평가받은 지원자 번호")
	private int applicantNo;
	@ApiModelProperty(value = "평가 질문 내용")
	private String evaluationQuestion;
	@ApiModelProperty(value = "평가 점수")
	private int evaluationScore;
}
