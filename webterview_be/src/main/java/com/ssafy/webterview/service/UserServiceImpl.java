package com.ssafy.webterview.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.webterview.dto.User;
import com.ssafy.webterview.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User login(User userDto) throws Exception {
		if(userDto.getUseremail() == null || userDto.getUserpw() == null)
			return null;
		//userInfo에서 가져온 비밀번호(암호화됨)와 지금 입력받은 비밀번호 match 확인
		String encodePw = userMapper.userInfo(userDto.getUseremail()).getUserpw();
		if(passwordEncoder.matches(userDto.getUserpw(),encodePw)) {
			//암호화 된 비밀번호로 pw 정보 변경 후 로그인
			userDto.setUserpw(encodePw);
			return userMapper.login(userDto);
		}else {
			return null;
		}
	}

	@Override
	public User userInfo(String useremail) throws Exception {
		return userMapper.userInfo(useremail);
	}

	@Override
	public int register(User userDto) throws Exception {
		//비밀번호 인코딩 후 회원가입
		userDto.setUserpw(passwordEncoder.encode(userDto.getUserpw()));
		return userMapper.register(userDto);
	}

	@Override
	public int modify(User userDto) throws Exception {
		//dto 비밀번호가 null값이 아니라면 인코딩해서 새로 저장
		if(userDto.getUserpw() != null) userDto.setUserpw(passwordEncoder.encode(userDto.getUserpw()));
		return userMapper.modify(userDto);
	}

	@Override
	public int delete(String useremail) throws Exception {
		return userMapper.delete(useremail);
	}

	@Override
	public User mailOverlap(String useremail) throws Exception {
		return userMapper.mailOverlap(useremail);
	}

	@Override
	public User findEmail(String name, String phone) throws Exception {
		return userMapper.findEmail(name, phone);
	}
}
