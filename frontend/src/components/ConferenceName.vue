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
      <button class="w-btn-delete w-btn-green-delete" @click="minusSection">-</button>
      
      <!-- <span class="title">방번호: {{oneroom}}</span> -->
      <span class="title">방번호: {{roomNo}}</span>
      <div class="bottom">
        <span>면접관 수:</span>
      </div>
      <div><span>지원자 수:</span></div>

    </div>
  </el-card>
</template>

<script>
import { mapActions, mapGetters } from "vuex"
export default {
  name: 'ConferenceName',
  // props: {
  //   oneroom: String
  // },
  // props: { comment: String },
  components: {

  },
  data() {
    return {
      // roomNo:this.roomList.roomNo
      roomNo: ''
    }
  },
  computed: {
    ...mapGetters(['roomList'])
  },

  methods: {
    ...mapActions(['deleteRoom', 'fetchRoomList']),
    minusSection() {
      //여기에 스토어 건드는 함수
      this.deleteRoom(this.roomNo)
    },
  },
  created() {
    this.roomNo = this.$route.params.roomNo
  },


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

.w-btn-delete {
  position: relative;
  border: none;
  display: inline-block;
  padding: 8px 16px;
  border-radius: 15px;
  font-family: "paybooc-Light", sans-serif;
  box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
  text-decoration: none;
  font-weight: 600;
  transition: 0.25s;
}
.w-btn-green-delete {
  background-color: #f05454;
  color: #f8e3e3;
}
</style>