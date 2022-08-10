<template>
<div class="limiter">
    <div class="container-login100 shadow-lg">
      <div class="wrap-login100" style="margin-left: 20%; margin-right: 5%;">
            <div class="headLine2">면접관 관리</div>
            <br>
            <form  @submit.prevent="uploadRaters">
                <div class="filebox ">
                    <label for="file"></label>
                    <input class="upload-name" type="file" id="file" accept=".xls,.xlsx">
                    <button type="submit" class="btn btn-primary mx-2 uploadFile">업로드</button>
                    <button type="button" class="btn btn-danger mx-2 deleteFile" @click="removeRaters(userNo)">삭제</button>
                </div>
            </form>
            <button class="plusRaterBtn" type="button" @click="wantUpload">개별 추가</button>
            <form v-if="isWantUpload" @submit.prevent="uploadRater(credentials)">
                {{ credentials }}
                <div>
                    <label for="">이름</label>
                    <input v-model="credentials.raterName" type="text" placeholder="이름">
                </div>
                <div>
                    <label for="">이메일</label>
                    <input v-model="credentials.raterEmail" type="email" placeholder="이메일">
                </div>
                <div>
                    <label for="">전화번호</label>
                    <input v-model="credentials.raterPhone" type="tel" placeholder="010-0000-0000" pattern = "[0-9]{3}-[0-9]{4}-[0-9]{4}">
                </div>
                <div>
                    <label for="">방번호(있는 방번호 입력해야)</label>
                    <input v-model="credentials.roomNo" type="text" placeholder="방번호">
                </div>
                <!-- <div>
                    <label for="">면접자 번호</label>
                    <input v-model="credentials.raterNo" type="text" placeholder="면접자 번호">
                </div> -->
                <div>
                    <label for="">관리자 번호</label>
                    <input v-model="credentials.userNo" type="text" placeholder="관리자">
                </div>
                <button type="submit">개별 업로드</button>
            </form>
            <br>
            <div class="d-flex flex-column justify-content-center align-items-between mt-2">
                <div class="d-flex justify-content-center align-items-between">
                    <div class="container border border-dark mx-2"> 
                        <div>
                            <div class="list-group" style="overflow: scroll; height: 60vh; width: 60vh;">
                                <!-- {{ raters }} -->
                                <div v-for="rater in raters" :key="rater.raterNo">
                                    <router-link :to="{ name: 'rater', params: {raterNo: rater.raterNo} }">
                                        <div class="d-flex justify-content-center">
                                        <div class="my-1" style="width: 100%">
                                            <div class="d-flex justify-content-between">
                                                <h5 class="mb-1">{{ rater.raterName }}</h5>
                                                <small>{{ rater.raterNo }}</small>
                                            </div>
                                            <p class="mb-1">{{ rater.raterEmail  }}</p>
                                            <p class="mb-1">{{ rater.roomNo  }}</p>
                                            <small>전화번호 {{ rater.raterPhone }}</small>
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
    name: 'RaterManView',
    data() {
      return {
        isWantUpload: false,
        file: "",
        // groupNo: "270",
        credentials: {       
            "raterEmail": "",
            "raterName": "",
            "raterNo": 0,
            "raterPhone": "",
            "roomNo": 0,
            "userNo": 0,
        }
      }
    },
    computed: {
      ...mapGetters(['token', 'raters', 'userNo', 'groupNo'])
    },
    methods: {
      ...mapActions(['fetchRaters', 'removeRaters']),
      wantUpload() {
        this.isWantUpload = !this.isWantUpload
      },
      uploadRaters() {
        if (this.groupNo === "") {
            alert('면접을 먼저 생성하세요')
            return 
        }
        console.log('Rater upload')
        var formData = new FormData();
        var excelFile = document.getElementById("file");
        formData.append("file", excelFile.files[0]);
        formData.append("groupNo", this.groupNo)
        formData.append("userNo", this.userNo)
        // formData.append("groupNo", "1")
        console.log(excelFile)
        //console.log(formData.getAll())
        axios({
            url: '/interview/raterAll',
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
            this.fetchRaters(this.userNo)
        })
        .catch((err) => {
            console.log(err)
        })
      },
      uploadRater(credentials) {
        console.log('One Rater upload')
        axios({
            url: '/interview/raterOne',
            method: 'post',
            data: credentials, 
            headers: {
            'access-token': this.token
            },
        })
        .then((res) => {
            console.log(res.data)
            this.fetchRaters(this.userNo)
        })
        .catch((err) => {
            console.log(err)
        })
      }
    },
    created() {
        this.fetchRaters(this.userNo)
        this.credentials.userNo = this.userNo
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

    .plusRaterBtn{
        /* position: relative;
        left: 40%; */
        border: none;
        padding: 8px 16px;
        border-radius: 15px;
        font-family: "paybooc-Light", sans-serif;
        box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
        text-decoration: none;
        font-weight: 600;
        transition: 0.25s;
        background-color: #f05454;
        color: #fff;
    }
</style>