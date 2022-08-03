package com.ssafy.webterview.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;
@Component
public class CodeGenerator {
    @Autowired
    CodeGenerator(){}

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
}
