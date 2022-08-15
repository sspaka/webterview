package com.ssafy.webterview.service;

import java.time.Instant;

public interface MailService {
	String makeHtml(String type, String code);
	String sendMail(String type, String email);
	String makeHtml(String type, String code, String dept, Instant start);
	String sendMail(int type, String code, String email, String dept, Instant start);
}
