package com.ssafy.webterview.service;

import com.ssafy.webterview.dto.EvaluationDto;
import com.ssafy.webterview.entity.Evaluation;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ScoreService {
    //평가문항
    List<Evaluation> saveQuestion(int groupNo, MultipartFile file) throws Exception;
    void deleteQuestion(int groupNo) throws Exception;
    List<EvaluationDto> getQuestion(int groupNo) throws Exception;

    //지원자
    void saveScoreAndUnique(Map<String,Object> map) throws Exception;

    List<Map<String,Object>> calcScore(int applicantNo) throws Exception;

    List<Map<String,Object>> calcScoreList(int groupNo) throws Exception;

    void exportExcel(int groupNo) throws Exception;

    List<Map<String,Object>> avgScoreList(int groupNo) throws Exception;

}
