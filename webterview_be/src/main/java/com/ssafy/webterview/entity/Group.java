package com.ssafy.webterview.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
@DynamicInsert
@Table(name = "`group`")
public class Group {
	@Id
	@Column(name = "GroupNo", nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "UserNo", nullable = false)
	private User userNo;

	@Column(name = "GroupStartDate")
	private Instant groupStartDate;

	@Column(name = "GroupCode", length = 45)
	private String groupCode;

	@Column(name = "GroupEndDate")
	private Instant groupEndDate;

	@Column(name = "GroupBlind")
	private Boolean groupBlind;

}