package com.ssafy.webterview.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@ApiModel(value = "Group : 그룹정보", description = "그룹의 상세 정보를 나타낸다.")
public class GroupDto implements Serializable {
	@ApiModelProperty(value = "그룹 번호")
	private int groupNo;
	@ApiModelProperty(value = "그룹 관리자 회원 번호")
	private int userNo;
	@ApiModelProperty(value = "그룹 시작 날짜 String")
	private String groupStart;
	@ApiModelProperty(value = "그룹 종료 날짜 String")
	private String groupEnd;
	@ApiModelProperty(value = "그룹 시작 날짜")
	private Instant groupStartDate;
	@ApiModelProperty(value = "그룹 종료 날짜")
	private Instant groupEndDate;
	@ApiModelProperty(value = "그룹 블라인드면접 여부")
	private boolean groupBlind;

	
}
