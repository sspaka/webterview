package com.ssafy.webterview.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "FileInfo")
@Data
@DynamicInsert
@DynamicUpdate
public class FileInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FileNo", nullable = false)
    private Integer fileNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ApplicantNo")
    private Applicant applicant;

    @Column(name = "SaveFolder", length = 45)
    private String saveFolder;

    @Column(name = "OriginFileName", length = 50)
    private String originFileName;

    @Column(name = "SaveFileName", length = 50)
    private String saveFileName;

}