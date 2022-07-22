<template>
  <img class="logo" src="@/assets/logo.png" alt="Logo" style="position: absolute; top: 20px; left:20px; width: 200px;" @click="logo">
  <div class="limiter d-flex justify-content-center">
    <div class="container-login100 shadow-lg">
      <div class="card shadow-lg p-3 mb-5 bg-body rounded" style="width: 800px; margin-top: 75px; margin-left: 75px; margin-right: 75px;margin-bottom: 75px;">
        <div class="head mb-4">계정</div>
        <dl class="row">
          <dt class="col-sm-3">이름</dt>
          <dd class="col-sm-9">{{ profile.userName }}</dd>

          <dt class="col-sm-3">소속</dt>
          <dd class="col-sm-9">{{ profile.userDept }}
          </dd>

          <dt class="col-sm-3">역할</dt>
          <dd class="col-sm-9">{{ profile.userRole }}</dd>

          <dt class="col-sm-3">메일</dt>
          <dd class="col-sm-9">{{ profile.userEmail }}</dd>

          <dt class="col-sm-3">비밀번호</dt>
          <dd class="col-sm-9">
            <p>{{ password }}</p>
          </dd>

          <dt class="col-sm-3">전화</dt>
          <dd class="col-sm-9">{{ profile.userPhone}}</dd>

        </dl>

        <div class="d-flex flex-row justify-content-center">
          <router-link :to="{ name: 'modify' }"><p>수정하기</p></router-link>
          <p class="mx-2" @click="deleteUser(email)" >회원탈퇴</p>
        </div>
        <router-link class="return" to="/webterview" >이전</router-link>
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