package com.ssafy.webterview.repository;

import com.ssafy.webterview.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Integer> {
//	@Query("select g.* " +
//			"from Group g " +
//			"left join User u " +
//			"on u.UserNo=g.UserNo " +
//			"where u.UserEmail=?1 " +
//			"and g.GroupStartDate <= now() " +
//			"and g.GroupEndDate >= now()")
//	Group findByUserEmail(String email);


}