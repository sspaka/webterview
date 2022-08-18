<template>
  <div class="information" style="">
    <div class="headLine2">자기소개서</div>

    <table class="table white-bg">
      <div v-for="resume in applicant.resumes" :key="resume.resumeNo">
        <div class="d-flex flex-col">
          <textarea v-model="resume.resumeQuestion" style="resize: none; background-color: #f5f5f5; height: 300px; text-align: left;"></textarea>
          <textarea v-model="resume.resumeAnswer" style="resize: none; height: 500px; text-align: left;"></textarea>
        </div>
      </div>
      <!-- <tbody>
        <tr v-for="resume in applicant.resumes" :key="resume.resumeNo"> 
          <th scope="row" style="text-align: left;">{{ resume.resumeQuestion }}: </th>
          <td colspan="3" style="text-align: left;">{{ resume.resumeAnswer }}</td>
        </tr>
      </tbody> -->
    </table>
    <!-- <div v-for="resume in applicant.resumes" :key="resume.resumeNo">
      <p>{{ resume.resumeQuestion }} : {{resume.resumeQuestion}}</p>
    </div> -->
    <!-- <div>
      안녕하십니까 지원자 김싸피입니다. '항상 연습을 실전처럼하자'가 저의 모토
      입니다. 하나의 결과물을 만들더라도 제대로 만드는 것을 좋아합니다. 저는
      1년간 삼성청년 소프트웨어 아카데미에서 개발자가 되기 위해 열심히
      노력하였습니다. 다양한 팀원들과 함께 공통,특화,자율 세 번의 프로젝트를
      진행하였으며 WebRtc, NFT, Tensorflow등 평소 쉽게 접하기 어려운 기술들을
      경험하고 실력을 쌓았습니다. 프로젝트는 최대한 실무와 동일한 환경으로
      진행하기 위해 git과 jira등을 활용하여 관리하였고 매일 아침 팀원들과
      스크럼을 진행하며 그날 하루의 일정을 공유하였습니다. 또한 스프린트를
      진행하며 프로젝트를 정해진 시간내에 완수하기 위해 항상 노력하였고. 항상
      최대한의 효율낼수 있는 방법을 고민하였습니다. 삼성청년 소프트웨어
      아카데미에서 활동 하는 동안 우수상도 여러번 수상하였습니다. 제가 A회사의
      ~부서에 입사하게 된다면 저의 이러한 경험을 바탕으로 빠르게 적응하고
      성장하여 회사에 큰 도움이 될수 있을것이라 생각합니다.
    </div> -->
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'

export default {
  data() {
    return {
      
    };
  },
  computed: {
    ...mapGetters(['applicant'])
  },
  methods: {
    ...mapActions(['fetchApplicant']),
  },
};
</script>

<style>
.information {
  margin: 10px;
  height: 80%;
  width: auto;
  /* overflow: auto; */
}
th {
  text-align: center;
}

</style>