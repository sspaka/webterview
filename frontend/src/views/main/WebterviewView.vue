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
export default {
    name: 'WebterviewView',

    components: { WebterviewSidebar  },
    data() {
        return {

        }
    },
    computed: {

    },
    methods: {

    }

}
</script>

<style>

</style>