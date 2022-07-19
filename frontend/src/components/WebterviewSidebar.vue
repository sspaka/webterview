<template>
  <div class="w3-sidebar w3-bar-block" style="width:25%">
    <h3>menu</h3>
    <div>
      <router-link to="/미팅룸관리">미팅룸관리</router-link>
    </div>
    <div>
      <router-link to="/지원자관리">지원자관리</router-link>
    </div>
    <div>
      <router-link to="/Q&A">Q&A</router-link>
    </div>
    <div>
      <router-link :to="{ name: 'profile', params: { useremail } }">프로필</router-link>
    </div>
    <div>
      <input type="button" value="로그아웃" @click="logout">
    </div>
  </div>


</template>

<script>
import { mapGetters, mapActions } from 'vuex'

export default {
    name: 'WebterviewSidebar',
    computed: {
      ...mapGetters(['email']),
      useremail() {
        return this.email
      },
    },
    methods: {
      ...mapActions(['logout']),
    },
  }
</script>

<style scoped>
.w3-sidebar{
    background-color: gainsboro;
}
a{
    text-decoration: none;
}
</style>