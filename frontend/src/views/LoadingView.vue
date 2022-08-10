<template> 
    <div class="container-fluid">
        <div class="text-center">
            <div class="error mx-auto" data-text="404">Loading!</div>
            <p class="lead text-gray-800 mb-5">Wait for a Sec...</p>
            <router-link to="/" ><p>&larr; Back to home</p></router-link>
        </div>

    </div>
</template>

<script>

import { mapActions, mapGetters } from "vuex";
import axios from 'axios'
import router from '@/router';

export default {
    name: "LoadingView",

  components: {
  },
  data() {
    return {
      
    };
  },
  computed: {
    // ...mapState([])
    ...mapGetters([ 'userNo', 'token' , 'email'])
  },
  methods: {
    ...mapActions(['fetchProfile']),

    readGroup(userNo) {
      axios({
          // url: drf.applicants.applicants(),
          url: '/admin'+'/group/' + userNo,
          method: 'get',
          headers: {
            'access-token': this.token,
          }
      })
        .then(res => {
          console.log(res.data.group)
          this.groupNo = res.data.group.groupNo
        })
        .catch(err => {
          console.error(err)
        })
    },
    goHome(){
        router.push({name: 'meetingroom_man'})
    }
  },

    created() {
        this.fetchProfile(this.email)
        this.readGroup(this.userNo)
        this.goHome
    }

};
</script>

<style>

</style>