package com.ssafy.webterview.controller;

import com.ssafy.webterview.dto.BoardDto;
import com.ssafy.webterview.dto.CommentDto;
import com.ssafy.webterview.entity.Board;
import com.ssafy.webterview.service.BoardService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	private BoardService boardService;

	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}

	@ApiOperation(value = "게시판 글 목록", notes = "모든 게시글의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<BoardDto>> retrieveBoard() {
		return new ResponseEntity<>(boardService.retrieveBoard(), HttpStatus.OK);
	}

	@ApiOperation(value = "게시판 글 개수", notes = "모든 게시글의 개수를 반환한다.", response = Long.class)
	@GetMapping("/count")
	public ResponseEntity<Long> getTotalCount() {
		return new ResponseEntity<>(boardService.getTotalCount(), HttpStatus.OK);
	}

	@ApiOperation(value = "게시판 글 보기", notes = "글번호에 해당하는 게시글의 정보를 반환한다.", response = Board.class)
	@GetMapping("/{boardNo}")
	public ResponseEntity<BoardDto> detailBoard(@PathVariable int boardNo) {
		return new ResponseEntity<>(boardService.detailBoard(boardNo), HttpStatus.OK);
	}

	@ApiOperation(value = "게시판 글 등록", notes = "새로운 게시글 정보를 입력한다. 그리고 DB 입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeBoard(@RequestBody BoardDto board) {
		boardService.insertBoard(board);
		return new ResponseEntity<>(SUCCESS, HttpStatus.OK);

//		logger.error("게시글 등록 실패");
//		return new ResponseEntity<>(FAIL,HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "게시판 글정보 수정", notes = "글번호에 해당하는 게시글의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/{boardNo}")
	public ResponseEntity<String> updateBoard(@RequestBody BoardDto board) {
		boardService.updateBoard(board);
		return new ResponseEntity<>(SUCCESS, HttpStatus.OK);

//		logger.error("게시글 수정 실패");
//		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "게시판 글삭제", notes = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{boardNo}")
	public ResponseEntity<String> deleteBoard(@PathVariable int boardNo) {
		boardService.deleteBoard(boardNo);
		return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
//		logger.error("게시글 삭제 실패");
//		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "댓글 입력", notes = "새로운 댓글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/comment")
	public ResponseEntity<String> writeComment(@RequestBody CommentDto comment) {
		boardService.insertComment(comment);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//		logger.error("댓글 등록 실패");
//		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "댓글 삭제", notes = "댓글번호에 해당하는 댓글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/comment/{commentNo}")
	public ResponseEntity<String> deleteComment(@PathVariable int commentNo) {
		boardService.deleteComment(commentNo);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//		logger.error("댓글 삭제 실패");
//		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

}