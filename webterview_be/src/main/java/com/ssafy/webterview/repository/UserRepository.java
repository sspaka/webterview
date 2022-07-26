package com.ssafy.webterview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ssafy.webterview.entity.User;

import java.util.Collection;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    List<User> findByUserEmail(String UserEmail);

    List<User> findByUserPhoneAndUserName(String UserPhone, String UserName);
//    @Modifying(clearAutomatically = true)
//    @Query("UPDATE User SET UserName = ?1, UserPw = ?2, UserPhone = ?3 WHERE UserEmail = ?4")
//    int updateUser(String UserName, String UserPw, String UserPhone, String UserEmail);

    //find형식은 String을 반환할 수 없구나 ~
//    User findUserPwByUserEmail(String UserEmail);

    User findByUserPwAndUserEmail(String UserPw, String UserEmail);
}
