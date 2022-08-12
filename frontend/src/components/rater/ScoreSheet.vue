<template>
  <!-- <div class="score">면접 평가창입니다.</div> -->
  <div class="score">
    <form @submit.prevent="upload">
      <div class="d-flex justify-content-between">
        <p style="color: #fff" >aaaa</p>
        <div class="headLine2">평가표</div>
        <button type="submit" class="btn btn-primary">점수 올리기</button>
      </div>
      <table>
        <!-- 평가문항에 대한 점수표분포를 만들어야됨 -->
        <!-- v-model에 계속 생성시켜줘야됨 -->
        <tr>
          <th class="checks" scope="row"> 문항별 점수 | A(5) | B(4) | C(3) | D(2) | F(1) |</th>
          <hr>
        </tr>
        <tr v-for="question in evalSheet" :key="question.evaluationNo">
          <div v-if="question.evaluationQuestion !== '특이사항'" class="align">
            <th class="checks" scope="row">{{ question.evaluationQuestion }} : &nbsp;</th>
            <td> A<input type="radio" :name=question.evaluationNo value="5" v-model="credentials.evalList[question.evaluationNo]" /></td>
            <td> B<input type="radio" :name=question.evaluationNo value="4" v-model="credentials.evalList[question.evaluationNo]" /></td>
            <td> C<input type="radio" :name=question.evaluationNo value="3" v-model="credentials.evalList[question.evaluationNo]" /></td>
            <td> D<input type="radio" :name=question.evaluationNo value="2" v-model="credentials.evalList[question.evaluationNo]" /></td>
            <td> F<input type="radio" :name=question.evaluationNo value="1" v-model="credentials.evalList[question.evaluationNo]" /></td>
            <hr>
          </div>
          <div v-else>
            <h3>특이사항</h3>
            <textarea v-model="credentials.evalList[question.evaluationNo]" placeholder="지원자의 특이사항을 입력" style="width: 100%"></textarea>
          </div>
        </tr>
      </table>
      <!-- <p style="white-space: pre-line">{{ credentials }}</p> -->
    </form>
    {{ credentials }}
  </div>
</template>

<script>
  import { mapActions, mapGetters } from 'vuex'

  export default {
    name: 'ScoreSheetComponent',
    data() {
      return {
        credentials: {
          Rater: "",
          applicantNo: "", // 어떻게 가져오지?
          evalList:{
              
          },
        },
      }
    },
    computed: {
      ...mapGetters(['groupNo', 'evalSheet','raterCode', 'applicantEmail', 'currentApplicant', 'applicant']),
      check_applicant() {return this.$store.getters.applicant}
    },
    watch: {
      check_applicantEmail(val) {
        this.credentials.applicantNo = val.applicantNo
      }
    },
    methods: {
      ...mapActions(['fetchEvalSheet', 'uploadScoreSheet']),
      upload() {
        this.credentials.applicantNo = this.applicant.applicantNo
        this.uploadScoreSheet(this.credentials)
      }

    },
    created() {
      this.fetchEvalSheet(this.groupNo)
      this.credentials.Rater = this.raterCode
      
      for(var i=0; i< this.evalSheet.length; i++){
        this.credentials.evalList[this.evalSheet[i]["evaluationNo"]] = ""
      }
      this.credentials.applicantNo = this.applicant.applicantNo

    },
  }
</script>

<style scoped>
.score {
  background-color: #ffffff;
  width: 100%;
  height: 100%;
  padding: 3rem;
  border-radius: 1rem;
}

.score table {
  width: 100%;
  height: 70%;
}

.score img {
  width: 100%;
  height: 100%;
}

.card {
  width: 100%;
  height: 100%;
}

input[type="radio"] {
  /* width: 50%; or whatever */
  padding-left: 10%;
}

input[type="radio"] {
  width: 40px; /*or whatever */
}


</style>
