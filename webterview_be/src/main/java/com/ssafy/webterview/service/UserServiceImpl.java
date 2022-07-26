package com.ssafy.webterview.service;

import com.ssafy.webterview.dto.UserDto;
import com.ssafy.webterview.util.DEConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.ssafy.webterview.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private DEConverter converter;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, DEConverter converter){
		this.userRepository = userRepository;
		this.converter = converter;
	}

	@Override
	public UserDto login(UserDto userDto) throws Exception {
		if(userDto.getUserEmail() == null || userDto.getUserPw() == null)
			return null;
		//userInfo에서 가져온 비밀번호(암호화됨)와 지금 입력받은 비밀번호 match 확인
		String encodePw = userRepository.findUserPwByUserEmail(userDto.getUserEmail());
		if(passwordEncoder.matches(userDto.getUserPw(),encodePw)) {
			//암호화 된 비밀번호로 pw 정보 변경 후 로그인
			userDto.setUserPw(encodePw);
			return converter.toUserDto(userRepository.findByUserEmailAndUserPw(userDto.getUserPw(),userDto.getUserEmail()));
		}else {
			return null;
		}
	}

	@Override
	public UserDto userInfo(String userEmail) throws Exception {
		UserDto userDto = converter.toUserDto(userRepository.findByUserEmail(userEmail));

		return userDto;
	}

	@Override
	public UserDto register(UserDto userDto) throws Exception {
		return converter.toUserDto(userRepository.save(converter.toUserEntity(userDto)));
	}

//	@Override
//	public int modify(User userDto) throws Exception {
//		//dto 비밀번호가 null값이 아니라면 인코딩해서 새로 저장
//		if(userDto.getUserPw() != null || !userDto.getUserPw().equals("")) userDto.setUserPw(passwordEncoder.encode(userDto.getUserPw()));
//		return userMapper.modify(userDto);
//	}
//
	@Override
	public void delete(String userEmail) throws Exception {
		userRepository.delete(userRepository.findByUserEmail(userEmail));
	}
//
//	@Override
//	public User mailOverlap(String useremail) throws Exception {
//		return userMapper.mailOverlap(useremail);
//	}
//
//	@Override
//	public User findEmail(String name, String phone) throws Exception {
//		return userMapper.findEmail(name, phone);
//	}
//
//	@Override
//	public boolean matchPw(String email, String inputPw) throws Exception {
//		return passwordEncoder.matches(inputPw, userMapper.getPw(email));
//	}
}
