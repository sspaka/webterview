<template>
  <div>
    <BoardForm :board="board" action="update" />
  </div>

</template>

<script>
import BoardForm from '@/components/BoardForm.vue'
import { mapGetters, mapActions } from 'vuex'
  export default {
    name: 'BoardEditView',
    components: { BoardForm },
    computed: {
      ...mapGetters(['board', 'isBoard',])
    },
    methods: {
      ...mapActions(['fetchBoard'])
    },
    created() {
      this.fetchBoard(this.$route.params.boardNo)
    },
  }
</script>

<style></style>
