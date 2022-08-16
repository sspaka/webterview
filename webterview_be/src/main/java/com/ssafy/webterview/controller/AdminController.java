package com.ssafy.webterview.controller;

import com.ssafy.webterview.dto.ApplicantDto;
import com.ssafy.webterview.dto.GroupDto;
import com.ssafy.webterview.dto.RoomDto;
import com.ssafy.webterview.service.AdminService;
import com.ssafy.webterview.service.InterviewService;
import com.ssafy.webterview.service.MailService;
import com.ssafy.webterview.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	private AdminService adminService;
	private MailService mailService;
	private UserService userService;
	private InterviewService interviewService;
	@Autowired
	public AdminController(InterviewService interviewService, AdminService adminService, MailService mailService, UserService userService){
		this.userService = userService;
		this.adminService = adminService;
		this.mailService = mailService;
		this.interviewService = interviewService;
	}

	// 그룹 생성
	@ApiOperation(value = "그룹 생성", notes = "면접 시작날짜와 종료날짜, 블라인드 유무 정보를 저장한 그룹을 생성한다", response = Map.class)
	@PostMapping("/createGroup")
	public ResponseEntity<Map<String,Object>> createGroup(@RequestBody GroupDto groupDto) {
		Map<String, Object> resultMap = new HashMap<>();

		try {
			resultMap.put("group",adminService.createGroup(groupDto));
			resultMap.put("message",SUCCESS);
		} catch (Exception e) {
			resultMap.put("message",e.getMessage());
		}
		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

	//면접 블라인드 여부 수정, 면접 기간 수정 -> dto로 받아서 함수 하나로 두 기능이 가능하도록
	@ApiOperation(value = "면접 정보 수정", notes = "버튼을 누를 시 블라인드 여부가 스위치된다/면접 기간을 수정할 수 있다.", response = String.class)
	@PutMapping("/modifyGroup")
	public ResponseEntity<Map<String,Object>> modifyGroup(@RequestBody GroupDto group, HttpServletRequest request) {
		logger.debug("modifyGroup - 호출");
		Map<String, Object> resultMap = new HashMap<>();
		try{
			resultMap.put("group", adminService.modifyGroup(group));
			resultMap.put("message", SUCCESS);
		} catch (Exception e){
			resultMap.put("message",FAIL);
		}
		return new ResponseEntity<>(resultMap, HttpStatus.OK);
		//return new ResponseEntity<GroupDto>(adminService.modifyGroup(group), HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "현재 열려있는 그룹 보기", notes = "그룹이 열려있다면 해당 그룹의 정보를 반환한다.", response = Map.class)
	@GetMapping("/group/{userNo}")
	public ResponseEntity<Map<String,Object>> readGroup(@PathVariable int userNo) {
		Map<String,Object> resultMap = new HashMap<>();
		try {
			GroupDto groupDto = adminService.readGroup(userNo);
			if(groupDto != null) {
				resultMap.put("group", groupDto);
				resultMap.put("message", SUCCESS);
			}
			else{
				resultMap.put("열려있는 그룹이 없습니다!", SUCCESS);
			}
		} catch (Exception e) {
			resultMap.put("error",e.getMessage());
		}
		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

	//그룹 삭제
	@ApiOperation(value = "그룹 삭제", notes = "관리자가 생성된 그룹을 삭제한다.", response = String.class)
	@DeleteMapping("/{groupNo}")
	public ResponseEntity<String> deleteGroup(@PathVariable int groupNo) {
		logger.debug("deleteGroup - 호출");

		try{
			adminService.deleteGroup(groupNo);
			return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
		}
		catch (Exception e){
			return new ResponseEntity<>(FAIL, HttpStatus.NO_CONTENT);
		}
	}

	@ApiOperation(value = "그룹 유무 확인", notes = "해당 관리자가 연 그룹이 있는지 확인한다", response = String.class)
	@GetMapping("/groupCheck/{userNo}")
	public ResponseEntity<Map<String,Object>> checkGroup(@PathVariable int userNo) {
		logger.debug("deleteGroup - 호출");
		Map<String,Object> resultMap = new HashMap<>();
		try{
			boolean b = adminService.checkGroup(userNo);

			if(b == true)
				resultMap.put("열려있는 그룹이 없습니다!", SUCCESS);
			else
				resultMap.put("열려있는 그룹이 있습니다!", SUCCESS);
		}
		catch (Exception e){
			resultMap.put("error",e.getMessage());
		}

		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

	//방 생성
	@ApiOperation(value = "방 생성", notes = "관리자는 방을 생성한다.", response = String.class)
	@PostMapping("/createRoom")
	public ResponseEntity<Map<String, Object>> createRoom(@RequestBody Map<String, Integer> res, HttpServletRequest request) {
		logger.debug("createRoom - 호출");
		Map<String, Object> resultMap = new HashMap<>();

		try{
			int n = res.get("num");
			resultMap.put("list",adminService.createRoom(n, res.get("groupNo")));

			resultMap.put("message",SUCCESS);
		} catch (Exception e){
			resultMap.put("message",e.getMessage());
		}
		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

	@ApiOperation(value = "그룹 안에 있는 방 보기", notes = "그룹 안에 있는 방들의 정보(리스트)를 반환한다.", response = Map.class)
	@GetMapping("/roomList/{groupNo}")
	public ResponseEntity<Map<String,Object>> listRoom(@PathVariable int groupNo) {
		Map<String,Object> resultMap = new HashMap<>();
		try {
			List<RoomDto> room = adminService.listRoom(groupNo);
			if(room.get(0) != null) {
				resultMap.put("roomList", room);
				resultMap.put("message", SUCCESS);
			}
			else{
				resultMap.put("열려있는 방이 없습니다!", SUCCESS);
			}
		} catch (Exception e) {
			resultMap.put("error",e.getMessage());
		}
		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

	@ApiOperation(value = "방 상세정보 보기", notes = "방안의 면접관들을 반환한다", response = Map.class)
	@GetMapping("/roomDetail/{roomNo}")
	public ResponseEntity<Map<String,Object>> detailBoard(@PathVariable int roomNo) {
		Map<String,Object> resultMap = new HashMap<>();
		try {
			resultMap.put("raterList",adminService.readRoom(roomNo));
			resultMap.put("message",SUCCESS);
		} catch (Exception e) {
			resultMap.put("message",e.getMessage());
		}
		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

	//방 삭제
	@ApiOperation(value = "방 삭제", notes = "관리자가 방을 삭제한다.", response = String.class)
	@DeleteMapping("/room/{roomNo}")
	public ResponseEntity<String> deleteRoom(@PathVariable int roomNo) {
		logger.debug("deleteRoom - 호출");
		try{
			adminService.deleteRoom(roomNo);
			return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity<>(FAIL, HttpStatus.ACCEPTED);
		}
	}

	//인덱스로 방 찾기
	@ApiOperation(value = "그룹 번호와 인덱스로 방 객체 찾기", notes = "방 정보를 반환한다", response = Map.class)
	@GetMapping("/room/findPk")
	public ResponseEntity<Map<String,Object>> findRoomByIdx(@RequestParam int idx, @RequestParam int groupNo) {
		Map<String,Object> resultMap = new HashMap<>();
		HttpStatus httpStatus = HttpStatus.ACCEPTED;
		try {
			resultMap.put("room",adminService.findRoomPkByIdx(groupNo, idx));
			resultMap.put("message",SUCCESS);
			httpStatus = HttpStatus.OK;
		} catch (Exception e) {
			resultMap.put("message",FAIL);
			resultMap.put("error",e.getMessage());
		}
		return new ResponseEntity<>(resultMap, httpStatus);
	}

	//방 코드 암호화 후 이메일 보내기
	@ApiOperation(value = "방 들어가기", notes = "면접관(지원자)이 방을 들어간다", response = String.class)
	@PostMapping("/goRoom")
	public ResponseEntity<Map<String,Object>> setRoom(@RequestBody Map<String, Object> map) {
		logger.debug("goRoom - 호출");
		Map<String,Object> resultMap = new HashMap<>();

		try{
			ArrayList<Map<String,String>> maplist = (ArrayList<Map<String,String>>)map.get("list");
			int person = (Integer) map.get("person");
			String dept = userService.userInfo((String)map.get("userEmail")).getUserDept();
			GroupDto group = adminService.readGroup(userService.userInfo((String)map.get("userEmail")).getUserNo());
			Instant start = group.getGroupStartDate(); //면접방 시작
			String code = null;

			resultMap.put("message", person);

			if(person == 1){ // 면접관
				for(int i=0;i<maplist.size();i++){
					int roomNo = Integer.valueOf(maplist.get(i).get("roomNo"));
					String email = maplist.get(i).get("email");

					//RaterDto raterDto = interviewService.detailRater2(email, roomNo);

					code = adminService.encrypt(adminService.detailRoom(roomNo).getRoomCode()+roomNo);
					mailService.sendMail(person, URLEncoder.encode(code,"UTF-8"), email, dept, start);
				}
			}
			else if(person == 2){ // 지원자
				resultMap.put("message", dept);
				for(int i=0;i<maplist.size();i++){
					int roomNo = Integer.valueOf(maplist.get(i).get("roomNo"));
					resultMap.put("message", roomNo);
					String email = maplist.get(i).get("email");
					resultMap.put("message", email);
					ApplicantDto applicantDto = interviewService.getApplicant(group.getGroupNo(), email);
					start = applicantDto.getApplicantDate();
					resultMap.put("message", start);
					
					code = adminService.encrypt(adminService.detailRoom(roomNo).getRoomCode()+roomNo);

					resultMap.put("message", URLEncoder.encode(code,"UTF-8"));
					mailService.sendMail(person, URLEncoder.encode(code,"UTF-8"), email, dept, start);
				}
			}

			resultMap.put("message", SUCCESS);
			//return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
		} catch (Exception e){
			resultMap.put("message",e.getMessage());
		}

		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

	@ApiOperation(value = "코드 복호화하기", notes = "암호화된 방코드를 복호화해서 리턴한다", response = String.class)
	@GetMapping("/decrypt")
	public ResponseEntity<Map<String,Object>> decrypt(@RequestParam String code) throws UnsupportedEncodingException {
		logger.debug("decrypt - 호출");
		Map<String,Object> resultMap = new HashMap<>();

		try{
			String decode = adminService.decrypt(code);
			resultMap.put("roomCode", decode.substring(0,5));
			resultMap.put("roomNo", decode.substring(5));
			resultMap.put("message", SUCCESS);
		} catch (Exception e){
			resultMap.put("message",e.getMessage());
		}

		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}
}
