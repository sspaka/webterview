package com.ssafy.webterview.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.webterview.dto.Applicant;
import com.ssafy.webterview.mapper.InterviewMapper;

@Service
public class InterviewServiceImpl implements InterviewService {
	@Autowired
	private InterviewMapper interviewMapper;

	@Override
	public Applicant saveApplicant() {
		return null;
	}

	@Override
	public Applicant modifyApplicant() {
		return null;
	}

	@Override
	public Applicant getApplicant() {
		return null;
	}

	@Override
	public int deleteApplicant() {
		return 0;
	}

	@Override
	public List<Applicant> listApplicant() {
		return null;
	}
}
