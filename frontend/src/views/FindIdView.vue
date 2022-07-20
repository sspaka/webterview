<template>
  <div class="head">
    여기는 아이디 찾기
  </div>
  <form @submit.prevent="findmail(credentials)">
    <div>
      <!-- <label for="UserName">이름:</label> -->
      <input class="btn" id="UserNameyn" v-model="credentials.name" type="text" placeholder="이름을 입력하세요" required>
    </div>
    <div>
      <!-- <label for="UserPhone">전화번호: </label> -->
      <input class="btn" id="UserPhoneyn" v-model="credentials.phone" type="tel" placeholder="010-0000-0000" required>
    </div>
    <div>
    <button class="submit-for-id" type="submit">보내기</button>
    </div>
    <div class="show-id" v-if="email.message==='success'">고객님의 아이디(이메일)은 {{ email.userEmail }} 입니다.</div>

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
#UserNameyn{
  border:none;
  outline:none;
  color: #636e72;
  font-size:16px;
  height:25px;
  background: none;
}

#UserPhoneyn {
  border:none;
  outline:none;
  color: #636e72;
  font-size:16px;
  height:25px;
  background: none;
}

.submit-for-id {
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
}

.show-id {

}
</style>