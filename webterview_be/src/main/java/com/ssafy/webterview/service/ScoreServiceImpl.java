package com.ssafy.webterview.service;

import com.ssafy.webterview.dto.EvaluationDto;
import com.ssafy.webterview.entity.Evaluation;
import com.ssafy.webterview.entity.Group;
import com.ssafy.webterview.repository.EvaluationRepository;
import com.ssafy.webterview.repository.GroupRepository;
import com.ssafy.webterview.util.DEConverter;
import com.ssafy.webterview.util.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {

	private EvaluationRepository evaluationRepository;
	private GroupRepository groupRepository;
	private DEConverter converter;
	@Autowired
	public ScoreServiceImpl(EvaluationRepository evaluationRepository, GroupRepository groupRepository, DEConverter converter){
		this.evaluationRepository = evaluationRepository;
		this.groupRepository = groupRepository;
		this.converter = converter;
	}

	@Override
	public void saveQuestion(int groupNo, MultipartFile file) throws Exception {
		Group group = groupRepository.getReferenceById(groupNo);
		List<Evaluation> evaluationList = ExcelHelper.excelToEvaluations(group, file.getInputStream());
		evaluationRepository.saveAll(evaluationList);
	}

	@Override
	public void deleteQuestion(int groupNo) throws Exception {
		evaluationRepository.deleteByGroupGroupNo(groupNo);
	}

	@Override
	public List<EvaluationDto> getQuestion(int groupNo) throws Exception {
		return converter.toEvaluationDtoList(evaluationRepository.findByGroupGroupNo(groupNo));
	}

}
