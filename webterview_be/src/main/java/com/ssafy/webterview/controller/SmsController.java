package com.ssafy.webterview.controller;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.webterview.dto.Request;
import com.ssafy.webterview.dto.SmsResponse;
import com.ssafy.webterview.service.SmsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/naverapi")
@RequiredArgsConstructor
public class SmsController {

	@Autowired
    private final SmsService smsService;

	@PostMapping("/sms")
    public ResponseEntity<SmsResponse> test(@RequestBody Request request) throws NoSuchAlgorithmException, URISyntaxException, UnsupportedEncodingException, InvalidKeyException, JsonProcessingException {
    	// HttpStatus status = HttpStatus.ACCEPTED;
    	SmsResponse data = smsService.sendSms(request.getRecipientPhoneNumber(), request.getContent());
        return ResponseEntity.ok().body(data);
    }
}

