package com.ssafy.webterview.service;

import com.ssafy.webterview.dto.UserDto;

public interface UserService {

	public UserDto login(UserDto userDto) throws Exception;

	public UserDto userInfo(String userEmail) throws Exception;

	public UserDto register(UserDto userDto) throws Exception;

	public UserDto modify(UserDto userDto) throws Exception;
//
	public void delete(String userEmail) throws Exception;
//	
	public UserDto mailOverlap(String userEmail) throws Exception;
//	
	public UserDto findEmail(String name, String phone) throws Exception;
//	
	public boolean matchPw(String email, String inputPw) throws Exception;
}