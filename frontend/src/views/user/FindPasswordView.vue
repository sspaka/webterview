<template>
<div class="limiter">
  <div class="card shadow-lg p-3 mb-5 bg-body rounded" style="margin-top: 75px;margin-left: 75px;margin-right: 75px;margin-bottom: 75px;">
  <div class="head">
    비밀번호 찾기
  </div>
  <!-- <div>{{ code }}</div> -->
    &nbsp;
  
    <!-- 이메일 인증코드 보내기 -->
    <form @submit.prevent="sendmail(checkEmail)">
      <!-- <form> -->
      <div class="form-group row ">
        <label  class="col-lg-4 col-form-label" for="val-useremail">Useremail <span class="text-danger">*</span></label>
        <!-- <input class="btn" @input="writeEmail" id="UserEmailyn" v-model="credentials.useremail" type="email" placeholder="이메일을 입력하세요" required /> -->
        <div class="col-lg-6">
          <div style="display: flex; flex-direction: row;">
            <input @input="writeEmail" v-model="credentials.userEmail" type="email"  class="form-control inputNew" id="val-useremail" name="val-useremail" placeholder="Enter a useremail.." required>
            <button class="btn btn mx-4" style="margin-top: 5px; margin-bottom: 5px; background-color: #30475E; color: white; border-radius: 25px;" @click='isClick' >인증코드 받기</button>
          </div>
        </div>
        &nbsp;

    
        <!-- 이메일 중복 확인 -->
        <br>
        &nbsp;

        <!-- 인증번호 확인 -->
        <!-- <input  id="code" class="btn" type="text" v-model="mailcode" placeholder="인증번호를 입력해주세요">
        <input class="btn" type="button" value="인증 번호 확인" @click="codeCheck">
        <div v-if="CodeConfirm" class="codeyn" style="color: red"> 코드가 일치하지 않습니다</div> -->
        <label  class="col-lg-4 col-form-label my-2" for="val-usercode">Code<span class="text-danger">*</span></label>
        <div class="col-lg-6 my-2">
          <div style="display: flex; flex-direction: row;">
            <input v-model="mailcode" type="text"  class="form-control inputNew" id="val-usercode" name="val-usercode" placeholder="Enter a code..">
            <button class="btn btn mx-4" @click='codeCheck' style="margin-top: 5px; margin-bottom: 5px; background-color: #30475E; color: white; border-radius: 25px;" >인증코드 확인</button>
          </div>
        </div>
        <!-- <input class="btn" type="button" value="인증 번호 확인" @click="codeCheck"> -->
        &nbsp;
      
        <div v-if="CodeConfirm" class="codeyn" style="color: red; margin-top: 5px;"> 코드가 일치하지 않습니다</div>
       </div>
       &nbsp;
    </form>

    <div v-if="newpw" class="newPw">비밀번호 재 설정
      <form @submit.prevent="saveNewPw(credentials)" @reset="onReset">
      <div class="form-group row">
      <!-- <div>
        <input class="btn" id="UserPasswordyn" v-model="credentials.userpw" type="password" placeholder="비밀번호" minlength="8" maxlength="50" required />
      </div>
      <div>
        <input class="btn" @input="passwordConfirm" id="UserPasswordyn-Confirmation" type="password" placeholder="비밀번호 확인" minlength="8" maxlength="50" required />
        <p v-if="PasswordConfirm" style="color: red"> 비밀번호가 일치하지 않습니다.</p>
      </div> -->
      <label  class="col-lg-4 col-form-label" for="val-userpassword">Userpassword <span class="text-danger">*</span></label>
      &nbsp;
        <div class="col-lg-6">
            <input id="UserPassword" v-model="credentials.userPw" type="password"  class="form-control inputNew"  name="val-userpassword" style="margin-top: 5px" placeholder="Enter a password.." minlength="8" maxlength="50" required />
        </div>
        &nbsp;
      <label  class="col-lg-4 col-form-label" for="val-userpassword-confirm">Userpassword Confirm<span class="text-danger">*</span></label>
      &nbsp;
        <div class="col-lg-6">
          <input id="UserPassword-Confirmation" @input="passwordConfirm" type="password"  class="form-control inputNew"  name="val-userpassword" style="margin-top: 5px" placeholder="Enter a password.." minlength="8" maxlength="50" required/>
          <div v-if="PasswordConfirm" class="codeyn" style="color: red; margin-top: 5px;"> 비밀번호가 일치하지 않습니다.</div>
          &nbsp;
        </div>
        <button class="btn btn" type="submit" style="margin-top: 5px; margin-bottom: 5px; background-color: #30475E; color: white; border-radius: 25px; width: 200px; margin: auto">비밀번호 변경하기</button>
      </div>
      </form>
    </div>
    <router-link class="return" to="/" >이전</router-link>
    </div>
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
      PasswordConfirm: false,
      mailcode: '',
      clicked: !!true,
      credentials: {
        userEmail: '',
        userPw: '',
        userYn: true,
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
        this.checkEmail.email = this.credentials.userEmail
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
    },
    passwordConfirm() {
        var p1 = document.getElementById('UserPassword').value;
        var p2 = document.getElementById('UserPassword-Confirmation').value;
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
      const btnElement 
    = document.getElementById('btn');
  
    const html 
    = '<div> 인증코드 재전송 </div>';
  
  btnElement.innerHTML = html;
    }

    }

  
}
</script>

<style>
* {
  margin: 0px;
  padding: 0px;
  text-decoration: none;
  font-family:sans-serif;

}

.head{
  margin-top: 5%;
  font-size:32px;
  font-weight: bold;
}
.background{
  height: 100vh;
}

#UserEmailyn {
  border:none;
  outline:none;
  color: #636e72;
  font-size:16px;
  height:25px;
  background: none;
}

#code{
  border:none;
  outline:none;
  color: #636e72;
  font-size:16px;
  height:25px;
  background: none;
  font-weight: bold;
}

/* .btn {
  position:relative;
  left:40%;
  transform: translateX(-50%);
  margin-bottom: 40px;
  width:80%;
  height:40px;
  background: linear-gradient(125deg,#F05454,#30475E,#121212);
  background-position: left;
  background-size: 200%;
  color:#F5F5F5;
  font-weight: bold;
  border:none;
  cursor:pointer;
  transition: 0.4s;
  display:inline;
} */

.codeyn{

  height:40px;
  background-size: 200%;
  font-weight: bold;
  border:none;

}
/* .newPw {
  border:none;
  outline:none;
  color: #30475E;
  font-size:16px;
  background: none;
  background-size: 200%;
  font-weight: bold;
  border:none;
  display:inline;
} */

#UserPasswordyn {
  margin-top:40px;
  border:none;
  outline:none;
  color: #636e72;
  font-size:16px;
  height:25px;
  background: none;
}

#UserPasswordyn-Confirmation{
  border:none;
  outline:none;
  color: #636e72;
  font-size:16px;
  height:25px;
  background: none;
}

.return{
  text-decoration: none;
  color: crimson;
}

</style>