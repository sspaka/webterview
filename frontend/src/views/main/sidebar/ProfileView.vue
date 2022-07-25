<template>
  <img class="logo" src="@/assets/logo.png" alt="Logo" style="position: absolute; top: 20px; left:20px; width: 200px;" @click="logo">
  <div class="limiter d-flex justify-content-center">
    <div class="container-login100 shadow-lg">
      <div class="card shadow-lg p-3 mb-5 bg-body rounded" style="width: 900px; margin-top: 75px; margin-left: 75px; margin-right: 75px;margin-bottom: 75px;">
        <div class="head mb-4">회원 정보</div>
        <dl class="row">

          <div class="form-group row">
            <label class="col-lg-4 col-form-label">Username</label>
            <div class="col-lg-6">
              <div class="inputNew d-flex flex-col align-item-center justify-content-center" >
                <p style="color: black;">{{ profile.userName }}</p>
              </div>
            </div>
          </div>

          <div class="form-group row">
            <label class="col-lg-4 col-form-label">UserDept</label>
            <div class="col-lg-6">
              <div class="inputNew d-flex flex-col align-item-center justify-content-center" >
                <p style="color: black;">{{ profile.userDept }}</p>
              </div>
            </div>
          </div>

          <!-- <div class="form-group row">
            <label class="col-lg-4 col-form-label">UserRole</label>
            <div class="col-lg-6">
              <div class="inputNew d-flex flex-col align-item-center justify-content-center" >
                <p style="color: black;">{{ profile.userRole }}</p>
              </div>
            </div>
          </div> -->

          <div class="form-group row">
            <label class="col-lg-4 col-form-label">UserEmail</label>
            <div class="col-lg-6">
              <div class="inputNew d-flex flex-col align-item-center justify-content-center" >
                <p style="color: black;">{{ profile.userEmail }}</p>
              </div>
            </div>
          </div>

          <div class="form-group row">
            <label class="col-lg-4 col-form-label">Password</label>
            <div class="col-lg-6">
              <div class="inputNew d-flex flex-col align-item-center justify-content-center" >
                <p style="color: black;">{{ password }}</p>
              </div>
            </div>
          </div>

          <div class="form-group row">
            <label class="col-lg-4 col-form-label">Phone (kr)</label>
            <div class="col-lg-6">
              <div class="inputNew d-flex flex-col align-item-center justify-content-center" >
                <p style="color: black;">{{ profile.userPhone }}</p>
              </div>
            </div>
          </div>

        </dl>

        <div class="d-flex flex-row justify-content-center">
          <button @click="toModify" class="btn btn" style="margin-top: 10px;  background-color: crimson; color: white; border-radius: 25px;">수정하기</button>
          <!-- <router-link :to="{ name: 'modify' }"><p>수정하기</p></router-link> -->
          <!-- <button @click="deleteUser(email)" class="btn btn mx-2" style="margin-top: 10px;  background-color: red; color: white; border-radius: 25px;">탈퇴하기</button> -->
          <!-- <p class="mx-2" @click="deleteUser(email)" >회원탈퇴</p> -->
        </div>
        <router-link class="return my-2" to="/webterview" >이전</router-link>
        <div class="text-center p-t-33">
          <p class="txt2" href="#">계정을 삭제하고 싶으신가요?
            <span @click="deleteUser(email)">click>>회원탈퇴</span>
          </p>
        </div>
        <div class="checkPw">비밀번호 확인 후 맞으면 탈퇴 시키기</div>
        &nbsp;
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
import router from '@/router'

export default {
  name: 'ProfileView',
  computed: {
    ...mapGetters(['profile', 'password', 'email']),
  },
  methods: {
    ...mapActions(['fetchProfile', 'deleteUser']),
    logo() {
      router.push({ name: 'meetingroom_man' })
    },
    toModify() {
      router.push({ name: 'modify' })
    }
  },
  created() {
    const payload = { useremail: this.$route.params.useremail }
    this.fetchProfile(payload)
  },
}
</script>

<style>
  .return{
  text-decoration: none;
  color: crimson;
  }
</style>