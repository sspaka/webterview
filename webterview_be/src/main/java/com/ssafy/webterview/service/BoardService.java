package com.ssafy.webterview.service;

import com.ssafy.webterview.dto.BoardDto;
import com.ssafy.webterview.dto.CommentDto;

import java.util.List;

public interface BoardService {
	List<BoardDto> retrieveBoard() throws Exception;

	BoardDto detailBoard(int boardNo) throws Exception;

	BoardDto insertBoard(BoardDto boardDto) throws Exception;

	BoardDto updateBoard(BoardDto boardDto) throws Exception;

	void deleteBoard(int boardNo) throws Exception;

	long getTotalCount() throws Exception;

	CommentDto insertComment(CommentDto commentDto) throws Exception;

	void deleteComment(int commentno) throws Exception;

}
