<template>
  <div class="limiter d-flex justify-content-center">
    <div class="container-login100 shadow-lg">
      <div class="card shadow-lg p-3 mb-5 bg-body rounded" style="width: 900px; margin-top: 75px; margin-left: 75px; margin-right: 75px;margin-bottom: 75px;">
        <div class="head mb-4">게시글 {{ board.boardNo }}</div>
        <dl class="row">

        <div class="form-group row">
          <label class="col-lg-4 col-form-label">Title </label>
          <div class="col-lg-6">
            <div class="inputNew d-flex flex-col align-item-center justify-content-center" >
              <p style="color: black;">{{ board.boardTitle}}</p>
            </div>
          </div>
        </div>

        <div class="form-group row">
          <label class="col-lg-4 col-form-label">Content</label>
          <div class="col-lg-6">
            <div class="inputNew d-flex flex-col align-item-center justify-content-center" >
              <p style="color: black;">{{ board.boardContent}}</p>
            </div>
          </div>
        </div>

        <div class="form-group row">
          <label class="col-lg-4 col-form-label">userNo</label>
          <div class="col-lg-6">
            <div class="inputNew d-flex flex-col align-item-center justify-content-center" >
              <p style="color: black;">{{board.userNo}}</p>
            </div>
          </div>
        </div>

        <div class="form-group row">
          <label class="col-lg-4 col-form-label">date</label>
          <div class="col-lg-6">
            <div class="inputNew d-flex flex-col align-item-center justify-content-center" >
              <p style="color: black;">boardRegDate {{board.boardRegdate}}</p>
            </div>
          </div>
        </div>

        </dl>

        <div class="d-flex justify-content-center">
          <router-link :to="{ name: 'boardEdit', params: { boardNo } }">
            <button class="button-edit mt-3 mx-3 ">수정</button>
          </router-link>
          <button class="button-delete w-50"  @click="deleteBoard(boardNo)">삭제</button>
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

  export default {
    name: 'BoardDetail',
    components: { CommentList },
    data() {
      return {
        boardNo: this.$route.params.boardNo,
      }
    },
    computed: {
      ...mapGetters(['isAuthor', 'board']),
    },
    methods: {
      ...mapActions([
        'fetchBoard',
        'deleteBoard',
      ])
    },
    created() {
        this.fetchBoard(this.boardNo)
    }
  }
</script>

<style>
.button-edit {
  color: coral;
  background-color: rgb(246, 242, 242);
  border: 2px solid coral;
  outline-color: coral;
  border-radius: 10px;
}

.button-delete {
  color: coral;
  background-color: rgb(246, 242, 242);
  border: 2px solid coral;
  outline-color: coral;
  border-radius: 10px;
}

.button-like {
  color: rgb(246, 242, 242);
  background-color: coral;
  
  border: 2px solid coral;
  outline-color: coral;
  border-radius: 10px;
}
</style>


