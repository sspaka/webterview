<template>
  <div class="account-error-list">
    <div v-for="(errors, field) in authError" :key="field">
      {{ field }}
      {{ errors }}
      <!-- <p v-for="(error, idx) in errors" :key="idx">
        {{ error }}
      </p> -->
    </div>

  </div>
</template>

<script>
  import { mapGetters } from 'vuex'
  
  export default {
    name: 'AccountErrorList',
    computed: {
      ...mapGetters(['authError'])
    },
    created() {
      console.log(this.authError)
    }
  }
</script>

<style>
  .account-error-list {
    color: red;
  }
</style>
