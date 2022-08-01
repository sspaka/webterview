package com.ssafy.webterview.service;

import com.ssafy.webterview.dto.GroupDto;

public interface AdminService {
//	public boolean insertRaterOne(Rater rater);
//	public List<Rater> listRater();
//	public Rater detailRater(int raterNo);
//	public Rater modifyRater(Rater rater);
//	public Group modifyGroup(Group group);
	public GroupDto createGroup(GroupDto groupDto);
	public void deleteGroup(int groupNo);
//	//public float linkGroup(Group group);
//	public boolean createRoom(Room room);
//	public boolean deleteRoom(int roomNo);
}