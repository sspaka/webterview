package com.ssafy.webterview.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Evaluation : 평가문항 정보", description = "평가문항의 상세 정보를 나타낸다.")
public class EvaluationDto implements Serializable {
	@ApiModelProperty(value = "평가문항 점수 코드")
	private int evaluationNo;
	@ApiModelProperty(value = "평가문항이 속한 그룹 번호")
	private int groupNo;
	@ApiModelProperty(value = "평가문항")
	private String evaluationQuestion;
	@ApiModelProperty(value = "평가문항 타입. 1=일반문항, 2=종합평가")
	private int evaluationType;
}
