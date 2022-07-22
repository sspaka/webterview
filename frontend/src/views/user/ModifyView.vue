<template>
  <img class="logo" src="@/assets/logo.png" alt="Logo" style="position: absolute; top: 20px; left:20px; width: 200px;" @click="logo">
  <div class="limiter d-flex justify-content-center">
    <div class="container-login100 shadow-lg">
      <div class="card shadow-lg p-3 mb-5 bg-body rounded" style="width: 800px; margin-top: 75px;margin-left: 75px;margin-right: 75px;margin-bottom: 75px;">
        <div class="head mb-4">수정</div>
        <form @submit.prevent="modify(credentials)" @reset="onReset">
          <dl class="row">
            <dt class="col-sm-3">이름<span class="text-danger"> *</span></dt>
            <dd class="col-sm-9">
              <input class="form-control" id="UserName" v-model="credentials.userName" type="text" placeholder="Enter UserName" required />
            </dd>

            <dt class="col-sm-3">소속</dt>
            <dd class="col-sm-9">{{ profile.userDept }}
            </dd>

            <dt class="col-sm-3">역할</dt>
            <dd class="col-sm-9">{{ profile.userRole }}</dd>

            <dt class="col-sm-3">메일</dt>
            <dd class="col-sm-9">{{ profile.userEmail }}</dd>

            <dt class="col-sm-3">비밀번호<span class="text-danger"> *</span></dt>
            <dd class="col-sm-9">
              <input class="form-control" id="UserPassword" v-model="credentials.userPw" type="password" placeholder="Enter UserPassword" minlength="8" maxlength="50" required />
            </dd>

            <dt class="col-sm-3">전화<span class="text-danger"> *</span></dt>
            <dd class="col-sm-9">
              <input class="form-control" id="UserPhone" v-model="credentials.userPhone" type="tel" placeholder="Enter UserPhone" pattern = "[0-9]{3}-[0-9]{4}-[0-9]{4}" required />
            </dd>

          </dl>
          <button class="mx-2" type="submit"><p>수정완료</p></button>
          <button type="reset"><p>Reset</p></button>
        </form>
        <router-link class="return" :to="{ name: 'profile', params: { useremail } }">이전</router-link>
        &nbsp;
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
import router from '@/router'

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
    ...mapGetters(['profile', 'password','email']),
    useremail() {
        return this.email
      },
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
      },
      logo() {
      router.push({ name: 'meetingroom_man' })
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
  .return{
  text-decoration: none;
  color: crimson;
}
</style>