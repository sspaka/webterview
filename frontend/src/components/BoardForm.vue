<template>
  <div class="limiter ">
    <div class="container-login100 shadow-lg">
      <div class="wrap-login100" style="margin-left: 20%; margin-right: 10%;">
      <!-- form 시작  -->
        <div v-if="action === 'create'" class="headLine2">게시글 작성</div>
        <div v-if="action === 'update'" class="headLine2">게시글 수정</div>
        <!-- <div v-if="action === 'update'" class="mb-2">게시글 번호 {{ board.boardNo }}</div> -->
        <br>
        <!-- 공지용 글쓰기 -->
        <div v-if="newBoard.boardType == 1" class="board-wrap" style="width: 100%">
          <div class="row-table-type">
            <div class="t-row">
                <div class="t-col">
                    <label for="titNm" class="label">
                      <i class="icon required"><span class="blind"></span></i>제목                  
                    </label>
                </div>
                <div class="t-col">
                    <input  v-model="newBoard.boardTitle" type="text" class="form-control wide boardTitle" id="titNm" name="titNm" placeholder="제목을 입력하세요." maxlength="100" required>
                </div>
            </div>
            
            <div class="t-row" style="height: 40vh;">
                <div class="t-col">
                    <label for="txtCont" class="label" style="height: 40vh;">
                      <i class="icon required"><span class="blind"></span></i>공지내용
                    </label>
                </div>
                
                <div class="t-col">
                    <div class="textarea" style="height: 40vh;">
                          <textarea  v-model="newBoard.boardContent" style="height: 100%;" name="txtCont2" id="txtCont" class="form-control noresize boardContent" placeholder="공지할 내용을 입력하세요." maxlength="2000" required></textarea>
                    </div>
                </div>
            </div>
          </div>
        </div>

        <!-- QnA용 글쓰기 -->
        <div v-if="newBoard.boardType == 2" class="board-wrap" style="width: 100%">
          <div class="row-table-type">
            <div class="t-row">
                <div class="t-col">
                    <label for="titNm" class="label">
                      <i class="icon required"><span class="blind"></span></i>제목                  
                    </label>
                </div>
                <div class="t-col">
                    <input  v-model="newBoard.boardTitle" type="text" class="form-control wide boardTitle" id="titNm" name="titNm" placeholder="제목을 입력하세요." maxlength="100" required>
                </div>
            </div>
            
            <div class="t-row" style="height: 40vh;">
                <div class="t-col">
                    <label for="txtCont" class="label" style="height: 40vh;">
                      <i class="icon required"><span class="blind"></span></i>문의내용
                    </label>
                </div>
                
                <div class="t-col">
                    <div class="textarea" style="height: 40vh;">
                          <textarea  v-model="newBoard.boardContent" style="height: 100%;" name="txtCont2" id="txtCont" class="form-control noresize boardContent" placeholder="도움이 필요하신가요? 함께라면 해결하지 못할 문제는 없습니다. 도움을 요청해 보세요." maxlength="2000" required></textarea>
                    </div>
                </div>
            </div>
          </div>
        </div>
        <br>
        <form @submit.prevent="onSubmit" class="login100-form" >
          <!-- <div class="wrap-input100">
              <input class="input100" v-model="newBoard.boardTitle" type="text" placeholder="게시글 제목을 입력하세요.." required>
          </div>

          <div class="wrap-input100 validate-input" data-validate = "Password is required">
              <input class="input100" v-model="newBoard.boardContent" type="textarea" placeholder="게시글 내용을 입력하세요" required>
          </div> -->
          <!-- <input type="radio" :name=boardType value="1" v-model="newBoard.boardType" />공지
          <input type="radio" :name=boardType value="2" v-model="newBoard.boardType" />질문 -->
                    
          <div class="container-login100-form-btn">
            <button class="login100-form-btn mx-1" type="button" style="width: 12vh; background-color: #4f5a66" @click="goBoards"> 취소 </button>
            <button class="login100-form-btn mx-1" type="submit" style="width: 12vh; background-color: #3c90e2"> 등록 </button> <!--  {{ action }} -->
          </div>
        </form>
      </div>
    </div>
  </div>
</template>


<script>
import router from '@/router'
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
          boardType: "2", // QnA는 2, 공지는 1 일단 2로 고정
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
      goBoards() {
        router.push({name: 'boards'})
      },
    },
    created() {
      this.newBoard.userNo = this.profile.userNo
      this.newBoard.boardType = this.profile.userRole
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

input:focus {outline:1px solid #111;}

textarea:focus {outline:1px solid #111;}

.boardTitle {
  height: 40px;
  width: 100%;
  border: 1px solid #e3e7eb;
  vertical-align: middle;
  padding: 5px 18px;
  
  font-family: Poppins-Medium;
  font-size: 15px;
  line-height: 1.5;
}

.boardContent {
  height: 200px;
  width: 100%;
  border: 1px solid #e3e7eb;
  vertical-align: middle;
  padding: 5px 5px 5px 18px;

  font-family: Poppins-Medium;
  font-size: 15px;
  line-height: 1.5;
}
</style>