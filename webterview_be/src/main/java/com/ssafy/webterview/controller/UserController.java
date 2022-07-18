package com.ssafy.webterview.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import com.ssafy.webterview.dto.User;
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

	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) User userDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			User loginUser = userService.login(userDto);
			if (loginUser != null) {
				String token = jwtService.create("useremail", loginUser.getUseremail(), "access-token");// key, data, subject
				logger.debug("로그인 토큰정보 : {}", token);
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{useremail}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("useremail") @ApiParam(value = "인증할 회원의 이메일.", required = true) String useremail,
			HttpServletRequest request) {
//		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				logger.info(useremail);
				User userDto = userService.userInfo(useremail);
				resultMap.put("userInfo", userDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "이메일 중복확인", response = Map.class)
	@PostMapping("/overlap")
	public ResponseEntity<Map<String, Object>> overlap(@RequestBody String useremail) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;

		try {
			if(userService.mailOverlap(useremail) == null) {
				resultMap.put("message", SUCCESS);
//				status = HttpStatus.ACCEPTED;
			}
			else {
				logger.error("이메일 중복 : {}");
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("이메일 확인 실패 : {}", e);
			resultMap.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "회원가입", response = Map.class)
	@PostMapping("/register")
	public ResponseEntity<Map<String, Object>> register(@RequestBody User userDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		
		if(!userDto.isUseryn()) {
			logger.error("회원가입 실패 : {}");
			resultMap.put("message", FAIL);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		try {
			if(userService.register(userDto) != 0) {
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

	// 회원정보수정//modify
	@PutMapping("/modify")
	public ResponseEntity<Map<String, Object>> modify(@RequestBody User userDto, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;

		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
				userService.modify(userDto);
				resultMap.put("userInfo", userService.userInfo(userDto.getUseremail()));
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보수정 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// 회원탈퇴//delete
	@DeleteMapping("/delete/{useremail}")
	public ResponseEntity<Map<String, Object>> delete(@PathVariable("useremail") String useremail, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
				userService.delete(useremail);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("회원탈퇴 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@PostMapping("/check")
	public ResponseEntity<Map<String, Object>> checkUser(@RequestBody User userDto, HttpServletRequest request) {
		//memberService.login 사용하는데 토큰 체크 추가
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
				if (userService.login(userDto) != null) {
					resultMap.put("message", SUCCESS);
				} else {
					resultMap.put("message", FAIL);
				}
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("불일치 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@GetMapping("/emailcheck/{email}")
	public ResponseEntity<Map<String, Object>> idCheck(@PathVariable("email") String userEmail) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			if(userService.userInfo(userEmail) == null) {
				//해당 아이디를 사용한 객체가 없어야 가입가능
				resultMap.put("message", SUCCESS);
			}else {
				resultMap.put("message",FAIL);
			}
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "이메일 인증코드 전송", notes = "전송한 인증코드를 반환한다.", response = Map.class)
	@PostMapping("/sendmail")
	public ResponseEntity<Map<String, Object>> sendMail(@RequestBody Map<String, String> map) { 
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		
		String code = mailService.sendMail(map.get("type"), map.get("email"));
		if(code.equals("error")) {
			resultMap.put("message",FAIL);
			status = HttpStatus.ACCEPTED;
		}else {
			resultMap.put("message", SUCCESS);
			resultMap.put("code", code);
			status = HttpStatus.ACCEPTED;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	
	//이메일(아이디) 찾기
		@ApiOperation(value = "이메일 찾기", notes = "이름과 전화번호에 맞는 이메일을 반환한다.", response = Map.class)
		@PostMapping("/findMail")
		public ResponseEntity<Map<String, Object>> findEmail(@RequestBody Map<String, String> map, HttpServletRequest request)
				throws Exception {
			Map<String, Object> resultMap = new HashMap<>();
			HttpStatus status = HttpStatus.ACCEPTED;
	
			
			try {
				User find = userService.findEmail(map.get("name"),map.get("phone"));
				if(find != null){
					resultMap.put("userEmail", find.getUseremail());
					resultMap.put("message", SUCCESS);
					status = HttpStatus.ACCEPTED;
				}
				else {
					resultMap.put("message", "해당하는 정보의 이메일이 존재하지 않습니다.");
					status = HttpStatus.ACCEPTED;
				}
			} catch (Exception e) {
				logger.error("정보수정 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
	
			return new ResponseEntity<Map<String, Object>>(resultMap, status);
		}
}
