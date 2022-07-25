package com.ssafy.webterview.service;

import com.ssafy.webterview.dto.BoardDto;
import com.ssafy.webterview.dto.CommentDto;

import java.util.List;

public interface BoardService {
	List<BoardDto> retrieveBoard();

	BoardDto detailBoard(int boardNo);

	BoardDto insertBoard(BoardDto boardDto);

	BoardDto updateBoard(BoardDto boardDto);

	void deleteBoard(int boardNo);

	long getTotalCount();

	CommentDto insertComment(CommentDto commentDto);

	void deleteComment(int commentno);

}
