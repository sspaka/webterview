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
  <div id="main-container-interviewer">
    <div id="session">
      <header>
        <h1>
          <a href="#" class="logo"
            ><img src="resources/images/Logo.png" width="240"
          /></a>
        </h1>
        <div id="layoutButton">
          <button type="button" @click="aboutbutton">
            <img src="../../public/resources/images/about.png" alt="about" />
          </button>
          <button type="button" @click="screenbutton">
            <img src="../../public/resources/images/screen.png" alt="screen" />
          </button>
          <button type="button" @click="scorebutton">
            <img src="../../public/resources/images/score.png" alt="score" />
          </button>
        </div>
        <div>
          <!-- <div class="dropdown">
            <button class="btn dropdown-toggle" type="button" id="applicantList" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              지원자 목록
            </button>
            <div class="dropdown-menu" aria-labelledby="applicantList">
              <div>목록</div>
            </div>
          </div> -->
          <input
            class="btn btn-large"
            type="button"
            id="nextApplicant"
            @click="nextApplicant"
            value="다음 지원자 부르기"
          />
          <input
            class="btn btn-large"
            type="button"
            id="buttonLeaveSession"
            @click="isModalViewed = true"
            value="나가기"
          />
        </div>
      </header>
      <grid-layout
        v-model:layout="layout"
        :col-num="6"
        :row-height="50"
        is-draggable
        is-resizable
        vertical-compact
        use-css-transforms
      >
        <grid-item
          :x="layout[0].x"
          :y="layout[0].y"
          :w="layout[0].w"
          :h="layout[0].h"
          :i="layout[0].i"
          :key="layout[0].i"
        >
          <about-applicant v-if="about"></about-applicant>
        </grid-item>
        <grid-item
          :x="layout[1].x"
          :y="layout[1].y"
          :w="layout[1].w"
          :h="layout[1].h"
          :i="layout[1].i"
          :key="layout[1].i"
        >
          <div v-if="screen" id="video-container">
            <div id="rater-video">
              <user-video
                v-for="sub in subscribers"
                :key="sub.stream.connection.connectionId"
                :stream-manager="sub"
              />
            </div>
            <div id="main-video">
              <user-video :stream-manager="mainStreamManager" />
            </div>
          </div>
        </grid-item>
        <grid-item
          :x="layout[2].x"
          :y="layout[2].y"
          :w="layout[2].w"
          :h="layout[2].h"
          :i="layout[2].i"
          :key="layout[2].i"
        >
          <score-sheet v-if="score"></score-sheet>
        </grid-item>
      </grid-layout>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { OpenVidu } from "openvidu-browser";
import UserVideo from "../components/openVidu/UserVideo";

// ./components/UserVideo

import AboutApplicant from "../components/rater/AboutApplicant.vue";
import ScoreSheet from "../components/rater/ScoreSheet.vue";
import VueGridLayout from "vue3-grid-layout";

//resize
// import VueResizeable from 'vue-resizeable'

axios.defaults.headers.post["Content-Type"] = "application/json";

const OPENVIDU_SERVER_URL = "https://i7c205.p.ssafy.io:4443";
const OPENVIDU_SERVER_SECRET = "MY_SECRET";

