package com.ssafy.webterview.service;

import com.ssafy.webterview.dto.ApplicantDto;
import com.ssafy.webterview.dto.RaterDto;
import com.ssafy.webterview.entity.Applicant;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

public interface InterviewService {
	//알람부분 없음

	RaterDto confirmRater(String name, String phone) throws Exception;

	ApplicantDto confirmApplicant(String name, String phone) throws Exception;

	List<ApplicantDto> saveApplicants(int groupNo, MultipartFile file) throws Exception;

	Applicant saveUnique(int applicantNo, String comment) throws Exception;

	Applicant modifyApplicant(int applicantNo, int roomNo, Date date) throws Exception;

	Applicant getApplicant(String email) throws Exception;

	void deleteApplicant(int groupNo) throws Exception;

	List<ApplicantDto> listGroupApplicant(int groupNo) throws Exception;

	List<ApplicantDto> listRoomApplicant(int roomNo) throws Exception;

	public RaterDto insertRaterOne(RaterDto rater);

	public List<RaterDto> saveAllRater(int groupNo, MultipartFile file) throws Exception;

	public List<RaterDto> listRater(int userNo);

	public RaterDto detailRater(int raterNo);

	public RaterDto modifyRater(RaterDto raterDto);

	public void deleteAllRater(int userNo);

	public void deleteRater(int raterNo);
}
