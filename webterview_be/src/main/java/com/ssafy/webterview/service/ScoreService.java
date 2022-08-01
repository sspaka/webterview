package com.ssafy.webterview.service;

import com.ssafy.webterview.dto.EvaluationDto;

import java.util.List;

public interface ScoreService {
    //평가문항
//    void saveQuestion() throws Exception;
    void deleteQuestion() throws Exception;
    List<EvaluationDto> getQuestion() throws Exception;

    //지원자
//    void
}
