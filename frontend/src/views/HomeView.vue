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
</template>

<script>
// @ is an alias to /src
import { mapActions, mapGetters } from 'vuex'
import AccountErrorList from '@/components/AccountErrorList.vue'
// import { Form, Field } from 'vee-validate';
// import * as Yup from 'yup'

export default {
  name: 'HomeView',
  components: {
    AccountErrorList,
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
