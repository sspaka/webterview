<template>
  <h2>회원가입</h2>
  <div class="container">
    <!-- <div>{{ code }}</div> -->
    <!-- 이메일 인증코드 보내기 -->
    <form @submit.prevent="sendmail(checkEmail)">
      <div>
        <label for="UserEmail">이메일: </label>
        <input @input="writeEmail" id="UserEmail" v-model="credentials.userEmail" type="email" placeholder="Enter UserEmail" required />
        <!-- 이메일 중복 확인 -->
        <button id="btn" @click='isClick'> 인증번호 받기</button>
      </div>
      <div>
        <!-- 인증번호 확인 -->
        <input type="text" v-model="mailcode" placeholder="인증번호를 입력해주세요">
        <input type="button" value="인증 번호 확인" @click="codeCheck">
        <p v-if="CodeConfirm" style="color: red"> 코드가 일치하지 않습니다.</p>
      </div>
    </form>

    <div v-if="confirmed">
      <form @submit.prevent="signup(credentials)" @reset="onReset">
        <!-- 비밀번호 확인 -->
        <div>
          <label for="UserPassword">비밀번호:  </label>
          <input id="UserPassword" v-model="credentials.userPw" type="password" placeholder="Enter UserPassword" minlength="8" maxlength="50" required />
        </div>
        <!-- 비밀번호 재확인 -->
        <div style="display: flex">
          <label for="UserPassword Confirmation">비밀번호 확인: </label>
          <input @input="passwordConfirm" id="UserPassword Confirmation" v-model="userpw2" type="password" placeholder="Enter UserPassword again" minlength="8" maxlength="50" required />
          
          <p v-if="PasswordConfirm" style="color: red"> 비밀번호가 일치하지 않습니다.</p>
        </div>


        <div>
          <label for="UserDepartment">소속(회사명): </label>
          <input id="UserDepartment" v-model="credentials.userDept" type="text" placeholder="Enter UserDepartment" required />
        </div>
        <div>
          <label for="UserRole">역할: </label>
          <input id="UserRole" v-model="credentials.userRole" type="text" placeholder="Enter UserRole" required />
        </div>
        
        <div>
          <label for="UserName">이름: </label>
          <input id="UserName" v-model="credentials.userName" type="text" placeholder="Enter UserName" required />
        </div>
        <div>
          <label for="UserPhone">전화번호: </label>
          <input id="UserPhone" v-model="credentials.userPhone" type="tel" placeholder="Enter UserUserPhone" pattern = "[0-9]{3}-[0-9]{4}-[0-9]{4}" required />
        </div>
    
        <button type="submit">회원가입</button>
        <button type="reset">Reset</button>
      </form>
    </div>
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
        confirmed: false,
        mailcode: '',
        userpw2: '',
        credentials: {
          userDept: "",
          userEmail: "",
          userName: "",
          userNo: 0,
          userPhone: "",
          userPw: "",
          userRole: "",
          userYn: true
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
        this.checkEmail.email = this.credentials.userEmail
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
      },
      isClick() {
      const btnElement = document.getElementById('btn');
      const html = '<div> 인증코드 재전송 </div>';
      btnElement.innerHTML = html;
      },
    },
  }
</script>

<style scoped></style>
