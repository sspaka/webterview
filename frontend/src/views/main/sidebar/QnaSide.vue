<template>
  <div class="limiter">
    <div class="container-login100 shadow-lg">
      <div class="wrap-login100" style="margin-left: 20%; margin-right: 10%;">
        <input class="writeBtn" type="button" value="새로운 글쓰기" @click="boardwrite"/>
        <div class="head mb-4">공지사항</div>
        <table class="table">
          <thead style="background-color: #30475e; color: #fff">
            <tr>
              <th >번호</th>
              <th>제목</th>
              <th >등록날짜</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="notice in notices" :key="notice.boardNo" @click="goBoardDetail(notice.boardNo)">
              <td>{{ notice.boardNo }}</td>
              <td >{{ notice.boardTitle }}</td>
              <td>{{ notice.boardRegdate }}</td>
            </tr>
          </tbody>
        </table>
        <!-- <ul class="board-ul">
          <li v-for="notice in notices" :key="notice.boardNo">
            <div class="board-list d-flex align-items-center justify-content-center">
              <div class="my-1 board">
                <router-link :to="{ name: 'board', params: {boardNo: notice.boardNo} }">
                  <div class="d-flex w-100 justify-content-between">
                    <h5 class="mb-1">{{ notice.boardTitle }}</h5>
                    <small>{{ notice.boardRegDate }}</small>
                  </div>
                  <p class="mb-1">{{ notice.boardContent }}</p>
                  <small>댓글: {{ notice.commentCnt }}</small>
                </router-link>
              </div>
            </div>
          </li>
        </ul> -->
        <div class="head mb-4">QnA</div>

        <table class="table">
          <thead style="background-color: #30475e; color: #fff">
            <tr>
              <th >번호</th>
              <th>제목</th>
              <th >등록날짜</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="board in boards" :key="board.boardNo" @click="goBoardDetail(board.boardNo)">
              <td>{{ notice.boardNo }}</td>
              <td >{{ notice.boardTitle }}</td>
              <td>{{ notice.boardRegdate }}</td>
            </tr>
          </tbody>
        </table>

        <ul class="board-ul">
          <li v-for="board in boards" :key="board.boardNo">
            <div class="board-list d-flex align-items-center justify-content-center">
              <div class="my-1 board">
                <router-link :to="{ name: 'board', params: {boardNo: board.boardNo} }">
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
    </div>
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
          userNo: '',
        },
      }
    },
    computed: {
      ...mapGetters(['boards','notices', 'profile'])
    },
    methods: {
      ...mapActions(['fetchBoards']),
      boardwrite() {
        router.push({ name: 'BoardWrite' })
      },
       goBoardDetail(boardNo) {
        this.$router.push({ name: 'board', params: {boardNo: boardNo }})
      },
    },
    created() {
      this.params.userNo = this.profile.userNo
      this.fetchBoards(this.params)
    },
  }
</script>

<style scoped>
  .board {
    width: 600px;
    background-color: #f5f5f5;
    border: 1px solid #151515;
    margin: 2px;
  }

  .writeBtn {
    position: relative;
    left: 40%;
    border: none;
    padding: 8px 16px;
    border-radius: 15px;
    font-family: "paybooc-Light", sans-serif;
    box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
    text-decoration: none;
    font-weight: 600;
    transition: 0.25s;
    background-color: #f05454;
    color: #fff;
  }

  .writeBtn:hover {
  letter-spacing: 0px;
  transform: scale(1.2);
  cursor: pointer;
}

th {
    text-align: center;
  }
</style>