package com.ssafy.webterview.service;

import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {
	@Autowired
	private JavaMailSender mailSender;

	@Override
	public String makeCode(int size) {
		Random ran = new Random();
		StringBuffer sb = new StringBuffer();
		int num = 0;
			do {
			num = ran.nextInt(75) + 48;
			if ((num >= 48 && num <= 57) || (num >= 65 && num <= 90) || (num >= 97 && num <= 122)) {
				sb.append((char) num);
			} else {
				continue;
			}
			} while (sb.length() < size);
		return sb.toString();
	}

	@Override
	public String makeHtml(String type, String code) {
		String html = null;
		switch(type) {
		case "register":
			html = "<h2 data-ke-size=\"size26\"><b>웹터뷰</b></h2>\r\n" + 
					"<p data-ke-size=\"size16\">안녕하세요.</p>\r\n" + 
					"<p data-ke-size=\"size16\">고객님이 요청하신 이메일 인증번호를 발급해드립니다.</p>\r\n" + 
					"<p data-ke-size=\"size16\">&nbsp;</p>\r\n" + 
					"<p data-ke-size=\"size16\">현재 회원가입 페이지 이메일 인증번호 입력란에 아래 인증번호를 입력하시기 바랍니다.</p>\r\n" + 
					"<hr contenteditable=\"false\" data-ke-type=\"horizontalRule\" data-ke-style=\"style6\" />\r\n" + 
					"<h3 style=\"text-align: center;\" data-ke-size=\"size23\"><b>"+code+"</b></h3>\r\n" + 
					"<hr contenteditable=\"false\" data-ke-type=\"horizontalRule\" data-ke-style=\"style6\" />\r\n" + 
					"<p style=\"text-align:center;\"><button onclick=\"window.open('https://naver.com')\" " //여기에 우리 사이트 입력해야함
					+"style=\"background-color: #4caf50; border: none; color: white; padding: 15px 32px; text-align: center;"
					+"text-decoration: none; display: inline-block; font-size: 16px;\">웹터뷰 홈페이지 바로가기</button></p>";
			break;
		case "findPw":
			
			break;
		}
		return html;
	}

	@Override
	public String sendMail(String type, String email) {
		//타입에 따라
		//1. 인증코드 만들기
		//2. html string만들기		
		String code = null, html = null, subject = null;
		switch(type) {
		case "register":
			code = makeCode(6);
			html = makeHtml(type, code);
			subject = "[웹터뷰] 회원가입 인증번호가 도착했습니다.";
			break;
		case "findPw":
			code = makeCode(10);
			html = makeHtml(type, code);
			subject = "[웹터뷰] 비밀번호 찾기 인정번호가 도착했습니다.";
			break;
		}
		
		//공통 - 메일보내기
		MimeMessage mail = mailSender.createMimeMessage();
		try {
			mail.setSubject(subject,"utf-8");
			mail.setText(html,"utf-8","html");
			mail.addRecipient(RecipientType.TO, new InternetAddress(email));
			mailSender.send(mail);
		} catch (MessagingException e) {
			e.printStackTrace();
			return "error";
		}
		
		return code;
	}

}
