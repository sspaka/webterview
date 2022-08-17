<template>
<div class="limiter">
    <div class="container-login100 shadow-lg">
      <div class="wrap-login100" style="margin-left: 20%; margin-right: 5%;">
            <div class="head mb-4">지원자 상세정보 및 항목별 점수</div>
            <div class="download">
                <input class="download" type="button" value="영상보기 및 다운" @click="downloadV({applicantNo: applicant.applicantNo, applicantFile: applicant.applicantFile })" style="background-color: palegoldenrod;">
                <div class="text txt1">지원자 면접 영상입니다.</div>
            </div>
            <div class="d-flex flex-column justify-content-center align-items-between mt-2">
                <div class="d-flex justify-content-center align-items-between">
                    <div class="container mx-2"> 
                        <div>
                            <div class="mb-4" style="height: 100%; width: 60vh;">
                                <div class="profile">
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
                                        <th>전화번호:</th>
                                        <td colspan="3">{{ applicant.applicantPhone}}</td>
                                        </tr>
                                        
                                        <!-- 자소서 파트 -->
                                        <tr v-for="resume in applicant.resumes" :key="resume.resumeNo">
                                        <th>{{ resume.resumeQuestion }}</th>
                                        <td colspan="3">{{ resume.resumeAnswer }}</td>
                                        </tr>
                                    </tbody>
                                    </table>
                                </div>
                                <br>
                                <div>
                                    <table class="table">
                                        <thead style="background-color: #f5f5f5; color: #111">
                                        <tr>
                                            <th scope="col">문항</th>
                                            <th scope="col">평균점수</th>
                                            <!-- <th scope="col">타입</th> -->
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <!-- <tr v-for="score in scores" :key="score.eval">
                                            <td>{{ score.eval }}</td>
                                            <td>{{ score.avg}}</td>
                                            <td>{{ score.type }}</td>
                                        </tr> -->
                                        <tr v-for="(score, index) in scores" :key="index">
                                            <td colspan='2'>{{ evalSheet[index].evaluationQuestion }}</td>
                                            <td>{{ score.avg }}</td>
                                            
                                        </tr>
                                        <tr v-for="(text, index) in texts" :key="index">
                                            <!-- <td>특이사항{{index+1}}</td> -->
                                            <td colspan='3'>{{ text }}</td>
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
      ...mapGetters(['applicant', 'scores', 'texts', 'evalSheet', 'groupNo'])
    },
    methods: {
      ...mapActions(['fetchApplicant', 'updateApplicants', 'fetchScores', 'downloadV', 'fetchEvalSheet']),
      
    },
    created() {
        console.log(this.applicantEmail)
        this.fetchApplicant(this.applicantEmail)
        this.fetchScores(this.applicantNo)
        this.fetchEvalSheet(this.groupNo)
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

    .text{
        color: #f5f5f5;
    }

    .download:hover .text {
        color: #999999;
    }
</style>