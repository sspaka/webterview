package com.ssafy.webterview.service;

import com.ssafy.webterview.dto.EvaluationDto;
import com.ssafy.webterview.entity.*;
import com.ssafy.webterview.repository.*;
import com.ssafy.webterview.util.DEConverter;
import com.ssafy.webterview.util.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ScoreServiceImpl implements ScoreService {

	private EvaluationRepository evaluationRepository;
	private GroupRepository groupRepository;
	private ApplicantRepository applicantRepository;
	private GradeRepository gradeRepository;
	private RaterRepository raterRepository;
	private DEConverter converter;

	@Autowired
	public ScoreServiceImpl(EvaluationRepository evaluationRepository,
							GroupRepository groupRepository, ApplicantRepository applicantRepository,
							GradeRepository gradeRepository, RaterRepository raterRepository,
							DEConverter converter){
		this.evaluationRepository = evaluationRepository;
		this.groupRepository = groupRepository;
		this.applicantRepository = applicantRepository;
		this.gradeRepository = gradeRepository;
		this.raterRepository = raterRepository;
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

	@Override
	@Transactional
	public void saveScoreAndUnique(Map<String, Object> map) throws Exception {
		//한 지원자의 점수목록을 받고 전부 저장
		//(실시간 순위일경우 --> 가 아니므로 지금은 이 과정 안함) 저장됐다면 순위계산 후 applicant 테이블에 순위 저장
		Applicant applicant = applicantRepository.getReferenceById((Integer)map.get("applicantNo"));

		ArrayList<Map<String,Object>> list = (ArrayList<Map<String, Object>>) map.get("evaluations");
		if(list != null){
			for(Map<String,Object> evalmap:list){
				Rater rater = raterRepository.getReferenceById((int) evalmap.get("Rater"));
				for(Map.Entry<String,Object> entry:evalmap.entrySet()){
					if(entry.getKey().equals("Rater")) continue;
					Evaluation eval = evaluationRepository.getReferenceById(Integer.parseInt(entry.getKey()));
					gradeRepository.save(new Grade(null,rater,applicant,eval,(int)entry.getValue()));
				}
			}
		}
	}

	@Override
	public void avgScore(int applicantNo) throws Exception {

	}

}
