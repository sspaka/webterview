package com.ssafy.webterview.service;

import com.ssafy.webterview.dto.ApplicantDto;
import com.ssafy.webterview.dto.RaterDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

public interface InterviewService {
	RaterDto confirmRater(String name, String phone, int roomNo) throws Exception;

	ApplicantDto confirmApplicant(String name, String phone, int roomNo) throws Exception;

	List<ApplicantDto> saveApplicants(int groupNo, MultipartFile file) throws Exception;

	ApplicantDto modifyApplicant(int applicantNo, int roomNo, Date date) throws Exception;

	ApplicantDto getApplicant(int groupNo, String email) throws Exception;

	void deleteApplicant(int groupNo) throws Exception;

	List<ApplicantDto> listGroupApplicant(int groupNo) throws Exception;

	List<ApplicantDto> listRoomApplicant(int roomNo) throws Exception;

	RaterDto insertRaterOne(RaterDto rater);

	List<RaterDto> saveAllRater(int groupNo, int userNo, MultipartFile file) throws Exception;

	List<RaterDto> listRater(int userNo);

	RaterDto detailRater(int raterNo);

	RaterDto detailRater2(String email, int roomNo);

	RaterDto modifyRater(RaterDto raterDto);

	void deleteAllRater(int userNo);

	void deleteRater(int raterNo);

	List<ApplicantDto> saveResumes(int groupNo, MultipartFile file) throws Exception;

	void deleteResume(int groupNo) throws Exception;

	ApplicantDto saveFile(int applicantNo, String url) throws Exception;

	String getFile(int applicantNo) throws Exception;
}
