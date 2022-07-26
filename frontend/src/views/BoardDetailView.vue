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

          
        <p>author Number {{board.userNo}}</p>
        <p>boardRegDate {{board.boardRegdate}}</p>
        <p>update {{board.boardUpdate}}</p>
        <p>comments {{board.comments}}</p>
        
        

        </dl>
        {{ isSpecific }}
        <div class="d-flex justify-content-center">
          <router-link :to="{ name: 'boardEdit', params: { boardNo } }">
            <button class="button-edit mt-3 mx-3 ">수정</button>
          </router-link>
          <button class="button-delete w-50" v-if="isSpecific" @click="deleteBoard(boardNo)">삭제</button>
        </div>
      </div>
    </div>
  </div>

  <div>
    <h2>게시글 세부정보</h2>
    <div>
        <!-- {{ board }} -->
        <p>board Number {{board.boardNo}}</p>
        <p>author Number {{board.userNo}}</p>
        <h2>title {{ board.boardTitle}}</h2>
        <h4>content {{ board.boardContent}}</h4>
        <p>boardRegDate {{board.boardRegDate}}</p>
        <p>update {{board.boardUpDate}}</p>
        <p>comments {{board.comments}}</p>
    </div>
    
    <!-- Comment UI -->
    <comment-list :comments="board.comments"></comment-list>
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
        isSpecific: this.$route.params.isSpecific,
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


