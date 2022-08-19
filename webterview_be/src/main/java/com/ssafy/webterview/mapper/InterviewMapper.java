package com.ssafy.webterview.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.webterview.dto.ApplicantDto;

public interface InterviewMapper {
	public ApplicantDto saveApplicant() throws SQLException;
	public ApplicantDto modifyApplicant() throws SQLException;
	public ApplicantDto getApplicant() throws SQLException;
	public int deleteApplicant() throws SQLException;	
	public List<ApplicantDto> listApplicant() throws SQLException;
}
