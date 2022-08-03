<template>
  <!-- <div class="w3-sidebar w3-bar-block" style="width:25%"> -->
  <div class="icon-bar">
    <br>
    <img class="logo" src="@/assets/logo.png" alt="Logo" style="width: 90%;" @click="logo">
    <router-link to="/webterview/meetingroom_man"><i class="fa-solid fa-house-laptop"></i>미팅룸관리</router-link>
    <router-link to="/webterview/raterMan"><i class="fa-regular fa-file"></i>면접관관리</router-link>
    <router-link to="/webterview/applicant_man"><i class="fa-solid fa-users"></i>지원자관리</router-link>
    <router-link to="/webterview/evaluationMan"><i class="fa-regular fa-file"></i>평가표관리</router-link>
    <router-link to="/webterview/boards"><i class="fa-solid fa-bullhorn"></i>게시판</router-link>
    <router-link to="/webterview/ranking"><i class="fa-solid fa-arrow-up-short-wide"></i>순위</router-link>
    <div class="dropdown-center">
      <a href="#" class="d-flex justify-content-center align-items-center text-black text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
        <i class="fa-regular fa-circle-user"></i>User
      </a>
      <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownUser1" style="background-color: #fff">
        <router-link :to="{ name: 'profile', params: { useremail } }">프로필</router-link>
        <li><hr class="dropdown-divider"></li>
        <li @click="logout"><a href="">로그아웃</a></li>
      </ul>
    </div>
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
  background-color: #fff;
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
  color: #121212;    
  font-size: 13px;  
  }

.icon-bar a:hover {    
  color: #000;
  background-color: #f5f5f5;
  }

.btn-logout {
  border-radius: 15px;
  display: inline-block;
  font-family: "paybooc-Light", sans-serif;
  box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
  padding: 4px 8px;
  color: white;
  background: #F05454;
  transition: 0.25s;
  font-weight: 600;
}

.btn-logout:hover {
  letter-spacing: 0px;
  transform: scale(1.2);
  cursor: pointer;
}

</style>