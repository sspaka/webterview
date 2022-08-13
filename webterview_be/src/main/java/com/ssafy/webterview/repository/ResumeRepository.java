package com.ssafy.webterview.repository;

import com.ssafy.webterview.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Integer> {
	void deleteByApplicantGroupGroupNo(int groupNo);
}