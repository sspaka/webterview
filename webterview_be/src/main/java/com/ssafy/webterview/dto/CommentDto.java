package com.ssafy.webterview.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Comment (댓글 정보)", description = "댓글번호, 작성자번호, 게시글 정보, 내용, 작성일을 가진 Domain Class")
public class CommentDto implements Serializable {
	@ApiModelProperty(value = "댓글 번호")
	private Integer commentNo;
	@ApiModelProperty(value = "작성자 번호")
	private Integer commentUserNo;
	@ApiModelProperty(value = "게시글 번호")
	private Integer boardNo;
	@ApiModelProperty(value = "댓글 내용")
	private String commentAnswer;
	@ApiModelProperty(value = "작성일")
	private Instant commentRegDate;
}
