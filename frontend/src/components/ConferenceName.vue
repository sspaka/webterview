<template>
  <el-card :body-style="{ padding: '0px' }">
    <div class="image-wrapper">
      <el-skeleton style="width: 100%">
        <template #template>
          <el-skeleton-item
            variant="image"
            style="width: 100%; height: 190px"
          />
        </template>
      </el-skeleton>
    </div>
    <div class="session">
      <!-- <button class="ing-or-not">a</button> -->
      <button
        class="w-btn-delete w-btn-green-delete"
        @click="deleteRoom(roomNo)"
        style="width: 40px; height: 40px"
      >
        <span class="minus">-</span>
      </button>
      <router-link
        style="background-color: #1b3b5b"
        :to="{
          name: 'InterviewRoomView',
          params: { roomCode: roomCode, roomNo: roomNo },
        }"
        ><div>
          <span class="title">방번호: {{ roomNo }}</span>
        </div>
        <div>
          <span> 코드: {{ roomCode }}</span>
        </div>
        <div class="bottom">
          <span>면접관 수: <span v-for="rater in raterList" :key="rater.raterNo"> {{rater.raterName}}&nbsp;</span> </span>
        </div>
        <div>
          <span>지원자 수:{{ raterList }}</span>
        </div>
        <!-- <p>그룹: {{ groupNo }}</p> -->
      </router-link>
    </div>
    <!-- <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500&display=swap" rel="stylesheet"> -->
  </el-card>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
import axios from "axios";

export default {
  name: "ConferenceName",
  props: {
    roomNo: { type: String },
    roomCode: { type: String },
    groupNo: { type: String },
  },
  components: {},
  data() {
    return {
      raterList: [],
    };
  },
  computed: {
    ...mapGetters(["token"]),
  },

  methods: {
    ...mapActions(["deleteRoom", "fetchRoomDetail"]),
    fetchRoomDetail(roomNo) {
      axios({
        // url: drf.applicants.applicants(),
        url: "/admin" + "/roomDetail/" + roomNo,
        method: "get",
        headers: {
          "access-token": this.token,
        },
      })
        .then((res) => {
          console.log(res.data);
          this.raterList = res.data.raterList;
        })
        .catch((err) => {
          console.error(err);
        });
    },
  },
  created() {
    // 방 정보 가져오기 -> raterList..
    this.fetchRoomDetail(this.roomNo);
  },
};
</script>

<style scoped>
.session {
  text-align: left;
  padding: 14px;
  background-color: #fef3f3;
  /* border: 1px solid violet; */
  margin: 2px;
  border-radius: 10px;
  margin-top: 15px;
  margin-right: 15px;
  margin-left: 15px;
  margin-bottom: 15px;
  box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
}

.el-card {
  margin: 0 8px;
  margin-bottom: 40px;
  border-radius: 10px;
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
  display: -webkit-box;
  word-wrap: break-word;
  -webkit-box-orient: vertical;
  overflow: hidden;
  text-overflow: ellipsis;
}
/* 테블릿, 모바일의 경우 두 줄 말줄임표시 */
@media (max-width: 1269px) {
  .el-card .bottom {
    -webkit-line-clamp: 2;
    height: 42px;
  }
}
/* 데스크탑의 경우 세 줄 말줄임표시 */
@media (min-width: 1270px) {
  .el-card .bottom {
    -webkit-line-clamp: 3;
    height: 60px;
  }
}

.w-btn-delete {
  position: relative;
  left: 90%;
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

.minus {
  font-size: auto;
  color: #f8e3e3;
}

span {
  color: black;
  /* font-family: 'Noto Sans KR', sans-serif;
  font-style: bold; */
}

/* .ing-or-not {
  padding: 4px 8px;
  background-color:aqua;
  border-radius: 100px;
  color:aqua;
}

.ing-or-not:focus {
  background-color: red;
  color: red;
} */
</style>
