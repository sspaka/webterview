<template>
  <el-card :body-style="{ padding: '0px' }">
    <div class="image-wrapper">
      <el-skeleton style="width: 100%">
        <template #template>
          <el-skeleton-item variant="image" style="width: 100%; height: 190px" />
        </template>
      </el-skeleton>
      </div>
    <div class="session">
      <span class="title">방번호:{{ roomNo }}</span>
      <div class="bottom">
        <span>면접관 수:{{ raterNo }}</span>
      </div>
      <div><span>지원자 수:{{ applicantNo }}</span></div>
    </div>
  </el-card>
</template>

<script>
export default {
  name: 'ConferenceName',

  // props: {
  //   roomNo: {
  //     type: String,
  //     default: "방번호"
  //   },
  //   raterNo: {
  //     type: String,
  //     default: "면접관"
  //   },
  //   applicantNo: {
  //     type: String,
  //     default: "지원자"
  //   }
  // },

  setup () {}
}
</script>

<style>
.session {
  text-align: left; 
  padding: 14px; 
  background-color: #f5f5f5;
  border: 1px solid #121212;
  margin: 2px;
}

.el-card {
  margin: 0 8px;
  margin-bottom: 40px;
}
.el-card .image-wrapper {
  width: 100%;
  height: 190px;
}
.el-card .title {
  font-weight: bold;
}
.el-card .bottom {
  margin-top: 5px;
  display:-webkit-box;
  word-wrap:break-word;
  -webkit-box-orient:vertical;
  overflow:hidden;
  text-overflow:ellipsis;
}
/* 테블릿, 모바일의 경우 두 줄 말줄임표시 */
@media (max-width: 1269px) {
  .el-card .bottom {
    -webkit-line-clamp: 2;
    height:42px;
  }
}
/* 데스크탑의 경우 세 줄 말줄임표시 */
@media (min-width: 1270px) {
  .el-card .bottom {
    -webkit-line-clamp: 3;
    height:60px;
  }
}

</style>