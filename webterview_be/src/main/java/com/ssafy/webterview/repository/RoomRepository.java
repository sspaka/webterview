package com.ssafy.webterview.repository;

import com.ssafy.webterview.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {

	List<Room> findByGroupGroupNo(int groupNo);
    long countByGroupGroupNo(int groupNo);

	@Query(value = "select sub.roomIdx " +
			"from " +
			"(SELECT r.*, @rownum := @rownum + 1 AS roomIdx FROM Room r, (SELECT @rownum := 0) tmp WHERE r.groupNo=?2) sub " +
			"where sub.roomNo=?1", nativeQuery = true)
	int changePkToIdx(int roomNo, int groupNo);

	//idx to pk
	Page<Room> findByGroupGroupNo(int groupNo, Pageable pageable);
}
