package com.ssafy.webterview.util;

import com.ssafy.webterview.entity.Applicant;
import com.ssafy.webterview.entity.Evaluation;
import com.ssafy.webterview.entity.Group;
import com.ssafy.webterview.entity.Room;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
//	static String[] HEADERs;
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
				int cellIdx = 0;
				while (cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();
					switch (cellIdx) {
						case 0:
							int num = (int)currentCell.getNumericCellValue();
							if(num>0 && num<=roomList.size()) applicant.setRoom(roomList.get(num-1));
							break;
						case 1:
							applicant.setApplicantDate(currentCell.getDateCellValue().toInstant());
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
		} catch (IOException e) {
			throw new RuntimeException("엑셀 파일 파싱 실패: " + e.getMessage());
		}
	}
}
