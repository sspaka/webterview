<template>
<div class="limiter">
    <div class="container-login100 shadow-lg">
      <div class="wrap-login100" style="margin-left: 20%; margin-right: 5%;">
            <div class="head mb-4">지원자 상세정보 {{applicantEmail}}</div>
            <div class="d-flex flex-column justify-content-center align-items-between mt-2">
                <div class="d-flex justify-content-center align-items-between">
                    <div class="container border border-dark mx-2"> 
                        <div>
                            <div class="" style="overflow: scroll; height: 60vh; width: 60vh;">
                                {{ applicant }}
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
                            </div>
                            {{ credentials }}
                            <form @submit.prevent="modifyApplicant(credentials)">
                                <label for="roomNo">면접장 번호</label>
                                <input type="text" v-model="credentials.roomNo" id="roomNo">
                                <label for="date">면접시각</label>
                                <input type="text" v-model="credentials.date" id="date">
                                <button type="submit">수정</button>
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
        credentials: {
            applicantNo: "",
            roomNo: "",
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
    created() {
        console.log(this.applicantEmail)
        this.fetchApplicant({ applicantEmail: this.applicantEmail, groupNo: this.groupNo })
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
</style>