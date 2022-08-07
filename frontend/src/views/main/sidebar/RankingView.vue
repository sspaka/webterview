<template>
<div style="margin-left: 20%; margin-right: 10%;">
  <div>
    <h2 class="my-5">지원자 순위표</h2>
  </div>
  <div class="d-flex flex-col justify-content-center align-items-center">
    <div style="width:80%; overflow: scroll; background-color: #fff; height: 80vh">
      <table class="table">
        <thead style="background-color: #30475e; color: #fff">
          <tr>
            <th scope="col">#</th>
            <th scope="col">이름</th>
            <th scope="col">평균 점수</th>
            <th scope="col">종합 점수</th>
            <th scope="col">총 점수</th>
            <th scope="col">지원자 상세정보 보기</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <th scope="row">1 {{ 순위 }}</th>
            <td>홍성준 {{ name }}</td>
            <td>80</td>
            <td>80</td>
            <td>160</td>
            <td><a href="">링크</a></td>
          </tr>
          <tr v-for="grade in grades" :key="grade.applicantNo">
            <th scope="row">순위!</th>
            <td>이름</td>
            <td>{{ grade.avg }}</td>
            <td>{{ grade.avg }}</td>
            <td>{{ grade.avg }}</td>
            <td><a href="">지원자 정보로 이동.. 링크</a></td>
          </tr>
          {{ grades }}
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
      ...mapGetters(['grades', 'groupNo'])
    },
    methods: {
      ...mapActions(['fetchGrades']),
      goDetail(id, title){
        this.$router.push({ name: 'grade', params: {gradePk: id, gradeTitle: title} })
      }
    },
    created() {
      //this.fetchGrades(this.groupNo)
      this.fetchGrades("270")
    },
  }
</script>

<style>
  th {
    text-align: center;
  }
</style>