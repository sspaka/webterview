package com.ssafy.webterview.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.webterview.dto.Applicant;

public interface InterviewMapper {
	public Applicant saveApplicant() throws SQLException;	
	public Applicant modifyApplicant() throws SQLException;	
	public Applicant getApplicant() throws SQLException;	
	public int deleteApplicant() throws SQLException;	
	public List<Applicant> listApplicant() throws SQLException;
}
