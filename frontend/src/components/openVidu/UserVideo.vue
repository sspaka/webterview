<template>
  <div class="uservideo" v-if="streamManager">
    <ov-video :stream-manager="streamManager" />
    <!-- <div>
      <p>{{ applicantNo }}</p>
    </div>
    <div>
      <p>{{ clientData }}</p>
    </div>
    <div>
      <p>{{ isApplicant }}</p>
    </div> -->
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
  created() {
    this.clientData();
    this.isApplicant();
  },
  // mounted() {
  //   this.clientData();
  //   this.isApplicant();
  // },
  // updated() {
  //   this.clientData();
  //   this.isApplicant();
  // },
  computed: {
    ...mapGetters(["ApplicantEmail", "isApplicantCheck"]),
  },

  methods: {
    ...mapActions(["setEmail", "setCheck"]),
    async clientData() {
      const { clientData } = await this.getConnectionData();
      this.setEmail(clientData);
      return clientData;
    },
    async isApplicant() {
      const { isApplicant } = await this.getConnectionData();
      this.setCheck(isApplicant);
      return isApplicant;
    },
    async getConnectionData() {
      const { connection } = await this.streamManager.stream;
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
