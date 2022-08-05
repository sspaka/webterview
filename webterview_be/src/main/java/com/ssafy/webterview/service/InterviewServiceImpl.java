package com.ssafy.webterview.service;

import com.ssafy.webterview.dto.ApplicantDto;
import com.ssafy.webterview.dto.RaterDto;
import com.ssafy.webterview.entity.Applicant;
import com.ssafy.webterview.entity.Room;
import com.ssafy.webterview.repository.ApplicantRepository;
import com.ssafy.webterview.repository.RaterRepository;
import com.ssafy.webterview.repository.RoomRepository;
import com.ssafy.webterview.util.DEConverter;
import com.ssafy.webterview.util.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
public class InterviewServiceImpl implements InterviewService {
	private ApplicantRepository applicantRepository;
	private RaterRepository raterRepository;
	private RoomRepository roomRepository;
	private DEConverter converter;

	@Autowired
	public InterviewServiceImpl(ApplicantRepository applicantRepository,RaterRepository raterRepository,
								RoomRepository roomRepository, DEConverter converter){
		this.applicantRepository = applicantRepository;
		this.raterRepository = raterRepository;
		this.roomRepository = roomRepository;
		this.converter = converter;
	}

	@Override
	public RaterDto confirmRater(String name, String phone) throws Exception {
		return converter.toRaterDto(raterRepository.findByRaterNameAndRaterPhone(name,phone));
	}

	@Override
	public ApplicantDto confirmApplicant(String name, String phone) throws Exception {
		return converter.toApplicantDto(applicantRepository.findByApplicantNameAndApplicantPhone(name,phone));
	}

	@Override
	public List<ApplicantDto> saveApplicants(int groupNo, MultipartFile file) throws Exception {
		List<Room> roomList = roomRepository.findByGroupGroupNo(groupNo);
		List<Applicant> applicantList = ExcelHelper.excelToApplicants(roomList, file.getInputStream());
		return converter.toApplicantDtoList(applicantRepository.saveAll(applicantList));
	}

	@Override
	@Transactional
	public ApplicantDto saveUnique(int applicantNo, String comment) throws Exception {
		Applicant applicant = applicantRepository.getReferenceById(applicantNo);
		applicant.setApplicantUnique(comment);
		return converter.toApplicantDto(applicant);
	}

	@Override
	@Transactional
	public ApplicantDto modifyApplicant(int applicantNo, int roomNo, Date date) throws Exception {
		Applicant applicant = applicantRepository.getReferenceById(applicantNo);
		applicant.setRoom(roomRepository.getReferenceById(roomNo));
		applicant.setApplicantDate(date.toInstant());
		return converter.toApplicantDto(applicant);
	}

	@Override
	public ApplicantDto getApplicant(String email) {
		return converter.toApplicantDto(applicantRepository.findByApplicantEmail(email));
	}

	@Override
	@Transactional
	public void deleteApplicant(int groupNo) throws Exception {
		applicantRepository.deleteByGroupGroupNo(groupNo);
	}

	@Override
	public List<ApplicantDto> listGroupApplicant(int groupNo) throws Exception {
		return converter.toApplicantDtoList(applicantRepository.findByRoomGroupGroupNo(groupNo));
	}

	@Override
	public List<ApplicantDto> listRoomApplicant(int roomNo) throws Exception {
		return converter.toApplicantDtoList(applicantRepository.findByRoomRoomNo(roomNo));
	}
}
