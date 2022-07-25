package com.ssafy.webterview.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class CommentDto implements Serializable {
	private final Integer commentNo;
	private final Integer commentUserNo;
	private final Integer boardNo;
	private final String commentAnswer;
	private final Instant commentRegDate;
}
