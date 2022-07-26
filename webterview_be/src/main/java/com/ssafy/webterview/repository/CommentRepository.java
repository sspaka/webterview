package com.ssafy.webterview.repository;

import com.ssafy.webterview.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query(value="SELECT COUNT(comment_no) FROM Comment WHERE board_no=?1")
    long countByBoardNo(int BoardNo);

    @Query(value="SELECT * FROM Comment WHERE board_no=?1",nativeQuery = true)
    List<Comment> findByBoardNo(int BoardNo);

    //comment.board.no ??
    //comment.boardNo ? ?
//    long countByBoardNo(int BoardNo);
}
