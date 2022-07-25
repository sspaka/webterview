package com.ssafy.webterview.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "User : 회원정보", description = "회원의 상세 정보를 나타낸다.")
public class UserDto implements Serializable {
    @ApiModelProperty(value = "회원 번호")
    private int userNo;
    @ApiModelProperty(value = "회원 역할")
    private int userRole;
    @ApiModelProperty(value = "회원 이메일")
    private String userEmail;
    @ApiModelProperty(value = "회원 이름")
    private String userName;
    @ApiModelProperty(value = "회원 비밀번호")
    private String userPw;
    @ApiModelProperty(value = "회원 전화번호")
    private String userPhone;
    @ApiModelProperty(value = "회원 부서")
    private String userDept;
    @ApiModelProperty(value = "이메일 인증 여부")
    private boolean userYn;
}