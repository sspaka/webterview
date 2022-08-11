<template>
  <div>
    <div>안녕하세요</div>
    <router-view></router-view>  
  </div>
</template>


<script>
import { mapGetters, mapActions } from 'vuex'

export default {
  name: 'App',
    components: { },
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
  overflow: auto;
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
