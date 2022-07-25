<template>
  <div>
    <h2>여기는 글 세부정보</h2>
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
    {{ isSpecific }}
    <router-link :to="{ name: 'boardEdit', params: { boardNo } }">
      <button class="button-edit mt-3 mx-3 ">수정</button>
    </router-link>
    <button class="button-delete" v-if="isSpecific" @click="deleteBoard(boardNo)">삭제</button>
  </div>
</template>

<script>
  import { mapGetters, mapActions } from 'vuex'

  export default {
    name: 'BoardDetail',
    components: { },
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


