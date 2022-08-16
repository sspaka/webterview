<template>
<div class="limiter">
    <div class="container-login100 shadow-lg">
      <div class="wrap-login100" style="margin-left: 20%; margin-right: 5%;">
            <div class="head mb-4">지원자 상세정보</div>
            <div class="d-flex flex-column justify-content-center align-items-between mt-2">
                <div class="d-flex justify-content-center align-items-between">
                    <div class="container mx-2"> 
                        <div>
                            <div class="mb-4" style="height: 100%; width: 100%;">
                               <div class="profile">
                                    <table class="table white-bg" style="overflow:auto;">
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
                                        <tr>
                                        <th>특이사항:</th>
                                        <td colspan="3">{{ applicant.applicantUnique }}</td>
                                        </tr>
                                        <tr v-for="resume in applicant.resumes" :key="resume.resumeNo">
                                            <th>{{resume.resumeQuestion}}</th>
                                            <td>{{resume.resumeAnswer}}</td>
                                        </tr>
                                    </tbody>
                                    </table>
                                </div>
                            </div>
                            <!-- {{ credentials }} -->
                            <!-- <br> -->
                            <form @submit.prevent="modifyApplicant(credentials)">
                                <span>
                                <label for="roomNo">면접장 번호:</label>
                                <input class="interview" type="text" v-model="credentials.roomNo" id="roomNo">
                                </span>
                                <span>
                                <label for="date">면접시각:</label>
                                <input class="interview" type="text" v-model="credentials.date" id="date">
                                </span>
                                <div>
                                <button class="interview adit-btn" type="submit" style="margin-top: 8px;">수정</button>
                                </div>
                            </form>
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
    name: 'ApplicantDetailView',
    data() {
      return {
        applicantEmail: this.$route.params.applicantEmail,
        groupNo: this.$route.params.groupNo,
        roomNo: this.$route.params.groupNo,
        credentials: {
            applicantNo: "",
            roomNo: 0,
            date: "",
        }
      }
    },
    computed: {
      ...mapGetters(['applicant'])
    },
    methods: {
      ...mapActions(['fetchApplicant', 'updateApplicants', 'modifyApplicant']),
      
    },
    async created() {
        console.log(this.applicantEmail)
        this.credentials.roomNo = this.roomNo
        await this.fetchApplicant(this.applicantEmail)
        this.credentials.applicantNo = this.applicant.applicantNo
        this.credentials.roomNo = this.applicant.roomNo
        this.credentials.date = this.applicant.applicantDate
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

    .interview {
        text-align: center;
        background-color:#e8e3e3;
        border-radius: 15px;
    }

    .adit-btn {
        padding: 4px 8px;
        background-color: crimson;
        color: #fff;
        font-size: 12px;
    }
</style>