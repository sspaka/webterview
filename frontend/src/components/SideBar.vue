<template>
  <div class="icon-bar btn-group-toggle" data-toggle="buttons">
    <br>
    <img class="logo" src="@/assets/logo.png" alt="Logo" style="width: 90%;" @click="logo">
    <div :class="{'light': noticemeet ==='light', 'dark': noticemeet==='dark'}">
      <router-link class="thema-meet" @click="clickTabmeet" to="/webterview/meetingroom_man">미팅룸관리</router-link>
    </div>
    <div :class="{'light': noticeappli ==='light', 'dark': noticeappli==='dark'}">
      <router-link class="thema-appli" @click="clickTabappli" to="/webterview/applicant_man">지원자관리</router-link>
    </div>
    <div :class="{'light': noticeeval ==='light', 'dark': noticeeval==='dark'}">
      <router-link class="thema-eval" @click="clickTabeval" to="/webterview/boards">게시판</router-link>
    </div>
    <div :class="{'light': noticerank ==='light', 'dark': noticerank==='dark'}">
      <router-link class="thema-rank" @click="clickTabrank" to="/webterview/ranking">순위</router-link>
    </div>

    <div class="dropdown-center">
      <a href="#" class="d-flex justify-content-center align-items-center text-white text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
        User
      </a>
      <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="dropdownUser1" style="background-color: #1b3b5b">
        <div :class="{'light': noticeprof ==='light', 'dark': noticeprof==='dark'}">
          <router-link class="thema-prof" @click="clickTabprof" :to="{ name: 'profile', params: { useremail } }">프로필</router-link>
        </div>
        <li><hr class="dropdown-divider"></li>
        <li @click="logout"><a href="">로그아웃</a></li>
      </ul>
    </div>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  </div>
</template>

<script>
import { mapGetters,  mapActions } from 'vuex'
import router from '@/router'

export default {
    components: { },
    data() {
      return {
        noticemeet: "dark",
        noticeappli: "light",
        noticeeval: "light",
        noticerank: "light",
        noticeprof: "light",
      }
    },
    computed: {
      ...mapGetters(['email']),
      useremail() {
        return this.email
      },
    },
    methods: {
      ...mapActions(['logout']),
      logo() {
        router.push({ name: 'meetingroom_man' })
      },
      clickTabmeet() {
        if (this.noticemeet ==="light") {
          this.noticemeet = "dark"
        } else {
          this.noticemeet = "light"
        }
      },
      clickTabappli() {
        if (this.noticeappli ==="light") {
          this.noticeappli = "dark"
        } else {
          this.noticeappli = "light"
        }
      },
      clickTabeval() {
        if (this.noticeeval ==="light") {
          this.noticeeval = "dark"
        } else {
          this.noticeeval = "light"
        }
      },
      clickTabrank() {
        if (this.noticerank ==="light") {
          this.noticerank = "dark"
        } else {
          this.noticerank = "light"
        }
      },
      clickTabprof() {
        if (this.noticeprof ==="light") {
          this.noticeprof = "dark"
        } else {
          this.noticeprof = "light"
        }
      }
      
      
    },
    created() {
      
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
.icon-bar a{    
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

.dark .thema-meet{
    background:#444;
    color:#F05454
}

.dark .thema-appli{
    background:#444;
    color:#F05454
}

.dark .thema-eval{
    background:#444;
    color:#F05454
}

.dark .thema-rank{
    background:#444;
    color:#F05454
}

.dark .thema-prof{
    background:#444;
    color:#F05454
}

</style>