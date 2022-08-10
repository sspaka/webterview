<template>
<div class="limiter">
    <div class="container-login100 shadow-lg">
      <div class="wrap-login100" style="margin-left: 20%; margin-right: 5%;">
            <div class="headLine2">지원자 관리</div>
                <div class="d-flex justify-content-between">
                    <form  @submit.prevent="uploadApplicant">
                        <div class="filebox d-flex flex-col">
                            <div>
                                <label for="file">지원자파일 : </label>
                                <input class="upload-name" type="file" id="file" accept=".xls,.xlsx">
                            </div>
                            <div>
                                <button type="submit" class="btn btn-primary mx-2 uploadFile">업로드</button>
                                <button type="button" class="btn btn-danger mx-2 deleteFile" @click="removeApplicants(groupNo)">삭제</button>
                            </div>
                        </div>
                    </form>
                    
                    <form  @submit.prevent="uploadResume">
                        <div class="filebox d-flex flex-col">
                            <div>
                                <label for="file">자기소개서 : </label>
                                <input class="upload-name" type="file" id="resume" accept=".xls,.xlsx">
                            </div>
                            <div>
                                <button type="submit" class="btn btn-primary mx-2 uploadFile">업로드</button>
                                <button type="button" class="btn btn-danger mx-2 deleteFile" @click="removeResume(groupNo)">삭제</button>
                            </div>
                        </div>
                    </form>
                </div>
            <div class="d-flex flex-column justify-content-center align-items-between mt-2">
                <div class="d-flex justify-content-center align-items-between">
                    <div class="container border border-dark mx-2"> 
                        <div>
                            <p> 지원자 목록 </p>
                            <div class="list-group" style="overflow: scroll; height: 60vh; width: 80vh;">
                                <!-- {{ applicants }} -->
                                <div v-for="applicant in applicants" :key="applicant.applicantNo">
                                    <router-link :to="{ name: 'applicant', params: {applicantEmail: applicant.applicantEmail, groupNo: applicant.groupNo} }">
                                        <div class="d-flex justify-content-center">
                                        <div class="my-1" style="width: 100%">
                                            <div class="d-flex justify-content-between">
                                                <h5 class="mb-1">{{ applicant.applicantName }}</h5>
                                                <small>{{ applicant.applicantNo }}</small>
                                            </div>
                                            <p class="mb-1">{{ applicant.applicantEmail  }}</p>
                                            <p class="mb-1">{{ applicant.applicantAge  }}</p>
                                            <p class="mb-1">면접 번호{{ applicant.groupNo  }}</p>
                                            <small>전화번호 {{ applicant.applicantPhone }}</small>
                                        </div>
                                        </div>
                                    </router-link>
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
import axios from 'axios'

export default {
    name: 'ApplicantManView',
    data() {
      return {
        file: "",
        //groupNo: "270"  
      }
    },
    computed: {
      ...mapGetters(['token', 'applicants', 'groupNo'])
    },
    methods: {
      ...mapActions(['fetchApplicants', 'removeApplicants']),
      changeFileName() {
        // var fileName = document.getElementById("file")
        // fileName + = " "
      },
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
            url: '/interview/applicant/save',
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
            url: '/interview/resume/save',
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
        })
        .catch((err) => {
            console.log(err)
        })
      }
    },
    created() {
        this.fetchApplicants(this.groupNo)
        // this.fetchApplicants("300")
    }
    
}
</script>

<style scoped>
    .btn {
        background-color: #30475e;
        border: none;
        padding: 8px 16px;
        border-radius: 15px;
        font-family: "paybooc-Light", sans-serif;
        box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
        text-decoration: none;
        font-weight: 300;
        transition: 0.25s;
        color: #fff;
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