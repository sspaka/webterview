package com.ssafy.webterview.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "comment")
@Data
@DynamicInsert
@DynamicUpdate
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_no", nullable = false)
	private Integer commentNo;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "board_no", nullable = false)
	private Board board;

	@Column(name = "comment_user_no")
	private Integer commentUserNo;

	@Column(name = "comment_answer", length = 100)
	private String commentAnswer;

	@Column(name = "comment_reg_date")
	private Instant commentRegDate;

	public void setBoardNo(int boardNo){
		this.board = new Board();
		board.setBoardNo(boardNo);
	}
}