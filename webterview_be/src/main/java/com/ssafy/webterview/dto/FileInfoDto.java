package com.ssafy.webterview.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "FileInfoDto : 지원자 정보", description = "지원자의 영상 주소 정보를 나타낸다.")
public class FileInfoDto implements Serializable {
    @ApiModelProperty(value = "파일 번호")
    private int fileNo;
    @ApiModelProperty(value = "지원자 번호")
    private int applicantNo;
    @ApiModelProperty(value = "저장 폴더")
    private String saveFolder;
    @ApiModelProperty(value = "원본 파일 이름")
    private String originFileName;
    @ApiModelProperty(value = "저장 파일 이름")
    private String saveFileName;
}
