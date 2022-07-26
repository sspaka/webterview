package com.ssafy.webterview.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "board")
@Data
@DynamicInsert
@DynamicUpdate
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BoardNo", nullable = false)
	private Integer boardNo;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "UserNo", nullable = false)
	private User user;

	@Column(name = "BoardType")
	private Integer boardType;

	@Column(name = "BoardTitle", length = 45)
	private String boardTitle;

	@Column(name = "BoardContent", length = 1000)
	private String boardContent;

	@Column(name = "BoardRegDate")
	private Instant boardRegdate;

	@Column(name = "BoardUpdate")
	private Instant boardUpdate;

	public void setUser(int user){
		this.user = new User();
		this.user.setUserNo(user);
	}
}