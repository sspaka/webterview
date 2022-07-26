<template>
  <form @submit.prevent="createComment(credentials)" class="comment-list-form">
    <!-- <label for="comment">comment: </label> -->
    <hr>
    <input class="comment-input" type="text" id="comment" v-model="credentials.commentAnswer" required placeholder="댓글을 작성해주세요">
    <p>
      <button class="comment-button mt-3">댓글</button>
    </p>
  </form>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'

export default {
  name: 'CommentListForm',
  data() {
    return {
      credentials: {
        boardNo: "",
        commentUserNo: "",
        commentAnswer: "",
        },
    }
  },
  computed: {
    ...mapGetters(['review']),
  },
  methods: {
    ...mapActions(['createComment']),
  }
}
</script>

<style>
.comment-list-form {
  /* border: 1px solid rgb(233, 14, 14); */
  margin: 1rem;
  padding: 1rem;
  border-radius: 10px;
}

.comment-input {
  background-color: rgb(246, 242, 242);
  /* border: none; */
  border: 2px solid rgb(243, 112, 64);
  outline-color: rgb(243, 112, 64);
  display: block;
  width: 350px;
  height: 35px;
  border-radius: 10px;
}

.comment-button {
  color: rgb(246, 242, 242);
  background-color: rgb(243, 112, 64);

  border: 2px solid rgb(246, 242, 242);
  outline-color: rgb(246, 242, 242);
  width: auto;
  height: 35px; 
  border-radius: 10px;
}
</style>