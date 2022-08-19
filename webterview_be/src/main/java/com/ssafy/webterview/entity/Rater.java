package com.ssafy.webterview.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Data
@DynamicInsert
@Table(name = "Rater")
public class Rater {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RaterNo", nullable = false)
	private Integer raterNo;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "UserNo", nullable = false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "GroupNo")
	private Group group;

	@ManyToOne(fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "RoomNo")
	private Room room;

	@Column(name = "RaterEmail", nullable = false, length = 45)
	private String raterEmail;

	@Column(name = "RaterName", nullable = false, length = 10)
	private String raterName;

	@Column(name = "RaterPhone", nullable = false, length = 15)
	private String raterPhone;


}