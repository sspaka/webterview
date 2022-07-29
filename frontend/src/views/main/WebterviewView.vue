<template>
  <img class="logo" src="@/assets/logo.png" alt="Logo" @click="logo">
  <div>
  <WebterviewSidebar/>
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

    components: { WebterviewSidebar  },
    data() {
        return {

        }
    },
    computed: {
      ...mapGetters(['email']),
    },
    methods: {
      ...mapActions(['fetchProfile']),
    },
    created() {
    const payload = { useremail: this.email}
    this.fetchProfile(payload)
  },

}
</script>

<style>

</style>