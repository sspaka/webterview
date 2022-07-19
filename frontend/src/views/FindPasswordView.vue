<template>
  <div>
    여기는 비밀번호 찾기
  </div>
 <div>{{ code }}</div>

    <!-- 이메일 인증코드 보내기 -->
    <form @submit.prevent="sendmail(checkEmail)">
      <div>
        <label for="UserEmail">이메일: </label>
        <input @input="writeEmail" id="UserEmail" v-model="credentials.useremail" type="email" placeholder="Enter UserEmail" required />
        
        <!-- 이메일 중복 확인 -->
        <button type="submit"> 인증번호 받기</button>
        
        <!-- 인증번호 확인 -->
        <input type="text" v-model="mailcode" placeholder="인증번호를 입력해주세요">
        <input type="button" value="인증 번호 확인" @click="codeCheck">
        <p v-if="CodeConfirm" style="color: red"> 코드가 일치하지 않습니다.</p>
      </div>
    </form>

    <div v-if="newpw">비밀번호 재 설정
      새 비밀번호

<!--       
      <div>
        <label for="UserPassword">비밀번호:  </label>
        <input id="UserPassword" v-model="credentials.userpw" type="password" placeholder="Enter UserPassword" minlength="8" maxlength="50" required />
      </div>
      <div style="display: flex">
        <label for="UserPassword Confirmation">비밀번호 확인: </label>
        <input @input="passwordConfirm" id="UserPassword Confirmation" v-model="userpw2" type="password" placeholder="Enter UserPassword again" minlength="8" maxlength="50" required />
        
        <p v-if="PasswordConfirm" style="color: red"> 비밀번호가 일치하지 않습니다.</p>
      </div> -->

    </div>
</template>

<script>
import { mapActions,mapGetters } from 'vuex'

export default {
  name: 'FindIdView',
  data() {
    return {
      CodeConfirm: false,
      newpw: false,
      mailcode: '',
      credentials: {
        useremail: '',
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
        this.newpw = true
        return true;
      }
    }

  }
}
</script>

<style>

</style>