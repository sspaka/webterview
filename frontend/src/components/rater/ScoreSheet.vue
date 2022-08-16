<template>
  <!-- <div class="score">면접 평가창입니다.</div> -->
  <div class="score">
    <form @submit.prevent="upload">
      <div class="d-flex justify-content-between">
        <p style="color: #fff">aaaa</p>
        <div class="headLine2">평가표</div>
        <button type="submit" class="btn-sm btn-primary">점수 올리기</button>
      </div>
      <table>
        <tr>
          <th class="checks" scope="row">
            문항별 점수 | A(5) | B(4) | C(3) | D(2) | F(1) |
          </th>
        </tr>
        <hr />
        <tr v-for="question in evalSheet" :key="question.evaluationNo">
          <div v-if="question.evaluationQuestion != null">
            <div
              v-if="question.evaluationQuestion !== '특이사항'"
              class="align"
            >
              <th class="checks" scope="row">
                {{ question.evaluationQuestion }} : &nbsp;
              </th>
              <td>
                <label :for="question.evalutionNo" class="radio-btn">
                  <input
                    :id="question.evalutionNo"
                    type="radio"
                    :name="question.evaluationNo"
                    value="5"
                    v-model="credentials.evalList[question.evaluationNo]"
                  />
                  <span class="abcde">A</span>
                </label>
              </td>

              <td>
                <label :for="question.evalutionNo" class="radio-btn">
                  <input
                    :id="question.evalutionNo"
                    type="radio"
                    :name="question.evaluationNo"
                    value="4"
                    v-model="credentials.evalList[question.evaluationNo]"
                  />
                  <span class="abcde">B</span>
                </label>
              </td>

              <td>
                <label :for="question.evalutionNo" class="radio-btn">
                  <input
                    :id="question.evalutionNo"
                    type="radio"
                    :name="question.evaluationNo"
                    value="3"
                    v-model="credentials.evalList[question.evaluationNo]"
                  />
                  <span class="abcde">C</span>
                </label>
              </td>

              <td>
                <label :for="question.evalutionNo" class="radio-btn">
                  <input
                    :id="question.evalutionNo"
                    type="radio"
                    :name="question.evaluationNo"
                    value="2"
                    v-model="credentials.evalList[question.evaluationNo]"
                  />
                  <span class="abcde">D</span>
                </label>
              </td>

              <td>
                <label :for="question.evalutionNo" class="radio-btn">
                  <input
                    :id="question.evalutionNo"
                    type="radio"
                    :name="question.evaluationNo"
                    value="1"
                    v-model="credentials.evalList[question.evaluationNo]"
                  />
                  <span class="abcde">F</span>
                </label>
              </td>

              <!-- <td> B<input type="radio" :name=question.evaluationNo value="4" v-model="credentials.evalList[question.evaluationNo]" /></td>
            <td> C<input type="radio" :name=question.evaluationNo value="3" v-model="credentials.evalList[question.evaluationNo]" /></td>
            <td> D<input type="radio" :name=question.evaluationNo value="2" v-model="credentials.evalList[question.evaluationNo]" /></td>
            <td> F<input type="radio" :name=question.evaluationNo value="1" v-model="credentials.evalList[question.evaluationNo]" /></td> -->
              <hr />
            </div>
            <div v-else>
              <h3>특이사항</h3>
              <textarea
                v-model="credentials.evalList[question.evaluationNo]"
                placeholder="지원자의 특이사항을 입력"
                style="width: 100%"
              ></textarea>
            </div>
          </div>
        </tr>
      </table>
      <!-- <p style="white-space: pre-line">{{ credentials }}</p> -->
    </form>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

export default {
  name: "ScoreSheetComponent",
  data() {
    return {
      credentials: {
        Rater: "",
        applicantNo: "", // 어떻게 가져오지?
        evalList: {},
      },
    };
  },
  computed: {
    ...mapGetters([
      "raterGroupNo",
      "evalSheet",
      "raterNo",
      "applicantEmail",
      "currentApplicant",
      "applicant",
    ]),
    check_applicant() {
      return this.$store.getters.applicant;
    },
  },
  watch: {
    check_applicantEmail(val) {
      this.credentials.applicantNo = val.applicantNo;
    },
  },
  methods: {
    ...mapActions(["fetchEvalSheet", "uploadScoreSheet"]),
    upload() {
      this.credentials.applicantNo = this.currentApplicant.applicantNo;
      this.credentials.Rater = this.raterNo
      this.uploadScoreSheet(this.credentials);
    },
  },
  created() {
    console.log(this.raterGroupNo)
    this.fetchEvalSheet(this.raterGroupNo);
    this.credentials.Rater = this.raterNo;

    for (var i = 0; i < this.evalSheet.length; i++) {
      this.credentials.evalList[this.evalSheet[i]["evaluationNo"]] = "";
    }
    this.credentials.applicantNo = this.applicant.applicantNo;
  },
};
</script>

<style scoped>
.score {
  background-color: #ffffff;
  width: 100%;
  height: 100%;
  padding: 3rem;
  border-radius: 1rem;
  white-space: nowrap;
  overflow: auto;
  text-overflow: ellipsis;
}

.score table {
  width: 100%;
  height: 100%;
}

.score img {
  width: 100%;
  height: 100%;
}

.card {
  width: 100%;
  height: 100%;
}

/* input[type="radio"] {

  padding-left: 10%;
}

input[type="radio"] {
  width: 40px;
} */

.radio-btn input[type="radio"] {
  display: none;
}

/*  */
.radio-btn input[type="radio"] + span {
  border-radius: 5px;
  display: inline-block;
  padding: 10px 10px;
  border: 1px solid #dfdfdf;
  background-color: #ffffff;
  text-align: center;
  cursor: pointer;
  font-size: 5px;
}

.radio-btn input[type="radio"]:checked + span {
  background-color: #3b5b7b;
  color: #ffffff;
}

.radio-btn input[type="radio"]:hover + span {
  background-color: #8ca0b3;
}

.abcde {
  margin-right: 1px;
  margin-right: 100%;
}

.checks {
  width: 105px;
}
</style>
