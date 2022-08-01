<template>
  <header>
    <h1>
      <a href="#" class="logo"
        ><img src="resources/images/Logo.png" width="240"
      /></a>
    </h1>
    <div>
      <input
        class="btn btn-large"
        type="button"
        id="buttonLeaveSession"
        @click="leaveSession"
        value="나가기"
      />
    </div>
  </header>
</template>

<script>
import axios from "axios";

axios.defaults.headers.post["Content-Type"] = "application/json";

export default {
  name: "App",

  data() {
    return {
      OV: undefined,
      session: undefined,
      mainStreamManager: undefined,
      publisher: undefined,
      subscribers: [],

      mySessionId: "SessionA",
      myUserName: "Participant" + Math.floor(Math.random() * 100),
    };
  },

  methods: {
    leaveSession() {
      // --- Leave the session by calling 'disconnect' method over the Session object ---
      if (this.session) this.session.disconnect();

      this.session = undefined;
      this.mainStreamManager = undefined;
      this.publisher = undefined;
      this.subscribers = [];
      this.OV = undefined;

      window.removeEventListener("beforeunload", this.leaveSession);
    },
  },
};
</script>

<style scoped>
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
</style>
