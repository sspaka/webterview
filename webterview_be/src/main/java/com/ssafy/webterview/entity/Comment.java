package com.ssafy.webterview.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@Table(name = "comment")
@DynamicInsert
@DynamicUpdate
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentNo", nullable = false)
    private Integer commentNo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "BoardNo", nullable = false)
    private Board Board;

    @Column(name = "CommentUserNo")
    private Integer UserNo;

    @Column(name = "CommentAnswer", length = 100)
    private String commentAnswer;

    @Column(name = "CommentRegDate")
    private Instant commentRegDate;
}