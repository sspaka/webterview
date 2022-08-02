package com.ssafy.webterview.util;

import com.ssafy.webterview.entity.Evaluation;
import com.ssafy.webterview.entity.Group;
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
}
