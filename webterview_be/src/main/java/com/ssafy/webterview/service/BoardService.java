package com.ssafy.webterview.service;

import com.ssafy.webterview.dto.BoardDto;
import com.ssafy.webterview.dto.CommentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardService {
//	List<BoardDto> retrieveBoard(Pageable pageable) throws Exception;

	Page<BoardDto> retrieveBoard(int userNo, Pageable pageable) throws Exception;

	List<BoardDto> retrieveNotice() throws Exception;

	BoardDto detailBoard(int boardNo) throws Exception;

	BoardDto insertBoard(BoardDto boardDto) throws Exception;

	BoardDto updateBoard(BoardDto boardDto) throws Exception;

	void deleteBoard(int boardNo) throws Exception;

	long getTotalCount() throws Exception;

	CommentDto insertComment(CommentDto commentDto) throws Exception;

	void deleteComment(int commentno) throws Exception;

}
