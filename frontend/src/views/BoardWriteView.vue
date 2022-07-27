<template>
  <div>
    <BoardForm :board="board" action="create"/>
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