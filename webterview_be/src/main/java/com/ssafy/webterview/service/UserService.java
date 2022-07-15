package com.ssafy.webterview.service;

import com.ssafy.webterview.dto.User;

public interface UserService {

	public User login(User userDto) throws Exception;

	public User userInfo(String useremail) throws Exception;

	public int register(User userDto) throws Exception;

	public int modify(User userDto) throws Exception;

	public int delete(String useremail) throws Exception;
	
	public User mailOverlap(String useremail) throws Exception;
}