package com.ssafy.webterview.repository;

import com.ssafy.webterview.entity.Rater;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ssafy.webterview.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RaterRepository extends JpaRepository<Rater, Integer> {
    List<Rater> findByRoomRoomNo(int roomNo);
  
	Rater findByRaterNameAndRaterPhone(String name, String email);

    List<Rater> findByUserUserNo(int userNo);
}