export default {
  name: "RTempScreen",

  components: {
    UserVideo,
    AboutApplicant,
    ScoreSheet,
    GridLayout: VueGridLayout.GridLayout,
    GridItem: VueGridLayout.GridItem,
  },

  data() {
    return {
      OV: undefined,
      session: undefined,
      mainStreamManager: undefined,
      publisher: undefined,
      subscribers: [],

      mySessionId: "meetingroomcode",
      myUserName: "Participant" + Math.floor(Math.random() * 100),

      isModalViewed: false,
      isListViewed: false,
      //
      // about: true,
      // screen: true,
      // score: true,
      about: undefined,
      screen: undefined,
      score: undefined,

      layout: [
        { x: 0, y: 0, w: 2, h: 10, i: "about" },
        { x: 2, y: 0, w: 2, h: 10, i: "screen" },
        { x: 4, y: 0, w: 2, h: 10, i: "score" },
      ],
    };
  },

  mounted() {
    this.about = true;
    this.screen = true;
    this.score = true;
    this.mySessionId = this.$route.params.roomCode;
    this.myUserName = "Participant" + Math.floor(Math.random() * 100);
    this.joinSession();
  },

  beforeUnmount() {
    window.removeEventListener("beforeunload", this.leaveSession);
  },

  methods: {
    joinSession() {
      // --- Get an OpenVidu object ---
      this.OV = new OpenVidu();

      // --- Init a session ---
      this.session = this.OV.initSession();

      // --- Specify the actions when events take place in the session ---

      // On every new Stream received...
      this.session.on("streamCreated", ({ stream }) => {
        const subscriber = this.session.subscribe(stream);

        if (subscriber.stream.connection.data === '{"clientData":"applicate"}')
          this.mainStreamManager = subscriber;
        else this.subscribers.push(subscriber);
      });

      // On every Stream destroyed...
      this.session.on("streamDestroyed", ({ stream }) => {
        const index = this.subscribers.indexOf(stream.streamManager, 0);
        if (index >= 0) {
          this.subscribers.splice(index, 1);
        }
      });

      // On every asynchronous exception...
      this.session.on("exception", ({ exception }) => {
        console.warn(exception);
      });

      // --- Connect to the session with a valid user token ---

      // 'getToken' method is simulating what your server-side should do.
      // 'token' parameter should be retrieved and returned by your own backend
      /**
      // 세션에 연결하려면 OpenVidu Server에 사용자 토큰을 요청해야 하는데,
      // 클라이언트 측이 아닌 서버 측에서 완전히 이루어져야 한다.
      // 그러나 지금은 애플리케이션 백엔드가 없기 때문에
      // Vue 프론트 자체가 OpenVidu 서버에 대한 POST 작업을 수행하게 함
      */
      this.getToken(this.mySessionId).then((token) => {
        this.session
          .connect(token, { clientData: this.myUserName })
          .then(() => {
            // --- Get your own camera stream with the desired properties ---

            let publisher = this.OV.initPublisher(undefined, {
              audioSource: undefined, // The source of audio. If undefined default microphone
              videoSource: undefined, // The source of video. If undefined default webcam
              publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
              publishVideo: true, // Whether you want to start publishing with your video enabled or not
              resolution: "640x480", // The resolution of your video
              frameRate: 30, // The frame rate of your video
              insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
              mirror: false, // Whether to mirror your local video or not
            });

            this.publisher = publisher;
            this.subscribers.push(publisher);

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

      window.addEventListener("beforeunload", this.leaveSession);
    },

    leaveSession() {
      // --- Leave the session by calling 'disconnect' method over the Session object ---
      if (this.session) this.session.disconnect();

      this.session = undefined;
      this.mainStreamManager = undefined;
      this.publisher = undefined;
      this.subscribers = [];
      this.OV = undefined;

      // 닫기 안 먹으면 뒤로가기 막아야 됨
      window.open("http://localhost:8081/", "_blank");
      window.open("about:blank", "_self").close();
      // window.removeEventListener("beforeunload", this.leaveSession);
    },

    updateMainVideoStreamManager(stream) {
      if (stream === "applicate") this.publisher = stream;
    },

    /**
     * --------------------------
     * SERVER-SIDE RESPONSIBILITY
     * --------------------------
     * These methods retrieve the mandatory user token from OpenVidu Server.
     * This behavior MUST BE IN YOUR SERVER-SIDE IN PRODUCTION (by using
     * the API REST, openvidu-java-client or openvidu-node-client):
     *   1) Initialize a Session in OpenVidu Server	(POST /openvidu/api/sessions)
     *   2) Create a Connection in OpenVidu Server (POST /openvidu/api/sessions/<SESSION_ID>/connection)
     *   3) The Connection.token must be consumed in Session.connect() method
     */

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
    aboutbutton() {
      this.about = !this.about;
    },
    screenbutton() {
      this.screen = !this.screen;
    },
    scorebutton() {
      this.score = !this.score;
    },
    list(isListViewed) {
      this.isListViewed = isListViewed;
    },
  },

  // $('#sidebarCollapse').on('click', function () {
  //   $('#sidebar-introduce').addClass('active');
  //   $('.overlay').fadeIn();
  //   }),

  // this.$refs.btn.on('click', function () {
  //   $('#sidebar-introduce').removeClass('active');
  //   $('.overlay').fadeOut();
  // })
};
</script>

<style scoped>
#join-dialog {
  background: rgb(255, 238, 238);
}

#header {
  padding: 50px;
}

#header img {
  width: 40%;
}

#video-container {
  background-color: #ffffff;
  padding: 3rem;
  border-radius: 1rem;
  display: grid;
  grid-gap: 1%;
  justify-items: center;
  height: 100%;
  overflow: hidden;
}

#main-container {
  margin: none;
  padding: 5%;
  display: grid;
  justify-content: center;
  align-items: center;
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
  grid-template-columns: 1fr;
  grid-gap: 1%;
  justify-content: center;
  width: 100%;
  height: 100%;
}

#main-video video {
  width: 100%;
  object-fit: cover;
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

#layoutButton {
  position: absolute;
  top: 10px;
  margin: 10px auto;
  top: 5px;
  left: 0;
  right: 0;
  text-align: center;
}

#layoutButton img {
  max-width: 30px;
  margin: 5px;
  vertical-align: middle;
  color: #f05454;
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

/* #applicantList {
  position: absolute;
  top: 10px;
  right: 10%;
  padding: 10px;
  margin: 10px;
  background-color: #30475E;
  color: white;
} */

#nextApplicant {
  position: absolute;
  top: 10px;
  right: 10%;
  padding: 10px;
  margin: 10px;
  background-color: #30475e;
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

.vue-grid-item.vue-grid-placeholder {
  background: red;
  opacity: 0.2;
  transition-duration: 100ms;
  z-index: 2;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  -o-user-select: none;
  user-select: none;
}

grid-item {
  min-height: 50%;
  overflow: auto;
}

.grid::before {
  content: "";
  background-size: calc(calc(100% - 5px) / 12) 40px;
  background-image: linear-gradient(to right, lightgrey 1px, transparent 1px),
    linear-gradient(to bottom, lightgrey 1px, transparent 1px);
  height: calc(100% - 5px);
  width: calc(100% - 5px);
  position: absolute;
  background-repeat: repeat;
  margin: 5px;
}
</style>
