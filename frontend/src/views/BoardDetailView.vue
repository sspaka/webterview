<template>
  <div class="limiter">
    <div class="container-login100 shadow-lg">
      <div class="wrap-login100" style="margin-left: 20%; margin-right: 10%;">
        <div class="head mb-4">게시글 번호 {{ board.boardNo }}</div>
        <dl class="row">

        <div class="form-group row">
          <label class="col-lg-4 col-form-label">제목 </label>
          <div class="col-lg-6">
            <div class="inputNew d-flex flex-col align-item-center justify-content-center" >
              <p style="color: black;">{{ board.boardTitle }}</p>
            </div>
          </div>
        </div>

        <div class="form-group row">
          <label class="col-lg-4 col-form-label">내용</label>
          <div class="col-lg-6">
            <div class="inputNew d-flex flex-col align-item-center justify-content-center" >
              <p style="color: black;">{{ board.boardContent }}</p>
            </div>
          </div>
        </div>

        <div class="form-group row">
          <label class="col-lg-4 col-form-label">작성자 번호</label>
          <div class="col-lg-6">
            <div class="inputNew d-flex flex-col align-item-center justify-content-center" >
              <p style="color: black;">{{board.userNo}}</p>
            </div>
          </div>
        </div>

        <div class="form-group row">
          <label class="col-lg-4 col-form-label">작성 날짜</label>
          <div class="col-lg-6">
            <div class="inputNew d-flex flex-col align-item-center justify-content-center" >
              <p style="color: black;">{{board.boardRegdate}}</p>
            </div>
          </div>
        </div>

        </dl>

        <div class="d-flex justify-content-center">
          <button class="login100-form-btn button-edit mx-2" @click="goBoardEdit">수정하기</button>
          <button class="login100-form-btn button-delete mx-2"  @click="deleteBoard(boardNo)">삭제하기</button>
        </div>

        <div>
          <!-- Comment UI -->
          <comment-list :boardNo="board.boardNo"></comment-list>
        </div>

      </div>
    </div>
  </div>
</template>

<script>
  import { mapGetters, mapActions } from 'vuex'
  import CommentList from '@/components/CommentList.vue'
  import router from '@/router'

  export default {
    name: 'BoardDetail',
    components: { CommentList },
    data() {
      return {
        boardNo: "",
      }
    },
    computed: {
      ...mapGetters(['isAuthor', 'board']),
    },
    methods: {
      ...mapActions([
        'fetchBoard',
        'deleteBoard',
        'fetchBoardNo',
      ]),
      goBoardEdit() {
        router.push({ name: 'boardEdit', params: { boardNo: this.boardNo } })
      },
    },
    created() {
      this.boardNo = this.$route.params.boardNo
      console.log(this.boardNo)
      this.fetchBoardNo(this.boardNo)
      if (this.boardNo) {
        this.fetchBoard(this.boardNo)
      }
    }
  }
</script>

<style scoped>
  .button-edit {
    background-color: green;
  }
  .button-delete {
    background-color: crimson;
  }
</style>


