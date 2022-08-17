package com.ssafy.webterview.repository;

import com.ssafy.webterview.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {

	List<Room> findByGroupGroupNo(int groupNo);
    long countByGroupGroupNo(int groupNo);

	@Query(value = "select sub.roomIdx from (select r.roomNo,row_number() over(partition by groupNo) roomIdx from Room r where r.groupNo=?2) sub where sub.roomNo=?1",nativeQuery = true)
	Integer changePkToIdx(int roomNo,int groupNo);

	//idx to pk
	Page<Room> findByGroupGroupNo(int groupNo, Pageable pageable);
}
