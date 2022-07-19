package com.ssafy.webterview.service;

import java.util.List;

import com.ssafy.webterview.dto.Board;
import com.ssafy.webterview.dto.Comment;

public interface BoardService {
	public List<Board> retrieveBoard();
	public Board detailBoard(int articleno);
	public boolean insertBoard(Board board);
	public boolean updateBoard(Board board);
	public boolean deleteBoard(int articleno);
	public int getTotalCount();
	
	public boolean insertComment(Comment comment);
	public boolean deleteComment(int commentno);
	
}
