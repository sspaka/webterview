<template>
  <!-- {{ comment }} -->
  <li class="d-flex justify-content-between mt-2 txt1">
    <div>작성자{{ comment.commentUserNo }} : {{ comment.commentAnswer }}</div>
    <button class="deleteCommentBtn" @click="deleteComment(comment)">삭제</button>
  </li>
  <span class="txt1">{{ changeDate(comment.commentRegDate) }}</span>
  
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
    changeDate(date) {
        const moment = require('moment-timezone')
        const time = moment(date).tz("Asia/Seoul").format('YYYY-MM-DD HH:mm');
        return time
      }
  },
  created() {
    
  }

}
</script>

<style scoped>
/* .comment-list-item {
  border: 1px bold #121212;
  font-size: 1.2rem;
} */

.deleteCommentBtn{
  font-family: Poppins-Regular;
  font-size: 13px;
  line-height: 1.5;
  color: #999999;
  background-color: #f5f5f5;
  border-radius: 15%;
}
</style>