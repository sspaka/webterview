<template>
  <div class="limiter">
    <div class="container-login100 shadow-lg">
      <div class="wrap-login100" style="margin-left: 20%; margin-right: 10%;">
        <!-- <div class="headLine2 mb-2">{{ board }}</div> -->

        <div class="board-wrap" style="width: 100%">
          <div class="detail-head ty02" style="text-align: left;">
            <div class="line-set">
                  <span v-if="board.boardType == 1" class="text-group">[공지]</span>
                  <span v-if="board.boardType == 2" class="text-group">[질문]</span>
                <span class="title headLine2">{{ board.boardTitle }}</span>
            </div>
              <div class="status tal">
                  <div class="manager">작성자{{ board.userNo }}</div>
              </div>
              <span class="date"> 
                <div>등록 시간: {{ changeDate(board.boardRegdate) }} </div>
                최근 수정: {{ changeDate(board.boardUpdate) }}
              </span>
          </div>
          <div class="datail-content mb20 headLine2" style="text-align: left; font-size: 16px;">{{ board.boardContent }}</div>
        </div>

        <!-- <dl class="row"> -->

        <!-- <div class="form-group row">
          <label class="col-lg-4 col-form-label">제목 </label>
          <div class="col-lg-6">
            <div class="inputNew d-flex flex-col align-item-center justify-content-center" >
              <p style="color: black;">{{ board.boardTitle }}</p>
            </div>
          </div>
        </div> -->

        <!-- <div class="form-group row">
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
        </div> -->

        <!-- </dl> -->

        <div style="width: 100%">
          <!-- Comment UI -->
          <comment-list :boardNo="board.boardNo"></comment-list>
        </div>

         <div class="d-flex justify-content-center">
          <button class="login100-form-btn button-edit mx-2" style="width: 12vh;" @click="goBoardEdit">수정</button>
          <button class="login100-form-btn button-delete mx-2" style="width: 12vh;" @click="deleteBoard(boardNo)">삭제</button>
          <button class="login100-form-btn button-list mx-2" style="width: 12vh; background-color: #3c90e2;" @click="goBoards()">목록</button>
          <!-- <router-link class="return my-2" :to="{ name: 'boards' }" ><div style="height: 25px; width: 30px; background-color: #3c90e2; color: #fff; border-radius: 15%; margin: auto;">목록</div></router-link> -->
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
      goBoards() {
        router.push({name: 'boards'})
      },
      changeDate(date) {
        const moment = require('moment-timezone')
        const time = moment(date).tz("Asia/Seoul").format('YYYY-MM-DD HH:mm');
        return time
      }
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


