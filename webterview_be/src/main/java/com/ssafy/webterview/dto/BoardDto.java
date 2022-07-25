package com.ssafy.webterview.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Board (게시글정보)", description = "글번호, 제목, 내용, 작성자아이디, 작성일, 댓글 개수, 댓글목록을 가진 Domain Class")
public class BoardDto implements Serializable {
	@ApiModelProperty(value = "글번호")
	private Integer boardNo;
	@ApiModelProperty(value = "작성자번호")
	private Integer userNo;
	@ApiModelProperty(value = "게시글유형")
	private Integer boardType;
	@ApiModelProperty(value = "게시글유형")
	private String boardTitle;
	@ApiModelProperty(value = "내용")
	private String boardContent;
	@ApiModelProperty(value = "작성일")
	private Instant boardRegDate;
	@ApiModelProperty(value = "수정일")
	private Instant boardUpDate;
	@ApiModelProperty(value="댓글 개수")
	private Long commentCnt;
	@ApiModelProperty(value = "댓글목록")
	private List<CommentDto> comments;

}
