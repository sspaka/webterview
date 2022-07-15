<template>
  <div>
    여기는 아이디 찾기
  </div>
  <form @submit.prevent="findEmail(credentials)">
    <div>
      <label for="UserName">이름:</label>
      <input id="UserName" v-model="credentials.UserName" type="text" required>
    </div>
    <div>
      <label for="UserPhone">전화번호: </label>
      <input id="UserPhone" v-model="credentials.UserPhone" type="tel" placeholder="010-0000-0000" required>
    </div>
    <button type="submit" @click="isClick">보내기</button>
    <p v-if="isStatusOn">이메일</p>

  </form>
</template>

<script>
import { mapActions } from 'vuex'

export default {
  name: 'FindIdView',
  data() {
    
    return {
      isStatusOn: false,
      credentials: {
        userName: '',
        UserPhone: '',

      },
    }
  },
  computed: {
    // ...mapGetters(['authError'])
  },
  methods: {
    ...mapActions(['findEmail']),

    onReset(event) {
      event.preventDefault()
      // Reset our form values
      this.credentials.UserName = ''
      this.credentials.UserPhone = ''
    },
    isClick() {
    this.isStatusOn = !this.isStatusOn
    },

  }
}
</script>

<style>

</style>