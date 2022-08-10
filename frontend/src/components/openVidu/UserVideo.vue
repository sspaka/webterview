<template>
  <div class="uservideo" v-if="streamManager">
    <ov-video :stream-manager="streamManager" />
    <div>
      <p>{{ clientData }}</p>
    </div>
    <div>
      <p>{{ isApplicant }}</p>
    </div>
  </div>
</template>

<script>
import OvVideo from "./OvVideo";
import { mapGetters, mapActions } from "vuex";

export default {
  name: "UserVideo",

  components: {
    OvVideo,
  },

  props: {
    streamManager: Object,
  },

  computed: {
    ...mapGetters(["ApplicantEmail", "isApplicantCheck"]),
    clientData() {
      const { clientData } = this.getConnectionData();
      this.setEmail(clientData);
      return clientData;
    },
    isApplicant() {
      const { isApplicant } = this.getConnectionData();
      this.setCheck(isApplicant);
      return isApplicant;
    },
  },

  methods: {
    ...mapActions(["setEmail", "setCheck"]),

    getConnectionData() {
      const { connection } = this.streamManager.stream;
      return JSON.parse(connection.data);
    },
  },
};
</script>

<style scoped>
.uservideo video {
  width: 100%;
  height: 100%;
}
</style>
