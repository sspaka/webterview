<template>
  <!-- <div class="score">면접 평가창입니다.</div> -->
  <div class="score">
    <form @submit.prevent="uploadScoreSheet(credentials)">
      <h3>평가표</h3>
      <table>
        <!-- 평가문항에 대한 점수표분포를 만들어야됨 -->
        <!-- v-model에 계속 생성시켜줘야됨 -->
        <tr v-for="question in evalSheet" :key="question.evaluationNo">
          <th class="checks" scope="row">{{ question.evaluationQuestion }} :</th>
          <td><input type="radio" name="{{question.evaluationNo}}" value="5" v-model="question.evaluationQuestion" />A</td>
          <td><input type="radio" name="{{question.evaluationNo}}" value="4" v-model="question.evaluationQuestion" />B</td>
          <td><input type="radio" name="{{question.evaluationNo}}" value="3" v-model="question.evaluationQuestion" />C</td>
          <td><input type="radio" name="{{question.evaluationNo}}" value="2" v-model="question.evaluationQuestion" />D</td>
          <td><input type="radio" name="{{question.evaluationNo}}" value="1" v-model="question.evaluationQuestion" />F</td>
          <hr>
        </tr>
      </table>
      {{ credentials }}
      <!-- {{ evalSheet }} -->
      <h3>특이사항</h3>
      <div>
        <span>특이사항: </span>
        <p style="white-space: pre-line">{{ credentials.comment }}</p>
        <br>
        <textarea v-model="credentials.comment" placeholder="이곳에 입력하세요"></textarea>
        <button type="submit" class="btn btn-large">지원자 정보 저장</button>
      </div>
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
          comment: "",
          raterEvaluations:
            {
              Rater:"19",
              // 평가문항 가져오는 코드 짜야됨
              평가문항1: "5",
              평가문항2: "4"

            }
          
        },
      }
    },
    computed: {
      ...mapGetters(['groupNo', 'evalSheet'])
    },
    methods: {
      ...mapActions(['fetchEvalSheet', 'uploadScoreSheet']),
    //   countevalSheet(evalSheet) {
    //     evalSheet
    //   }
    },
    created() {
      // this.fetchEvalSheet(this.groupNo)
      this.fetchEvalSheet("300")
    //   this.countevalsheet(this.evalSheet)
      
    //   this.credentials.evaluations[0][this.evalSheet[0].evaluationQuestion] = ""
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


input[type="radio"]{width:30px; /*or whatever */}


</style>