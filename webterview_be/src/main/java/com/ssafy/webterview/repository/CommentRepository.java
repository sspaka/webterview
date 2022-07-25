package com.ssafy.webterview.repository;

import com.ssafy.webterview.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
//    @Query("SELECT COUNT(*) FROM Comment c)
//    long countByBoardNo(int BoardNo); WHERE c.BoardNo=:boardNo"

    //comment.board.no ??
    //comment.boardNo ? ?
    long countByBoardNo(int BoardNo);
}
