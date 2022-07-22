package com.ssafy.webterview.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.webterview.dto.Grade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("ScoreController V1")
@RestController
@RequestMapping("/score")
public class ScoreController {
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@ApiOperation(value = "평가문항 저장", notes = "", response = Map.class)
	@PostMapping("/")
	public ResponseEntity<Map<String, Object>> saveQuestion() {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "평가문항 일괄삭제", notes = "", response = Map.class)
	@PostMapping("/")
	public ResponseEntity<Map<String, Object>> deleteQuestion() {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "평가문항 조회", notes = "", response = Map.class)
	@PostMapping("/")
	public ResponseEntity<Map<String, Object>> getQuestion() {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "지원자 점수 저장", notes = "지원자 점수 목록을 DB에 저장한다.", response = Map.class)
	@PostMapping("/save")
	public ResponseEntity<Map<String, Object>> saveScore(@RequestBody @ApiParam(value = "지원자 점수 목록", required=true) List<Grade> list) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		//한 지원자의 점수목록을 받고 전부 저장
		//(실시간 순위일경우) 저장됐다면 순위계산 후 applicant 테이블에 순위 저장
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "지원자 목록", notes = "", response = Map.class)
	@PostMapping("/")
	public ResponseEntity<Map<String, Object>> ___() {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	//나머지는 그때하기로 ^___^
}
