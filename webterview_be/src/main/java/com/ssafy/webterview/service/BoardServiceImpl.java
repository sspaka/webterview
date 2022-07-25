package com.ssafy.webterview.service;

import com.ssafy.webterview.dto.BoardDto;
import com.ssafy.webterview.dto.CommentDto;
import com.ssafy.webterview.entity.Board;
import com.ssafy.webterview.repository.BoardRepository;
import com.ssafy.webterview.repository.CommentRepository;
import com.ssafy.webterview.util.DEConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public List<BoardDto> retrieveBoard() {
		List<BoardDto> dtoList = converter.toBoardDtoList(boardRepository.findAll());
		for(BoardDto board:dtoList){

			board.setCommentCnt(commentRepository.countByBoardNo(1));
		}
		return dtoList;
	}

	@Override
	public BoardDto insertBoard(BoardDto boardDto) {
		//작성, 수정 시각 저장
		boardDto.setBoardRegDate(Instant.now());
		boardDto.setBoardUpDate(Instant.now());
		//1) dto를 entity로 변경 2) 저장 3) 다시 dto로 변경
		return converter.toBoardDto(boardRepository.save(converter.toBoardEntity(boardDto)));
	}

	@Override
	public BoardDto detailBoard(int boardNo) {
		Board board = boardRepository.getReferenceById(boardNo);
//		Comment comment = board.getComments().get(0);
//		DtoEntityConverter.toCommentDto(comment);
		return converter.toBoardDto(board);
//		return boardRepository.getReferenceById(boardNo);
	}

	@Override
	@Transactional
	public BoardDto updateBoard(BoardDto boardDto) {
		Board board = boardRepository.getReferenceById(boardDto.getBoardNo());
		if (boardDto.getBoardContent() != null) board.setBoardContent(boardDto.getBoardContent());
		if (boardDto.getBoardTitle() != null) board.setBoardTitle(boardDto.getBoardTitle());
		board.setBoardUpDate(Instant.now());
		return converter.toBoardDto(board);
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
	public CommentDto insertComment(CommentDto commentDto) {
		commentDto.setCommentRegDate(Instant.now());
		//		DtoEntityConverter.toBoardDto(boardRepository.save(DtoEntityConverter.toBoardEntity(boardDto)));
		return converter.toCommentDto(commentRepository.save(converter.toCommentEntity(commentDto)));
	}

	@Override
	public void deleteComment(int commentno) {
		commentRepository.delete(commentRepository.getReferenceById(commentno));
	}

}