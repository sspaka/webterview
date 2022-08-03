package com.ssafy.webterview.service;

import com.ssafy.webterview.dto.GroupDto;
import com.ssafy.webterview.dto.RoomDto;

public interface AdminService {
//	public boolean insertRaterOne(Rater rater);
//	public List<Rater> listRater();
//	public Rater detailRater(int raterNo);
//	public Rater modifyRater(Rater rater);
	public GroupDto modifyGroup(GroupDto group);
	public GroupDto createGroup(GroupDto groupDto);
	public GroupDto readGroup(int userNo) throws Exception;
	public void deleteGroup(int groupNo) throws Exception;
//	//public float linkGroup(Group group);
	public RoomDto createRoom(RoomDto room) throws Exception;
	public void deleteRoom(int roomNo);
}