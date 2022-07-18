package com.ssafy.webterview.service;

public interface MailService {
	String makeCode(int size);
	String makeHtml(String type, String code);
	String sendMail(String type, String email);
}
