package com.ssafy.webterview.util;

import com.ssafy.webterview.dto.BoardDto;
import com.ssafy.webterview.dto.CommentDto;
import com.ssafy.webterview.entity.Board;
import com.ssafy.webterview.entity.Comment;
import org.modelmapper.ModelMapper;
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
    }

    private <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }

    public List<BoardDto> toBoardDtoList(List<Board> list){
        return mapList(list, BoardDto.class);
    }

    public BoardDto toBoardDto(Board board) {
        return modelMapper.map(board, BoardDto.class);
    }

    public Board toBoardEntity(BoardDto boardDto){
        return modelMapper.map(boardDto,Board.class);
    }

    public CommentDto toCommentDto(Comment comment){
        //Board 객체를 boardNo로 바꿈
        CommentDto commentDto = modelMapper.map(comment,CommentDto.class);
        commentDto.setCommentNo(comment.getBoard().getBoardNo());
        return commentDto;
    }

    public Comment toCommentEntity(CommentDto commentDto){
        Comment comment = modelMapper.map(commentDto,Comment.class);
        comment.setBoard(new Board());
        comment.getBoard().setBoardNo(commentDto.getBoardNo());
        return comment;
    }

}
