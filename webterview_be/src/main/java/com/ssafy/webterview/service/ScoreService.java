package com.ssafy.webterview.service;

import com.ssafy.webterview.dto.EvaluationDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ScoreService {
    //평가문항
    void saveQuestion(int groupNo, MultipartFile file) throws Exception;
    void deleteQuestion(int groupNo) throws Exception;
    List<EvaluationDto> getQuestion(int groupNo) throws Exception;

    //지원자
    void saveScoreAndUnique(Map<String,Object> map) throws Exception;

//    void calcScore() throws Exception;
//
//    void calcScoreList() throws Exception;
//
    List<Map<String,Object>> avgScore(int applicantNo) throws Exception;
//
//    void avgScoreList() throws Exception;
//
//    void exportExcel() throws Exception;
}
