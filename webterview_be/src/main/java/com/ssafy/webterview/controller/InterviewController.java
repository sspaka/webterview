package com.ssafy.webterview.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.webterview.service.InterviewService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("InterviewController V1")
@RestController
@RequestMapping("/interview")
public class InterviewController {
	
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
		
	@Autowired
	private InterviewService interviewService;
	
	@ApiOperation(value = "본인 확인", notes = "접속자 유형에 따라 본인확인 후 ?? ?", response = Map.class)
	@PostMapping("/confirm")
	public ResponseEntity<Map<String,Object>> confirm(){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "알람", notes = "", response = Map.class)
	@PostMapping("/alarm")
	public ResponseEntity<Map<String,Object>> alarm(){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "지원자 일괄 추가", notes = "지원자 목록을 엑셀로 일괄 추가한다.", response = Map.class)
	@PostMapping("/applicant/save")
	public ResponseEntity<Map<String,Object>> saveApplicant(){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "특이사항 작성", notes = "", response = Map.class)
	@PostMapping("/applicant/unique")
	public ResponseEntity<Map<String,Object>> saveUnique(){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "지원자 정보 수정", notes = "", response = Map.class)
	@PostMapping("/applicant/modify")
	public ResponseEntity<Map<String,Object>> modifyApplicant(){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "지원자 정보 확인", notes = "", response = Map.class)
	@PostMapping("/applicant/info/{email}")
	public ResponseEntity<Map<String,Object>> getApplicant(@PathVariable("email") @ApiParam(value="지원자 이메일", required=true) String email){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "지원자 정보 삭제", notes = "", response = Map.class)
	@PostMapping("/applicant/delete")
	public ResponseEntity<Map<String,Object>> deleteApplicant(){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "지원자 목록", notes = "", response = Map.class)
	@PostMapping("/applicant/list")
	public ResponseEntity<Map<String,Object>> listApplicant(){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}
