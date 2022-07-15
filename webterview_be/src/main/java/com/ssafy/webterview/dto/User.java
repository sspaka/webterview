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
	
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public String getUserrole() {
		return userrole;
	}
	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpw() {
		return userpw;
	}
	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}
	public String getUserphone() {
		return userphone;
	}
	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}
	public String getUserdept() {
		return userdept;
	}
	public void setUserdept(String userdept) {
		this.userdept = userdept;
	}
	public boolean getUseryn() {
		return useryn;
	}
	public void setUseryn(boolean useryn) {
		this.useryn = useryn;
	}
}
