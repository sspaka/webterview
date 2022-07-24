package com.ssafy.webterview.service;

import com.ssafy.webterview.entity.Board;
import com.ssafy.webterview.entity.Comment;
import com.ssafy.webterview.repository.BoardRepository;
import com.ssafy.webterview.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

	private BoardRepository boardRepository;
	private CommentRepository commentRepository;

	@Autowired
	public BoardServiceImpl(BoardRepository boardRepository, CommentRepository commentRepository) {
		this.boardRepository = boardRepository;
		this.commentRepository = commentRepository;
	}

	@Override
	public List<Board> retrieveBoard() {
		return boardRepository.findAll();
	}

	@Override
	public Board insertBoard(Board board) {
		board.setBoardRegDate(Instant.now());
		board.setBoardUpDate(Instant.now());
		return boardRepository.save(board);
	}

	@Override
	public Board detailBoard(int boardNo) {
		return boardRepository.getReferenceById(boardNo);
	}

	@Override
	@Transactional
	public Board updateBoard(Board board) {
		Board entity = boardRepository.getReferenceById(board.getBoardNo());
		if (board.getBoardContent() != null) entity.setBoardContent(board.getBoardContent());
		if (board.getBoardTitle() != null) entity.setBoardTitle(board.getBoardTitle());
		entity.setBoardUpDate(Instant.now());
		return entity;
	}

	@Override
	@Transactional
	public void deleteBoard(int boardNo) {
		boardRepository.delete(boardRepository.getReferenceById(boardNo));
	}

	@Override
	public long getTotalCount() {
		return boardRepository.count();
	}

	@Override
	public Comment insertComment(Comment comment) {
		comment.setCommentRegDate(Instant.now());
		return commentRepository.save(comment);
	}

	@Override
	public void deleteComment(int commentno) {
		commentRepository.delete(commentRepository.getReferenceById(commentno));
	}

}