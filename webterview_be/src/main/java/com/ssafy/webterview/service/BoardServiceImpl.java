package com.ssafy.webterview.service;

import java.util.List;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.webterview.dto.Board;
import com.ssafy.webterview.dto.Comment;
import com.ssafy.webterview.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;
	private Timestamp now = Timestamp.valueOf(LocalDateTime.now());
	
	@Override
	public List<Board> retrieveBoard() {
		return boardMapper.retrieveBoard();
	}

	@Override
	public boolean insertBoard(Board board) {
		board.setBoardRegDate(now);
		board.setBoardUpDate(now);
		return boardMapper.insertBoard(board) == 1;
	}

	@Override
	public Board detailBoard(int articleno) {
		return boardMapper.detailBoard(articleno);
	}

	@Override
	@Transactional
	public boolean updateBoard(Board board) {
		board.setBoardUpDate(now);
		return boardMapper.updateBoard(board) == 1;
	}

	@Override
	@Transactional
	public boolean deleteBoard(int articleno) {
		return boardMapper.deleteBoard(articleno) == 1;
	}
	
	@Override
	public int getTotalCount() {
		return boardMapper.getTotalCount();
	}

	@Override
	public boolean insertComment(Comment comment) {
		comment.setCommentRegDate(now);
		return boardMapper.insertComment(comment) == 1;
	}

	@Override
	public boolean deleteComment(int commentno) {
		return boardMapper.deleteComment(commentno) == 1;
	}
	
}