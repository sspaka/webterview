<template>
  <div class="container">

    <!-- <div v-if="authError" title="로그인 정보가 정확하지 않습니다" class="mx-4" style="max-width: 30rem;">
      <div>
        <account-error-list v-if="authError"></account-error-list>
      </div>
    </div> -->

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


    <form @submit.prevent="signup(credentials)" @reset="onReset">
      <!-- 비밀번호 확인 -->
      <div>
        <label for="UserPassword">비밀번호:  </label>
        <input id="UserPassword" v-model="credentials.userpw" type="password" placeholder="Enter UserPassword" minlength="8" maxlength="50" required />
      </div>
      <!-- 비밀번호 재확인 -->
      <div style="display: flex">
        <label for="UserPassword Confirmation">비밀번호 확인: </label>
        <input @input="passwordConfirm" id="UserPassword Confirmation" v-model="userpw2" type="password" placeholder="Enter UserPassword again" minlength="8" maxlength="50" required />
        
        <p v-if="PasswordConfirm" style="color: red"> 비밀번호가 일치하지 않습니다.</p>
      </div>


      <div>
        <label for="UserDepartment">소속(회사명): </label>
        <input id="UserDepartment" v-model="credentials.userdept" type="text" placeholder="Enter UserDepartment" required />
      </div>
      <div>
        <label for="UserRole">역할: </label>
        <input id="UserRole" v-model="credentials.userrole" type="text" placeholder="Enter UserRole" required />
      </div>
      
      <div>
        <label for="UserName">이름: </label>
        <input id="UserName" v-model="credentials.username" type="text" placeholder="Enter UserName" required />
      </div>
      <div>
        <label for="UserPhone">전화번호: </label>
        <input id="UserPhone" v-model="credentials.userphone" type="tel" placeholder="Enter UserUserPhone" pattern = "[0-9]{3}-[0-9]{4}-[0-9]{4}" required />
      </div>
  
      <button type="submit">회원가입</button>
      <button type="reset">Reset</button>
    </form>
    <router-link to="/">이전</router-link>
  </div>

  
</template>

<script>
  import { mapActions, mapGetters } from 'vuex'
  //import AccountErrorList from '@/components/AccountErrorList.vue'

  export default {
    name: 'SignupView',
    components: {
      //AccountErrorList,
    },
    data() {
      return {
        // htmlString: '<p style="color:red;">비밀번호가 다릅니다.</p>',
        CodeConfirm: false,
        PasswordConfirm: false,
        mailcode: '',
        userpw2: '',
        credentials: {
          // userrole: '',
          useremail: '',
          username: '',
          userpw: '',
          userdept: '',
          userphone: '',
          useryn: false,
        },
        checkEmail: {
          email: "",
          type: "register"
        },
      }
    },
    computed: {
      ...mapGetters(['authError', 'code'])
    },
    methods: {
      ...mapActions(['signup', 'sendmail']),
      // ...mapActions(['codecheck'])
      writeEmail() {
        this.checkEmail.email = this.credentials.useremail
      },
      codeCheck() {
        var p1 = this.code
        var p2 = this.mailcode
        if( p1 != p2 ) {
          //alert("비밀번호가 일치 하지 않습니다");
          this.CodeConfirm = true
          return false;
        } else{
          //alert("비밀번호가 일치합니다");
          this.CodeConfirm = false
          this.credentials.useryn= true
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
      onReset(event) {
        event.preventDefault()
        // Reset our form values
        this.credentials.userrole = ''
        this.credentials.useremail = ''
        this.credentials.userpw = ''
        this.credentials.userpw2 = ''
        this.credentials.username = ''
        this.credentials.userdept = ''
        this.credentials.userphone = ''
      }
    },
  }
</script>

<style scoped></style>
