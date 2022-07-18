//views/FindPasswordView.vue

<template>
  <div>
    여기는 비밀번호 찾기
  </div>
  <form @submit.prevent="sendmail(checkEmail)">
  <div>
    <label for="UserEmail">이메일:</label>
    <input @input="writeEmail" id="UserEmail" v-model="credentials.UserEmail" type="email" required/>
  </div>

    <!-- 버튼동작시 이동되는 페이지 만들어야됨 -->
    <button type="submit">찾기</button>
    <input type="text" v-model="mailcode" placeholder="인증번호를 입력해주세요">
        <input type="button" value="인증 번호 확인" @click="codeCheck">
        <p v-if="CodeConfirm" style="color: red"> 코드가 일치하지 않습니다.</p>
  </form>
  <div>{{ code }}</div>
</template>

<script>
import { mapActions,mapGetters } from 'vuex'

export default {
  name: 'FindIdView',
  data() {
    return {
      CodeConfirm: false,
      mailcode: '',
      credentials: {
        userName: '',
        UserPhone: '',
        UserEmail: '',
      },
      checkEmail: {
          email: "",
          type: "findPw"
      },
    }
  },
  computed: {
    ...mapGetters(['authError', 'code'])
    
  },
  methods: {
    ...mapActions(['sendmail']),
    writeEmail() {
        this.checkEmail.email = this.credentials.useremail
    },
    
    codeCheck() {
      var p1 = this.code
      var p2 = this.mailcode
      if(p1 != p2) {
        this.CodeConfirm = true
        return false;
      } else{
        this.CodeConfirm = false
        // this.credentials.useryn = true
        return true;
      }
    }

  }
}
</script>

<style>

</style>