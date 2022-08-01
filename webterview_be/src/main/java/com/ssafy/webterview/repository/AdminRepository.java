package com.ssafy.webterview.repository;

import com.ssafy.webterview.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Group, Integer> {

}
