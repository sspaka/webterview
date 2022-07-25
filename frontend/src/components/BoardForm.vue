<template>
  <h3>새러운 글 작성하셍</h3>
  <form @submit.prevent="onSubmit">
    <div>
      <label for="title"> 제목을 입력하실게요 </label>
      <input v-model="newBoard.boardTitle" type="text" class="input100" id="title" placeholder="게시글 내용"/>
    </div>

    <div>
      <label for="title"> 내용도 입력하실까요 </label>
      <input v-model="newBoard.boardContent" type="text" class="input100" id="title" placeholder="게시글 내용"/>
    </div>

    <div>
      <button class="create-button">{{ action }}</button>
    </div>
  </form>
</template>


<script>
import { mapActions, mapGetters } from 'vuex'

  export default {
    name: 'boardForm',
    props: {
      board: Object,
      action: String,
    },
    data() {
      return {
        newBoard: {
          boardTitle: this.board.boardTitle,
          userNo: '',
          boardContent: this.board.boardContent,
          boardType: 1 // QnA는 1, 공지는 2 일단 1로 고정
        },
      }
    },
    computed: {
      ...mapGetters(['boards', 'profile'])
    },
    methods: {
      ...mapActions(['createBoard', 'updateBoard']),
      onSubmit() {
        if (this.action === 'create') {
          this.createBoard(this.newBoard)
        } else if (this.action === 'update') {
          const payload = {
            boardNo: this.board.boardNo,
            ...this.newBoard,
          }
          this.updateBoard(payload)
        }
      },
    },
  }
</script>


<style>
  .create-button {
  width: 100px;
  background: coral;
  border: 2px solid coral;
  border-radius: 10px;
}
</style>