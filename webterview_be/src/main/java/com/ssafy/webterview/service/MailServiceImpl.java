package com.ssafy.webterview.service;

import com.ssafy.webterview.util.CodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

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
			html = "<h2 data-ke-size=\"size26\"><b>웹터뷰</b></h2>\r\n" + 
					"<p data-ke-size=\"size16\">안녕하세요.</p>\r\n" + 
					"<p data-ke-size=\"size16\">고객님이 요청하신 이메일 인증번호를 발급해드립니다.</p>\r\n" + 
					"<p data-ke-size=\"size16\">&nbsp;</p>\r\n" + 
					"<p data-ke-size=\"size16\">현재 회원가입 페이지 이메일 인증번호 입력란에 아래 인증번호를 입력하시기 바랍니다.</p>\r\n" + 
					"<hr contenteditable=\"false\" data-ke-type=\"horizontalRule\" data-ke-style=\"style6\" />\r\n" + 
					"<h3 style=\"text-align: center;\" data-ke-size=\"size23\"><b>"+code+"</b></h3>\r\n" + 
					"<hr contenteditable=\"false\" data-ke-type=\"horizontalRule\" data-ke-style=\"style6\" />\r\n" + 
					"<p style=\"text-align:center;\"><button "
					+"style=\"background-color: #4caf50; border: none; color: white; padding: 15px 32px; text-align: center;"
					+"text-decoration: none; display: inline-block; font-size: 16px;\">"
					+ "<a href=\"https://www.naver.com\" style=\"text-decoration:none;color: #222;\"><b>웹터뷰 홈페이지 바로가기</b></a></button></p>";
			break;
		case "findPw":
			html = "<h2 data-ke-size=\"size26\"><b>웹터뷰</b></h2>\r\n" + 
					"<p data-ke-size=\"size16\">안녕하세요.</p>\r\n" + 
					"<p data-ke-size=\"size16\">고객님이 요청하신 이메일 인증번호를 발급해드립니다.</p>\r\n" + 
					"<p data-ke-size=\"size16\">&nbsp;</p>\r\n" + 
					"<p data-ke-size=\"size16\">현재 비밀번호 찾기 이메일 인증번호 입력란에 아래 인증번호를 입력하시기 바랍니다.</p>\r\n" + 
					"<hr contenteditable=\"false\" data-ke-type=\"horizontalRule\" data-ke-style=\"style6\" />\r\n" + 
					"<h3 style=\"text-align: center;\" data-ke-size=\"size23\"><b>"+code+"</b></h3>\r\n" + 
					"<hr contenteditable=\"false\" data-ke-type=\"horizontalRule\" data-ke-style=\"style6\" />\r\n" + 
					"<p style=\"text-align:center;\"><button "
					+"style=\"background-color: #4caf50; border: none; color: white; padding: 15px 32px; text-align: center;"
					+"text-decoration: none; display: inline-block; font-size: 16px;\">"
					+ "<a href=\"https://www.naver.com\" style=\"text-decoration:none;color: #222;\"><b>웹터뷰 홈페이지 바로가기</b></a></button></p>";
			break;
			case "goRoom":
				String url = "https://localhost:8080/interview/";
				url += code;
				html = "<h2 data-ke-size=\\\"size26\\\"><b>웹터뷰</b></h2>\\r\\n\" + \n" +
						"\t\t\t\t\t\"<p data-ke-size=\\\"size16\\\">안녕하세요.</p>\\r\\n\" + \n" +
						"\t\t\t\t\t\"<p data-ke-size=\\\"size16\\\">고객님의 면접장 URL을 발급해드립니다.</p>\\r\\n\" + \n" +
						"\t\t\t\t\t\"<p data-ke-size=\\\"size16\\\">&nbsp;</p>\\r\\n\" + \n" +
						"\t\t\t\t\t\"<p data-ke-size=\\\"size16\\\">아래 URL로 입장해주시기 바랍니다.</p>\\r\\n\" + \n" +
						"\t\t\t\t\t\"<hr contenteditable=\\\"false\\\" data-ke-type=\\\"horizontalRule\\\" data-ke-style=\\\"style6\\\" />\\r\\n\" + \n" +
						"\t\t\t\t\t\"<a href="+url+" style=\\\"text-decoration:none;color: #222;\\\"><b>"+url+"</b></h3>\" + \n" +
						"\t\t\t\t\t\"<hr contenteditable=\\\"false\\\" data-ke-type=\\\"horizontalRule\\\" data-ke-style=\\\"style6\\\" />\\r\\n\" + \n" +
						"\t\t\t\t\t\"<p style=\\\"text-align:center;\\\"><button \"\n" +
						"\t\t\t\t\t+\"style=\\\"background-color: #4caf50; border: none; color: white; padding: 15px 32px; text-align: center;\"\n" +
						"\t\t\t\t\t+\"text-decoration: none; display: inline-block; font-size: 16px;\\\">\"\n" +
						"\t\t\t\t\t+ \"<a href=\\\"https://www.naver.com\\\" style=\\\"text-decoration:none;color: #222;\\\"><b>웹터뷰 홈페이지 바로가기</b></a></button></p>";


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
	public String makeHtml(String type, String code, String dept, String start) {
		String html = null;
		String url = "https://localhost:8080/interview/";
		switch(type) {
			case "rater":
				url += code;
				html = "<h2 data-ke-size=\\\"size26\\\"><b>웹터뷰</b></h2>\\r\\n\" + \n" +
						"\t\t\t\t\t\"<p data-ke-size=\\\"size16\\\">안녕하세요 면접관님.</p>\\r\\n\" + \n" +
						"\t\t\t\t\t\"<p data-ke-size=\\\"size16\\\">"+start+" "+dept+" 면접장 URL을 발급해드립니다.</p>\\r\\n\" + \n" +
						"\t\t\t\t\t\"<p data-ke-size=\\\"size16\\\">&nbsp;</p>\\r\\n\" + \n" +
						"\t\t\t\t\t\"<p data-ke-size=\\\"size16\\\">아래 URL로 입장해주시기 바랍니다.</p>\\r\\n\" + \n" +
						"\t\t\t\t\t\"<hr contenteditable=\\\"false\\\" data-ke-type=\\\"horizontalRule\\\" data-ke-style=\\\"style6\\\" />\\r\\n\" + \n" +
						"\t\t\t\t\t\"<a href="+url+" style=\\\"text-decoration:none;color: #222;\\\"><b>"+url+"</b></h3>\\r\\n\" + \n" +
						"\t\t\t\t\t\"<hr contenteditable=\\\"false\\\" data-ke-type=\\\"horizontalRule\\\" data-ke-style=\\\"style6\\\" />\\r\\n\" + \n" +
						"\t\t\t\t\t\"<p style=\\\"text-align:center;\\\"><button \"\n" +
						"\t\t\t\t\t+\"style=\\\"background-color: #4caf50; border: none; color: white; padding: 15px 32px; text-align: center;\"\n" +
						"\t\t\t\t\t+\"text-decoration: none; display: inline-block; font-size: 16px;\\\">\"\n" +
						"\t\t\t\t\t+ \"<a href=\\\"https://www.naver.com\\\" style=\\\"text-decoration:none;color: #222;\\\"><b>웹터뷰 홈페이지 바로가기</b></a></button></p>";
				break;
			case "appli":
				url += code;
				html = "<h2 data-ke-size=\\\"size26\\\"><b>웹터뷰</b></h2>\\r\\n\" + \n" +
						"\t\t\t\t\t\"<p data-ke-size=\\\"size16\\\">안녕하세요 지원자님.</p>\\r\\n\" + \n" +
						"\t\t\t\t\t\"<p data-ke-size=\\\"size16\\\">"+start+" "+dept+" 면접장 URL을 발급해드립니다.</p>\\r\\n\" + \n" +
						"\t\t\t\t\t\"<p data-ke-size=\\\"size16\\\">&nbsp;</p>\\r\\n\" + \n" +
						"\t\t\t\t\t\"<p data-ke-size=\\\"size16\\\">아래 URL로 입장해주시기 바랍니다.</p>\\r\\n\" + \n" +
						"\t\t\t\t\t\"<hr contenteditable=\\\"false\\\" data-ke-type=\\\"horizontalRule\\\" data-ke-style=\\\"style6\\\" />\\r\\n\" + \n" +
						"\t\t\t\t\t\"<a href="+url+" style=\\\"text-decoration:none;color: #222;\\\"><b>"+url+"</b></h3>\\r\\n\" + \n" +
						"\t\t\t\t\t\"<hr contenteditable=\\\"false\\\" data-ke-type=\\\"horizontalRule\\\" data-ke-style=\\\"style6\\\" />\\r\\n\" + \n" +
						"\t\t\t\t\t\"<p style=\\\"text-align:center;\\\"><button \"\n" +
						"\t\t\t\t\t+\"style=\\\"background-color: #4caf50; border: none; color: white; padding: 15px 32px; text-align: center;\"\n" +
						"\t\t\t\t\t+\"text-decoration: none; display: inline-block; font-size: 16px;\\\">\"\n" +
						"\t\t\t\t\t+ \"<a href=\\\"https://www.naver.com\\\" style=\\\"text-decoration:none;color: #222;\\\"><b>웹터뷰 홈페이지 바로가기</b></a></button></p>";
				break;
		}
		return html;
	}

	@Override
	public String sendMail(int type, String code, String email, String dept, String start) {
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
