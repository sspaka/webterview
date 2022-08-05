package com.ssafy.webterview.controller;

import com.ssafy.webterview.service.ScoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Api("ScoreController V1")
@RestController
@RequestMapping("/score")
public class ScoreController {
    public static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    private ScoreService scoreService;
    @Autowired
    public ScoreController(ScoreService scoreService){
        this.scoreService = scoreService;
    }

    @ApiOperation(value = "평가문항 일괄저장", notes = "엑셀로 업로드한 평가문항을 DB에 일괄 저장한다.", response = Map.class)
    @PostMapping("/eval/save")
    public ResponseEntity<Map<String, Object>> saveQuestion(@RequestParam int groupNo, @RequestParam(name="file") MultipartFile file) {
        //엑셀 일괄업로드
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;

        try {
            resultMap.put("list", scoreService.saveQuestion(groupNo,file));
            resultMap.put("message",SUCCESS);
            status=HttpStatus.OK;

        } catch (Exception e) {
            resultMap.put("message",FAIL);
            resultMap.put("error", e.getMessage());
        }
        return new ResponseEntity<>(resultMap, status);
    }

    @ApiOperation(value = "평가문항 일괄삭제", notes = "해당 그룹의 평가문항을 전체 삭제한다.", response = Map.class)
    @DeleteMapping("/eval/delete")
    public ResponseEntity<Map<String, Object>> deleteQuestion(@RequestParam int groupNo) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;

        try {
            scoreService.deleteQuestion(groupNo);
            resultMap.put("message",SUCCESS);
            status=HttpStatus.OK;
        } catch (Exception e) {
            resultMap.put("message",FAIL);
            resultMap.put("error", e.getMessage());
        }

        return new ResponseEntity<>(resultMap, status);
    }

    @ApiOperation(value = "평가문항 조회", notes = "해당 그룹의 평가문항을 조회한다.", response = Map.class)
    @GetMapping("/eval")
    public ResponseEntity<Map<String, Object>> getQuestion(@RequestParam("groupNo") int groupNo) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;

        try {
            resultMap.put("list",scoreService.getQuestion(groupNo));
            resultMap.put("message",SUCCESS);
            status=HttpStatus.OK;
        } catch (Exception e) {
            resultMap.put("message",FAIL);
            resultMap.put("error", e.getMessage());
        }

        return new ResponseEntity<>(resultMap, status);
    }

    @ApiOperation(value = "지원자 점수 저장", notes = "지원자 점수 목록을 DB에 저장한다.", response = Map.class)
    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> saveScore(@RequestBody @ApiParam(value="지원자 특이사항 및 평가점수 list") Map<String,Object> map) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;

		try {
			scoreService.saveScoreAndUnique(map);
			resultMap.put("message",SUCCESS);

		} catch (Exception e) {
			resultMap.put("message",FAIL);
			resultMap.put("error", e.getMessage());
		}

        return new ResponseEntity<>(resultMap, status);
    }

    @ApiOperation(value = "지원자 항목별 상세 평가표 조회", notes = "평가항목별로 지원자의 평균 점수를 조회한다.", response = Map.class)
    @GetMapping("/detail")
    public ResponseEntity<Map<String, Object>> getScoreTable(@RequestParam int applicantNo) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;

        try {
            resultMap.put("list",scoreService.calcScore(applicantNo));
            resultMap.put("message",SUCCESS);

        } catch (Exception e) {
            resultMap.put("message",FAIL);
            resultMap.put("error", e.getMessage());
        }

        return new ResponseEntity<>(resultMap, status);
    }
    @ApiOperation(value = "전체 지원자 항목별 상세 평가표 엑셀 다운로드", notes = "전체 지원자의 항목별 평균 점수를 엑셀로 다운로드한다.", response = Map.class)
    @GetMapping("/download")
    public ResponseEntity<Resource> getAllScoreTable(@RequestParam int userNo, @RequestParam int groupNo) throws Exception {
        String filename = scoreService.getExcelTitle(userNo);
        InputStreamResource file = new InputStreamResource(scoreService.exportExcel(groupNo));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }

    @ApiOperation(value = "전체 지원자 랭킹 조회", notes = "해당 그룹 지원자들의 평가항목 평균, 종합평가 평균 리스트를 조회한다.", response = Map.class)
    @GetMapping("/ranking")
    public ResponseEntity<Map<String, Object>> getRanking(@RequestParam int groupNo) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;

        try {
            resultMap.put("list",scoreService.avgScoreList(groupNo));
            resultMap.put("message",SUCCESS);

        } catch (Exception e) {
            resultMap.put("message",FAIL);
            resultMap.put("error", e.getMessage());
        }

        return new ResponseEntity<>(resultMap, status);
    }

}
