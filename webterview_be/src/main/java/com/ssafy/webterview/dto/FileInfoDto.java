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
@ApiModel(value = "FileInfo : 파일 정보", description = "저장된 파일 정보를 나타낸다.")
public class FileInfoDto implements Serializable {
	@ApiModelProperty(value = "파일 번호")
	private int fileNo;
	@ApiModelProperty(value = "지원자 번호")
	private int applicantNo;
	@ApiModelProperty(value = "파일 저장폴더명")
	private String saveFolder;
	@ApiModelProperty(value = "원본 파일명")
	private String originFileName;
	@ApiModelProperty(value = "서버에 저장된 파일명")
	private String saveFileName;
}
