package com.ssafy.webterview.dto;

public class Comment {
	private int CommentNo;
	private int BoardNo;
	private String CommentUserNo;
	private String CommentAnswer;
	private String CommentDate;
	
	
	public int getCommentNo() {
		return CommentNo;
	}
	public void setCommentNo(int commentNo) {
		CommentNo = commentNo;
	}
	public int getBoardNo() {
		return BoardNo;
	}
	public void setBoardNo(int boardNo) {
		BoardNo = boardNo;
	}
	public String getCommentUserNo() {
		return CommentUserNo;
	}
	public void setCommentUserNo(String commentUserNo) {
		CommentUserNo = commentUserNo;
	}
	public String getCommentAnswer() {
		return CommentAnswer;
	}
	public void setCommentAnswer(String commentAnswer) {
		CommentAnswer = commentAnswer;
	}
	public String getCommentDate() {
		return CommentDate;
	}
	public void setCommentDate(String commentDate) {
		CommentDate = commentDate;
	}
	
	
	
}
