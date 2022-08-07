<template>
  <div>
    <!-- <div class="child"><about-applicant></about-applicant></div> -->
    <!-- <div class="child"><r-temp-screen></r-temp-screen></div> -->
    <!-- <div class="child"><score-sheet></score-sheet></div> -->
    <r-temp-screen></r-temp-screen>
  </div>
</template>

<script>
import RTempScreen from "../../components/RTempScreen.vue";

// import AboutApplicant from "../../components/rater/AboutApplicant.vue";
// import ScoreSheet from "../../components/rater/ScoreSheet.vue";

export default {
  name: "RInterviewView",
  components: { RTempScreen },
  // components: { RTempScreen, AboutApplicant, ScoreSheet },
  data() {
    return {};
  },
};
</script>

<style>
/* .parent {
  display: grid;
  grid-template-columns: auto auto auto;
  grid-gap: 1%;
} */
</style>
