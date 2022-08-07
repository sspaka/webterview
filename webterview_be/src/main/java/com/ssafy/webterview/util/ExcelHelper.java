package com.ssafy.webterview.util;

import com.ssafy.webterview.entity.*;
import com.ssafy.webterview.repository.ApplicantRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelHelper {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String SHEET;

	public static boolean hasExcelFormat(MultipartFile file) {
		return TYPE.equals(file.getContentType());
	}

	public static List<Evaluation> excelToEvaluations(Group group, InputStream is) {
//		HEADERs = new String[]{"Id", "Title", "Description", "Published"};
		SHEET = "평가문항";
		try {
			Workbook workbook = new XSSFWorkbook(is);
			Sheet sheet = workbook.getSheet(SHEET);
			Iterator<Row> rows = sheet.iterator();
			List<Evaluation> evaluationList = new ArrayList<>();
			int rowNumber = 0;
			while (rows.hasNext()) {
				Row currentRow = rows.next();
				// 제목부분 스킵
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}
				Iterator<Cell> cellsInRow = currentRow.iterator();
				Evaluation evaluation = new Evaluation();
				evaluation.setGroup(group);
				int cellIdx = 0;
				while (cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();
					if(cellIdx==1){
						evaluation.setEvaluationQuestion(currentCell.getStringCellValue());
					}
					cellIdx++;
				}
				evaluationList.add(evaluation);
			}
			workbook.close();
			return evaluationList;
		} catch (IOException e) {
			throw new RuntimeException("엑셀 파일 파싱 실패: " + e.getMessage());
		}
	}

	public static List<Applicant> excelToApplicants(List<Room> roomList, InputStream is) {
		SHEET = "지원자";
		try {
			Workbook workbook = new XSSFWorkbook(is);
			Sheet sheet = workbook.getSheet(SHEET);
			Iterator<Row> rows = sheet.iterator();
			List<Applicant> applicantList = new ArrayList<>();
			int rowNumber = 0;

			while (rows.hasNext()) {
				Row currentRow = rows.next();
				// 제목부분 스킵
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}
				Iterator<Cell> cellsInRow = currentRow.iterator();
				Applicant applicant = new Applicant();
				applicant.setGroup(roomList.get(0).getGroup());
				int cellIdx = 0;
				while (cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();
					switch (cellIdx) {
						case 0:
							int num = (int)currentCell.getNumericCellValue();
							if(num>0 && num<=roomList.size()) applicant.setRoom(roomList.get(num-1));
							break;
						case 1:
							String dateStr = currentCell.getStringCellValue();
							SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
							applicant.setApplicantDate(formatter.parse(dateStr).toInstant());//.minusSeconds(32400));
							break;
						case 2:
							applicant.setApplicantName(currentCell.getStringCellValue());
							break;
						case 3:
							applicant.setApplicantEmail(currentCell.getStringCellValue());
							break;
						case 4:
							applicant.setApplicantPhone(currentCell.getStringCellValue());
							break;
						case 5:applicant.setApplicantAge((int)currentCell.getNumericCellValue());
							break;
						case 6:applicant.setApplicantUniv(currentCell.getStringCellValue());
							break;
						case 7:applicant.setApplicantGPA(currentCell.getNumericCellValue());
							break;
						case 8:applicant.setApplicantLicense(currentCell.getStringCellValue());
							break;
						case 9:applicant.setApplicantLang(currentCell.getStringCellValue());
							break;
						default:
							break;
					}
					cellIdx++;
				}
				applicantList.add(applicant);
			}
			workbook.close();
			return applicantList;
		} catch (IOException | ParseException e) {
			throw new RuntimeException("엑셀 파일 파싱 실패: " + e.getMessage());
		}
	}

	public static List<Resume> excelToResume(ApplicantRepository applicantRepository, int groupNo, InputStream is){
		//지원자 이름이랑 이메일로 객체 찾음
		//resume 객체에 지원자 넣음
		//헤더정보랑 cellno맞춰서 resume 객체 여러개 만들어야됨
		SHEET = "자기소개서";
		try {
			Workbook workbook = new XSSFWorkbook(is);
			Sheet sheet = workbook.getSheet(SHEET);
			Iterator<Row> rows = sheet.iterator();
			List<Resume> resumeList = new ArrayList<>();
			Map<Integer,String> questionMap = new HashMap<>();
			int rowNumber = 0;
			while (rows.hasNext()) {
				Row currentRow = rows.next();
				Iterator<Cell> cellsInRow = currentRow.iterator();
				int cellIdx = 0;

				// 제목부분=질문 문항 있음.
				// cellNo를 키값삼아 map으로 만들어놓기
				if (rowNumber == 0) {
					cellsInRow.next();//이름
					cellsInRow.next();//이메일
					cellIdx=2;
					while(cellsInRow.hasNext()){
						Cell currentCell = cellsInRow.next();
						questionMap.put(cellIdx++,currentCell.getStringCellValue());
					}
					rowNumber++;
					continue;
				}

				Applicant applicant = null;
				while (cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();

					if(cellIdx==0){
						//이름부분은 건너뜀
						cellIdx++;
						continue;
					}else if(cellIdx==1){
						//이메일로 지원자 객체 찾아놓음.
						applicant = applicantRepository.findByGroupGroupNoAndApplicantEmail(groupNo,currentCell.getStringCellValue());
						cellIdx++;
						continue;
					}

					Resume resume = new Resume();
					resume.setApplicant(applicant);
					resume.setResumeQuestion(questionMap.get(cellIdx));
					resume.setResumeAnswer(currentCell.getStringCellValue());
					resumeList.add(resume);

					cellIdx++;
				}
			}
			workbook.close();
			return resumeList;
		} catch (IOException e) {
			throw new RuntimeException("엑셀 파일 파싱 실패: " + e.getMessage());
		}
	}

	public static ByteArrayInputStream avgScorelistToExcel(String[] HEADERs, List<ExcelApplicant> body) {
		SHEET = "지원자 성적";
		try (Workbook workbook = new XSSFWorkbook();
			 ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			Sheet sheet = workbook.createSheet(SHEET);
			// Header
			Row headerRow = sheet.createRow(0);
			for (int col = 0; col < HEADERs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(HEADERs[col]);
			}
			int rowIdx = 1;
			int cellIdx;
			for (ExcelApplicant applicant:body) {
				Row row = sheet.createRow(rowIdx++);
				cellIdx = 0;
				row.createCell(cellIdx++).setCellValue(applicant.getName());
				row.createCell(cellIdx++).setCellValue(applicant.getEmail());
				for(Double score: applicant.getScores()){
					row.createCell(cellIdx++).setCellValue(score);
				}
			}
			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
		}
	}
}
