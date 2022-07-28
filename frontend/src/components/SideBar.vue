<template>
  <div class="icon-bar">
    <router-link to="/webterview/meetingroom_man">미팅룸관리</router-link>
    <router-link to="/webterview/applicant_man">지원자관리</router-link>
    <router-link to="/webterview/boards">게시판</router-link>
    <router-link :to="{ name: 'profile', params: { useremail } }">프로필</router-link>
    <input class="btn-logout" type="button" value="로그아웃" @click="logout">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  </div>
</template>

<script>
import { mapGetters,  mapActions } from 'vuex'

export default {
    components: { },
    computed: {
      ...mapGetters(['email']),
      useremail() {
        return this.email
      },
    },
    methods: {
      ...mapActions(['logout']),
    },
    created() {
      
    }
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