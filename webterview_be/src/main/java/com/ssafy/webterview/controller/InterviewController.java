package com.ssafy.webterview.controller;

import com.ssafy.webterview.service.InterviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Api("InterviewController V1")
@RestController
@RequestMapping("/interview")
public class InterviewController {

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	private InterviewService interviewService;

	@Autowired
	public InterviewController(InterviewService interviewService) {
		this.interviewService = interviewService;
	}

	@ApiOperation(value = "본인 확인", notes = "접속자 유형에 따라 해당 테이블에 정보가 있는지 확인한다.", response = Map.class)
	@PostMapping("/confirm")
	public ResponseEntity<Map<String, Object>> confirm(String type, String name, String phone) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;

		try {
			if (type.equals("rater")) {
				resultMap.put("rater", interviewService.confirmRater(name, phone));

			} else if (type.equals("applicant")) {
				resultMap.put("applicant", interviewService.confirmApplicant(name, phone));
			}
			resultMap.put("message",SUCCESS);
			status=HttpStatus.OK;
		} catch (Exception e) {
			resultMap.put("message", FAIL);
			resultMap.put("error", e.getMessage());
		}

		return new ResponseEntity<>(resultMap, status);
	}

//	@ApiOperation(value = "알람", notes = "", response = Map.class)
//	@PostMapping("/alarm")
//	public ResponseEntity<Map<String, Object>> alarm() {
//		Map<String, Object> resultMap = new HashMap<>();
//		HttpStatus status = null;
//
//
//		return new ResponseEntity<Map<String, Object>>(resultMap, status);
//	}

	@ApiOperation(value = "지원자 일괄 추가", notes = "지원자 목록을 엑셀로 일괄 추가한다.", response = Map.class)
	@PostMapping("/applicant/save")
	public ResponseEntity<Map<String, Object>> saveApplicant(@RequestParam int groupNo, @RequestParam(name="file") MultipartFile file) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;

		try{
			resultMap.put("list", interviewService.saveApplicants(groupNo, file));
			resultMap.put("message",SUCCESS);
			status=HttpStatus.OK;
		}catch(Exception e){
			resultMap.put("message",FAIL);
			resultMap.put("error", e.getMessage());
		}

		return new ResponseEntity<>(resultMap, status);
	}

	@ApiOperation(value = "특이사항 작성", notes = "면접이 종료된 후 지원자의 특이사항을 저장한다.", response = Map.class)
	@PutMapping("/applicant/unique")
	public ResponseEntity<Map<String, Object>> saveUnique(@RequestBody Map<String,Object> map) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;

		int applicantNo = (int)map.get("applicantNo");
		String comment = (String)map.get("comment");

		try {
			interviewService.saveUnique(applicantNo, comment);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.OK;
		} catch (Exception e) {
			resultMap.put("message", FAIL);
			resultMap.put("error", e.getMessage());
		}

		return new ResponseEntity<>(resultMap, status);
	}

	@ApiOperation(value = "지원자 정보 수정", notes = "지원자의 면접장 번호와 면접시각을 수정한다.", response = Map.class)
	@PostMapping("/applicant/modify")
	public ResponseEntity<Map<String, Object>> modifyApplicant(@RequestBody Map<String,String> map) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;

		try {
			resultMap.put("applicant",interviewService.modifyApplicant(Integer.parseInt(map.get("applicantNo")),
																	Integer.parseInt(map.get("roomNo")),
																	new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").parse(map.get("date"))));
			resultMap.put("message", SUCCESS);
			status = HttpStatus.OK;
		} catch (Exception e) {
			resultMap.put("message", FAIL);
			resultMap.put("error", e.getMessage());
		}

		return new ResponseEntity<>(resultMap, status);
	}

	@ApiOperation(value = "지원자 정보 확인", notes = "지원자의 모든 정보를 반환한다.", response = Map.class)
	@GetMapping("/applicant/info")
	public ResponseEntity<Map<String, Object>> getApplicant(@RequestParam @ApiParam(value = "지원자 이메일", required = true) String email, @RequestParam int groupNo) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;

		try {
			resultMap.put("applicant",interviewService.getApplicant(groupNo,email));
			resultMap.put("message", SUCCESS);
			status = HttpStatus.OK;
		} catch (Exception e) {
			resultMap.put("message", FAIL);
			resultMap.put("error", e.getMessage());
		}

		return new ResponseEntity<>(resultMap, status);
	}

	@ApiOperation(value = "지원자 정보 삭제", notes = "해당 면접의 지원자들을 전부 삭제한다.", response = Map.class)
	@DeleteMapping("/applicant/delete")
	public ResponseEntity<Map<String, Object>> deleteApplicant(@RequestParam int groupNo) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;

		try {
			interviewService.deleteApplicant(groupNo);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.OK;
		} catch (Exception e) {
			resultMap.put("message", FAIL);
			resultMap.put("error", e.getMessage());
		}

		return new ResponseEntity<>(resultMap, status);
	}

	@ApiOperation(value = "지원자 목록", notes = "면접번호를 받아 해당 면접의 지원자를 전부 반환한다.", response = Map.class)
	@GetMapping("/applicant/group")
	public ResponseEntity<Map<String, Object>> listGroupApplicant(@RequestParam int groupNo) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;

		try {
			resultMap.put("applicantList",interviewService.listGroupApplicant(groupNo));
			resultMap.put("message", SUCCESS);
			status = HttpStatus.OK;
		} catch (Exception e) {
			resultMap.put("message", FAIL);
			resultMap.put("error", e.getMessage());
		}
		return new ResponseEntity<>(resultMap, status);
	}

	@ApiOperation(value = "지원자 목록", notes = "면접장 번호를 받아 해당 면접장의 지원자를 반환한다.", response = Map.class)
	@GetMapping("/applicant/room")
	public ResponseEntity<Map<String, Object>> listRoomApplicant(@RequestParam int roomNo) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;

		try {
			resultMap.put("applicantList",interviewService.listRoomApplicant(roomNo));
			resultMap.put("message", SUCCESS);
			status = HttpStatus.OK;
		} catch (Exception e) {
			resultMap.put("message", FAIL);
			resultMap.put("error", e.getMessage());
		}
		return new ResponseEntity<>(resultMap, status);
	}

	@ApiOperation(value = "지원자 자기소개서 일괄 추가", notes = "자기소개서를 엑셀로 일괄 추가한다.", response = Map.class)
	@PostMapping("/resume/save")
	public ResponseEntity<Map<String, Object>> saveResumes(@RequestParam int groupNo, @RequestParam(name="file") MultipartFile file) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;

		try{
			resultMap.put("list", interviewService.saveResumes(groupNo, file));
			resultMap.put("message",SUCCESS);
			status=HttpStatus.OK;
		}catch(Exception e){
			resultMap.put("message",FAIL);
			resultMap.put("error", e.getMessage());
		}

		return new ResponseEntity<>(resultMap, status);
	}

	@ApiOperation(value = "지원자 자기소개서 정보 삭제", notes = "해당 면접의 지원자 자기소개서를 전부 삭제한다.", response = Map.class)
	@DeleteMapping("/resume/delete")
	public ResponseEntity<Map<String, Object>> deleteResume(@RequestParam int groupNo) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;

		try {
			interviewService.deleteResume(groupNo);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.OK;
		} catch (Exception e) {
			resultMap.put("message", FAIL);
			resultMap.put("error", e.getMessage());
		}

		return new ResponseEntity<>(resultMap, status);
	}

}
