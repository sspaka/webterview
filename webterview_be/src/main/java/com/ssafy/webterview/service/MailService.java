package com.ssafy.webterview.service;

public interface MailService {
	String makeHtml(String type, String code);
	String sendMail(String type, String email);
}
