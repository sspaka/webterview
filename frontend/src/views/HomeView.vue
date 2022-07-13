
<template>
  <div class="home">

    <div v-if="authError" title="로그인 정보가 정확하지 않습니다" class="mx-4" style="max-width: 30rem;">
      <div>
        <account-error-list v-if="authError"></account-error-list>
      </div>
    </div>

    <form @submit.prevent="login(credentials)" @reset="onReset">
      <div>
        <label for="UserEmail">이메일: </label>
        <input id="UserEmail" v-model="credentials.UserEmail" type="text" name="이메일" placeholder="Enter your UserEmail" required>
      </div>
      <div>
        <label for="UserPassword">비밀번호: </label>
        <input id="UserPassword" v-model="credentials.UserPassword" type="password" placeholder="Enter UserPassword" required>
      </div>
      <button type="submit">login</button>
      <button type="reset">reset</button>
      <router-link to="/signup">회원가입</router-link>
    </form>
  </div>
</template>

<script>
// @ is an alias to /src
import { mapActions, mapGetters } from 'vuex'
import AccountErrorList from '@/components/AccountErrorList.vue'

export default {
  name: 'HomeView',
  components: {
    AccountErrorList,
  },
  data() {
    return {
      credentials: {
        userEmail: '',
        UserPassword: '',
      }
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
      this.credentials.UserEmail = ''
      this.credentials.UserPassword = ''
    }
  }

}
</script>
