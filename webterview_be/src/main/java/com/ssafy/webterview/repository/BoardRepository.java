package com.ssafy.webterview.repository;

import com.ssafy.webterview.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
//	BoardEntity findById(int BoardNo){
//
//	}

}