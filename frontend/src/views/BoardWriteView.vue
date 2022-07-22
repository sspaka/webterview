<template>
  <div>
    <h3 class="new-writing">새로운 글 쓰기</h3>
    <hr>
    <board-form :board="board" action="create"></board-form>

  </div>
</template>

<script>
import BoardForm from '@/components/BoardForm.vue'

  export default {
    name: 'boardWriteView',
    components: { BoardForm },
    data() {
      return {
        board: {
          pk: null,
          movie_title: '',
          title: '',
          content: '',
        }
      }
    },
  }
</script>

<style>
.new-writing {
  color: black;
  text-align: center;
  margin-top: 15px;
}
</style>