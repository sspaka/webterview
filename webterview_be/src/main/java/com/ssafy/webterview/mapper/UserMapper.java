package com.ssafy.webterview.mapper;

import java.sql.SQLException;
import com.ssafy.webterview.dto.UserDto;

public interface UserMapper {
	public UserDto login(UserDto userDto) throws SQLException;
	public UserDto userInfo(String useremail) throws SQLException;
	public int register(UserDto userDto) throws SQLException;
	public int modify(UserDto userDto) throws SQLException;
	public int delete(String useremail) throws SQLException;
	public UserDto mailOverlap(String useremail) throws SQLException;
	public UserDto findEmail(String name, String phone) throws SQLException;
	public String getPw(String email) throws SQLException;
}