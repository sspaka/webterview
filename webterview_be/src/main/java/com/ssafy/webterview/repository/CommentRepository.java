package com.ssafy.webterview.repository;

import com.ssafy.webterview.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    long countByBoardBoardNo(int BoardNo);

    List<Comment> findByBoardBoardNo(int BoardNo);
}
