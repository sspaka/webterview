package com.ssafy.webterview.service;

import com.ssafy.webterview.dto.BoardDto;
import com.ssafy.webterview.dto.CommentDto;
import com.ssafy.webterview.entity.Board;
import com.ssafy.webterview.entity.Comment;
import com.ssafy.webterview.repository.BoardRepository;
import com.ssafy.webterview.repository.CommentRepository;
import com.ssafy.webterview.util.DEConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

	private BoardRepository boardRepository;
	private CommentRepository commentRepository;
	private DEConverter converter;

	@Autowired
	public BoardServiceImpl(BoardRepository boardRepository, CommentRepository commentRepository, DEConverter converter) {
		this.boardRepository = boardRepository;
		this.commentRepository = commentRepository;
		this.converter = converter;
	}

	@Override
	public List<BoardDto> retrieveBoard() throws Exception {
		List<BoardDto> dtoList = converter.toBoardDtoList(boardRepository.findAll());
		for(BoardDto board:dtoList){
			board.setCommentCnt(commentRepository.countByBoardNo(board.getBoardNo()));
		}
		return dtoList;
	}

	@Override
	public BoardDto insertBoard(BoardDto boardDto) throws Exception {
		//작성, 수정 시각, 댓글 개수 저장
		boardDto.setBoardRegdate(Instant.now());
		boardDto.setBoardUpdate(Instant.now());
		//1) dto를 entity로 변경 2) 저장 3) 다시 dto로 변경
		return converter.toBoardDto(boardRepository.save(converter.toBoardEntity(boardDto)));
	}

	@Override
	public BoardDto detailBoard(int boardNo) throws Exception {
		BoardDto boardDto = converter.toBoardDto(boardRepository.getReferenceById(boardNo));
		boardDto.setComments(converter.toCommentDtoList(commentRepository.findByBoardNo(boardNo)));
		return boardDto;
	}

	@Override
	@Transactional
	public BoardDto updateBoard(BoardDto boardDto) throws Exception {
		Board board = boardRepository.getReferenceById(boardDto.getBoardNo());
		if (boardDto.getBoardContent() != null) board.setBoardContent(boardDto.getBoardContent());
		if (boardDto.getBoardTitle() != null) board.setBoardTitle(boardDto.getBoardTitle());
		board.setBoardUpdate(Instant.now());
		return converter.toBoardDto(board);
	}

	@Override
	@Transactional
	public void deleteBoard(int boardNo) throws Exception {
		boardRepository.delete(boardRepository.getReferenceById(boardNo));
	}

	@Override
	public long getTotalCount() throws Exception {
		return boardRepository.count();
	}

	@Override
	public CommentDto insertComment(CommentDto commentDto) throws Exception {
		commentDto.setCommentRegDate(Instant.now());
		return converter.toCommentDto(commentRepository.save(converter.toCommentEntity(commentDto)));
	}

	@Override
	public void deleteComment(int commentno) throws Exception {
		commentRepository.delete(commentRepository.getReferenceById(commentno));
	}

}