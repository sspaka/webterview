package com.ssafy.webterview.dto;

import java.sql.Timestamp;
import java.util.ArrayList;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Board (게시글정보)", description = "글번호, 제목, 내용, 작성자아이디, 작성일을 가진   Domain Class")
public class Board {
	@ApiModelProperty(value = "글번호")
	private int boardNo;
	@ApiModelProperty(value = "작성자번호")
	private int userNo;
	@ApiModelProperty(value = "게시글유형")
	private int boardType;
	@ApiModelProperty(value = "제목")
	private String boardTitle;
	@ApiModelProperty(value = "내용")
	private String boardContent;
	@ApiModelProperty(value = "작성일")
	private Timestamp boardDate;
	@ApiModelProperty(value = "댓글목록")
	private ArrayList<Comment> comments;
	
	
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public int getBoardType() {
		return boardType;
	}
	public void setBoardType(int boardType) {
		this.boardType = boardType;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public Timestamp getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(Timestamp boardDate) {
		this.boardDate = boardDate;
	}
	
	


}