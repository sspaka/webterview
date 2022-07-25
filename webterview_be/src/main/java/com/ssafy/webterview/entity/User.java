package com.ssafy.webterview.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserNo", nullable = false)
    private Integer userNo;

    @Column(name = "UserRole", nullable = false, length = 45)
    private String userRole;

    @Column(name = "UserEmail", nullable = false, length = 45)
    private String userEmail;

    @Column(name = "UserPw", nullable = false, length = 100)
    private String userPw;

    @Column(name = "UserName", nullable = false, length = 45)
    private String userName;

    @Column(name = "UserPhone", nullable = false, length = 15)
    private String userPhone;

    @Column(name = "UserDept", nullable = false, length = 45)
    private String userDept;

}