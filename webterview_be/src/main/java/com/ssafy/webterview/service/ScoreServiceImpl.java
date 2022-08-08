package com.ssafy.webterview.service;

import com.ssafy.webterview.dto.EvaluationDto;
import com.ssafy.webterview.entity.*;
import com.ssafy.webterview.repository.*;
import com.ssafy.webterview.util.DEConverter;
import com.ssafy.webterview.util.ExcelApplicant;
import com.ssafy.webterview.util.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ScoreServiceImpl implements ScoreService {

	private EvaluationRepository evaluationRepository;
	private GroupRepository groupRepository;
	private ApplicantRepository applicantRepository;
	private GradeRepository gradeRepository;
	private RaterRepository raterRepository;
	private UserRepository userRepository;
	private DEConverter converter;

	@Autowired
	public ScoreServiceImpl(EvaluationRepository evaluationRepository,
							GroupRepository groupRepository, ApplicantRepository applicantRepository,
							GradeRepository gradeRepository, RaterRepository raterRepository,
							UserRepository userRepository, DEConverter converter){
		this.evaluationRepository = evaluationRepository;
		this.groupRepository = groupRepository;
		this.applicantRepository = applicantRepository;
		this.gradeRepository = gradeRepository;
		this.raterRepository = raterRepository;
		this.userRepository = userRepository;
		this.converter = converter;
	}

	@Override
	public List<EvaluationDto> saveQuestion(int groupNo, MultipartFile file) throws Exception {
		Group group = groupRepository.getReferenceById(groupNo);
		List<Evaluation> evaluationList = new ArrayList<>();
		evaluationList.add(new Evaluation(null,group,"종합평가",2));
		evaluationList.addAll(ExcelHelper.excelToEvaluations(group, file.getInputStream()));
		return converter.toEvaluationDtoList(evaluationRepository.saveAll(evaluationList));
	}

	@Override
	@Transactional
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
	public List<Map<String,Object>> calcScore(int applicantNo) throws Exception {
		return gradeRepository.getAvgScores(applicantNo);
	}

	@Override
	public List<Map<String,Object>> calcScoreList(int groupNo) throws Exception {
		return gradeRepository.getAvgScoreList(groupNo);
	}

	@Override
	public List<Map<String, Object>> avgScoreList(int groupNo) throws Exception {
		List<Map<String, Object>> ranking = gradeRepository.getRanking(groupNo);
		Map<Integer,Map<String,Object>> result = new HashMap<>();
		for(Map<String,Object> map:ranking){
			int key = (int)map.get("applicantNo");

			if(!result.containsKey(key)) {
				result.put(key, new HashMap<>(map));
				result.get(key).remove("avg");
			}

			if((int)map.get("type")==1){
				result.get(key).put("score1",(double)map.get("avg"));
			}else{
				result.get(key).put("score2",(double)map.get("avg"));
			}
		}

		return new ArrayList<>(result.values());
	}

	@Override
	public String getExcelTitle(int userNo) throws Exception {
		Group group = groupRepository.getCurrentGroup(userNo);
		return "score_"+DateTimeFormatter.ofPattern("yyyyMMdd").withZone(ZoneId.systemDefault()).format(group.getGroupStartDate())+".xlsx";
	}

	@Override
	public ByteArrayInputStream exportExcel(int groupNo) throws Exception {
		//평가점수 저장된 리스트
		List<Map<String,Object>> avgScoreList = gradeRepository.getAvgScoreList(groupNo);
		Collections.sort(avgScoreList,(o1, o2) -> (int)o1.get("no")-(int)o2.get("no"));

		//평가문항 리스트
		List<EvaluationDto> evaluationList = converter.toEvaluationDtoList(evaluationRepository.findByGroupGroupNo(groupNo));
		Collections.sort(evaluationList,(o1,o2)->o1.getGroupNo()-o2.getGroupNo());

		//헤더
		String[] HEADERs = new String[evaluationList.size()+2];
		HEADERs[0] = "이름";
		HEADERs[1] = "이메일";
		for(int i=0;i< evaluationList.size();i++){
			HEADERs[i+2] = evaluationList.get(i).getEvaluationQuestion();
		}

		//평가문항 셀번호 map
		Map<Integer,Integer> evaluationCellMap = new HashMap<>();
		int i=0;
		for(EvaluationDto eval:evaluationList){
			evaluationCellMap.put(eval.getEvaluationNo(),i++);
		}

		//body
		ArrayList<ExcelApplicant> body = new ArrayList<>();
		for(i=0;i<avgScoreList.size();i++){
			if(i==0 || body.get(body.size()-1).getNo() != (int)avgScoreList.get(i).get("no")){
				body.add(new ExcelApplicant(
							(int)avgScoreList.get(i).get("no"),
							(String)avgScoreList.get(i).get("name"),
							(String)avgScoreList.get(i).get("email"),
							evaluationList.size()
						));
			}
			body.get(body.size()-1).getScores()[evaluationCellMap.get((int)avgScoreList.get(i).get("evalNo"))] = (double)avgScoreList.get(i).get("avg");
		}

		ByteArrayInputStream in = ExcelHelper.avgScorelistToExcel(HEADERs, body);
		return in;

	}
}
