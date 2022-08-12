package com.ssafy.webterview.service;

import com.ssafy.webterview.dto.ApplicantDto;
import com.ssafy.webterview.dto.RaterDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

public interface InterviewService {
	//알람부분 없음

	RaterDto confirmRater(String name, String phone) throws Exception;

	ApplicantDto confirmApplicant(String name, String phone) throws Exception;

	List<ApplicantDto> saveApplicants(int groupNo, MultipartFile file) throws Exception;

	ApplicantDto modifyApplicant(int applicantNo, int roomNo, Date date) throws Exception;

	ApplicantDto getApplicant(int groupNo, String email) throws Exception;

	ApplicantDto getApplicantDto(int groupNo, String email) throws Exception;

	void deleteApplicant(int groupNo) throws Exception;

	List<ApplicantDto> listGroupApplicant(int groupNo) throws Exception;

	List<ApplicantDto> listRoomApplicant(int roomNo) throws Exception;

	public RaterDto insertRaterOne(RaterDto rater);

	public List<RaterDto> saveAllRater(int groupNo, int userNo, MultipartFile file) throws Exception;

	public List<RaterDto> listRater(int userNo);

	public RaterDto detailRater(int raterNo);

	public RaterDto modifyRater(RaterDto raterDto);

	public void deleteAllRater(int userNo);

	public void deleteRater(int raterNo);

	List<ApplicantDto> saveResumes(int groupNo, MultipartFile file) throws Exception;

	void deleteResume(int groupNo) throws Exception;

	ApplicantDto saveFile(int applicantNo, String url) throws Exception;

	String getFile(int applicantNo) throws Exception;
}
