<template>
  <li class="comment-list-item mt-2">
    {{ comment.CommentAnswer }}  
    <button class="button" @click="deleteComment()">삭제</button>

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
    ...mapGetters(['currentUser']),
  },
  methods: {
    ...mapActions(['deleteComment', 'fetchComment']),
  },
  created() {
    //this.fetchComment()
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