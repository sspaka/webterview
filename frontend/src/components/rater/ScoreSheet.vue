<template>
  <!-- <div class="score">면접 평가창입니다.</div> -->
  <div class="score">
    <form @submit.prevent="uploadScoreSheet(credentials)">
      <h3>평가표</h3>
      <table>
        <!-- 평가문항에 대한 점수표분포를 만들어야됨 -->
        <!-- v-model에 계속 생성시켜줘야됨 -->
        <tr v-for="question in evalSheet" :key="question.evaluationNo">
          <div v-if="question.evaluationQuestion !== '특이사항'">
            <th class="checks" scope="row">{{ question.evaluationQuestion }} :</th>
            <td><input type="radio" :name=question.evaluationNo value="5" v-model="credentials.evalList[question.evaluationNo]" />A</td>
            <td><input type="radio" :name=question.evaluationNo value="4" v-model="credentials.evalList[question.evaluationNo]" />B</td>
            <td><input type="radio" :name=question.evaluationNo value="3" v-model="credentials.evalList[question.evaluationNo]" />C</td>
            <td><input type="radio" :name=question.evaluationNo value="2" v-model="credentials.evalList[question.evaluationNo]" />D</td>
            <td><input type="radio" :name=question.evaluationNo value="1" v-model="credentials.evalList[question.evaluationNo]" />F</td>
            <hr>
          </div>
          <div v-else>
            <h3>특이사항</h3>
            <textarea v-model="credentials.evalList[question.evaluationNo]" placeholder="지원자의 특이사항을 입력" style="width: 100%"></textarea>
          </div>
        </tr>
      </table>
      <p style="white-space: pre-line">{{ credentials }}</p>
      <button type="submit" class="btn btn-primary">점수 올리기</button>
    </form>
  </div>
</template>

<script>
  import { mapActions, mapGetters } from 'vuex'

  export default {
    name: 'ScoreSheetComponent',
    data() {
      return {
        credentials: {
          Rater: 155, // 어떻게 가져오지?
          applicantNo: 456, // 어떻게 가져오지?
          evalList:{
              
          },
        },
      }
    },
    computed: {
      ...mapGetters(['groupNo', 'evalSheet',])
    },
    methods: {
      ...mapActions(['fetchEvalSheet', 'uploadScoreSheet']),

    },
    created() {
      this.fetchEvalSheet("270")
      
      for(var i=0; i< this.evalSheet.length; i++){
        this.credentials.evalList[this.evalSheet[i]["evaluationNo"]] = ""
      }
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
  width: 30px; /*or whatever */
}
</style>
