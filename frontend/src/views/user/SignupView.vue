<template>
<img class="logo" src="@/assets/logo.png" alt="Logo" style="position: absolute; top: 20px; left:20px; width: 200px;" @click="logo">
<div class="limiter d-flex justify-content-center">
  <div class="container-login100 shadow-lg">
    <div class="card shadow-lg p-3 mb-5 bg-body rounded" style="width: 900px; margin-top: 7.500;margin-left: 7.500;margin-top: 75px;margin-left: 75px;margin-right: 75px;margin-bottom: 75px;">
      <div class="head mb-4">회원가입</div>
      <!-- <div>{{ code }}</div> -->

      <!-- 이메일 인증코드 보내기 -->
      <form @submit.prevent="sendmail(checkEmail)">
        <div class="form-group row">
          <!-- <label for="UserEmail">이메일: </label> -->
          <label class="col-lg-3 col-form-label" for="val-useremail">Useremail <span class="text-danger">*</span></label>
          <div class="col-lg-9">
            <div style="display: flex; flex-direction: row;">
              <input class="form-control inputNew" @input="writeEmail" id="UserEmail" v-model="credentials.userEmail" name="val-useremail" type="email" placeholder="Your valid email.." required />
              <button class="btn btn mx-4" style="margin-top: 5px; margin-bottom: 5px; background-color: #30475E; color: white; border-radius: 25px;" type="submit"> 인증번호 받기</button>
            </div>
          </div>
          <span v-if="isEmail === 1" style="color: red; margin-top:8px;" > 이메일을 전송했습니다</span>
          <span v-if="isEmail >= 2" style="color: red; margin-top:8px;" > 이메일을 재전송했습니다</span>
        </div>
        
          &nbsp;

          <div class="form-group row">
          <!-- 인증번호 확인 -->
          <label class="col-lg-3 col-form-label" for="val-code">Code <span class="text-danger">*</span></label>
          <div class="col-lg-9">
            <div style="display: flex; flex-direction: row;">
              <input class="form-control inputNew" type="text" v-model="mailcode" placeholder="Enter a code..">
              <button type="button" class="btn btn mx-4" style="margin-top: 5px; margin-bottom: 5px;  background-color: #30475E; color: white; border-radius: 25px;"  @click="codeCheck"> 인증번호 확인</button>
            </div>
          </div>
          <!-- <input type="button" value="인증 번호 확인" @click="codeCheck"> -->
          <p v-if="CodeConfirm" style="color: red" class="my-2"> 코드가 일치하지 않습니다.</p>
          <!-- <button class="btn btn" style="margin-top: 5px; margin-bottom: 5px;  background-color: #30475E; color: white;" type="submit"  @click="codeCheck"> 인증번호 확인</button> -->
          </div>
          <!-- <button class="btn btn" style="margin-top: 5px; margin-bottom: 5px;  background-color: #30475E; color: white;" type="submit"  @click="codeCheck"> 인증번호 확인</button> -->
          <!-- <p v-if="CodeConfirm" style="color: red"> 코드가 일치하지 않습니다.</p> -->
      </form>
      <button @click="overlapEmail(credentials.userEmail)">중복확인</button>

        &nbsp;
      <div v-if="confirmed">
        <form @submit.prevent="signup(credentials)" @reset="onReset">
          <!-- 비밀번호 확인 -->
          <div class="form-group row">
            <!-- <label for="UserPassword">비밀번호:  </label> -->
            <label class="col-lg-3 col-form-label" for="val-userpassword">UserPassword <span class="text-danger">*</span><p>8자이상 입력하세요</p></label>
            <div class="col-lg-9">
              <input class="form-control inputNew" id="UserPassword" v-model="credentials.userPw" type="password" placeholder="Choose a safe one.." minlength="8" maxlength="50" required />
            </div>
          </div>
          &nbsp;
          <!-- 비밀번호 재확인 -->
          <div class="form-group row">
            <!-- <label class="col-lg-4 col-form-label" for="val-code">Code <span class="text-danger">*</span></label> -->
            <label class="col-lg-3 col-form-label" for="UserPassword Confirmation">Confirm password: <span class="text-danger">*</span><p>8자이상 입력하세요</p></label>
            <div class="col-lg-9">
              <input class="form-control inputNew" @input="passwordConfirm" id="UserPassword Confirmation" v-model="userpw2" type="password" placeholder="..and confirm it!" minlength="8" maxlength="50" required />
            </div>
            
            <span v-if="PasswordConfirm" style="color: red; margin-top:8px;" > 비밀번호가 일치하지 않습니다.</span>
          </div>
          &nbsp;

          <!-- 부서 -->
          <div class="form-group row">
            <label class="col-lg-3 col-form-label" for="UserDepartment">Department <span class="text-danger">*</span></label>
            <div class="col-lg-9">
              <input class="form-control inputNew" id="UserDepartment" v-model="credentials.userDept" type="text" placeholder="Enter UserDepartment" required />
            </div>
          </div>
          &nbsp;
          <!-- 이름 -->
          <div class="form-group row">
            <label class="col-lg-3 col-form-label" for="UserName">Name <span class="text-danger">*</span></label>
            <div class="col-lg-9">
              <input class="form-control inputNew" id="UserName" v-model="credentials.userName" type="text" placeholder="Enter UserName" required />
            </div>
          </div>
          &nbsp;
          <!-- 전화번호 -->
          <div class="form-group row">
            <label class="col-lg-3 col-form-label" for="UserPhone">Phone-number(kr)<span class="text-danger">*</span><p>('-'입력)</p></label>
            <div class="col-lg-9">
              <input class="form-control inputNew" id="UserPhone" v-model="credentials.userPhone" type="tel" placeholder="010-0000-0000" pattern = "[0-9]{3}-[0-9]{4}-[0-9]{4}" required />
            </div>
          </div>
          &nbsp;

          <button type="reset" class="btn btn" style="margin-top: 5px; margin-bottom: 5px; margin-left: 5px; margin-right: 5px;  background-color: #30475E; color: white; border-radius: 25px;">Reset</button>
          <button type="submit" class="btn btn" style="margin-top: 5px; margin-bottom: 5px; margin-left: 5px; margin-right: 5px; background-color: crimson; color: white; border-radius: 25px;">회원가입</button>
        </form>
      </div>
      &nbsp;
      <router-link class="return" to="/" >이전</router-link>
    </div>
  </div>
  </div>
  
</template>

<script>
  import router from '@/router'
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
        mailcnt: 0,
        availableEmail : true,
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
      ...mapGetters(['authError', 'code', 'isEmail'])
    },
    methods: {
      ...mapActions(['signup', 'sendmail', 'deleteEmail', 'overlapEmail']),
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
        this.credentials.userRole = ''
        this.credentials.userEmail = ''
        this.credentials.userPw = ''
        this.credentials.userpw2 = ''
        this.credentials.userName = ''
        this.credentials.userDept = ''
        this.credentials.userPhone = ''
      },
      isClick() {
      const btnElement = document.getElementById('btn');
      const html = '<div> 인증코드 재전송 </div>';
      btnElement.innerHTML = html;
      },
      logo() {
        router.push({ name: 'home' })
      },
      // 이메일 중복 확인
    //  async checkDuplicate() {
    //     this.availableEmail = true;
    //     const response = await this.checkDuplicateEmail(this.email);
    //     if (!response.data) {
    //       this.availableEmail = false;
    //     } else {
    //       this.availableEmail = true;
    //     }
    //     },
      }
    }
</script>

<style scoped>
.return{
  text-decoration: none;
  color: crimson;
}
</style>
