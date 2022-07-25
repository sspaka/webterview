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
@DynamicInsert
@DynamicUpdate
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BoardNo", nullable = false)
    private Integer boardNo;
    @Column(name = "UserNo", nullable = false)
    private Integer userNo;
    @Column(name = "BoardType")
    private Integer boardType;
    @Column(name = "BoardTitle", length = 45)
    private String boardTitle;
    @Column(name = "BoardContent", length = 1000)
    private String boardContent;
    @Column(name = "BoardRegDate")
    private Instant boardRegDate;
    @Column(name = "BoardUpDate")
    private Instant boardUpDate;
//	@JsonManagedReference 	//정상적인 직렬화 수행 부분
//	@OneToMany(mappedBy = "board")
//	private List<Comment> comments = new ArrayList<>();

}
