<template>
  <div>
    <side-bar v-if="token"></side-bar>
    <router-view></router-view>
  </div>
</template>

<script>
import SideBar from '@/components/SideBar.vue'
import { mapGetters, mapActions } from 'vuex'

export default {
  name: 'App',
    components: { SideBar },
    computed: {
      ...mapGetters(['token'])
    },
    methods: {
      ...mapActions(['removeToken'])
    },
    created() {
      this.removeToken()
    }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  background-color: #f5f5f5;
  height: 100%;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
