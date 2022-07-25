package com.ssafy.webterview.service;

import com.ssafy.webterview.entity.Board;
import com.ssafy.webterview.entity.Comment;

import java.util.List;

public interface BoardService {
	List<Board> retrieveBoard();

	Board detailBoard(int boardNo);

	Board insertBoard(Board board);

	Board updateBoard(Board board);

	void deleteBoard(int boardNo);

	long getTotalCount();

	Comment insertComment(Comment comment);

	void deleteComment(int commentno);

}
