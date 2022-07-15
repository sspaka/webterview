package com.ssafy.webterview.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "User : 회원정보", description = "회원의 상세 정보를 나타낸다.")
public class User {

	@ApiModelProperty(value = "회원 이메일")
	private String useremail;
	@ApiModelProperty(value = "회원 역할")
	private String userrole;
	@ApiModelProperty(value = "회원 이름")
	private String username;
	@ApiModelProperty(value = "회원 비밀번호")
	private String userpw;
	@ApiModelProperty(value = "회원 전화번호")
	private String userphone;
	@ApiModelProperty(value = "회원 부서")
	private String userdept;
	@ApiModelProperty(value = "회원 이메일 인증 여부")
	private boolean useryn;
}
