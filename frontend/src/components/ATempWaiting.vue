<template>
  <div id="modal" v-if="isModalViewed">
    <div
      id="overlay"
      class="jumbotron vertical-center"
      @click="isModalViewed = false"
    />
    <div id="modal-card">
      <div style="text-align: left">
        <div style="font-size: x-large"><b>면접을 종료하시겠습니까?</b></div>
        <div style="color: darkgrey">퇴장 후에는 재입장이 불가능합니다.</div>
      </div>
      <br />
      <div style="display: inline-block; float: right">
        <button
          @click="isModalViewed = false"
          class="btn btn-modal"
          style="background-color: white; color: black; border-color: darkgrey"
        >
          취소
        </button>
        <button
          @click="leaveSession"
          class="btn btn-modal"
          style="background-color: #f05454; color: white"
        >
          종료
        </button>
      </div>
    </div>
  </div>
  <header>
    <h1>
      <a href="#" class="logo"
        ><img src="../../public/resources/images/logo.png" width="240"
      /></a>
    </h1>
    <div>
      <input
        class="btn btn-large"
        type="button"
        id="buttonLeaveSession"
        @click="isModalViewed = true"
        value="나가기"
      />
    </div>
  </header>
  <div id="video-container" style="margin: 5%">
    <camera :resolution="{ width: 1000, height: 1000 }" autoplay></camera>
    <div>
      <h3>💻 화면/음성상태 체크</h3>
      <br /><br />
      <div v-if="!admission">
        <div>👤 잠시만 기다려 주세요</div>

        <div
          class="btn"
          style="background-color: #f5f5f5; color: #777777"
        >
          승인 대기 중
        </div>
      </div>
      <div v-if="admission">
        <div>👤 입장해 주세요</div>
        <button
          type="button"
          class="btn"
          @click="GoApplicantRoom()"
          style="background-color: #f05454; color: white"
        >
          입장하기
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import Camera from "simple-vue-camera";

export default {
  data() {
    return {
      admission: false,
    };
  },

  components: {
    Camera,
  },
  methods: {
    GoApplicantRoom() {
      this.$router.push("/interviewee");
    },
  },
};
</script>

<style>
#video-container {
  background-color: #ffffff;
  padding: 3rem;
  border-radius: 1rem;
  display: flex;
  grid-gap: 1%;
  justify-items: center;
}

#video-container div {
  flex: 1;
}

header {
  width: 100%;
  text-align: center;
  position: relative;
  height: 80px;
  box-shadow: 0 5px 10px 10px #e5e5e5;
  background-color: #fff;
}
header h1 {
  position: absolute;
  top: 5px;
  left: 5%;
}

#buttonLeaveSession {
  position: absolute;
  top: 10px;
  right: 5%;
  padding: 10px;
  margin: 10px;
  background-color: #f05454;
  color: white;
}

/* Modal */
#modal,
#overlay {
  width: 100%;
  height: 100%;
  position: fixed;
  left: 0;
  top: 0;
  z-index: 9997;
}
#overlay {
  opacity: 0.5;
  background-color: black;
}
#modal-card {
  position: relative;
  max-width: 30%;
  margin: auto;
  margin-top: 200px;
  padding: 3%;
  background-color: white;
  z-index: 9998;
  opacity: 1;
  border-radius: 0.5rem;
  width: auto;
  height: auto;
  overflow: hidden;
}

.btn-modal {
  z-index: 9999;
  margin: 0 10px;
}

.btn-modal:hover {
  letter-spacing: 0px;
  transform: scale(1.2);
  cursor: pointer;
}
</style>
