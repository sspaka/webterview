package com.ssafy.webterview.service;

import com.ssafy.webterview.dto.ApplicantDto;
import com.ssafy.webterview.dto.RaterDto;
import com.ssafy.webterview.entity.*;
import com.ssafy.webterview.repository.*;
import com.ssafy.webterview.util.DEConverter;
import com.ssafy.webterview.util.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class InterviewServiceImpl implements InterviewService {
	private ApplicantRepository applicantRepository;
	private RaterRepository raterRepository;
	private RoomRepository roomRepository;
	private GradeRepository gradeRepository;
	private UserRepository userRepository;
	private ResumeRepository resumeRepository;
	private GroupRepository groupRepository;
	private DEConverter converter;

	@Autowired
	public InterviewServiceImpl(ApplicantRepository applicantRepository,RaterRepository raterRepository,
								RoomRepository roomRepository, UserRepository userRepository,
								ResumeRepository resumeRepository, GradeRepository gradeRepository,
								GroupRepository groupRepository, DEConverter converter){
		this.applicantRepository = applicantRepository;
		this.raterRepository = raterRepository;
		this.roomRepository = roomRepository;
		this.userRepository = userRepository;
		this.resumeRepository = resumeRepository;
		this.gradeRepository = gradeRepository;
		this.groupRepository = groupRepository;
		this.converter = converter;
	}

	@Override
	public RaterDto confirmRater(String name, String phone, int roomNo) throws Exception {
		RaterDto raterDto = converter.toRaterDto(raterRepository.findByRaterNameAndRaterPhoneAndRoomRoomNo(name,phone,roomNo));
		raterDto.setGroupNo(roomRepository.getReferenceById(roomNo).getGroup().getGroupNo());
		return raterDto;
	}

	@Override
	public ApplicantDto confirmApplicant(String name, String phone, int roomNo) throws Exception {
		return converter.toApplicantDto(applicantRepository.findByApplicantNameAndApplicantPhoneAndRoomRoomNo(name,phone,roomNo));
	}

	@Override
	public List<ApplicantDto> saveApplicants(int groupNo, MultipartFile file) throws Exception {
		List<Room> roomList = roomRepository.findByGroupGroupNo(groupNo);
		List<Applicant> applicantList = ExcelHelper.excelToApplicants(roomList, file.getInputStream());

		Group group = groupRepository.getReferenceById(groupNo);
		Instant sdate = group.getGroupStartDate();
		Instant edate = group.getGroupEndDate();

		for(int i=0;i<applicantList.size();i++){
			Instant date = applicantList.get(i).getApplicantDate();
			if(!sdate.isBefore(date) || !edate.isAfter(date))
				applicantList.get(i).setApplicantDate(null);
		}

		return converter.toApplicantDtoList(applicantRepository.saveAll(applicantList));
	}

	@Override
	@Transactional
	public ApplicantDto modifyApplicant(int applicantNo, int groupNo, int roomIdx, Date date) throws Exception {
		Applicant applicant = applicantRepository.getReferenceById(applicantNo);
		applicant.setRoom(roomRepository.findByGroupGroupNo(groupNo, PageRequest.of(roomIdx-1,1)).getContent().get(0));
		applicant.setApplicantDate(date.toInstant());//.minusSeconds(9*60*60));
		ApplicantDto dto = converter.toApplicantDto(applicant);
		dto.setRoomIdx(roomIdx);
		return dto;
	}

	@Override
	public ApplicantDto getApplicant(int groupNo, String email) {
		ApplicantDto dto = converter.toApplicantDto(applicantRepository.findByGroupGroupNoAndApplicantEmail(groupNo,email));
		Integer idx = roomRepository.changePkToIdx(dto.getRoomNo(),groupNo);
		dto.setRoomIdx(idx==null?0:idx);
		return dto;
	}

	@Override
	@Transactional
	public void deleteApplicant(int groupNo) throws Exception {
		applicantRepository.deleteByGroupGroupNo(groupNo);
	}

	@Override
	public List<ApplicantDto> listGroupApplicant(int groupNo) throws Exception {
		List<ApplicantDto> applicantDtoList = converter.toApplicantDtoList(applicantRepository.findByRoomGroupGroupNo(groupNo));

		for (ApplicantDto dto : applicantDtoList) {
			Integer idx = roomRepository.changePkToIdx(dto.getRoomNo(), dto.getGroupNo());
			dto.setRoomIdx(idx==null?0:idx);
		}
		return applicantDtoList;
	}

	@Override
	public List<ApplicantDto> listRoomApplicant(int roomNo) throws Exception {
		List<ApplicantDto> applicantDtoList = converter.toApplicantDtoList(applicantRepository.findByRoomRoomNo(roomNo));
		for (ApplicantDto dto : applicantDtoList) {
			Integer idx = roomRepository.changePkToIdx(dto.getRoomNo(), dto.getGroupNo());
			dto.setRoomIdx(idx==null?0:idx);
		}
		return applicantDtoList;
	}

	@Override
	public List<RaterDto> saveAllRater(int groupNo, int userNo, MultipartFile file) throws Exception {
		List<Room> roomList = roomRepository.findByGroupGroupNo(groupNo);
		User user = userRepository.getReferenceById(userNo);
		List<Rater> raterList = ExcelHelper.excelToRaters(roomList, user, file.getInputStream());
		Group group = groupRepository.getReferenceById(groupNo);

		for(int i=0;i<raterList.size();i++){
			raterList.get(i).setGroup(group);
		}
		return converter.toRaterDtoList(raterRepository.saveAll(raterList));
	}

	@Override
	public RaterDto insertRaterOne(RaterDto raterDto) {
		Rater rater = converter.toRaterEntity(raterDto);
		rater.setRoom(roomRepository.findByGroupGroupNo(raterDto.getGroupNo(),PageRequest.of(raterDto.getRoomIdx()-1,1)).getContent().get(0));
		RaterDto dto = converter.toRaterDto(raterRepository.save(rater));
		dto.setRoomIdx(raterDto.getRoomIdx());
		return dto;
	}

	@Override
	public List<RaterDto> listRater(int userNo){
		int groupNo = groupRepository.getCurrentGroup(userNo).getGroupNo();
		List<RaterDto> dtoList = converter.toRaterDtoList(raterRepository.findByUserUserNo(userNo));
		for (RaterDto dto : dtoList) {
			Integer idx = roomRepository.changePkToIdx(dto.getRoomNo(),groupNo);
			dto.setRoomIdx(idx==null?0:idx);
		}
		return dtoList;
	}

	@Override
	public RaterDto detailRater(int raterNo) {
		RaterDto dto = converter.toRaterDto(raterRepository.getReferenceById(raterNo));
		Integer idx = roomRepository.changePkToIdx(dto.getRoomNo(),roomRepository.getReferenceById(dto.getRoomNo()).getGroup().getGroupNo());
		dto.setRoomIdx(idx==null?0:idx);
		return dto;
	}

	@Override
	public RaterDto detailRater2(String email, int roomNo) {
		RaterDto raterDto = converter.toRaterDto(raterRepository.findByRaterEmailAndRoomRoomNo(email, roomNo));
		Integer idx = roomRepository.changePkToIdx(raterDto.getRoomNo(),roomRepository.getReferenceById(raterDto.getRoomNo()).getGroup().getGroupNo());
		raterDto.setRoomIdx(idx==null?0:idx);
		return raterDto;
	}

	@Override
	@Transactional
	public RaterDto modifyRater(RaterDto raterDto) {
		Rater rater = raterRepository.getReferenceById(raterDto.getRaterNo());
		rater.setRoom(roomRepository.findByGroupGroupNo(raterDto.getGroupNo(), PageRequest.of(raterDto.getRoomIdx()-1,1)).getContent().get(0));
		RaterDto dto = converter.toRaterDto(rater);
		dto.setRoomIdx(raterDto.getRoomIdx());
		return dto;
	}

	@Override
	@Transactional
	public void deleteAllRater(int userNo){
		List<Rater> raterList = raterRepository.findByUserUserNo(userNo);

		for(int i=0;i<raterList.size();i++){
			List<Grade> grade = gradeRepository.findByRaterRaterNo(raterList.get(i).getRaterNo());
			for(int j=0;j<grade.size();j++){
				grade.get(j).setRater(null);
			}
			raterRepository.delete(raterList.get(i));
		}
	}

	@Override
	@Transactional
	public void deleteRater(int raterNo){
		raterRepository.delete(raterRepository.getReferenceById(raterNo));
	}

	@Override
	public List<ApplicantDto> saveResumes(int groupNo, MultipartFile file) throws Exception {
		List<Resume> resumeList = ExcelHelper.excelToResume(applicantRepository, groupNo, file.getInputStream());
		resumeRepository.saveAll(resumeList);
		return converter.toApplicantDtoList(applicantRepository.findByRoomGroupGroupNo(groupNo));
	}

	@Override
	@Transactional
	public void deleteResume(int groupNo) throws Exception {
		resumeRepository.deleteByApplicantGroupGroupNo(groupNo);
	}

	@Override
	@Transactional
	public ApplicantDto saveFile(int applicantNo, String url) throws Exception {
		Applicant applicant = applicantRepository.getReferenceById(applicantNo);
		applicant.setApplicantFile(url);

		return converter.toApplicantDto(applicant);
	}

	@Override
	public String getFile(int applicantNo) throws Exception {
		Applicant applicant = applicantRepository.getReferenceById(applicantNo);
		ApplicantDto applicantDto = converter.toApplicantDto(applicant);
		String url = applicantDto.getApplicantFile();

		return url;
	}
}
