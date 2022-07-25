<template>
  <div>
    <h1>Edit Board</h1>
    <!-- <review-form v-if="isReview" :review="review" action="update"> -->
    <board-form :board="board" action="update">
        수정하기
    </board-form>
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
