<template>
  <div class="limiter ">
    <div class="container-login100 shadow-lg">
      <div class="wrap-login100">
      <!-- form 시작  -->
        <div v-if="action === 'create'" class="head mb-4">게시글 쓰기</div>
        <div v-if="action === 'update'" class="head mb-4">게시글 수정</div>
        <div v-if="action === 'update'" class="mb-2">게시글 번호 {{ board.boardNo }}</div>
        <br>
        <form @submit.prevent="onSubmit" class="login100-form" >
          <div class="wrap-input100">
              <input class="input100" v-model="newBoard.boardTitle" type="text" placeholder="게시글 제목을 입력하세요.." required>
          </div>

          <div class="wrap-input100 validate-input" data-validate = "Password is required">
              <input class="input100" v-model="newBoard.boardContent" type="text" placeholder="게시글 내용을 입력하세요" required>
          </div>
          <input type="radio" :name=boardType value="1" v-model="newBoard.boardType" />공지
          <input type="radio" :name=boardType value="2" v-model="newBoard.boardType" />질문
                    
          <div class="container-login100-form-btn">
            <button class="login100-form-btn" type="submit">{{ action }}</button>
          </div>
        </form>
      </div>
    </div>
  </div>
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