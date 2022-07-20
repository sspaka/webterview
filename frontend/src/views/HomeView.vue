<template>
  <div class="home">
    <h1>시작하기</h1>

    <form @submit.prevent="login(credentials)" @reset="onReset">
      <div>
        <label for="UserEmail">이메일: </label>
        <input id="UserEmail" v-model="credentials.useremail" type="email" name="이메일" placeholder="Enter your UserEmail" required>
      </div>
      <div>
        <label for="UserPassword">비밀번호: </label>
        <input id="UserPassword" v-model="credentials.userpw" type="password" placeholder="Enter UserPassword" required>
      </div>
      <button type="submit">login</button>
      <button type="reset">reset</button>
    </form>
    <div>
      <router-link to="/signup">회원가입</router-link>
    </div>
    <div>
      <router-link to="/findid">아이디 찾기</router-link>
    </div>
    <div>
      <router-link to="/findpw">비밀번호 찾기</router-link>
    </div>

    <!-- <Form @submit.prevent="login(credentials)" :validation-schema="schema" v-slot= "{ errors }">
      <div class="form-group col">
        <label>Email</label>
        <Field name="email" type="text" class="form-control" :class="{ 'is-invalid': errors.email }" />
        <div class="invalid-feedback">{{errors.email}}</div>
      </div>
      <button type="submit">login</button>
    </Form> -->
  </div>

  <!-- 템플릿 -->
  <div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<div class="login100-pic js-tilt" data-tilt>
					<img src="@/assets/img-01.png" alt="IMG">
				</div>

        <!-- form 시작  -->
				<form class="login100-form validate-form" form @submit.prevent="login(credentials)">
					<span class="login100-form-title">
						Member Login
					</span>

					<div class="wrap-input100 validate-input" data-validate = "Valid email is required: ex@abc.xyz">
            <!-- 이메일 작성 -->
						<input class="input100" id="UserEmail" v-model="credentials.useremail" type="email" name="이메일" placeholder="Enter your UserEmail" required>
						<span class="focus-input100"></span>
						<span class="symbol-input100">
							<i class="fa fa-envelope" aria-hidden="true"></i>
						</span>
					</div>

					<div class="wrap-input100 validate-input" data-validate = "Password is required">
            <!-- 비밀번호 작성 -->
						<input class="input100" id="UserPassword" v-model="credentials.userpw" type="password" placeholder="Enter UserPassword" required>
						<span class="focus-input100"></span>
						<span class="symbol-input100">
							<i class="fa fa-lock" aria-hidden="true"></i>
						</span>
					</div>
					
					<div class="container-login100-form-btn">
            <!-- 로그인 버튼 -->
            <button class="login100-form-btn" type="submit">Login</button>
					</div>

					<div class="text-center p-t-12">
						<span class="txt1">
							회원정보를 잊어버리셨나요?
						</span>
            <!-- 아이디 찾기, 비밀번호 찾기 -->
						<p class="txt2" href="#">
							<router-link to="/findid">아이디 찾기</router-link> | 
              <router-link to="/findpw">비밀번호 찾기</router-link>
						</p>
					</div>

					<div class="text-center p-t-136">
            <!-- 회원가입하러 가기 -->
						<p class="txt2" href="#">
							<router-link to="/signup">회원가입</router-link>
						</p>
					</div>
				</form>
			</div>
		</div>
	</div>
</template>

<script>
// @ is an alias to /src
import { mapActions, mapGetters } from 'vuex'
//import AccountErrorList from '@/components/AccountErrorList.vue'
// import { Form, Field } from 'vee-validate';
// import * as Yup from 'yup'

export default {
  name: 'HomeView',
  components: {
    //AccountErrorList,
    //Form, 
    //Field,
  },
  data() {
    // const schema = Yup.object().shape({
    //     email: Yup.string()
    //       .required('Email is required')
    //       .email('Email is invalid'),
    //   })

    return {
      credentials: {
        useremail: '',
        userpw: '',
      },
    }
  },
  computed: {
    ...mapGetters(['authError'])
    
  },
  methods: {
    ...mapActions(['login']),
    onReset(event) {
      event.preventDefault()
      // Reset our form values
      this.credentials.useremail = ''
      this.credentials.userpw = ''
    }
  }

}
</script>

