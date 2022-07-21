<template>
  <div>로그인시 보여지는 화면!</div>
  <WebterviewSidebar/>
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