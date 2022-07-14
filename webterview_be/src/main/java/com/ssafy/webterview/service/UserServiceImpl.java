package com.ssafy.webterview.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.webterview.dto.User;
import com.ssafy.webterview.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User login(User userDto) throws Exception {
		if(userDto.getUseremail() == null || userDto.getUserpassword() == null)
			return null;
		return userMapper.login(userDto);
	}

	@Override
	public User userInfo(String useremail) throws Exception {
		return userMapper.userInfo(useremail);
	}

	@Override
	public int register(User userDto) throws Exception {
		return userMapper.register(userDto);
	}

	@Override
	public int modify(User userDto) throws Exception {
		return userMapper.modify(userDto);
	}

	@Override
	public int delete(String useremail) throws Exception {
		return userMapper.delete(useremail);
	}

}
