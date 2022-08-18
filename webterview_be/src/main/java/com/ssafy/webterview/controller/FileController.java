package com.ssafy.webterview.controller;

import com.ssafy.webterview.dto.FileInfoDto;
import com.ssafy.webterview.service.InterviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Api("FileController V1")
@RequestMapping("/file")
@Controller
public class FileController {

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    private InterviewService interviewService;
    private ServletContext servletContext;

    @Autowired
    public FileController(InterviewService interviewService, ServletContext servletContext) {
        this.interviewService = interviewService;
        this.servletContext = servletContext;
    }

    @ApiOperation(value = "지원자 영상 주소 저장", notes = "해당 지원자의 영상 주소를 디비에 저장한다.", response = Map.class)
    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> saveFile(@RequestParam int applicantNo, @RequestParam MultipartFile file) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;

        try {
            String realPath = servletContext.getRealPath("/upload");
            String today = new SimpleDateFormat("yyMMdd").format(new Date());
            String saveFolder = realPath + File.separator + today;
            File folder = new File(saveFolder);
            if (!folder.exists())	folder.mkdirs();
            FileInfoDto fileInfoDto = new FileInfoDto();
            String originalFileName = file.getOriginalFilename();

            if (originalFileName != null && !originalFileName.isEmpty()) {
                String saveFileName = UUID.randomUUID() + originalFileName.substring(originalFileName.lastIndexOf('.'));
                fileInfoDto.setApplicantNo(applicantNo);
                fileInfoDto.setSaveFolder(today);					//서버 저장폴더명
                fileInfoDto.setOriginFileName(originalFileName);	//원본 파일 이름
                fileInfoDto.setSaveFileName(saveFileName);			//실제 저장 파일 이름
                file.transferTo(new File(folder, saveFileName));	//2-5번
            }

            //db에 정보 저장
            interviewService.saveFile(fileInfoDto);

            resultMap.put("message",SUCCESS);
            resultMap.put("file",fileInfoDto);
            status = HttpStatus.OK;

        } catch (Exception e) {
            resultMap.put("error",e.getMessage());
            resultMap.put("message", FAIL);
        }

        return new ResponseEntity<>(resultMap, status);
    }

    @ApiOperation(value = "지원자 영상 주소 불러오기", notes = "해당 지원자의 영상 주소를 불러온다.", response = Map.class)
    @GetMapping("/download")
    public ModelAndView getFile(int applicantNo) throws Exception {
        FileInfoDto fileInfoDto = interviewService.getFile(applicantNo);

        Map<String, Object> fileInfo = new HashMap<>();
        fileInfo.put("sfolder", fileInfoDto.getSaveFolder());
        fileInfo.put("ofile", fileInfoDto.getOriginFileName());
        fileInfo.put("sfile", fileInfoDto.getSaveFileName());

        return new ModelAndView("fileDownloadView", "downloadFile", fileInfo);
    }

}
