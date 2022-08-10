<template>
  <!-- <div class="info">지원자 정보와 창입니다.</div> -->
  <div class="profile">
    <h3 class="clickh3" @click="modalgo">입사지원서</h3>
    <div style="margin-top: 10px; margin-bottom: 10px">이름: {{ applicant.applicantName}}</div>
    <table class="table blind" v-if="모달창열렸니==true && groupBlind">
      <div>현재 블라인드 채용전형 입니다.</div>
    </table>
    <table class="table white-bg" v-if="모달창열렸니 == true && groupBlind==false">
      <tbody>
        <tr>
          <th scope="row">이름:</th>
          <td colspan="3">{{ applicant.applicantName}}</td>
        </tr>
        <tr>
          <th scope="row">나이:</th>
          <td colspan="3">{{ applicant.applicantAge }}</td>
        </tr>
        <tr>
          <th scope="row">이메일:</th>
          <td colspan="3">{{ applicant.applicantEmail }}</td>
        </tr>
        <tr>
          <th scope="row">대학교:</th>
          <td colspan="3">{{ applicant.applicantUniv }}</td>
        </tr>
        <tr>
          <th>학점:</th>
          <td colspan="3">{{ applicant.applicantGPA}}</td>
        </tr>
        <tr>
          <th>자격증:</th>
          <td colspan="3">{{ applicant.applicantLicense}}</td>
        </tr>
        <tr>
          <th>어학점수:</th>
          <td colspan="3">{{ applicant.applicantLang }}</td>
        </tr>
        <tr>
          <th>특이사항:</th>
          <td colspan="3">{{ applicant.applicantUnique }}</td>
        </tr>
        <tr>
          <th>전화번호:</th>
          <td colspan="3">{{ applicant.applicantPhone}}</td>
        </tr>
      </tbody>
    </table>
    <IntroduceMySelf />
  </div>
  <div></div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
//
import IntroduceMySelf from "./IntroduceMySelf.vue";
export default {
  name: "AboutApplicant",

  components: {
    IntroduceMySelf,
  },
  data() {
    return {
      // 나중에 받아오는 정보로 바꿔줘야 해해해해
      applicantEmail: "kim@naver.com",
      groupNo: "270",
      모달창열렸니: false,
    };
  },
  computed: {
    ...mapGetters(['applicant', 'groupBlind'])
  },
  methods: {
    ...mapActions(['fetchApplicant']),
    modalgo() {
      this.모달창열렸니 = !this.모달창열렸니;
    },
  },
  created() {
    this.fetchApplicant({ applicantEmail: this.applicantEmail, groupNo: this.groupNo })
  }
};
</script>

<style scoped>
.profile {
  background-color: #ffffff;
  width: 100%;
  height: 100%;
  padding: 3rem;
  border-radius: 1rem;
}

.profile img {
  width: 100%;
  height: 100%;
}

th {
  text-align: center;
}

.black-bg {
  width: auto;
  height: auto;
  background: rgba(0, 0, 0, 0.5);
  position: fixed;
  padding: 20px;
}
.white-bg {
  width: 100%;
  background: white;
  border-radius: 8px;
  padding: 20px;
}

.clickh3 :hover {
  color: #30475e;
}

th{
  text-align: center;
}

.black-bg {
  width: auto; height:auto;
  background: rgba(0,0,0,0.5);
  position: fixed; padding: 20px;
}
.white-bg {
  width: 100%; background: white;
  border-radius: 8px;
  padding: 20px;
} 

.clickh3 :hover {
  color:#30475e;
}

.blind {
  background-color: black;
  color: white;
}

</style>
