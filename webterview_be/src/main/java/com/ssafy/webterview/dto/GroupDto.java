package com.ssafy.webterview.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Group : 그룹정보", description = "그룹의 상세 정보를 나타낸다.")
public class GroupDto implements Serializable {
	@ApiModelProperty(value = "그룹 번호")
	private int groupNo;
	@ApiModelProperty(value = "그룹 코드")
	private String groupCode;
	@ApiModelProperty(value = "그룹 관리자 회원 번호")
	private int userNo;
	@ApiModelProperty(value = "그룹 시작 날짜")
	private Instant groupStartDate;
	@ApiModelProperty(value = "그룹 종료 날짜")
	private Instant groupEndDate;
	@ApiModelProperty(value = "그룹 블라인드면접 여부")
	private boolean groupBlind;

	
}
