package com.ssafy.webterview.dto;

import java.sql.Timestamp;

public class Comment {
	private int commentNo;
	private int boardNo;
	private int commentUserNo;
	private String commentAnswer;
	private Timestamp commentRegDate;
	
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public int getCommentUserNo() {
		return commentUserNo;
	}
	public void setCommentUserNo(int commentUserNo) {
		this.commentUserNo = commentUserNo;
	}
	public String getCommentAnswer() {
		return commentAnswer;
	}
	public void setCommentAnswer(String commentAnswer) {
		this.commentAnswer = commentAnswer;
	}
	public Timestamp getCommentRegDate() {
		return commentRegDate;
	}
	public void setCommentRegDate(Timestamp commentRegDate) {
		this.commentRegDate = commentRegDate;
	}

	
}
