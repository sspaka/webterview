package com.ssafy.webterview.mapper;

import java.sql.SQLException;

import com.ssafy.webterview.dto.User;

public interface UserMapper {
	public User login(User userDto) throws SQLException;
	public User userInfo(String useremail) throws SQLException;
	public int register(User userDto) throws SQLException;
	public int modify(User userDto) throws SQLException;
	public int delete(String useremail) throws SQLException;
}