package com.ssafy.webterview.mapper;

import java.util.List;
import com.ssafy.webterview.dto.GroupDto;
import com.ssafy.webterview.dto.RaterDto;
import com.ssafy.webterview.dto.RoomDto;

public interface AdminMapper {
	public int insertRaterOne(RaterDto raterDto);
	public List<RaterDto> listRater();
	public RaterDto detailRater(int raterNo);
	public RaterDto modifyRater(RaterDto raterDto);
	public GroupDto modifyGroup(GroupDto groupDto);
	public int deleteGroup(int groupNo);
	//public float linkGroup(Group group);
	public int createRoom(RoomDto roomDto);
	public int deleteRoom(int roomNo);
}
