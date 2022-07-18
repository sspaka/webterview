<template>
  <div>
    여기는 아이디 찾기
  </div>
  <form @submit.prevent="findmail(credentials)">
    <div>
      <label for="UserName">이름:</label>
      <input id="UserName" v-model="credentials.name" type="text" required>
    </div>
    <div>
      <label for="UserPhone">전화번호: </label>
      <input id="UserPhone" v-model="credentials.phone" type="tel" placeholder="010-0000-0000" required>
    </div>
    <button type="submit">보내기</button>
    <p v-if="email.message==='success'">고객님의 아이디(이메일)은 {{ email.userEmail }} 입니다.</p>

  </form>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'

export default {
  name: 'FindIdView',
  data() {
    
    return {
      isStatusOn: false,
      credentials: {
        name: '',
        phone: '',

      },
    }
  },
  computed: {
    ...mapGetters(['authError', 'email'])
  },
  methods: {
    ...mapActions(['findmail']),

    onReset(event) {
      event.preventDefault()
      // Reset our form values
      this.credentials.name = ''
      this.credentials.phone = ''
    },
    
  }
}
</script>

<style>

</style>