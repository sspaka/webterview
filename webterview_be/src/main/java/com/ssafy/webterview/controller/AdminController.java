package com.ssafy.webterview.controller;

import javax.servlet.http.HttpServletRequest;

import com.ssafy.webterview.dto.BoardDto;
import com.ssafy.webterview.dto.GroupDto;
import com.ssafy.webterview.dto.RaterDto;
import com.ssafy.webterview.dto.RoomDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.webterview.service.AdminService;
import com.ssafy.webterview.service.JwtServiceImpl;

import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private AdminService adminService;

	@Autowired
	private JwtServiceImpl jwtService;

	//면접관 일괄 추가

	//면접관 개별 추가
//	@ApiOperation(value = "면접관 개별 추가", notes = "일괄 등록 외의 면접관 정보를 추가한다.일괄 등록과는 다르게 한 사람 씩 가능하다.", response = String.class)
//	@PostMapping("/raterOne")
//	public ResponseEntity<String> writeRaterOne(@RequestBody RaterDto rater, HttpServletRequest request) {
//		logger.debug("writeRaterOne - 호출");
//
//		if (jwtService.isUsable(request.getHeader("access-token"))) {
//			logger.info("사용 가능한 토큰!!!");
//			if (adminService.insertRaterOne(rater)) {
//				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//			}else {
//				logger.error("면접관 등록 실패");
//			}
//		} else {
//			logger.error("사용 불가능 토큰!!!");
//		}
//
//		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
//	}
//
//	//면접관 리스트 보기
//	@ApiOperation(value = "면접관 리스트 보기", notes = "등록된 면접관들의 리스트를 보여준다.", response = String.class)
//	@PostMapping("/raterList")
//	public ResponseEntity<String> retrieveRater(@RequestBody RaterDto rater,HttpServletRequest request) {
//		logger.debug("retrieveRater - 호출");
//		//adminService.listRater();
//
//		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
//	}
//
//	//면접관 정보 상세 보기
//	@ApiOperation(value = "면접관 정보 상세 보기", notes = "선택한 면접관에 대한 정보를 반환한다.", response = String.class)
//	@GetMapping("/{raterNo}")
//	public ResponseEntity<RaterDto> detailRater(@PathVariable int raterNo) {
//		logger.debug("detailRater - 호출");
//
//		return new ResponseEntity<RaterDto>(adminService.detailRater(raterNo), HttpStatus.NO_CONTENT);
//	}
//
//	//면접관 정보 수정
//	@ApiOperation(value = "면접관 정보 수정", notes = "등록된 면접관들의 정보를 수정한다.", response = String.class)
//	@PutMapping("/{raterNo}")
//	public ResponseEntity<RaterDto> modifyRater(@RequestBody RaterDto rater,HttpServletRequest request) {
//		logger.debug("modifyRater - 호출");
//
//
//		return new ResponseEntity<RaterDto>(adminService.modifyRater(rater), HttpStatus.NO_CONTENT);
//	}
//
	// 그룹 생성
	@ApiOperation(value = "그룹 생성", notes = "면접 시작날짜와 종료날짜, 블라인드 유무 정보를 저장한 그룹을 생성한다", response = Map.class)
	@PostMapping("/createGroup")
	public ResponseEntity<Map<String,Object>> createGroup(@RequestBody GroupDto groupDto) {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			resultMap.put("board",adminService.createGroup(groupDto));
			resultMap.put("message",SUCCESS);
		} catch (Exception e) {
			resultMap.put("message",FAIL);
		}
		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

	//면접 블라인드 여부 수정, 면접 기간 수정 -> dto로 받아서 함수 하나로 두 기능이 가능하도록
	@ApiOperation(value = "면접 정보 수정", notes = "버튼을 누를 시 블라인드 여부가 스위치된다/면접 기간을 수정할 수 있다.", response = String.class)
	@PutMapping("/modifyGroup")
	public ResponseEntity<GroupDto> modifyGroup(@RequestBody GroupDto group, HttpServletRequest request) {
		logger.debug("blindRater - 호출");

		return null;
		//return new ResponseEntity<GroupDto>(adminService.modifyGroup(group), HttpStatus.NO_CONTENT);
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
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		}
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
	public ResponseEntity<String> createRoom(@RequestBody RoomDto room, HttpServletRequest request) {
		logger.debug("linkGroup - 호출");
		//adminService.createRoom(room);

		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}


	//방 삭제
	@ApiOperation(value = "방 삭제", notes = "관리자가 방을 삭제한다.", response = String.class)
	@DeleteMapping("/{roomNo}")
	public ResponseEntity<String> deleteRoom(@PathVariable int roomNo, HttpServletRequest request) {
		logger.debug("deleteRoom - 호출");
		//adminService.deleteRoom(roomNo);

		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

}
