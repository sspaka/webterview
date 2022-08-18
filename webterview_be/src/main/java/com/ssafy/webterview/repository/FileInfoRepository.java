package com.ssafy.webterview.repository;

import com.ssafy.webterview.entity.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileInfoRepository extends JpaRepository<FileInfo, Integer> {
    FileInfo findByApplicantApplicantNo(int applicantNo);
}