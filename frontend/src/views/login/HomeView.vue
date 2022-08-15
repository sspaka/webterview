<template>
  <!-- 템플릿 -->
  <div class="limiter ">
        <div class="container-login100 shadow-lg">
            <div class="wrap-login100">
              <div>
                <img class="logo" src="@/assets/logo.png" alt="Logo">
              </div>

                <div class="login100-pic js-tilt" data-tilt>
                    <img src="@/assets/img-01.png" alt="IMG">
                </div>

        <!-- form 시작  -->
                <form class="login100-form validate-form" form @submit.prevent="login(credentials)">
                    <!-- <span class="login100-form-title">
                        Member Login
                    </span> -->

                    <div class="wrap-input100 validate-input" data-validate = "Valid email is required: ex@abc.xyz">
            <!-- 이메일 작성 -->
                        <input class="input100" id="UserEmail" v-model="credentials.userEmail" type="email" name="이메일" placeholder="Enter your UserEmail" required>
                        <span class="focus-input100"></span>
                        <span class="symbol-input100">
                            <i class="fa fa-envelope" aria-hidden="true"></i>
                        </span>
                    </div>

                    <div class="wrap-input100 validate-input" data-validate = "Password is required">
            <!-- 비밀번호 작성 -->
                        <input class="input100" id="UserPassword" v-model="credentials.userPw" type="password" placeholder="Enter UserPassword" required>
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
                            <router-link to="/findid">아이디 찾기a</router-link> | 
              <router-link to="/findpw">비밀번호 찾기a</router-link>
                        </p>
                    </div>

                    <div class="text-center p-t-33">
            <!-- 회원가입하러 가기 -->
                        <p class="txt2" href="#">
              회원이 아닌가요?
                            <router-link to="/signup">지금 가입하세요</router-link>
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
        userEmail: '',
        userPw: '',
      },
    }
  },
  computed: {
    ...mapGetters(['authError', 'email', 'userNo'])
    
  },
  methods: {
    ...mapActions(['login','deleteisEmail','fetchProfile', 'readGroup']),
    onReset(event) {
      event.preventDefault()
      // Reset our form values
      this.credentials.userEmail = ''
      this.credentials.userPw = ''
    },
    // async loginProcess(credentials) {
    //   await this.login(credentials)
    //   await this.fetchProfile(this.email)
    //   await this.readGroup(this.userNo)

    // }
  },
  created() {
    console.log('deleteisEmail')
    this.deleteisEmail()
  },
  
}
</script>

<style scoped>
  
</style>