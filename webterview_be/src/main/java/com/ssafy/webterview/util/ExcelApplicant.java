package com.ssafy.webterview.util;

import lombok.Data;

@Data
public class ExcelApplicant {
	private int no;
	private String name;
	private String email;
	private double[] scores;

	public ExcelApplicant(int no, String name, String email, int size){
		this.no = no;
		this.name = name;
		this.email = email;
		this.scores = new double[size];
	}
}
