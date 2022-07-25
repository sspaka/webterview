<template>
  <div>
    <h1 class="box">Board QnA</h1>
    <input type="button" value="새로운 글쓰기" @click="boardwrite"/>
    {{ boards }}
    
    <!-- <ul class="community-ul">
      게시글 목록들
        <li v-for="board in boards" :key="board.boardNo">
          <p class="fw-bold fs-6">
          ({{ board.boardTitle }}) 관련된 글
          </p>
      </li>
    </ul> -->
  </div>
</template>

<script>
  import { mapActions, mapGetters } from 'vuex'
  import router from '@/router'

  export default {
    name: 'BoardList',
    data() {
      
    },
    computed: {
      ...mapGetters(['boards'])
    },
    methods: {
      ...mapActions(['fetchBoards']),
      boardwrite() {
        router.push({ name: 'BoardWrite' })
      }
    },
    created() {
      this.fetchBoards()
    },
  }
</script>

<style>

</style>