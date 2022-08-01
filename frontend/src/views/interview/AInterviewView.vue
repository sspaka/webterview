<template>
  <div>
    <video-header></video-header>
    <a-temp-screen></a-temp-screen>
  </div>
</template>

<script>
import ATempScreen from "../../components/ATempScreen.vue";
import VideoHeader from "../../components/openVidu/VideoHeader.vue";

export default {
  name: "AInterviewView",
  components: { ATempScreen, VideoHeader },
};
</script>

<style scoped></style>
