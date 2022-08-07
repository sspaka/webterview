package com.ssafy.webterview.service;

public interface MailService {
	String makeHtml(String type, String code);
	String sendMail(String type, String email);
	String makeHtml(String type, String code, String dept, String start);
	String sendMail(int type, String code, String email, String dept, String start);
}
