<template>
  <div>
    여기는 비밀번호 찾기
  </div>
  <form>
  <div @submit.prevent="emailConfirm(emailAddress)" @reset="onReset">
    <label for="UserEmail">이메일:</label>
    <input id="UserEmail" v-model="credentials.UserEmail" type="email" required>
  </div>
  </form>
  <form>
    <div>
      <label for="UserName">이름:</label>
      <input id="UserName" v-model="credentials.UserName" type="text" required>
    </div>
  </form>
  <form>
    <div>
      <label for="UserPhone">전화번호: </label>
      <input id="UserPhone" v-model="credentials.UserPhone" type="tel" placeholder="010-0000-0000" required>
    </div>
    <!-- 버튼동작시 이동되는 페이지 만들어야됨 -->
    <button type="submit">찾기</button>

  </form>
</template>

<script>
import { mapActions } from 'vuex'

export default {
  name: 'FindIdView',
  data() {
    return {
      credentials: {
        userName: '',
        UserPhone: '',
        UserEmail: '',
      },
    }
  },
  computed: {
    //...mapGetters(['authError'])
    
  },
  methods: {
    ...mapActions(['emailConfirm']),
    onReset(event) {
      event.preventDefault()
      // Reset our form values
      this.credentials.UserName = ''
      this.credentials.UserPhone = ''
      this.credentials.UserEmail = ''
    }
  }
}
</script>

<style>

</style>