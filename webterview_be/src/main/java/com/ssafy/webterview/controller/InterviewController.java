package com.ssafy.webterview.controller;

import com.ssafy.webterview.dto.RaterDto;
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

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
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

	@ApiOperation(value = "지원자 정보 수정", notes = "지원자의 면접장 번호와 면접시각을 수정한다.", response = Map.class)
	@PutMapping("/applicant/modify")
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

	///////////////////면접관
	//면접관 일괄 추가
	@ApiOperation(value = "면접관 일괄 추가", notes = "면접관 목록을 엑셀로 일괄 추가한다.", response = Map.class)
	@PostMapping("/raterAll")
	public ResponseEntity<Map<String, Object>> saveRaterAll(@RequestParam int groupNo, @RequestParam int userNo, @RequestParam(name="file") MultipartFile file) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;

		try{
			resultMap.put("list", interviewService.saveAllRater(groupNo, userNo, file));
			resultMap.put("message",SUCCESS);
			status=HttpStatus.OK;
		}catch(Exception e){
			resultMap.put("message",FAIL);
			resultMap.put("error", e.getMessage());
		}

		return new ResponseEntity<>(resultMap, status);
	}
	//면접관 개별 추가
	@ApiOperation(value = "면접관 개별 추가", notes = "일괄 등록 외의 면접관 정보를 추가한다.일괄 등록과는 다르게 한 사람 씩 가능하다.", response = String.class)
	@PostMapping("/raterOne")
	public ResponseEntity<Map<String, Object>> writeRaterOne(@RequestBody RaterDto rater, HttpServletRequest request) {
		logger.debug("writeRaterOne - 호출");
		Map<String, Object> resultMap = new HashMap<>();

		try{
			resultMap.put("rater",interviewService.insertRaterOne(rater));
			resultMap.put("message", SUCCESS);
		} catch (Exception e){
			resultMap.put("message", e.getMessage());
		}

		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}
	//
	//면접관 리스트 보기
	@ApiOperation(value = "면접관 리스트 보기", notes = "관리자가 등록한 면접관들의 리스트를 보여준다.", response = String.class)
	@GetMapping("/raterList/{userNo}")
	public ResponseEntity<Map<String,Object>> retrieveRater(@PathVariable int userNo) {
		logger.debug("retrieveRater - 호출");
		Map<String, Object> resultMap = new HashMap<>();

		try{
			List<RaterDto> list = interviewService.listRater(userNo);
			resultMap.put("list", list);
			resultMap.put("message", HttpStatus.OK);
		} catch (Exception e){
			resultMap.put("message", e.getMessage());
		}
		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

//	//면접관 정보 상세 보기
	@ApiOperation(value = "면접관 정보 상세 보기", notes = "선택한 면접관에 대한 정보를 반환한다.", response = String.class)
	@GetMapping("/detailRater/{raterNo}")
	public ResponseEntity<Map<String, Object>> detailRater(@PathVariable int raterNo) {
		logger.debug("detailRater - 호출");
		Map<String, Object> resultMap = new HashMap<>();
		try{
			resultMap.put("rater", interviewService.detailRater(raterNo));
			resultMap.put("message", HttpStatus.OK);
		} catch(Exception e){
			resultMap.put("message", e.getMessage());
		}
		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

	//면접관 정보 수정
	@ApiOperation(value = "면접관 정보 수정", notes = "등록된 면접관들의 정보를 수정한다.", response = String.class)
	@PutMapping("/rater/{raterNo}")
	public ResponseEntity<Map<String, Object>> modifyRater(@RequestBody RaterDto raterDto,HttpServletRequest request) {
		logger.debug("modifyRater - 호출");
		Map<String, Object> resultMap = new HashMap<>();
		try{
			resultMap.put("modify", interviewService.modifyRater(raterDto));
			resultMap.put("message", HttpStatus.OK);
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
		}
		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

	//면접관 일괄 삭제
	@ApiOperation(value = "면접관 정보 일괄 삭제", notes = "관리자가 등록한 면접관들의 정보를 삭제한다.", response = String.class)
	@DeleteMapping("/alldelete/{userNo}")
	public ResponseEntity<Map<String, Object>> deleteAllRater(@PathVariable int userNo) {
		logger.debug("deleteAllRater - 호출");
		Map<String, Object> resultMap = new HashMap<>();
		try{
			interviewService.deleteAllRater(userNo);
			resultMap.put("message", HttpStatus.OK);
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
		}
		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

	//면접관 한명 삭제
	@ApiOperation(value = "면접관 정보 삭제", notes = "선택관 면접관의 정보를 삭제한다.", response = String.class)
	@DeleteMapping("/delete/{raterNo}")
	public ResponseEntity<Map<String, Object>> deleteRater(@PathVariable int raterNo) {
		logger.debug("deleteAllRater - 호출");
		Map<String, Object> resultMap = new HashMap<>();
		try{
			interviewService.deleteRater(raterNo);
			resultMap.put("message", HttpStatus.OK);
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
		}
		return new ResponseEntity<>(resultMap, HttpStatus.OK);
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
