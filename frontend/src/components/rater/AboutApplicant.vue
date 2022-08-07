<template>
  <!-- <div class="info">지원자 정보와 창입니다.</div> -->
  <div class="impormation">
    <h3 class="clickh3" @click="modalgo">입사지원서</h3>
    
    <div style="margin-top: 10px; margin-bottom: 10px;">이름: 테스트지원자</div>
  <table class="table white-bg"  v-if="모달창열렸니 == true">
  <tbody>
    <tr>
      <th scope="row">이름:</th>
      <td colspan="3">테스트지원자</td>

    </tr>
    <tr>
      <th scope="row">나이:</th>
      <td colspan="3">25</td>
    </tr>
    <tr>
      <th scope="row">이메일:</th>
      <td colspan="3">test@applicant.co.kr</td>
      
    </tr>
    <tr>
      <th scope="row">대학교:</th>
      <td colspan="3">싸피대학교</td>
    </tr>
    <tr>
      <th>학점:</th>
      <td colspan="3">4.5</td>
    </tr>
    <tr>
      <th>자격증:</th>
      <td>정보처리기사</td>
      <td>ADsP</td>
      <td>SQLD</td>
    </tr>
    <tr>
      <th>어학점수:</th>
      <td colspan="3">토스 LV6</td>
    </tr>
    <tr>
      <th>특이사항:</th>
      <td colspan="3"></td>
    </tr>
    <tr>
      <th>전화번호:</th>
      <td colspan="3">010-1111-2222</td>
    </tr>
  </tbody>
</table>
</div>
<div><IntroduceMySelf/></div>



</template>

<script>
// 
import IntroduceMySelf from '../rater/IntroduceMySelf.vue';
export default {
  name: "AboutApplicant",

  components: {
    IntroduceMySelf,
  },
  data() {
    return {
      모달창열렸니 : false,
    }
  },
  methods: {
    modalgo() {
      this.모달창열렸니 = !this.모달창열렸니
    }
  }
}
</script>

<style scoped>
.impormation {
  background-color: #ffffff;
  width: 100%;
  padding: 3rem;
  border-radius: 1rem;
}

.impormation img {
  width: 100%;
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

</style>
