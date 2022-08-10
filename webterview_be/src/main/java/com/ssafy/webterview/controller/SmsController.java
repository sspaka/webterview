package com.ssafy.webterview.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.webterview.dto.RequestDto;
import com.ssafy.webterview.dto.SmsResponseDto;
import com.ssafy.webterview.service.SmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/naverapi")
@RequiredArgsConstructor
public class SmsController {

	@Autowired
    private final SmsService smsService;

	@PostMapping("/sms")
    public ResponseEntity<SmsResponseDto> test(@RequestBody RequestDto request) throws NoSuchAlgorithmException, URISyntaxException, UnsupportedEncodingException, InvalidKeyException, JsonProcessingException {
    	// HttpStatus status = HttpStatus.ACCEPTED;
    	SmsResponseDto data = smsService.sendSms(request.getRecipientPhoneNumber(), request.getContent());
        return ResponseEntity.ok().body(data);
    }
}

