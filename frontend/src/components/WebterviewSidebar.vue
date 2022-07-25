<template>
  <!-- <div class="w3-sidebar w3-bar-block" style="width:25%"> -->
  <div class="icon-bar">
    <router-link to="/webterview/meetingroom_man">미팅룸관리</router-link>
    <router-link to="/webterview/applicant_man">지원자관리</router-link>
    <router-link to="/webterview/boards">게시판</router-link>
    <router-link :to="{ name: 'profile', params: { useremail } }">프로필</router-link>
    <input type="button" value="로그아웃" @click="logout">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
.icon-bar {    
  height: 100%;
  width: 17%;
  background-color: #30475E;
  position: fixed; /* Fixed Sidebar (stay in place on scroll) */    /*z-index: 1;*/ /* Stay on top */    
  top: 0; /* Stay at the top */    
  left: 0;    
  overflow-x: hidden; /* Disable horizontal scroll */    
  padding-top: 0;  
  }
.icon-bar a {    
  display: block;    
  text-align: center;    
  padding: 16px;    
  transition: all 0.3s ease;    
  color: white;    
  font-size: 13px;  
  }

.icon-bar a:hover {    
  color: #F05454;
  background-color: #1b3b5b;
  }







</style>