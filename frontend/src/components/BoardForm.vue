<template>
  <form @submit.prevent="onSubmit">
    <div class="d-flex flex-col justify-content-center">
      <div class=" wrap-input100 my-1 d-flex justify-content-center align-item-center">
        <input v-model="newBoard.boardTitle" type="text" class="w-50 input100" id="title" placeholder="게시글 제목"/>
      </div>
      <div class="my-1 d-flex justify-content-center align-item-center">
        <input v-model="newBoard.boardContent" type="text" class=" w-50 input100" id="title" placeholder="게시글 내용"/>
      </div>
    </div>

    <div>
      <button class="create-button">{{ action }}</button>
    </div>
  </form>
  <p>{{ profile }}</p>
  <p>{{ board }}</p>
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
          userNo: "",
          boardContent: this.board.boardContent,
          boardType: "1", // QnA는 1, 공지는 2 일단 1로 고정
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
    created() {
      this.newBoard.userNo = this.profile.userNo
    }
  }
</script>


<style>
  .create-button {
  width: 100px;
  background: #f05454;
  border: 2px solid #f05454;
  border-radius: 10px;
}
</style>