<style scoped>
  /*//////////////////////////////////////////////////////////////////
  [ FONT ]*/

  /* @font-face {
    font-family: Poppins-Regular;
    src: url('../fonts/poppins/Poppins-Regular.ttf'); 
  }

  @font-face {
    font-family: Poppins-Bold;
    src: url('../fonts/poppins/Poppins-Bold.ttf'); 
  }

  @font-face {
    font-family: Poppins-Medium;
    src: url('../fonts/poppins/Poppins-Medium.ttf'); 
  }

  @font-face {
    font-family: Montserrat-Bold;
    src: url('../fonts/montserrat/Montserrat-Bold.ttf'); 
  } */

  /*//////////////////////////////////////////////////////////////////
  [ RESTYLE TAG ]*/

  * {
    margin: 0px; 
    padding: 0px; 
    box-sizing: border-box;
  }

  body, html {
    height: 100%;
    font-family: Poppins-Regular, sans-serif;
  }

  /*---------------------------------------------*/
  a {
    font-family: Poppins-Regular;
    font-size: 14px;
    line-height: 1.7;
    color: #666666;
    margin: 0px;
    transition: all 0.4s;
    -webkit-transition: all 0.4s;
    -o-transition: all 0.4s;
    -moz-transition: all 0.4s;
  }

  a:focus {
    outline: none !important;
  }

  a:hover {
    text-decoration: none;
    color: #57b846;
  }

  /*---------------------------------------------*/
  h1,h2,h3,h4,h5,h6 {
    margin: 0px;
  }

  p {
    font-family: Poppins-Regular;
    font-size: 14px;
    line-height: 1.7;
    color: #666666;
    margin: 0px;
  }

  ul, li {
    margin: 0px;
    list-style-type: none;
  }


  /*---------------------------------------------*/
  input {
    outline: none;
    border: none;
  }

  textarea {
    outline: none;
    border: none;
  }

  textarea:focus, input:focus {
    border-color: transparent !important;
  }

  input:focus::-webkit-input-placeholder { color:transparent; }
  input:focus:-moz-placeholder { color:transparent; }
  input:focus::-moz-placeholder { color:transparent; }
  input:focus:-ms-input-placeholder { color:transparent; }

  textarea:focus::-webkit-input-placeholder { color:transparent; }
  textarea:focus:-moz-placeholder { color:transparent; }
  textarea:focus::-moz-placeholder { color:transparent; }
  textarea:focus:-ms-input-placeholder { color:transparent; }

  input::-webkit-input-placeholder { color: #999999; }
  input:-moz-placeholder { color: #999999; }
  input::-moz-placeholder { color: #999999; }
  input:-ms-input-placeholder { color: #999999; }

  textarea::-webkit-input-placeholder { color: #999999; }
  textarea:-moz-placeholder { color: #999999; }
  textarea::-moz-placeholder { color: #999999; }
  textarea:-ms-input-placeholder { color: #999999; }

  /*---------------------------------------------*/
  button {
    outline: none !important;
    border: none;
    background: transparent;
  }

  button:hover {
    cursor: pointer;
  }

  iframe {
    border: none !important;
  }


  /*//////////////////////////////////////////////////////////////////
  [ Utility ]*/
  .txt1 {
    font-family: Poppins-Regular;
    font-size: 13px;
    line-height: 1.5;
    color: #999999;
  }

  .txt2 {
    font-family: Poppins-Regular;
    font-size: 13px;
    line-height: 1.5;
    color: #666666;
  }


  /*//////////////////////////////////////////////////////////////////
  [ login ]*/

  .limiter {
    width: 100%;
    margin: 0 auto;
  }

  .container-login100 {
    width: 100%;  
    min-height: 100vh;
    display: -webkit-box;
    display: -webkit-flex;
    display: -moz-box;
    display: -ms-flexbox;
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    align-items: center;
    padding: 15px;
    background: #f05454;
    background: -webkit-linear-gradient(-135deg, #f05454, #f5f5f5);
    background: -o-linear-gradient(-135deg, #f05454, #f5f5f5);
    background: -moz-linear-gradient(-135deg, #f05454, #f5f5f5);
    background: linear-gradient(-135deg, #f05454, #f5f5f5);
  }

  .wrap-login100 {
    width: 960px;
    background: #fff;
    border-radius: 10px;
    overflow: hidden;

    display: -webkit-box;
    display: -webkit-flex;
    display: -moz-box;
    display: -ms-flexbox;
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
    padding: 177px 130px 33px 95px;
  }

  /*------------------------------------------------------------------
  [  ]*/
  .login100-pic {
    width: 316px;
  }

  .login100-pic img {
    max-width: 100%;
  }


  /*------------------------------------------------------------------
  [  ]*/
  .login100-form {
    width: 290px;
  }

  .login100-form-title {
    font-family: Poppins-Bold;
    font-size: 24px;
    color: #333333;
    line-height: 1.2;
    text-align: center;

    width: 100%;
    display: block;
    padding-bottom: 54px;
  }


  /*---------------------------------------------*/
  .wrap-input100 {
    position: relative;
    width: 100%;
    z-index: 1;
    margin-bottom: 10px;
  }

  .input100 {
    font-family: Poppins-Medium;
    font-size: 15px;
    line-height: 1.5;
    color: #666666;

    display: block;
    width: 100%;
    background: #e6e6e6;
    height: 50px;
    border-radius: 25px;
    padding: 0 30px 0 68px;
  }


  /*------------------------------------------------------------------
  [ Focus ]*/
  .focus-input100 {
    display: block;
    position: absolute;
    border-radius: 25px;
    bottom: 0;
    left: 0;
    z-index: -1;
    width: 100%;
    height: 100%;
    box-shadow: 0px 0px 0px 0px;
    color: rgba(87,184,70, 0.8);
  }

  .input100:focus + .focus-input100 {
    -webkit-animation: anim-shadow 0.5s ease-in-out forwards;
    animation: anim-shadow 0.5s ease-in-out forwards;
  }

  @-webkit-keyframes anim-shadow {
    to {
      box-shadow: 0px 0px 70px 25px;
      opacity: 0;
    }
  }

  @keyframes anim-shadow {
    to {
      box-shadow: 0px 0px 70px 25px;
      opacity: 0;
    }
  }

  .symbol-input100 {
    font-size: 15px;

    display: -webkit-box;
    display: -webkit-flex;
    display: -moz-box;
    display: -ms-flexbox;
    display: flex;
    align-items: center;
    position: absolute;
    border-radius: 25px;
    bottom: 0;
    left: 0;
    width: 100%;
    height: 100%;
    padding-left: 35px;
    pointer-events: none;
    color: #666666;

    -webkit-transition: all 0.4s;
    -o-transition: all 0.4s;
    -moz-transition: all 0.4s;
    transition: all 0.4s;
  }

  .input100:focus + .focus-input100 + .symbol-input100 {
    color: #57b846;
    padding-left: 28px;
  }

  /*------------------------------------------------------------------
  [ Button ]*/
  .container-login100-form-btn {
    width: 100%;
    display: -webkit-box;
    display: -webkit-flex;
    display: -moz-box;
    display: -ms-flexbox;
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    padding-top: 20px;
  }

  .login100-form-btn {
    font-family: Montserrat-Bold;
    font-size: 15px;
    line-height: 1.5;
    color: #fff;
    text-transform: uppercase;

    width: 100%;
    height: 50px;
    border-radius: 25px;
    background: #f05454;
    display: -webkit-box;
    display: -webkit-flex;
    display: -moz-box;
    display: -ms-flexbox;
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 0 25px;

    -webkit-transition: all 0.4s;
    -o-transition: all 0.4s;
    -moz-transition: all 0.4s;
    transition: all 0.4s;
  }

  .login100-form-btn:hover {
    background: #333333;
  }



  /*------------------------------------------------------------------
  [ Responsive ]*/



  @media (max-width: 992px) {
    .wrap-login100 {
      padding: 177px 90px 33px 85px;
    }

    .login100-pic {
      width: 35%;
    }

    .login100-form {
      width: 50%;
    }
  }

  @media (max-width: 768px) {
    .wrap-login100 {
      padding: 100px 80px 33px 80px;
    }

    .login100-pic {
      display: none;
    }

    .login100-form {
      width: 100%;
    }
  }

  @media (max-width: 576px) {
    .wrap-login100 {
      padding: 100px 15px 33px 15px;
    }
  }


  /*------------------------------------------------------------------
  [ Alert validate ]*/

  .validate-input {
    position: relative;
  }

  .alert-validate::before {
    content: attr(data-validate);
    position: absolute;
    max-width: 70%;
    background-color: white;
    border: 1px solid #c80000;
    border-radius: 13px;
    padding: 4px 25px 4px 10px;
    top: 50%;
    -webkit-transform: translateY(-50%);
    -moz-transform: translateY(-50%);
    -ms-transform: translateY(-50%);
    -o-transform: translateY(-50%);
    transform: translateY(-50%);
    right: 8px;
    pointer-events: none;

    font-family: Poppins-Medium;
    color: #c80000;
    font-size: 13px;
    line-height: 1.4;
    text-align: left;

    visibility: hidden;
    opacity: 0;

    -webkit-transition: opacity 0.4s;
    -o-transition: opacity 0.4s;
    -moz-transition: opacity 0.4s;
    transition: opacity 0.4s;
  }

  .alert-validate::after {
    content: "\f06a";
    font-family: FontAwesome;
    display: block;
    position: absolute;
    color: #c80000;
    font-size: 15px;
    top: 50%;
    -webkit-transform: translateY(-50%);
    -moz-transform: translateY(-50%);
    -ms-transform: translateY(-50%);
    -o-transform: translateY(-50%);
    transform: translateY(-50%);
    right: 13px;
  }

  .alert-validate:hover:before {
    visibility: visible;
    opacity: 1;
  }

  @media (max-width: 992px) {
    .alert-validate::before {
      visibility: visible;
      opacity: 1;
    }
  }
</style>
