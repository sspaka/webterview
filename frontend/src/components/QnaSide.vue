<template>
  <div>
    <h1 class="box">Board QnA</h1>
    <input type="button" value="새로운 글쓰기"/>
    <hr class="bg-light">
    <ul class="community-ul">
      <!-- community 버튼을 눌렀을때 나오는 전체 게시글 -->
        <li v-for="review in reviews" :key="review.pk">
      <!-- v-show="review.movie.title==" -->
          <p class="fw-bold fs-6">
          ({{ review.movie_title }}) 관련된 글
          </p>
        <!-- 작성자 -->
        <span class="fs-6">
          {{ review.user.username }}님이 작성:
        </span>
        <!-- 글 이동 링크 (제목) -->
        <router-link class="review-title"
          :to="{ name: 'review', params: {reviewPk: review.pk, isSpecific: false} }">
          <span class="fs-5">
            {{ review.title }}

          </span>

        </router-link>

        <!-- 댓글 개수/좋아요 개수 -->
        <p class="fw-bold">
         댓글:({{ review.comment_count }})
        <font-awesome-icon class="mx-2" style="color:red;" icon="fa-solid fa-heart" beat/>:{{ review.like_count }}
        </p>
        <hr>
      </li>
<!-- /////////////////////////////////////////////// -->
  </ul>
   
  </div>
</template>

<script>
export default {

}
</script>

<style>

</style>