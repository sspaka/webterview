<template>
  <div>
    <p>여기는 프로필</p>
    <div>이메일: {{ profile.useremail }}</div>
    <div>역할: {{ profile.userrole }}</div>
    <div>이름: {{ profile.username }}</div>
    <div>비밀번호: {{ profile.userpw }}</div>
    <div>Tel: {{ profile.userphone }}</div>
    <div>소속: {{ profile.userdept }}</div>
    <div>인증여부?: {{ profile.useryn }}</div>

    <div>
      <router-link :to="{ name: 'modify' }">프로필 수정</router-link>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'

export default {
  name: 'ProfileView',
  computed: {
    ...mapGetters(['profile']),
  },
  methods: {
    ...mapActions(['fetchProfile'])
  },
  created() {
    const payload = { useremail: this.$route.params.useremail }
    this.fetchProfile(payload)
  },
}
</script>

<style>

</style>