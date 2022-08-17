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
          <!--종료-->
          <!-- ============== Recording 추가 start ============== -->
          <a class="download-button">종료</a>
          <!-- ============== Recording 추가 end ============== -->
        </button>
      </div>
    </div>
  </div>
  <div id="session">
    <header>
      <h1>
        <a href="#" class="logo"
          ><img src="@/../public/resources/images/logo.png" width="240"
        /></a>
      </h1>
      <div>
        <input
          class="btn btn-large"
          type="button"
          id="buttonLeaveSession"
          @click="[(isModalViewed = true), stopRec()]"
          value="나가기"
        />
      </div>
    </header>
    <div class="big-container">
      <div id="video-container">
        <div id="rater-video">
          <user-video
            v-for="sub in subscribers"
            :key="sub.stream.connection.connectionId"
            :stream-manager="sub"
          />
        </div>
        <div id="main-video">
          <user-video :stream-manager="mainStreamManager" />
          <user-video :stream-manager="publisher" />
        </div>
      </div>
    </div>
    <!-- ============== Recording 추가 start ============== -->
    <div>
      <video ref="recordVideo" style="display: none">Record Video</video>
    </div>
    <!-- ============== Recording 추가 end ============== -->
  </div>
</template>

<script>
import axios from "axios";
import { OpenVidu } from "openvidu-browser";
import UserVideo from "@/components/openVidu/UserVideo";
// ./components/UserVideo
// ============== Recording 추가 start ==============
// import drf from "@/api/drf";
// ============== Recording 추가 end ==============

axios.defaults.headers.post["Content-Type"] = "application/json";

const OPENVIDU_SERVER_URL = "https://i7c205.p.ssafy.io:4443";
const OPENVIDU_SERVER_SECRET = "MY_SECRET";

