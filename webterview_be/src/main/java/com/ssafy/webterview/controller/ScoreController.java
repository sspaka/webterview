package com.ssafy.webterview.controller;

import com.ssafy.webterview.dto.GradeDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api("ScoreController V1")
@RestController
@RequestMapping("/score")
public class ScoreController {
    public static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @ApiOperation(value = "평가문항 일괄저장", notes = "", response = Map.class)
    @PostMapping("/eval/save")
    public ResponseEntity<Map<String, Object>> saveQuestion() {
        //엑셀 일괄업로드
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        return new ResponseEntity<>(resultMap, status);
    }

    @ApiOperation(value = "평가문항 일괄삭제", notes = "", response = Map.class)
    @DeleteMapping("/eval/delete")
    public ResponseEntity<Map<String, Object>> deleteQuestion() {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        return new ResponseEntity<>(resultMap, status);
    }

    @ApiOperation(value = "평가문항 조회", notes = "", response = Map.class)
    @GetMapping("/eval")
    public ResponseEntity<Map<String, Object>> getQuestion(@RequestParam("groupNo") int groupNo) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        return new ResponseEntity<>(resultMap, status);
    }

    @ApiOperation(value = "지원자 점수 저장", notes = "지원자 점수 목록을 DB에 저장한다.", response = Map.class)
    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> saveScore(@RequestBody @ApiParam(value = "지원자 점수 목록", required = true) List<GradeDto> list) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        //한 지원자의 점수목록을 받고 전부 저장
        //(실시간 순위일경우) 저장됐다면 순위계산 후 applicant 테이블에 순위 저장
        return new ResponseEntity<>(resultMap, status);
    }

    @ApiOperation(value = "지원자 항목별 상세 평가표 조회", notes = "", response = Map.class)
    @GetMapping("/detail")
    public ResponseEntity<Map<String, Object>> getScoreTable() {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        return new ResponseEntity<>(resultMap, status);
    }

    @ApiOperation(value = "전체 지원자 랭킹 조회", notes = "", response = Map.class)
    @GetMapping("/ranking")
    public ResponseEntity<Map<String, Object>> getRanking() {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        return new ResponseEntity<>(resultMap, status);
    }

    @ApiOperation(value = "전체 지원자 평가표 엑셀 다운로드", notes = "", response = Map.class)
    @PostMapping("/download")
    public ResponseEntity<Map<String, Object>> getAllScoreTable() {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        return new ResponseEntity<>(resultMap, status);
    }

}
