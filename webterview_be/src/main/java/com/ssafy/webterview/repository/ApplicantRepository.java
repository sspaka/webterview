package com.ssafy.webterview.repository;

import com.ssafy.webterview.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicantRepository extends JpaRepository<Applicant, Integer> {
	Applicant findByApplicantNameAndApplicantPhone(String name, String phone);

	Applicant findByApplicantEmail(String email);

	void deleteByGroupGroupNo(int groupNo);

	List<Applicant> findByRoomGroupGroupNo(int groupNo);

	List<Applicant> findByRoomRoomNo(int roomNo);
}