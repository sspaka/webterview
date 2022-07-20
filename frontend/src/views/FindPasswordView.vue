<template>
  <h2>비밀번호 찾기</h2>
 <!--<div>{{ code }}</div> -->

    <!-- 이메일 인증코드 보내기 -->
    <form @submit.prevent="sendmail(checkEmail)">
      <!-- <form> -->
      <div>
        <label for="UserEmail"></label>
        <input @input="writeEmail" id="UserEmail" v-model="credentials.useremail" type="email" placeholder="Enter UserEmail" required />
        <!-- 이메일 중복 확인 -->
        <button id="btn" @click='isClick'>인증코드 받기</button>
      </div>
      <div>
        <!-- 인증번호 확인 -->
        <input type="text" v-model="mailcode" placeholder="인증번호를 입력해주세요">
        <input type="button" value="인증 번호 확인" @click="codeCheck">
        <p v-if="CodeConfirm" style="color: red"> 코드가 일치하지 않습니다.</p>
      </div>
    </form>

    <div v-if="confirmed">비밀번호 재 설정
      <form @submit.prevent="saveNewPw(credentials)" @reset="onReset">
        <div>
          <label for="UserPassword">비밀번호:  </label>
          <input id="UserPassword" v-model="credentials.userpw" type="password" placeholder="Enter UserPassword" minlength="8" maxlength="50" required />
        </div>
        <div>
          <label for="UserPassword Confirmation">비밀번호 확인: </label>
          <input @input="passwordConfirm" id="UserPassword Confirmation" type="password" placeholder="Enter UserPassword again" minlength="8" maxlength="50" required />
          <p v-if="PasswordConfirm" style="color: red"> 비밀번호가 일치하지 않습니다.</p>
        </div>
        <button type="submit" >비밀번호 변경하기</button>
      </form>
    </div>
</template>

<script>
import { mapActions,mapGetters } from 'vuex'

export default {
  name: 'FindIdView',
  data() {
    return {
      CodeConfirm: false,
      confirmed: false,
      PasswordConfirm: false,
      mailcode: '',
      clicked: !!true,
      credentials: {
        useremail: '',
        userpw: '',
        useryn: true,
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
    ...mapActions(['sendmail', 'saveNewPw']),
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
        this.confirmed = true
        return true;
      }
    },
    passwordConfirm() {
        var p1 = document.getElementById('UserPassword').value;
        var p2 = document.getElementById('UserPassword Confirmation').value;
        if( p1 != p2 ) {
          //alert("비밀번호가 일치 하지 않습니다");
          this.PasswordConfirm = true
          return false;
        } else{
          //alert("비밀번호가 일치합니다");
          this.PasswordConfirm = false
          return true;
        }
      },
    isClick() {
      const btnElement = document.getElementById('btn');
      const html = '인증코드 재전송';
      btnElement.innerHTML = html;
      },
    }

  
}
</script>

<style>

</style>