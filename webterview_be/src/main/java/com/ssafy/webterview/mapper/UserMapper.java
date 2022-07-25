package com.ssafy.webterview.mapper;

import java.sql.SQLException;

public interface UserMapper {
	public User login(User userDto) throws SQLException;
	public User userInfo(String useremail) throws SQLException;
	public int register(User userDto) throws SQLException;
	public int modify(User userDto) throws SQLException;
	public int delete(String useremail) throws SQLException;
	public User mailOverlap(String useremail) throws SQLException;
	public User findEmail(String name, String phone) throws SQLException;
	public String getPw(String email) throws SQLException;
}