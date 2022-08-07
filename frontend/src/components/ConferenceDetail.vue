<template>
  <div class="container">
    {{ $route.params.conferenceId + "번 방 상세 보기 페이지" }}
    <div class="row justify-content-center">
      <div class="col-6">
        <button type="button" class="btn btn-primary" @click="GoRaterRoom()">
          Interviewer
        </button>
      </div>
      <div class="col-6">
        <button
          type="button"
          class="btn btn-warning"
          @click="GoApplicantRoom()"
        >
          Applicant
        </button>
      </div>
    </div>
  </div>
</template>
<style scoped>
.container {
  padding-top: 50px;
}
.btn {
  width: 600px;
  height: 500px;
  font-size: 100px;
}
</style>
<script>
import { reactive, onMounted, onUnmounted } from "vue";
import { useStore } from "vuex";
import { useRoute } from "vue-router";

export default {
  name: "ConferenceDetail",

  setup() {
    const route = useRoute();
    const store = useStore();
    const state = reactive({
      conferenceId: "",
    });

    // 페이지 진입시 불리는 훅
    onMounted(() => {
      state.conferenceId = route.params.conferenceId;
      store.commit("root/setMenuActiveMenuName", "home");
    });

    // 페이지 이탈시 불리는 훅
    onUnmounted(() => {
      state.conferenceId = "";
    });

    return { state };
  },

  methods: {
    GoRaterRoom() {
      this.$router.push("/interviewer");
    },
    GoApplicantRoom() {
      this.$router.push("/interviewee");
    },
  },
};
</script>
