package com.ssafy.webterview.repository;

import com.ssafy.webterview.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
	Page<Board> findByBoardTypeAndUserUserNo(int boardType, int userNo, Pageable pageable);

	List<Board> findTop3ByBoardTypeOrderByBoardNoDesc(int boardType);
}