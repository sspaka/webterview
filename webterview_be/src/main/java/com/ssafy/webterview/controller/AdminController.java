package com.ssafy.webterview.controller;

import com.ssafy.webterview.dto.ApplicantDto;
import com.ssafy.webterview.dto.GroupDto;
import com.ssafy.webterview.dto.RaterDto;
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
//	//그룹 링크 생성 -> 정말 어케 할지 모르겠기에 일단 대충 해놓음..
//	@ApiOperation(value = "그룹 링크 생성", notes = "그룹+방으로 만든 코드를 통해 링크를 생성한다.", response = String.class)
//	@PostMapping("/groupLink")
//	public ResponseEntity<String> linkGroup(@RequestBody GroupDto group, HttpServletRequest request) {
//		logger.debug("linkGroup - 호출");
//		//adminService.linkGroup(group);
//
//		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
//	}

	//방 생성
	@ApiOperation(value = "방 생성", notes = "관리자는 방을 생성한다.", response = String.class)
	@PostMapping("/createRoom")
	public ResponseEntity<Map<String, Object>> createRoom(@RequestBody Map<String, Integer> res, HttpServletRequest request) {
		logger.debug("createRoom - 호출");
		Map<String, Object> resultMap = new HashMap<>();

		try{
			int n = res.get("num");
			adminService.createRoom(n, res.get("groupNo"));

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

	//방 코드 암호화 후 이메일 보내기
	@ApiOperation(value = "방 들어가기", notes = "면접관(지원자)이 방을 들어간다", response = String.class)
	@PostMapping("/goRoom")
	public ResponseEntity<Map<String,Object>> setRoom(@RequestBody Map<String, Object> map) {
		logger.debug("goRoom - 호출");
		Map<String,Object> resultMap = new HashMap<>();

		try{
			List<String> emailList = (List<String>)map.get("email");
			int person = (Integer) map.get("person");
			String dept = userService.userInfo((String)map.get("userEmail")).getUserDept();
			GroupDto group = adminService.readGroup(userService.userInfo((String)map.get("userEmail")).getUserNo());
			String start = group.getGroupStart(); //면접방 시작
			String code = null;

			if(person == 1){ // 면접관
				for(int i=0;i<emailList.size();i++){
					RaterDto raterDto = interviewService.detailRater2(emailList.get(i));
					int roomNo = raterDto.getRoomNo();
					code = adminService.detailRoom(roomNo).getRoomCode();
					mailService.sendMail(person, code, emailList.get(i), dept, start);
				}
			}
			else if(person == 2){ // 지원자
				for(int i=0;i<emailList.size();i++){
					ApplicantDto applicantDto = interviewService.getApplicant(group.getGroupNo(), emailList.get(i));
					int roomNo = applicantDto.getRoomNo();
					code = adminService.detailRoom(roomNo).getRoomCode();
					mailService.sendMail(person, code, emailList.get(i), dept, start);
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
	@GetMapping("/decrypt/{code}")
	public ResponseEntity<Map<String,Object>> decrypt(@PathVariable String code) {
		logger.debug("decrypt - 호출");
		Map<String,Object> resultMap = new HashMap<>();

		try{
			String decode = adminService.decrypt(code);
			resultMap.put("decode", decode.substring(0,5));
			resultMap.put("roomNo", decode.substring(5));
			resultMap.put("message", SUCCESS);
		} catch (Exception e){
			resultMap.put("message",e.getMessage());
		}

		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}
}
