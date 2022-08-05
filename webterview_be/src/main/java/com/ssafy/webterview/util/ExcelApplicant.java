package com.ssafy.webterview.util;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ExcelApplicant {
	private int no;
	private String name;
	private String email;
	private ArrayList<Double> scores;

	public ExcelApplicant(int no, String name, String email, int size){
		this.no = no;
		this.name = name;
		this.email = email;
		this.scores = new ArrayList<>(size);
	}
}
