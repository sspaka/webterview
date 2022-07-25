<template>
  <div>
    <h1 class="box">Board QnA</h1>
    <input type="button" value="새로운 글쓰기" @click="boardwrite"/>
    
    <ul class="board-ul">
      QnA
        <li v-for="board in boards" :key="board.boardNo">
          <div class="board-list d-flex align-items-center justify-content-center">
            <div class="board">
              <p class="fw-bold fs-6" style="color: #121212">
              제목: {{ board.boardTitle }}
              </p>
              <p>내용: {{ board.boardContent }}</p> <span> 생성일: {{ board.boardRegDate }}</span>
              <router-link :to="{ name: 'board', params: {boardNo: board.boardNo, isSpecific: true} }">
                <span class="fs-5">{{ board.boardTitle }}</span>
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
  .board {
  width: 400px;
  background-color: #f5d682;
  border: 1px solid red;
  margin: 2px;
  }
</style>