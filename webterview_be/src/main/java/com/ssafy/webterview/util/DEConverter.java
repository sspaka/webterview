package com.ssafy.webterview.util;

import com.ssafy.webterview.dto.*;
import com.ssafy.webterview.entity.*;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Dto <-> Entity Converter
 */
@Component
public class DEConverter {

	private ModelMapper modelMapper;

	@Autowired
	DEConverter(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
//        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
    }

	private <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
		return source
				.stream()
				.map(element -> modelMapper.map(element, targetClass))
				.collect(Collectors.toList());
	}

	/* 게시판 부분 변환 */
	public List<BoardDto> toBoardDtoList(List<Board> list) {
		return mapList(list, BoardDto.class);
	}

	public Page<BoardDto> toBoardDtoList(Page<Board> boardList){
		return boardList.map(m->modelMapper.map(m,BoardDto.class));
	}

	public BoardDto toBoardDto(Board board) {
		return modelMapper.map(board, BoardDto.class);
	}

	public Board toBoardEntity(BoardDto boardDto) {
		return modelMapper.map(boardDto, Board.class);
	}

	public CommentDto toCommentDto(Comment comment) {
		return modelMapper.map(comment, CommentDto.class);
	}

	public Comment toCommentEntity(CommentDto commentDto) {
		return modelMapper.map(commentDto, Comment.class);
	}

	public List<CommentDto> toCommentDtoList(List<Comment> list) {
		return mapList(list, CommentDto.class);
	}

	/* 유저 부분 변환 */
	public UserDto toUserDto(User user) {
		return modelMapper.map(user, UserDto.class);
	}

	public User toUserEntity(UserDto userDto) {
		return modelMapper.map(userDto, User.class);
	}

	/* 평가문항 부분 변환 */
	public List<EvaluationDto> toEvaluationDtoList(List<Evaluation> list){
		return mapList(list, EvaluationDto.class);
	}

	/* 면접관 부분 변환 */
	public RaterDto toRaterDto(Rater rater) {
		return modelMapper.map(rater, RaterDto.class);
	}

	/* 지원자 부분 변환 */
	public ApplicantDto toApplicantDto(Applicant applicant) {
		return modelMapper.map(applicant,ApplicantDto.class);
	}

	public List<ApplicantDto> toApplicantDtoList(List<Applicant> list) {
		return mapList(list,ApplicantDto.class);
	}

	public GroupDto toGroupDto(Group group){ return modelMapper.map(group, GroupDto.class); }

	public Group toGroupEntity(GroupDto groupDto) { return modelMapper.map(groupDto, Group.class); }

	public RoomDto toRoomDto(Room room){ return modelMapper.map(room, RoomDto.class); }

	public Room toRoomEntity(RoomDto roomDto) { return modelMapper.map(roomDto, Room.class); }

	public List<RoomDto> toRoomDtoList(List<Room> list) {
		return mapList(list, RoomDto.class);
	}
	public List<RaterDto> toRaterDtoList(List<Rater> list) {
		return mapList(list, RaterDto.class);
	}
}
