package com.ssafy.webterview.service;

import com.ssafy.webterview.dto.GroupDto;
import com.ssafy.webterview.dto.RaterDto;
import com.ssafy.webterview.dto.RoomDto;

import java.util.List;

public interface AdminService {
	public GroupDto modifyGroup(GroupDto group);
	public GroupDto createGroup(GroupDto groupDto);
	public GroupDto readGroup(int userNo) throws Exception;
	public boolean checkGroup(int userNo) throws Exception;
	public void deleteGroup(int groupNo) throws Exception;
//	//public float linkGroup(Group group);
	public List<RoomDto> createRoom(int num, int groupNo) throws Exception;
	public List<RoomDto> listRoom(int groupNo) throws Exception;
	public List<RaterDto> readRoom(int roomNo) throws Exception;
	public RoomDto detailRoom(int roomNo) throws Exception;
	public void deleteRoom(int roomNo);
	public String setRoomCode(int roomNo) throws  Exception;
	public String encrypt(String text) throws Exception;
	public String decrypt(String cipherText) throws Exception;
}