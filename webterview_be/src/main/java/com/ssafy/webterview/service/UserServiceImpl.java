package com.ssafy.webterview.service;

import com.ssafy.webterview.dto.UserDto;
import com.ssafy.webterview.entity.User;
import com.ssafy.webterview.util.DEConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.ssafy.webterview.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
		String encodePw = converter.toUserDto(userRepository.findByUserEmail(userDto.getUserEmail())).getUserPw();

		if(passwordEncoder.matches(userDto.getUserPw(),encodePw)) {
			//암호화 된 비밀번호로 pw 정보 변경 후 로그인
			userDto.setUserPw(encodePw);
			return converter.toUserDto(userRepository.findByUserPwAndUserEmail(userDto.getUserPw(),userDto.getUserEmail()));
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
		userDto.setUserPw(passwordEncoder.encode(userDto.getUserPw()));
		return converter.toUserDto(userRepository.save(converter.toUserEntity(userDto)));
	}

	@Override
	@Transactional
	public UserDto modify(UserDto userDto) throws Exception {
		//이메일 변경 안되고 unique 하기때문에 이메일로 찾아서 가져옴
		User user = userRepository.findByUserEmail(userDto.getUserEmail());
		if(userDto.getUserPw() != null && !userDto.getUserPw().equals(""))
			user.setUserPw(passwordEncoder.encode(userDto.getUserPw()));

		if(userDto.getUserName() != null && !userDto.getUserName().equals(""))
			user.setUserName(userDto.getUserName());

		if(userDto.getUserPhone() != null && !userDto.getUserPhone().equals(""))
			user.setUserPhone(userDto.getUserPhone());


		return converter.toUserDto(user);
	}

	@Override
	public void delete(String userEmail) throws Exception {
		userRepository.delete(userRepository.findByUserEmail(userEmail));
	}

	@Override
	public UserDto mailOverlap(String userEmail) throws Exception {
		User check = userRepository.findByUserEmail(userEmail);
		UserDto userDto = new UserDto();
		if(check == null)
			return null;
		else
			userDto = converter.toUserDto(check);

		return userDto;
	}
//
	@Override
	public UserDto findEmail(String name, String phone) throws Exception {
		List<User> check = userRepository.findByUserPhoneAndUserName(phone, name);

		if(check.isEmpty())
			return null;
		else{
			return converter.toUserDto(check.get(0));
		}
	}
//
	@Override
	public boolean matchPw(String email, String inputPw) throws Exception {
		User user = userRepository.findByUserEmail(email);
		UserDto userDto = converter.toUserDto(user);

		return passwordEncoder.matches(inputPw, userDto.getUserPw());
	}

}
