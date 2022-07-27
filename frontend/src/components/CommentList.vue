<template>
  <div class="comment-list">
    여기부터 comment list
    <ul>
      <comment-list-item v-for="comment in comments" :comment="comment" :key="comment.commentNo"></comment-list-item>        
    </ul>

    <comment-list-form></comment-list-form>
  </div>
</template>

<script>
import CommentListItem from '@/components/CommentListItem.vue'
import CommentListForm from '@/components/CommentListForm.vue'
import { mapGetters, mapActions } from 'vuex'


export default {
  name: 'CommentList',
  components: { CommentListForm, CommentListItem },
  props: { boardNo: String },
  computed: {
    ...mapGetters(['comments']),
  },
  methods: {
    ...mapActions(['fetchComments', 'fetchBoard'])
  },
  created() {
    //this.fetchBoard(this.boardNo)
    //this.fetchComments(this.comments)
  }
}
</script>

<style>

</style>