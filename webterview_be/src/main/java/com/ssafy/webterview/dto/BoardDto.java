package com.ssafy.webterview.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Data
public class BoardDto implements Serializable {
	private final Integer boardNo;
	private final Integer userNo;
	private final Integer boardType;
	private final String boardTitle;
	private final String boardContent;
	private final Instant boardRegDate;
	private final Instant boardUpDate;
	private final List<CommentDto> comments;
}
