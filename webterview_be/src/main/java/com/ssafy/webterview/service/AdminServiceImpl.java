package com.ssafy.webterview.service;

import com.ssafy.webterview.dto.GroupDto;
import com.ssafy.webterview.repository.AdminRepository;
import com.ssafy.webterview.util.DEConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminServiceImpl implements AdminService {
	private AdminRepository adminRepository;
	private DEConverter converter;

	@Autowired
	public AdminServiceImpl(AdminRepository adminRepository, DEConverter converter){
		this.adminRepository = adminRepository;
		this.converter = converter;
	}

//	@Autowired
//	private AdminMapper adminMapper;
//
//	@Override
//	public boolean insertRaterOne(Rater rater) {
//		return adminMapper.insertRaterOne(rater) == 1;
//	}
//
//	@Override
//	public List<Rater> listRater(){
//		return adminMapper.listRater();
//	}
//
//	@Override
//	public Rater detailRater(int raterNo) {
//
//		return adminMapper.detailRater(raterNo);
//	}
//
//	@Override
//	public Rater modifyRater(Rater rater) {
//
//		return adminMapper.modifyRater(rater);
//	}
//
//	@Override
//	public Group modifyGroup(Group group) {
//
//		return adminMapper.modifyGroup(group);
//	}

	@Override
	public GroupDto createGroup(GroupDto groupDto){
		//T붙은 이상한 시간 instant형으로 바꿔서 dto에 넣어줌
		//groupDto.setGroupStartDate();
		//groupDto.setGroupEndDate();

		return converter.toGroupDto(adminRepository.save(converter.toGroupEntity(groupDto)));
	}
	@Override
	@Transactional
	public void deleteGroup(int groupNo){
		adminRepository.delete(adminRepository.getReferenceById(groupNo));
	}

//	@Override
//	public float linkGroup(Group group) {
//		return adminMapper.linkGroup(group);
//	}

//	@Override
//	public boolean createRoom(Room room) {
//
//		return adminMapper.createRoom(room) == 1;
//	}
//
//	@Override
//	public boolean deleteRoom(int roomNo) {
//
//		return adminMapper.deleteRoom(roomNo) == 1;
//	}
}
