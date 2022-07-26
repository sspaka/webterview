package com.ssafy.webterview.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "board")
@Data
@DynamicInsert
@DynamicUpdate
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "board_no", nullable = false)
	private Integer boardNo;

	@Column(name = "user_no", nullable = false)
	private Integer userNo;

	@Column(name = "board_type")
	private Integer boardType;

	@Column(name = "board_title", length = 45)
	private String boardTitle;

	@Column(name = "board_content", length = 1000)
	private String boardContent;

	@Column(name = "board_regDate")
	private Instant boardRegdate;

	@Column(name = "board_update")
	private Instant boardUpdate;
}