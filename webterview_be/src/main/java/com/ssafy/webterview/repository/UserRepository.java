package com.ssafy.webterview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ssafy.webterview.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value="SELECT * FROM user WHERE UserEmail=?1")
    User getReferenceByEmail(String UserEmail);

    String findUserPwByUserEmail(String UserEmail);

    User findByUserEmailAndUserPw(String UserPw, String UserEmail);
}
