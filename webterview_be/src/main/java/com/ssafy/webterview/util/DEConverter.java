package com.ssafy.webterview.util;

import com.ssafy.webterview.dto.BoardDto;
import com.ssafy.webterview.dto.CommentDto;
import com.ssafy.webterview.dto.UserDto;
import com.ssafy.webterview.entity.Board;
import com.ssafy.webterview.entity.Comment;
import com.ssafy.webterview.entity.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
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
    DEConverter(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
//        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    private <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }

    /* 게시판 부분 변환 */
    public List<BoardDto> toBoardDtoList(List<Board> list){
        return mapList(list, BoardDto.class);
    }

    public BoardDto toBoardDto(Board board) {
        return modelMapper.map(board, BoardDto.class);
//        return modelMapper.typeMap()
    }

    public Board toBoardEntity(BoardDto boardDto){
        return modelMapper.map(boardDto,Board.class);
    }

    public CommentDto toCommentDto(Comment comment){
        //Board 객체를 boardNo로 바꿈
        CommentDto commentDto = modelMapper.map(comment,CommentDto.class);
        commentDto.setBoardNo(comment.getBoard().getBoardNo());
        return commentDto;
    }

    public Comment toCommentEntity(CommentDto commentDto){
        return modelMapper.map(commentDto,Comment.class);
    }

    public List<CommentDto> toCommentDtoList(List<Comment> list){
        return mapList(list,CommentDto.class);
    }

    /* 유저 부분 변환 */
    public UserDto toUserDto(User user){
        return modelMapper.map(user, UserDto.class);
    }

    public User toUserEntity(UserDto userDto){
        return modelMapper.map(userDto, User.class);
    }
}
