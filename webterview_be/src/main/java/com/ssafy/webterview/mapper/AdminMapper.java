package com.ssafy.webterview.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.webterview.dto.Group;
import com.ssafy.webterview.dto.Rater;
import com.ssafy.webterview.dto.Room;

@Mapper
public interface AdminMapper {
	public int insertRaterOne(Rater rater);
	public List<Rater> listRater();
	public Rater detailRater(int raterNo);
	public Rater modifyRater(Rater rater);
	public Group modifyGroup(Group group);
	public int deleteGroup(int groupNo);
	//public float linkGroup(Group group);
	public int createRoom(Room room);
	public int deleteRoom(int roomNo);
}
