package com.ssafy.webterview.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Data
@DynamicInsert
@Table(name = "rater")
public class Rater {
	@Id
	@Column(name = "RaterNo", nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "UserNo", nullable = false)
	private User userNo;

	@Column(name = "RoomNo")
	private Integer roomNo;

	@Column(name = "RaterEmail", nullable = false, length = 45)
	private String raterEmail;

}