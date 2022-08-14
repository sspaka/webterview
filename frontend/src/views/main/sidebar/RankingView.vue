<template>
<div style="margin-left: 20%; margin-right: 10%;">
  <div>
    <h2 class="headLine2 my-5">지원자 순위표</h2>
    <span v-if="rankGroupNo !== groupNo">이전 면접의 순위입니다</span>
    <input type="button" value="지원자 세부 점수 다운로드" @click="download(rankGroupNo)" style="background-color: palegoldenrod;">
  </div>
  <div class="d-flex flex-col justify-content-center align-items-center">
    <div style="width:80%; overflow: scroll; background-color: #fff; height: 80vh">
      <table class="table">
        <thead style="background-color: #30475e; color: #fff">
          <tr>
            <th scope="col">순위</th>
            <th scope="col">이름</th>
            <th scope="col">일반점수</th>
            <th scope="col">종합점수</th>
            <th scope="col">총 점수</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="grade in grades" :key="grade.applicantNo" @click="goScoreDetail(grade.email, rankGroupNo, grade.applicantNo)">
            <td>{{ grade.rank + 1 }}</td>
            <td>{{grade.name }}</td>
            <td>{{ grade.score1 }}</td>
            <td>{{ grade.score2 }}</td>
            <td>{{ grade.score1 + grade.score2 }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</div>
      <!-- <div class="grade-card mx-2 my-2" style="width:250px;" v-for="grade in grades" :key="grade.id">
        <div @click="goDetail(grade.id, grade.title)" class="grade-information">
          <div class="title"><strong>{{ grade.title }}</strong></div>
          <p class="grade-release-date"><small>{{ grade.release_date }}</small></p>
        </div>
      </div> -->
</template>

<script>
  import { mapActions, mapGetters } from 'vuex'

  export default {
    name: 'RankList',
    computed: {
      ...mapGetters(['grades', 'rankGroupNo', 'groupNo'])
    },
    methods: {
      ...mapActions(['fetchGrades','download']),
      goScoreDetail(email, groupNo, applicantNo){
        this.$router.push({ name: 'applicantScore', params: {applicantEmail: email, groupNo: groupNo, applicantNo: applicantNo } })
      }
    },
    created() {
      //this.fetchGrades(this.groupNo)
      this.fetchGrades(this.groupNo)
    },
  }
</script>

<style scoped>
  th {
    text-align: center;
  }
</style>