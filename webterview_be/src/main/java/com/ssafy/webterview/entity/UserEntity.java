package com.ssafy.webterview.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.ssafy.webterview.dto.User;

import lombok.Data;

@Entity
@Table(name="user")
@Data
@DynamicInsert
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="UserNo")
	private int userNo;	
	
	@Column(name="UserEmail")
	private String userEmail;
	
	@Column(name="UserName")
	private String userName;
	
	@Column(name="UserPw")
	private String userPw;
	
	@Column(name="UserPhone")
	private String userPhone;
	
	@Column(name="UserDept")
	private String userDept;

	public User toUserDto() {
		User userDto = new User();
		
		userDto.setUserNo(this.userNo);
		userDto.setUserEmail(this.userEmail);
		userDto.setUserName(this.userName);
		userDto.setUserPw(this.userPw);
		userDto.setUserPhone(this.userPhone);
		userDto.setUserDept(this.userDept);
		
		return userDto;
	}
}
