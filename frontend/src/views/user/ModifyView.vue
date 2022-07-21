<template>
  <div>
    <div> 프로필 수정 하시겠습니까?</div>
    <!-- <div>{{ profile }}</div> -->
    <!-- 이메일 인증코드 보내기 -->
    <form @submit.prevent="modify(credentials)" @reset="onReset">
      <!-- <div>
        <label for="UserEmail">이메일: </label>
        <input id="UserEmail" v-model="credentials.useremail" type="email" placeholder= "Enter UserEmail" required />
      </div> -->
      <div>
        <label for="UserPassword">비밀번호:  </label>
        <input id="UserPassword" v-model="credentials.userPw" type="password" placeholder="Enter UserPassword" minlength="8" maxlength="50" required />
      </div>
      <!-- <div>
        <label for="UserDepartment">소속(회사명): </label>
        <input id="UserDepartment" v-model="credentials.userdept" type="text" placeholder="Enter UserDepartment" required />
      </div> -->
      <!-- <div>
        <label for="UserRole">역할: </label>
        <input id="UserRole" v-model="credentials.userrole" type="text" placeholder="Enter UserRole" required />
      </div>  -->
      <div>
        <label for="UserName">이름: </label>
        <input id="UserName" v-model="credentials.userName" type="text" placeholder="Enter UserName" required />
      </div>
      <div>
        <label for="UserPhone">전화번호: </label>
        <input id="UserPhone" v-model="credentials.userPhone" type="tel" placeholder="Enter UserPhone" pattern = "[0-9]{3}-[0-9]{4}-[0-9]{4}" required />
      </div>
      <button type="submit">업데이트</button>
      <button type="reset">Reset</button>
    </form>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'

export default {
  name: "ModifyView",
  components: {

  },
  data() {
    return {
        credentials: {
          userDept: "",
          userEmail: "",
          userName: "",
          userNo: 0,
          userPhone: "",
          userPw: "",
          userRole: "",
          userYn: true         
        },
    }
  },
  computed: {
    ...mapGetters(['profile', 'password'])
  },
  methods: {
    ...mapActions(['modify']),
    onReset(event) {
        event.preventDefault()
        // Reset our form values
        this.credentials.userRole = ''
        this.credentials.userEmail = ''
        this.credentials.userPw = ''
        this.credentials.userName = ''
        this.credentials.userDept = ''
        this.credentials.userPhone = ''
      }
  },
  created() {
    this.credentials.userEmail = this.profile.userEmail
    this.credentials.userName = this.profile.userName
    this.credentials.userPw = this.password
    this.credentials.userDept = this.profile.userDept
    this.credentials.userPhone = this.profile.userPhone
    this.credentials.userRole = this.profile.userRole
  }
}
</script>

<style>

</style>