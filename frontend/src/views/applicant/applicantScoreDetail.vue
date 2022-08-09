<template>
<div class="limiter">
    <div class="container-login100 shadow-lg">
      <div class="wrap-login100" style="margin-left: 20%; margin-right: 5%;">
            <div class="head mb-4">지원자 상세정보 및 항목별 점수 {{applicantEmail}}</div>
            <div class="d-flex flex-column justify-content-center align-items-between mt-2">
                <div class="d-flex justify-content-center align-items-between">
                    <div class="container border border-dark mx-2"> 
                        <div>
                            <div class="" style="overflow: scroll; height: 60vh; width: 60vh;">
                                <div class="profile">
                                    <h3 style="background-color: #30475e; color: #fff" >입사지원서</h3>
                                    <div style="margin-top: 10px; margin-bottom: 10px">이름: {{ applicant.applicantName}}</div>
                                    <table class="table white-bg">
                                    <tbody>
                                        <tr>
                                        <th scope="row">이름:</th>
                                        <td colspan="3">{{ applicant.applicantName}}</td>
                                        </tr>
                                        <tr>
                                        <th scope="row">나이:</th>
                                        <td colspan="3">{{ applicant.applicantAge }}</td>
                                        </tr>
                                        <tr>
                                        <th scope="row">이메일:</th>
                                        <td colspan="3">{{ applicant.applicantEmail }}</td>
                                        </tr>
                                        <tr>
                                        <th scope="row">대학교:</th>
                                        <td colspan="3">{{ applicant.applicantUniv }}</td>
                                        </tr>
                                        <tr>
                                        <th>학점:</th>
                                        <td colspan="3">{{ applicant.applicantGPA}}</td>
                                        </tr>
                                        <tr>
                                        <th>자격증:</th>
                                        <td colspan="3">{{ applicant.applicantLicense}}</td>
                                        </tr>
                                        <tr>
                                        <th>어학점수:</th>
                                        <td colspan="3">{{ applicant.applicantLang }}</td>
                                        </tr>
                                        <tr>
                                        <th>특이사항:</th>
                                        <td colspan="3">{{ applicant.applicantUnique }}</td>
                                        </tr>
                                        <tr>
                                        <th>전화번호:</th>
                                        <td colspan="3">{{ applicant.applicantPhone}}</td>
                                        </tr>
                                    </tbody>
                                    </table>
                                </div>
                                <h3 style="background-color: #30475e; color: #fff">자기소개서</h3>
                                <div v-for="resume in applicant.resumes" :key="resume.resumeNo">
                                    <p>{{ resume.resumeQuestion }} : {{resume.resumeQuestion}}</p>
                                </div>
                                <div>
                                    <table class="table">
                                        <thead style="background-color: #30475e; color: #fff">
                                        <tr>
                                            <th scope="col">문항번호</th>
                                            <th scope="col">평균점수</th>
                                            <th scope="col">타입</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr v-for="score in scores" :key="score.eval">
                                            <td>{{ score.eval }}</td>
                                            <td>{{ score.avg}}</td>
                                            <td>{{ score.type }}</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                </div>
            </div>
      </div>
    </div>
</div>
  
</template>

<script>
import { mapActions, mapGetters } from 'vuex'

export default {
    name: 'ApplicantScoreDetail',
    data() {
      return {
        applicantEmail: this.$route.params.applicantEmail,
        groupNo: this.$route.params.groupNo,
        applicantNo: this.$route.params.applicantNo,
      }
    },
    computed: {
      ...mapGetters(['applicant', 'scores', 'texts'])
    },
    methods: {
      ...mapActions(['fetchApplicant', 'updateApplicants', 'fetchScores']),
      
    },
    created() {
        console.log(this.applicantEmail)
        this.fetchApplicant({ applicantEmail: this.applicantEmail, groupNo: this.groupNo })
        this.fetchScores(this.applicantNo)
    }
    
}
</script>

<style scoped>
    .btn {
        background-color: #30475e;
        color: white;
    }

    .container {
        background-color: #fff;
        padding: 1px;
    }

    .filebox .upload-name {
        display: inline-block;
        vertical-align: middle;
        border: 1px solid #dddddd;
        width: 60%;
        color: #999999;
    }

    .detail {
        background-color: #c4d4e3;
        height: 65vh;
    }

    .deleteFile {
        background-color: crimson;
        border-block-color: crimson;
    }
</style>