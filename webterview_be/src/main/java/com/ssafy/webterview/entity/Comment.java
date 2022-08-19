package com.ssafy.webterview.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "Comment")
@Data
@DynamicInsert
@DynamicUpdate
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CommentNo", nullable = false)
	private Integer commentNo;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "BoardNo", nullable = false)
	private Board board;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "UserNo", nullable = false)
	private User user;

	@Column(name = "CommentAnswer", length = 500)
	private String commentAnswer;

	@Column(name = "CommentRegDate")
	private Instant commentRegDate;

//	public void setBoard(int board){
//		this.board = new Board();
//		this.board.setBoardNo(board);
//	}
//
//	public void setUser(int user){
//		this.user = new User();
//		this.user.setUserNo(user);
//	}
}