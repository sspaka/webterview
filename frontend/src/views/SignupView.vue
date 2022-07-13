<template>
  <div class="container">

    <div v-if="authError" title="로그인 정보가 정확하지 않습니다" class="mx-4" style="max-width: 30rem;">
      <div>
        <account-error-list v-if="authError"></account-error-list>
      </div>
    </div>

    <form @submit.prevent="signup(credentials)" @reset="onReset">
      <div>
        <label for="UserRole">역할: </label>
        <input id="UserRole" v-model="credentials.UserRole" type="text" placeholder="Enter UserRole" required />
      </div>
      <div>
        <label for="UserEmail">이메일: </label>
        <input id="UserEmail" v-model="credentials.UserEmail" type="text" placeholder="Enter UserEmail" required />
      </div>
      <div>
        <label for="UserPassword">비밀번호:  </label>
        <input id="UserPassword" v-model="credentials.UserPassword1" type="password" placeholder="Enter UserPassword" required />
      </div>
      <div>
        <label for="UserPassword">비밀번호 확인: </label>
        <input id="UserPassword" v-model="credentials.UserPassword2" type="password" placeholder="Enter UserPassword" required />
      </div>
      <div>
        <label for="UserName">이름: </label>
        <input id="UserName" v-model="credentials.UserName" type="text" placeholder="Enter UserName" required />
      </div>
      <div>
        <label for="UserDepartment">소속(회사명) </label>
        <input id="UserDepartment" v-model="credentials.UserDepartment" type="text" placeholder="Enter UserDepartment" required />
      </div>
  
      <button type="submit">회원가입</button>
      <button type="reset">Reset</button>
    </form>
    <router-link to="/">이전</router-link>
  </div>
</template>

<script>
  import { mapActions, mapGetters } from 'vuex'
  import AccountErrorList from '@/components/AccountErrorList.vue'

  export default {
    name: 'SignupView',
    components: {
      AccountErrorList,
    },
    data() {
      return {
        credentials: {
          UserRole: '',
          UserEmail: '',
          UserName: '',
          UserPassword1: '',
          UserPassword2: '',
          UserDepartment: '',
        }
      }
    },
    computed: {
      ...mapGetters(['authError'])
    },
    methods: {
      ...mapActions(['signup']),
      onReset(event) {
        event.preventDefault()
        // Reset our form values
        this.credentials.UserRole = ''
        this.credentials.UserEmail = ''
        this.credentials.UserPassword1 = ''
        this.credentials.UserPassword2 = ''
        this.credentials.UserName = ''
        this.credentials.UserDepartment = ''
      }
    },
  }
</script>

<style scoped></style>
