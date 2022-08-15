<template>
<div class="limiter">
    <div class="container-login100 shadow-lg">
      <div class="wrap-login100" style="margin-left: 20%; margin-right: 5%;">
            <div class="headLine2">지원자 관리</div>
                
            <div class="d-flex flex-column justify-content-center align-items-between mt-2">
                <div class="d-flex justify-content-center align-items-between">
                    <div class="container mx-2"> 
                        <div>
                            <p> 지원자 목록 </p>
                            <div class="list-group" style="overflow: auto; height: 55vh; width: 80vh;">
                                <!-- {{ applicants }} -->
                                <div v-for="applicant in applicants" :key="applicant.applicantNo">
                                    <router-link :to="{ name: 'applicant', params: {applicantEmail: applicant.applicantEmail, groupNo: applicant.groupNo} }">
                                        <div class="d-flex justify-content-center">
                                        <div class="my-1" style="width: 100%">
                                            <!-- <div class="d-flex justify-content-between"> -->
                                                <h5 class="mb-1">{{ applicant.applicantName }}</h5>
                                                <!-- <small>{{ applicant.applicantNo }}</small> -->
                                            <!-- </div> -->
                                            <p class="mb-1">{{ applicant.applicantEmail  }}</p>
                                            <small class="mb-1">나이 {{ applicant.applicantAge  }}</small>
                                            &nbsp;
                                            <!-- <p class="mb-1">면접 번호{{ applicant.groupNo  }}</p> -->
                                            <small>전화번호 {{ applicant.applicantPhone }}</small>

                                        </div>
                                        </div>
                                    </router-link>
                                    <hr>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                </div>
            </div>

            <div class="d-flex justify-content-between">
                <form  @submit.prevent="uploadApplicant" style="margin-right:10px; margin-top:10px;">
                    <div class="filebox d-flex flex-col">
                        <div>
                            <label class="col-lg-3 col-form-label" for="file">지원자파일<span class="text-danger">*</span></label>
                            <input class="form-control form-control-sm" type="file" id="file" accept=".xls,.xlsx">
                        </div>
                        <div style="margin-top: 15px; justify-content: space-between;">
                            <button type="submit" class="btn btn-primary mx-2 uploadFile">업로드</button>
                            <button type="button" class="btn btn-danger mx-2 deleteFile" @click="removeApplicants(groupNo)">삭제</button>
                        </div>
                    </div>
                </form>
                
                <form  @submit.prevent="uploadResume" style="margin-left:10px; margin-top:10px;">
                    <div class="filebox d-flex flex-col">
                        <div>
                            <label class="col-lg-3 col-form-label" for="file">자기소개서<span class="text-danger">*</span></label>
                            <input class="form-control form-control-sm" type="file" id="resume" accept=".xls,.xlsx">
                        </div>
                        <div style="margin-top: 15px;">
                            <button type="submit" class="btn btn-primary mx-2 uploadFile">업로드</button>
                            <button type="button" class="btn btn-danger mx-2 deleteFile" @click="removeResume(groupNo)">삭제</button>
                        </div>
                    </div>
                </form>
            </div>
            <button type="button" @click="goRoom(raterMails)">이메일로 링크보내기</button>
      </div>
    </div>
</div>
  
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
import axios from 'axios'
import drf from '@/api/drf'

export default {
    name: 'ApplicantManView',
    data() {
      return {
        file: "",
      }
    },
    computed: {
      ...mapGetters(['token', 'applicants', 'groupNo', 'raterMails'])
    },
    methods: {
      ...mapActions(['fetchApplicants', 'removeApplicants', 'goRoom']),
      uploadApplicant() {
        if (this.groupNo === "") {
            alert('면접을 먼저 생성하세요')
            return 
        }
        console.log('Applicant upload')
        var formData = new FormData();
        var excelFile = document.getElementById("file");
        formData.append("file", excelFile.files[0]);
        formData.append("groupNo", this.groupNo)
        // formData.append("groupNo", "1")
        console.log(excelFile)
        //console.log(formData.getAll())
        axios({
            url: drf.applicants.saveApplicants(),
            // url: '/interview/applicant/save',
            method: 'post',
            data: formData, 
            headers: {
            'Access-Control-Allow-Origin': '*',
            'Content-Type': 'multipart/form-data',
            'access-token': this.token
            },
        })
        .then((res) => {
            console.log(res)
            alert("업로드가 완료되었습니다.")
            this.fetchApplicants(this.groupNo)
        })
        .catch((err) => {
            console.log(err)
        })
      },
      uploadResume() {
        if (this.groupNo === "") {
            alert('면접을 먼저 생성하세요')
            return 
        }
        if (this.applicants === []) {
            alert('지원자를 먼저 업로드하세요')
            return 
        }
        console.log('Resume upload')
        var formData = new FormData();
        var resumeFile = document.getElementById("resume");
        formData.append("file", resumeFile.files[0]);
        formData.append("groupNo", this.groupNo)
        // formData.append("groupNo", "1")
        //console.log(formData.getAll())
        axios({
            url: drf.applicants.saveResumes(),
            // url: '/interview/resume/save',
            method: 'post',
            data: formData, 
            headers: {
            'Access-Control-Allow-Origin': '*',
            'Content-Type': 'multipart/form-data',
            'access-token': this.token
            },
        })
        .then((res) => {
            console.log(res.data)
            alert("업로드가 완료되었습니다.")
        })
        .catch((err) => {
            console.log(err)
        })
      }
    },
    created() {
        this.fetchApplicants(this.groupNo)
    }
    
}
</script>

<style scoped>
    .btn {
        background-color: #30475e;
        color: white;
        font-size: 12px;
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
        /* float: right; */

    }

    .uploadFile {
        /* float:left; */
    }

    .col-form-label{
        padding-left: 0px;
        padding-right: 0px;
        font-size:12px;
    }
</style>