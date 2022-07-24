package com.ssafy.webterview.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "board")
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@ApiModel(value = "Board (게시글정보)", description = "글번호, 제목, 내용, 작성자아이디, 작성일을 가진 Domain Class")
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BoardNo", nullable = false)
	@ApiModelProperty(value = "글번호")
	private Integer boardNo;
	@Column(name = "UserNo", nullable = false)
	@ApiModelProperty(value = "작성자번호")
	private Integer userNo;
	@Column(name = "BoardType")
	@ApiModelProperty(value = "게시글유형")
	private Integer boardType;
	@Column(name = "BoardTitle", length = 45)
	@ApiModelProperty(value = "제목")
	private String boardTitle;
	@Column(name = "BoardContent", length = 1000)
	@ApiModelProperty(value = "내용")
	private String boardContent;
	@Column(name = "BoardRegDate")
	@ApiModelProperty(value = "작성일")
	private Instant boardRegDate;
	@Column(name = "BoardUpDate")
	@ApiModelProperty(value = "수정일")
	private Instant boardUpDate;
//	@JsonManagedReference 	//정상적인 직렬화 수행 부분
	@OneToMany(mappedBy = "board")
	@ApiModelProperty(value = "댓글목록")
	private List<Comment> comments = new ArrayList<>();

//	public BoardEntity() {	}
//
//	public BoardEntity(Board board) {
//		this.boardNo = board.getBoardNo();
//		if(board.getUserNo() != 0) this.userNo = board.getUserNo();
//		if(board.getBoardType() != 0) this.boardType = board.getBoardType();
//		if(board.getBoardTitle() != null) this.boardTitle = board.getBoardTitle();
//		if(board.getBoardContent() != null) this.boardContent = board.getBoardContent();
//		if(board.getBoardRegDate() != null) this.boardRegDate = board.getBoardRegDate();
//		if(board.getBoardUpDate() != null) this.boardUpDate = board.getBoardUpDate();
////		if (board.getComments() != null) {
////			for (Comment comment : board.getComments()) {
////				this.comments.add(new CommentEntity(comment));
////			}
////		}
//	}
//
//	public Board toBoardDto() {
//		Board boardDto = new Board();
//		boardDto.setBoardNo(this.boardNo);
//		boardDto.setUserNo(this.userNo);
//		if(this.boardType != null) boardDto.setBoardType(this.boardType);
//		boardDto.setBoardContent(this.boardContent);
//		boardDto.setBoardRegDate(this.boardRegDate);
//		boardDto.setBoardUpDate(this.boardUpDate);
//
//		return boardDto;
//	}
}
