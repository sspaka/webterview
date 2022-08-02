package com.ssafy.webterview.service;

import com.ssafy.webterview.dto.EvaluationDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ScoreService {
    //평가문항
    void saveQuestion(int groupNo, MultipartFile file) throws Exception;
    void deleteQuestion(int groupNo) throws Exception;
    List<EvaluationDto> getQuestion(int groupNo) throws Exception;

    //지원자
//    void saveScore() throws Exception;
//
//    void calcScore() throws Exception;
//
//    void calcScoreList() throws Exception;
//
//    void avgScore() throws Exception;
//
//    void avgScoreList() throws Exception;
//
//    void exportExcel() throws Exception;
}
