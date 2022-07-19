<template>
  <div>
    <p>여기는 프로필</p>
    <div>이메일: {{ profile.useremail }}</div>
    <div>역할: {{ profile.userrole }}</div>
    <div>이름: {{ profile.username }}</div>
    <div>비밀번호: {{ profile.userpw }}</div>
    <div>비밀번호: {{ password }}</div>
    <div>Tel: {{ profile.userphone }}</div>
    <div>소속: {{ profile.userdept }}</div>
    <div>인증여부?: {{ profile.useryn }}</div>

    <div>
      <router-link :to="{ name: 'modify' }">프로필 수정</router-link>
    </div>
    <div>
      <input type="button" value="회원탈퇴" @click="deleteUser(email)" >
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'

export default {
  name: 'ProfileView',
  computed: {
    ...mapGetters(['profile', 'password', 'email']),
  },
  methods: {
    ...mapActions(['fetchProfile', 'deleteUser'])
  },
  created() {
    const payload = { useremail: this.$route.params.useremail }
    this.fetchProfile(payload)
  },
}
</script>

<style>

</style>