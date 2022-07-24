package com.ssafy.webterview.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.Instant;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comment")
@DynamicInsert
@DynamicUpdate
@ApiModel(value = "Comment (댓글 정보)", description = "댓글번호, 게시글 정보, 작성자번호, 내용, 작성일을 가진 Domain Class")
public class Comment {
//
//	public CommentEntity(){}
//
//	public CommentEntity(Comment comment){
//		this.commentNo = comment.getCommentNo();
//		this.board = new BoardEntity(comment.getBoard());
//		this.commentUserNo = comment.getCommentUserNo();
//		this.commentAnswer = comment.getCommentAnswer();
//		this.commentRegDate = comment.getCommentRegDate();
//	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CommentNo", nullable = false)
	@ApiModelProperty(value = "댓글번호")
	private Integer commentNo;

//	@JsonBackReference	//직렬화수행x
//	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(columnDefinition = "integer", name = "BoardNo", nullable = false)
	@ApiModelProperty(value = "게시글 객체")
	private Board board;

	@Column(name = "CommentUserNo")
	@ApiModelProperty(value = "작성자 번호")
	private Integer commentUserNo;

	@Column(name = "CommentAnswer", length = 100)
	@ApiModelProperty(value = "댓글 내용")
	private String commentAnswer;

	@Column(name = "CommentRegDate")
	@ApiModelProperty(value = "작성일")
	private Instant commentRegDate;

//	public Comment toCommentDto(){
//		Comment comment = new Comment();
//		comment.setCommentNo(this.commentNo);
//		comment.setBoard(this.board.toBoardDto());
//		comment.setCommentUserNo(this.commentUserNo);
//		comment.setCommentRegDate(this.commentRegDate);
//
//		return comment;
//	}
}

