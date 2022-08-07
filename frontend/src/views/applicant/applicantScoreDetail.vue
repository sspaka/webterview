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
                                <!-- {{ applicant }}
                                {{ scores }} -->
                                <div class="d-flex justify-content-center">
                                    <div class="my-1">
                                        <div class="d-flex justify-content-between">
                                            <h5 class="mb-1">{{ applicant.applicantName }}</h5>
                                            <small>{{ applicant.applicantNo }}</small>
                                        </div>
                                        <p class="mb-1">{{ applicant.applicantEmail  }}</p>
                                        <p class="mb-1">{{ applicant.applicantAge  }}</p>
                                        <small>전화번호 {{ applicant.applicantPhone }}</small>
                                    </div>
                                </div>
                                <div class="d-flex flex-col align-items-center jusify-content-between">
                                    <div>
                                        문항번호 : 평균점수 :  타입
                                    </div>
                                    <div v-for="score in scores" :key="score.eval">
                                        {{ score.eval }} :{{ score.avg}} {{ score.type }}
                                    </div>
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
      ...mapGetters(['applicant', 'scores'])
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