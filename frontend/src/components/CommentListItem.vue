<template>
  <!-- {{ comment }} -->
  <li class="comment-list-item mt-2">
    {{ comment.commentAnswer }}  
    <button class="button" @click="deleteComment(comment)">삭제</button>
  </li>
  
</template>

<script>
import { mapGetters, mapActions } from 'vuex'

export default {
  name: 'CommentListItem',
  props: { comment: Object },
  data() {
    return {
    }
  },
  computed: {
    ...mapGetters(['currentUser', 'comments']),
  },
  methods: {
    ...mapActions(['deleteComment']),
  },
  created() {
    
  }

}
</script>

<style>
.comment-list-item {
  list-style: none;

}

.username {
  text-decoration: none;
  color: black;
}

.button {
  border-radius: 10px;
  background-color: coral;
  border: 2px coral;
  color: white;
}
</style>