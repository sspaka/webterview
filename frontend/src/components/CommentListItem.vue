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

<style scoped>
.comment-list-item {
  border: 1px bold #121212;
  font-size: 1.2rem;
}

.button {
  color: #121212 ;
  background-color: #f5f5f5;
}
</style>