package com.ssafy.webterview.service;

import com.ssafy.webterview.util.CodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class MailServiceImpl implements MailService {
	@Autowired
	private JavaMailSender mailSender;
	private CodeGenerator generator;

	public MailServiceImpl(CodeGenerator generator){
		this.generator = generator;
	}

	@Override
	public String makeHtml(String type, String code) {
		String html = null;
		switch(type) {
		case "register":
			html = "<h2 data-ke-size=\"size26\"><img style='width: 400px; text-align: left;' src='https://i7c205.p.ssafy.io/img/logo.035cab6c.png'>" +
					"<a href='https://i7c205.p.ssafy.io'></a></img></h2>\r\n" +
					"<br><br><p data-ke-size=\"size16\">안녕하세요.</p>\r\n" +
					"<p data-ke-size=\"size16\">고객님이 요청하신 이메일 인증번호를 발급해드립니다.</p>\r\n" +
					"<p data-ke-size=\"size16\">&nbsp;</p>\r\n" +
					"<p data-ke-size=\"size16\">현재 회원가입 페이지 이메일 인증번호 입력란에 아래 인증번호를 입력하시기 바랍니다.</p><br><br>\r\n" +
					"<hr contenteditable=\"false\" data-ke-type=\"horizontalRule\" data-ke-style=\"style6\" />\r\n" +
					"<h3 style=\"text-align: center;\" data-ke-size=\"size23\"><b>"+code+"</b></h3>\r\n" +
					"<hr contenteditable=\"false\" data-ke-type=\"horizontalRule\" data-ke-style=\"style6\" />\r\n" +
					"<div style=\"text-align:center;\"><button "
					+"style=\"background-color: #f05454; border: none; color: white; padding: 15px 32px; text-align: center;"
					+"text-decoration: none; display: inline-block; font-size: 16px;\">"
					+ "<a href=\"https://i7c205.p.ssafy.io\" style=\"text-decoration:none;color: #f5f5f5;\"><b>웹터뷰 홈페이지 바로가기</b></a></button></div><br><br>";
			break;
		case "findPw":
			html = "<h2 data-ke-size=\"size26\"><img style='width: 400px; text-align: left;' src='https://i7c205.p.ssafy.io/img/logo.035cab6c.png'>" +
					"<a href='https://i7c205.p.ssafy.io'></a></img></h2>\r\n" +
					"<br><br><p data-ke-size=\"size16\">안녕하세요.</p>\r\n" +
					"<p data-ke-size=\"size16\">고객님이 요청하신 이메일 인증번호를 발급해드립니다.</p>\r\n" +
					"<p data-ke-size=\"size16\">&nbsp;</p>\r\n" +
					"<p data-ke-size=\"size16\">현재 비밀번호 찾기 이메일 인증번호 입력란에 아래 인증번호를 입력하시기 바랍니다.</p><br><br>\r\n" +
					"<hr contenteditable=\"false\" data-ke-type=\"horizontalRule\" data-ke-style=\"style6\" />\r\n" +
					"<h3 style=\"text-align: center;\" data-ke-size=\"size23\"><b>"+code+"</b></h3>\r\n" +
					"<hr contenteditable=\"false\" data-ke-type=\"horizontalRule\" data-ke-style=\"style6\" />\r\n" +
					"<div style=\"text-align:center;\"><button "
					+"style=\"background-color: #f05454; border: none; color: white; padding: 15px 32px; text-align: center;"
					+"text-decoration: none; display: inline-block; font-size: 16px;\">"
					+ "<a href=\"https://i7c205.p.ssafy.io\" style=\"text-decoration:none;color: #f5f5f5;\"><b>웹터뷰 홈페이지 바로가기</b></a></button></div><br><br>";
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
			code = generator.makeCode(6);
			html = makeHtml(type, code);
			subject = "[웹터뷰] 회원가입 인증번호가 도착했습니다.";
			break;
		case "findPw":
			code = generator.makeCode(10);
			html = makeHtml(type, code);
			subject = "[웹터뷰] 비밀번호 찾기 인증번호가 도착했습니다.";
			break;
			default:
				code = type;
				html = makeHtml("goRoom", code);
				subject = "[웹터뷰] 면접장 URL이 도착했습니다.";
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

	@Override
	public String makeHtml(String type, String code, String dept, Instant start) {
		String html = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
		LocalDate date = LocalDateTime.ofInstant(start, ZoneOffset.UTC).toLocalDate();
		String startdate = simpleDateFormat.format(date);


		String url = "https://i7c205.p.ssafy.io/";
		switch(type) {
			case "rater":
				url += code;
				html = "<h2 data-ke-size=\"size26\"><img style='width: 400px; text-align: left;' src='https://i7c205.p.ssafy.io/img/logo.035cab6c.png'>" +
						"<a href='https://i7c205.p.ssafy.io'></a></img></h2>\n" +
						"<br><br><p data-ke-size=\"size16\">안녕하세요.</p>\n" +
						"<p data-ke-size=\"size16\">면접관님의 "+startdate+" 면접장 URL을 발급해드립니다.</p>\n" +
						"<p data-ke-size=\"size16\"> </p><br><br>\n" +
						"<hr contenteditable=\"false\" data-ke-type=\"horizontalRule\" data-ke-style=\"style5\" />\n" +
						"<p style=\"text-align: center;\" data-ke-size=\"size16\">면접장 url : <a href='"+url+"' target=\"_blank\" rel=\"noopener\">바로가기</a></p>\n" +
						"<hr contenteditable=\"false\" data-ke-type=\"horizontalRule\" data-ke-style=\"style5\" />\n" +
						"<p data-ke-size=\"size16\"> </p><br><br>";
				break;
			case "appli":
				url += code;
				html = "<h2 data-ke-size=\"size26\"><img style='width: 400px; text-align: left;' src='https://i7c205.p.ssafy.io/img/logo.035cab6c.png'>" +
						"<a href='https://i7c205.p.ssafy.io'></a></img></h2>\n" +
						"<p data-ke-size=\"size16\">안녕하세요.</p>\n" +
						"<br><br><p data-ke-size=\"size16\">지원자님의 "+startdate+" 면접장 URL을 발급해드립니다.</p>\n" +
						"<p data-ke-size=\"size16\"> </p><br><br>\n" +
						"<hr contenteditable=\"false\" data-ke-type=\"horizontalRule\" data-ke-style=\"style5\" />\n" +
						"<p style=\"text-align: center;\" data-ke-size=\"size16\">면접장 url : <a href='"+url+"' target=\"_blank\" rel=\"noopener\">바로가기</a></p>\n" +
						"<hr contenteditable=\"false\" data-ke-type=\"horizontalRule\" data-ke-style=\"style5\" />\n" +
						"<p data-ke-size=\"size16\"> </p><br><br>";
				break;
		}
		return html;
	}

	@Override
	public String sendMail(int type, String code, String email, String dept, Instant start) {
		//타입에 따라
		//1. 인증코드 만들기
		//2. html string만들기
		String html = null, subject = null;

		if(type == 1){
			html = makeHtml("rater", code, dept, start);
		}
		else if(type == 2){
			html = makeHtml("appli", code, dept, start);
		}
		subject = "[웹터뷰] "+dept+" 면접장 URL이 도착했습니다.";

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