export default {
  name: "ATempScreen",

  components: {
    UserVideo,
  },

  // ============== Recording 추가 start ==============
  mounted() {
    this.recordVideo = this.$refs.recordVideo;
    this.recordedChunks = [];
    this.startVideo();
  },
  // ============== Recording 추가 end ==============

  data() {
    return {
      OV: undefined,
      session: undefined,
      mainStreamManager: undefined,
      publisher: undefined,
      subscribers: [],

      mySessionId: undefined,
      applicantNo: undefined,
      applicantEmail: undefined,

      isModalViewed: undefined,

      // ============== Recording 추가 start ==============
      recordVideo: null,
      recordUrl: null,

      // preview: null,

      recordedChunks: [],
      recorder: undefined,

      recordedChunksTest: [],
      // ============== Recording 추가 end ==============
    };
  },
  created() {
    this.joinSession();
  },
  update() {
    console.log(this.isModalViewed);
  },
  methods: {
    joinSession() {
      this.mySessionId = this.$route.params.roomCode;
      this.applicantEmail = this.$route.params.email;
      this.applicantNo = this.$route.params.applicantNo;

      // console.log("현재 세션: " + this.mySessionId);

      // --- Get an OpenVidu object ---
      this.OV = new OpenVidu();

      // --- Init a session ---
      this.session = this.OV.initSession();

      // --- Specify the actions when events take place in the session ---

      // On every new Stream received...
      this.session.on("streamCreated", ({ stream }) => {
        const subscriber = this.session.subscribe(stream);
        this.subscribers.push(subscriber);
        this.mainStreamManager = this.subscribers[0];
      });

      // On every Stream destroyed...
      this.session.on("streamDestroyed", ({ stream }) => {
        const index = this.subscribers.indexOf(stream.streamManager, 0);
        if (index >= 0) {
          this.subscribers.splice(index, 1);
        } else {
          this.mainStreamManager = undefined;
        }
      });

      // On every asynchronous exception...
      this.session.on("exception", ({ exception }) => {
        console.warn(exception);
      });

      // --- Connect to the session with a valid user token ---

      // 'getToken' method is simulating what your server-side should do.
      // 'token' parameter should be retrieved and returned by your own backend
      this.getToken(this.mySessionId).then((token) => {
        // console.log("이메일!!!! " + this.applicantEmail);
        this.session
          .connect(token, {
            applicantNo: this.applicantNo,
            clientData: this.applicantEmail,
            isApplicant: true,
          })
          .then(() => {
            // --- Get your own camera stream with the desired properties ---

            let publisher = this.OV.initPublisher(undefined, {
              audioSource: undefined, // The source of audio. If undefined default microphone
              videoSource: undefined, // The source of video. If undefined default webcam
              publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
              publishVideo: true, // Whether you want to start publishing with your video enabled or not
              resolution: "640x480", // The resolution of your video
              frameRate: 120, // The frame rate of your video
              insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
              mirror: false, // Whether to mirror your local video or not
            });

            this.publisher = publisher;

            // --- Publish your stream ---

            this.session.publish(this.publisher);
          })
          .catch((error) => {
            console.log(
              "There was an error connecting to the session:",
              error.code,
              error.message
            );
          });
      });

      // 말하는 사람 왼쪽 하단에 위치
      this.session.on("publisherStartSpeaking", (event) => {
        console.log(
          "User " + event.connection.connectionId + " start speaking"
        );
        if (
          event.connection.connectionId ===
          this.publisher.stream.connection.connectionId
        )
          return;
        this.updateMainVideoStreamManager(event.connection);
      });

      window.addEventListener("beforeunload", this.leaveSession);

      // this.startRecording();
    },

    updateMainVideoStreamManager(stream) {
      if (this.mainStreamManager === stream) return;
      this.mainStreamManager = stream;
    },

    leaveSession() {
      // this.stopRecording();
      // --- Leave the session by calling 'disconnect' method over the Session object ---
      if (this.session) this.session.disconnect();

      this.session = undefined;
      this.mainStreamManager = undefined;
      this.publisher = undefined;
      this.subscribers = [];
      this.OV = undefined;

      // ============== Recording 추가 start ==============
      this.saveRec();
      this.sendUrl();
      // ============== Recording 추가 end ==============

      // 닫기 안 먹으면 뒤로가기 막아야 됨
      // window.open("http://localhost:8081/", "_blank");
      // window.open("about:blank", "_self").close();
      // window.removeEventListener("beforeunload", this.leaveSession);
    },

    getToken(mySessionId) {
      return this.createSession(mySessionId).then((sessionId) =>
        this.createToken(sessionId)
      );
    },

    // See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-session
    createSession(sessionId) {
      return new Promise((resolve, reject) => {
        axios
          .post(
            `${OPENVIDU_SERVER_URL}/openvidu/api/sessions`,
            JSON.stringify({
              customSessionId: sessionId,
            }),
            {
              auth: {
                username: "OPENVIDUAPP",
                password: OPENVIDU_SERVER_SECRET,
              },
            }
          )
          .then((response) => response.data)
          .then((data) => resolve(data.id))
          .catch((error) => {
            if (error.response.status === 409) {
              resolve(sessionId);
            } else {
              console.warn(
                `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`
              );
              if (
                window.confirm(
                  `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`
                )
              ) {
                location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
              }
              reject(error.response);
            }
          });
      });
    },

    // See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-connection
    createToken(sessionId) {
      return new Promise((resolve, reject) => {
        axios
          .post(
            `${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`,
            {},
            {
              auth: {
                username: "OPENVIDUAPP",
                password: OPENVIDU_SERVER_SECRET,
              },
            }
          )
          .then((response) => response.data)
          .then((data) => resolve(data.token))
          .catch((error) => reject(error.response));
      });
    },

    // ============== Recording 추가 start ==============
    startVideo() {
      console.log("step1");
      // 성공
      navigator.mediaDevices
        .getUserMedia({ video: true, audio: true })
        .then((stream) => {
          console.log(stream);
          this.recordVideo.srcObject = stream;
          console.log(
            "this.recordVideo.srcObject: " + this.recordVideo.srcObject
          );
          this.recordVideo.play();
          console.log("step2");
          this.startRec(this.recordVideo.captureStream());
        })
        .catch((error) => {
          console.log(error);
        });
    },

    startRec(stream) {
      console.log("step3");
      // let recordedChunks = [];
      // let recorder = new MediaRecorder(stream);
      this.recorder = new MediaRecorder(stream);
      console.log("stream: " + stream);
      console.log("recorder: " + this.recorder);
      console.log(this.recorder.ondataavailable);
      // this.recorder.ondataavailable = (e) => {
      //   this.recordedChunks.push(e.data);
      //   console.log(this.recordedChunks);
      // };
      console.log("함수전:" + this.recordedChunks); //  → 나온다.
      this.recorder.ondataavailable = (e) => {
        console.log(this.recordedChunks); // undefined
        // let recordedChunks = [];
        // this.recordedChunks = [];
        this.recordedChunks?.push(e.data);
        this.recordedChunksTest = this.recordedChunks; // 대체 이게 왜 안될까 진심 몇시간을 헤매는거야 여기서
        // this.recordedChunksTest.push(recordedChunks);
        console.log(this.recordedChunks); // [object Blob] 가 나와야 한다 → 나온다. (해결 완료)
        console.log("step6");
      };
      console.log("step4");
      this.recorder.start();
    },
    saveRec() {
      console.log("step7");
      console.log(
        "leavesessionReal recordedChunks: " + this.recordedChunksTest
      ); // [object Blob] 가 나와야 한다

      const recordedBlob = new Blob(this.recordedChunks, {
        type: "video/mp4",
      });
      // console.log("recordedBlob: " + recordedBlob);
      this.recordUrl = URL.createObjectURL(recordedBlob);
      // this.preview.src = URL.createObjectURL(recordedBlob);
      // this.preview.play();
      // 세션 나가기 전에 axios로 DB에 정보 보내기
      const downloadButton = document.querySelector(".download-button");
      downloadButton.href = this.recordUrl;

      // 다운로드 하기 싫으면 이 버튼을 막으면 된다.
      downloadButton.download = `recording_${new Date()}.mp4`;
      // downloadButton.download = "recording_"+this.applicantEmail+".mp4";
      console.log("recordUrl: " + this.recordUrl);
    },
    stopRec() {
      // 지금까지 녹화된 영상을 blob에 저장
      console.log(this.recordVideo.srcObject.getTracks());
      this.recordVideo.srcObject.getTracks().forEach((track) => track.stop());
      console.log("step5");
      this.recorder.stop();
      // console.log(this.recordedChunks);
    },

    sendUrl() {
      // 프로젝트 axios 붙여넣을 것
      console.log(this.applicantNo);
      console.log(encodeURIComponent(this.recordUrl));
      axios({
        // url: drf.interviews.saveurl(),
        url: /interview/applicant/savefile,
        method: "post",
        data: {
          applicantNo: this.applicantNo,
          url: encodeURIComponent(this.recordUrl),
        },
      })
        .then((res) => {
          console.log("url DB로 전송 성공");
          console.log(res);
        })
        .catch((error) => {
          console.log("url DB로 전송 실패");
          console.log(error);
        });
    },
    // ============== Recording 추가 end ==============
  },
};
</script>

<style scoped>
/* #main-container {
  margin: none;
  padding: 3rem;
  display: flex;
  justify-content: center;
  align-items: center;
} */

.big-container {
  display: grid;
  padding: 3rem;
  grid-gap: 1%;
}

#video-container {
  background-color: #ffffff;
  padding: 3rem;
  border-radius: 1rem;
  display: grid;
  grid-gap: 1%;
  justify-items: center;
}

#rater-video {
  padding: 10px;
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(10px, 1fr));
  grid-gap: 1%;
  justify-items: center;
  max-width: 100%;
}

#rater-video div {
  grid-row: 1;
  max-width: 180px;
}

#main-video {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  grid-gap: 1%;
  justify-content: center;
}

#main-video video {
  width: 100%;
  object-fit: cover;
}

#buttonLeaveSession {
  background-color: #f05454;
  color: #fff;
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
