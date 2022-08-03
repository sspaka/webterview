<template>
  <!-- <img class="logo" src="@/assets/logo.png" alt="Logo" @click="logo"> -->
  <div>
    <WebterviewSidebar/>
    <div class="dropdown">
      <button class="dropbtn">User</button>
    <div class="dropdown-content">
      <router-link class="profile" :to="{ name: 'profile', params: { useremail } }">프로필</router-link>
      <li @click="logout"><a href="">로그아웃</a></li>
    </div>
</div>
  </div>
  <!-- <MeetingRoomMan/> -->
  <router-view/>

</template>
<script>
// import MeetingRoomMan from '@/components/MeetingRoomMan.vue'
import WebterviewSidebar from '@/components/WebterviewSidebar.vue'
import { mapActions, mapGetters } from 'vuex'

export default {
    name: 'WebterviewView',

    components: { WebterviewSidebar },
    data() {
        return {

        }
    },
    computed: {
      ...mapGetters(['email']),
      useremail() {
        return this.email
      },
    },
    methods: {
      ...mapActions(['fetchProfile']),
      ...mapActions(['logout']),
    },
    created() {
    const payload = { useremail: this.email}
    this.fetchProfile(payload)
  },

}
</script>

<style scoped>
.dropbtn {
  position: fixed;
  top: 1%;
  left: 93%;
  background-color: #F05454;
  color: white;
  padding: 16px;
  font-size: 16px;
  border: none;
  border-radius: 15px;
}

.dropdown {
  position: relative;
  display: inline-block;
}

.dropdown-content {
  display: none;
  position: fixed;
  top: 7%;
  left: 89%;
  background-color: #f1f1f1;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

.dropdown-content a {
  color: gray;
  padding: 10px 10px 10px 10px;
  text-decoration: none;
  display: block;
  font-size:15px;
}

.dropdown-content a:hover {
  background-color: #ddd;
  color:#F05454;
  }

.dropdown:hover .dropdown-content {display: block;}

.dropdown:hover .dropbtn {background-color: #30475E;}
</style>