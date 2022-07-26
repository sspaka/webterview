package com.ssafy.webterview.controller;

import java.util.HashMap;
import java.util.Map;

import com.ssafy.webterview.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.webterview.service.JwtServiceImpl;
import com.ssafy.webterview.service.MailService;
import com.ssafy.webterview.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("UserController V1")
@RestController
@RequestMapping("/user")
public class UserController {

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private JwtServiceImpl jwtService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private MailService mailService;

//	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
//	@PostMapping("/login")
//	public ResponseEntity<Map<String, Object>> login(
//			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) User userDto) {
//		Map<String, Object> resultMap = new HashMap<>();
//		HttpStatus status = null;
//		try {
//			User loginUser = userService.login(userDto);
//			if (loginUser != null) {
//				String token = jwtService.create("useremail", loginUser.getUserEmail(), "access-token");// key, data, subject
//				logger.debug("로그인 토큰정보 : {}", token);
//				resultMap.put("access-token", token);
//				resultMap.put("message", SUCCESS);
//				status = HttpStatus.ACCEPTED;
//			} else {
//				resultMap.put("message", FAIL);
//				status = HttpStatus.ACCEPTED;
//			}
//		} catch (Exception e) {
//			logger.error("로그인 실패 : {}", e);
//			resultMap.put("message", e.getMessage());
//			status = HttpStatus.INTERNAL_SERVER_ERROR;
//		}
//		return new ResponseEntity<Map<String, Object>>(resultMap, status);
//	}
//
//	@ApiOperation(value = "회원 정보 보기", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
//	@GetMapping("/info/{useremail}")
//	public ResponseEntity<Map<String, Object>> getInfo(
//			@PathVariable("useremail") @ApiParam(value = "인증할 회원의 이메일.", required = true) String useremail,
//			HttpServletRequest request) {
////		logger.debug("userid : {} ", userid);
//		Map<String, Object> resultMap = new HashMap<>();
//		HttpStatus status = HttpStatus.ACCEPTED;
//		if (jwtService.isUsable(request.getHeader("access-token"))) {
//			logger.info("사용 가능한 토큰!!!");
//			try {
////				로그인 사용자 정보.
//				logger.info(useremail);
//				User userDto = userService.userInfo(useremail);
//				resultMap.put("userInfo", userDto);
//				resultMap.put("message", SUCCESS);
//				status = HttpStatus.ACCEPTED;
//			} catch (Exception e) {
//				logger.error("정보조회 실패 : {}", e);
//				resultMap.put("message", e.getMessage());
//				status = HttpStatus.INTERNAL_SERVER_ERROR;
//			}
//		} else {
//			logger.error("사용 불가능 토큰!!!");
//			resultMap.put("message", FAIL);
//			status = HttpStatus.ACCEPTED;
//		}
//		return new ResponseEntity<Map<String, Object>>(resultMap, status);
//	}
//
//	@ApiOperation(value = "이메일 중복확인", notes="이메일 중복확인 후 성공여부를 반환한다.", response = Map.class)
//	@PostMapping("/overlap")
//	public ResponseEntity<Map<String, Object>> overlap(@RequestBody @ApiParam(value="입력한 이메일", required=true) String useremail) {
//		Map<String, Object> resultMap = new HashMap<>();
//		HttpStatus status = HttpStatus.ACCEPTED;
//
//		try {
//			if(userService.mailOverlap(useremail) == null) {
//				resultMap.put("message", SUCCESS);
////				status = HttpStatus.ACCEPTED;
//			}
//			else {
//				logger.error("이메일 중복 : {}");
//				resultMap.put("message", FAIL);
//				status = HttpStatus.ACCEPTED;
//			}
//		} catch (Exception e) {
//			logger.error("이메일 확인 실패 : {}", e);
//			resultMap.put("message", FAIL);
//			status = HttpStatus.INTERNAL_SERVER_ERROR;
//		}
//
//		return new ResponseEntity<Map<String, Object>>(resultMap, status);
//	}
//	
	@ApiOperation(value = "회원가입", notes = "회원가입 후 성공여부를 반환한다.", response = Map.class)
	@PostMapping("/register")
	public ResponseEntity<Map<String, Object>> register(@RequestBody @ApiParam(value="저장할 User객체", required=true) UserDto userDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		
		if(!userDto.isUserYn()) {
			logger.error("회원가입 실패 : {}");
			resultMap.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		try {
			if(userService.register(userDto) != null) {
				resultMap.put("message", SUCCESS);
//				status = HttpStatus.ACCEPTED;
			}
			else {
				logger.error("회원가입 실패 : {}");
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("회원가입 실패 : {}", e);
			resultMap.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
//
//	// 회원정보수정//modify
//	@ApiOperation(value = "회원 정보 수정", notes = "회원 정보 수정 후 성공여부와 User 객체를 반환한다. ", response = Map.class)
//	@PutMapping("/modify")
//	public ResponseEntity<Map<String, Object>> modify(@RequestBody @ApiParam(value="수정할 정보를 담은 User객체", required=true) User userDto, HttpServletRequest request)
//			throws Exception {
//		Map<String, Object> resultMap = new HashMap<>();
//		HttpStatus status = HttpStatus.ACCEPTED;
//
//		if (jwtService.isUsable(request.getHeader("access-token"))) {
//			logger.info("사용 가능한 토큰!!!");
//			try {
//				userService.modify(userDto);
//				resultMap.put("userInfo", userService.userInfo(userDto.getUserEmail()));
//				resultMap.put("message", SUCCESS);
//				status = HttpStatus.ACCEPTED;
//			} catch (Exception e) {
//				logger.error("정보수정 실패 : {}", e);
//				resultMap.put("message", e.getMessage());
//				status = HttpStatus.INTERNAL_SERVER_ERROR;
//			}
//		} else {
//			logger.error("사용 불가능 토큰!!!");
//			resultMap.put("message", FAIL);
//			status = HttpStatus.ACCEPTED;
//		}
//
//		return new ResponseEntity<Map<String, Object>>(resultMap, status);
//	}
//
//	// 회원탈퇴//delete
//	@ApiOperation(value = "회원 탈퇴", notes = "회원탈퇴 후 성공여부를 반환한다.", response = Map.class)
//	@DeleteMapping("/delete/{useremail}")
//	public ResponseEntity<Map<String, Object>> delete(@PathVariable("useremail")  @ApiParam(value="탈퇴할 회원 이메일", required=true) String useremail, HttpServletRequest request)
//			throws Exception {
//		Map<String, Object> resultMap = new HashMap<>();
//		HttpStatus status = HttpStatus.ACCEPTED;
//		if (jwtService.isUsable(request.getHeader("access-token"))) {
//			logger.info("사용 가능한 토큰!!!");
//			try {
//				userService.delete(useremail);
//				resultMap.put("message", SUCCESS);
//				status = HttpStatus.ACCEPTED;
//			} catch (Exception e) {
//				logger.error("회원탈퇴 실패 : {}", e);
//				resultMap.put("message", e.getMessage());
//				status = HttpStatus.INTERNAL_SERVER_ERROR;
//			}
//		} else {
//			logger.error("사용 불가능 토큰!!!");
//			resultMap.put("message", FAIL);
//			status = HttpStatus.ACCEPTED;
//		}
//
//		return new ResponseEntity<Map<String, Object>>(resultMap, status);
//	}
//	
//	@ApiOperation(value = "이메일 인증코드 전송", notes = "전송한 인증코드를 반환한다.", response = Map.class)
//	@PostMapping("/sendmail")
//	public ResponseEntity<Map<String, Object>> sendMail(@RequestBody @ApiParam(value="email과 type 정보",required=true) Map<String, String> map) { 
//		Map<String, Object> resultMap = new HashMap<>();
//		HttpStatus status = null;
//		
//		String code = mailService.sendMail(map.get("type"), map.get("email"));
//		if(code.equals("error")) {
//			resultMap.put("message",FAIL);
//			status = HttpStatus.ACCEPTED;
//		}else {
//			resultMap.put("message", SUCCESS);
//			resultMap.put("code", code);
//			status = HttpStatus.ACCEPTED;
//		}
//		
//		return new ResponseEntity<Map<String, Object>>(resultMap, status);
//	}
//
//	
//	//이메일(아이디) 찾기
//	@ApiOperation(value = "이메일 찾기", notes = "이름과 전화번호에 맞는 이메일을 반환한다.", response = Map.class)
//	@PostMapping("/findMail")
//	public ResponseEntity<Map<String, Object>> findEmail(@RequestBody @ApiParam(value="name과 phone 정보", required=true) Map<String, String> map, HttpServletRequest request)
//			throws Exception {
//		Map<String, Object> resultMap = new HashMap<>();
//		HttpStatus status = HttpStatus.ACCEPTED;
//		
//		try {
//			User find = userService.findEmail(map.get("name"),map.get("phone"));
//			if(find != null){
//				resultMap.put("userEmail", find.getUserEmail());
//				resultMap.put("message", SUCCESS);
//				status = HttpStatus.ACCEPTED;
//			}
//			else {
//				resultMap.put("message", "해당하는 정보의 이메일이 존재하지 않습니다.");
//				status = HttpStatus.ACCEPTED;
//			}
//		} catch (Exception e) {
//			logger.error("정보수정 실패 : {}", e);
//			resultMap.put("message", e.getMessage());
//			status = HttpStatus.INTERNAL_SERVER_ERROR;
//		}
//
//		return new ResponseEntity<Map<String, Object>>(resultMap, status);
//	}
//	
//	//새 비밀번호 저장
//	@ApiOperation(value = "새 비밀번호 저장", notes = "새로운 비밀번호를 저장한다.", response = Map.class)
//	@PutMapping("/saveNewPw")
//	public ResponseEntity<Map<String, Object>> saveNewPw(@RequestBody @ApiParam(value="새로운 비밀번호가 저장된 User객체",required=true) User userDto, HttpServletRequest request)
//			throws Exception {
//		Map<String, Object> resultMap = new HashMap<>();
//		HttpStatus status = HttpStatus.ACCEPTED;
//		
//		if(!userDto.isUserYn()) {
//			logger.error("새 비밀번호 저장 실패 : {}");
//			resultMap.put("message", FAIL);
//			status = HttpStatus.INTERNAL_SERVER_ERROR;
//		}	
//		
//		try {
//			userService.modify(userDto);
//			resultMap.put("message", SUCCESS);
//		}catch(Exception e) {
//			logger.error("새 비밀번호 저장 실패 : {}", e);
//			resultMap.put("message", e.getMessage());
//			status = HttpStatus.INTERNAL_SERVER_ERROR;
//		}
//		
//		return new ResponseEntity<Map<String, Object>>(resultMap, status);
//	} 
//
//	//현재 비밀번호와 일치 확인
//	@ApiOperation(value = "현재 비밀번호와 일치 확인", notes = "사용자가 입력한 비밀번호가 저장된 비밀번호와 일치하는지 확인한다.", response = Map.class)
//	@PostMapping("/matchPw")
//	public ResponseEntity<Map<String, Object>> matchPw(@RequestBody @ApiParam(value="email, pw 정보", required=true) Map<String, String> map, HttpServletRequest request)
//			throws Exception {
//		Map<String, Object> resultMap = new HashMap<>();
//		HttpStatus status = HttpStatus.ACCEPTED;
//		
//		if(userService.matchPw(map.get("email"), map.get("pw"))) {
//			resultMap.put("message", SUCCESS);
//		}else {
//			resultMap.put("message", FAIL);
//		}
//
//		return new ResponseEntity<Map<String, Object>>(resultMap, status);
//	} 	
}
