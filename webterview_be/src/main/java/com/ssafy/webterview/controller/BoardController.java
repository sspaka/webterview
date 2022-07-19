package com.ssafy.webterview.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.webterview.dto.Board;
import com.ssafy.webterview.dto.Comment;
import com.ssafy.webterview.service.BoardService;
import com.ssafy.webterview.service.JwtServiceImpl;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/board")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private BoardService boardService;

	@Autowired
	private JwtServiceImpl jwtService;

	@ApiOperation(value = "게시판 글목록", notes = "모든 게시글의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<Board>> retrieveBoard(HttpServletRequest request) throws Exception {
		logger.debug("retrieveBoard - 호출");
		return new ResponseEntity<List<Board>>(boardService.retrieveBoard(), HttpStatus.OK);
	}

	@ApiOperation(value = "게시판 글 개수", notes = "모든 게시글의 개수를 반환한다.", response = List.class)
	@GetMapping("/count")
	public ResponseEntity<Integer> getTotalCount() throws Exception {
		return new ResponseEntity<Integer>(boardService.getTotalCount(), HttpStatus.OK);
	}

	@ApiOperation(value = "게시판 글보기", notes = "글번호에 해당하는 게시글의 정보를 반환한다.", response = Board.class)
	@GetMapping("/{boardNo}")
	public ResponseEntity<Board> detailBoard(@PathVariable int boardNo) {
		logger.debug("detailBoard - 호출");
		return new ResponseEntity<Board>(boardService.detailBoard(boardNo), HttpStatus.OK);
	}

	@ApiOperation(value = "게시판 글 등록", notes = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeBoard(@RequestBody Board board,HttpServletRequest request) {
		logger.debug("writeBoard - 호출");
		
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			if (boardService.insertBoard(board)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}else {
				logger.error("게시글 등록 실패");
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
		}

		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "게시판 글정보 수정", notes = "글번호에 해당하는 게시글의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/{boardNo}")
	public ResponseEntity<String> updateBoard(@RequestBody Board board, HttpServletRequest request) {
		logger.debug("updateBoard - 호출");
		logger.debug("" + board);
		
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			
			if (boardService.updateBoard(board)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}else {
				logger.error("게시글 수정 실패");
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
		}

		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "게시판 글삭제", notes = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{boardNo}")
	public ResponseEntity<String> deleteBoard(@PathVariable int boardNo,HttpServletRequest request) {
		logger.debug("deleteBoard - 호출");
		
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			if (boardService.deleteBoard(boardNo)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}else {
				logger.error("게시글 삭제 실패");
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
		}
		
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "댓글 입력", notes = "새로운 댓글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/comment")
	public ResponseEntity<String> writeComment(@RequestBody Comment comment,HttpServletRequest request) {
		logger.debug("writeComment - 호출");
		
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			if (boardService.insertComment(comment)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}else {
				logger.error("댓글 등록 실패");
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "댓글 삭제", notes = "댓글번호에 해당하는 댓글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/comment/{commentNo}")
	public ResponseEntity<String> deleteComment(@PathVariable int commentNo,HttpServletRequest request) {
		logger.debug("deleteComment - 호출");
		
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			if (boardService.deleteComment(commentNo)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}else {
				logger.error("댓글 삭제 실패");
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

}