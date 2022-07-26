<template>
  <div>
    <input class="" type="button" value="새로운 글쓰기" @click="boardwrite"/>
    <h2 class="box">Board 공지</h2>
    <p>공지....</p>

    <h2 class="box">QnA</h2>
    <ul class="board-ul">
        <li v-for="board in boards" :key="board.boardNo">
          <div class="board-list d-flex align-items-center justify-content-center">
            <div class="my-1 board">
              <router-link :to="{ name: 'board', params: {boardNo: board.boardNo, isSpecific: true} }">
                <div class="d-flex w-100 justify-content-between">
                  <h5 class="mb-1">{{ board.boardTitle }}</h5>
                  <small>{{ board.boardRegDate }}</small>
                </div>
                <p class="mb-1">{{ board.boardContent }}</p>
                <small>댓글: {{ board.commentCnt }}</small>
              </router-link>
            </div>
          </div>
      </li>
    </ul>
  </div>
</template>

<script>
  import { mapActions, mapGetters } from 'vuex'
  import router from '@/router'
  

  export default {
    name: 'BoardList',
    data() {
      return {
        params: {
          page: 0,
          size: 10,
          userNo: 1,
        },
      }
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
      this.fetchBoards(this.params)
    },
  }
</script>

<style>
  .board {
    width: 600px;
    background-color: #f5f5f5;
    border: 1px solid #151515;
    margin: 2px;
  }
</style>