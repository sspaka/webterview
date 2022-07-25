package com.ssafy.webterview.controller;

import com.ssafy.webterview.dto.BoardDto;
import com.ssafy.webterview.dto.CommentDto;
import com.ssafy.webterview.service.BoardService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		try {
			return new ResponseEntity<>(boardService.retrieveBoard(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ArrayList<>(),HttpStatus.ACCEPTED);
		}
	}

	@ApiOperation(value = "게시판 글 개수", notes = "모든 게시글의 개수를 반환한다.", response = Long.class)
	@GetMapping("/count")
	public ResponseEntity<Long> getTotalCount() {
		try {
			return new ResponseEntity<>(boardService.getTotalCount(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(0L,HttpStatus.ACCEPTED);
		}
	}

	@ApiOperation(value = "게시판 글 보기", notes = "성공여부 메세지와 글 번호에 해당하는 게시글의 정보를 반환한다.", response = Map.class)
	@GetMapping("/{boardNo}")
	public ResponseEntity<Map<String,Object>> detailBoard(@PathVariable int boardNo) {
		Map<String,Object> resultMap = new HashMap<>();
		try {
			resultMap.put("board",boardService.detailBoard(boardNo));
			resultMap.put("message",SUCCESS);
		} catch (Exception e) {
			resultMap.put("message",FAIL);
		}
		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

	@ApiOperation(value = "게시판 글 등록", notes = "새로운 게시글 정보를 입력한다. 그리고 DB 입력 성공여부 메세지, 등록한 글 객체를 반환한다.", response = Map.class)
	@PostMapping
	public ResponseEntity<Map<String,Object>> writeBoard(@RequestBody BoardDto board) {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			resultMap.put("board",boardService.insertBoard(board));
			resultMap.put("message",SUCCESS);
		} catch (Exception e) {
			resultMap.put("message",FAIL);
		}
		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

	@ApiOperation(value = "게시판 글 정보 수정", notes = "글 번호에 해당하는 게시글의 정보를 수정한다. 그리고 DB 입력 성공여부 메세지, 등록한 글 객체를 반환한다.", response = Map.class)
	@PutMapping("/modify")
	public ResponseEntity<Map<String,Object>> updateBoard(@RequestBody BoardDto board) {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			resultMap.put("board",boardService.updateBoard(board));
			resultMap.put("message",SUCCESS);
		} catch (Exception e) {
			resultMap.put("message",FAIL);
		}
		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

	@ApiOperation(value = "게시판 글삭제", notes = "글 번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB 삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{boardNo}")
	public ResponseEntity<String> deleteBoard(@PathVariable int boardNo) {
		Map<String,Object> resultMap = new HashMap<>();
		try {
			boardService.deleteBoard(boardNo);
			return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(FAIL, HttpStatus.ACCEPTED);
		}
	}

	@ApiOperation(value = "댓글 입력", notes = "새로운 댓글 정보를 입력한다. 그리고 DB 입력 성공여부 메세지, 등록한 댓글 객체를 반환한다.", response = Map.class)
	@PostMapping("/comment")
	public ResponseEntity<Map<String,Object>> writeComment(@RequestBody CommentDto comment) {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			resultMap.put("comment",boardService.insertComment(comment));
			resultMap.put("message",SUCCESS);
		} catch (Exception e) {
			resultMap.put("message",FAIL);
		}
		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

	@ApiOperation(value = "댓글 삭제", notes = "댓글 번호에 해당하는 댓글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/comment/{commentNo}")
	public ResponseEntity<String> deleteComment(@PathVariable int commentNo) {
		try {
			boardService.deleteComment(commentNo);
			return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(FAIL, HttpStatus.ACCEPTED);
		}
	}

}