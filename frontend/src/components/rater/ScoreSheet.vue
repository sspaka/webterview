<template>
  <!-- <div class="score">면접 평가창입니다.</div> -->
  <div class="score">
    <form @submit.prevent="uploadScoreSheet(credentials)">
      <h3>평가표</h3>
      <table>
        <tr v-for="question in evalSheet" :key="question.evaluationNo">
          <th class="checks" scope="row">{{ question.evaluationQuestion }} :</th>
          <td><input type="radio" name="chk_info" value="5" v-model="chk_info" />A</td>
          <td><input type="radio" name="chk_info" value="4" v-model="chk_info" />B</td>
          <td><input type="radio" name="chk_info" value="3" v-model="chk_info" />C</td>
          <td><input type="radio" name="chk_info" value="2" v-model="chk_info" />D</td>
          <td><input type="radio" name="chk_info" value="1" v-model="chk_info" />F</td>
        </tr>
      </table>
      {{ credentials }}
      <!-- {{ evalSheet }} -->
      <h3>특이사항</h3>
      <div>
        <span>특이사항: </span>
        <p style="white-space: pre-line">{{ credentials.comment }}</p>
        <br>
        <textarea v-model="credentials.comment" placeholder="여러줄을 입력해보세요"></textarea>
        <!-- <input class="card" type="textarea" /> -->
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
          evaluations:[
            {
              Rater:"19",
            }
          ]
        }
      }
    },
    computed: {
      ...mapGetters(['groupNo', 'evalSheet'])
    },
    methods: {
      ...mapActions(['fetchEvalSheet']),
    },
    created() {
      this.fetchEvalSheet("300")
      // this.credentials.evaluations[0][this.evalSheet[0].evaluationQuestion] = ""
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
