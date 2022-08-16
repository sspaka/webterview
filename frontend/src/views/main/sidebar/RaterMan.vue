<template>
<div class="limiter">
    <div class="container-login100 shadow-lg">
      <div class="wrap-login100" style="margin-left: 20%; margin-right: 5%;">
            <div class="headLine2">면접관 관리</div>
            <br>
            <form  @submit.prevent="uploadRaters">
                <div class="filebox">
                    <label class="col-lg-3 col-form-label" for="file">면접관파일<span class="text-danger">*</span></label>
                    <br>
                    <input class="form-control form-control-sm" type="file" id="file" accept=".xls,.xlsx" multiple>
                    <div style="margin-top: 15px; justify-content: space-between;">
                        <i class="fa-solid fa-file-arrow-down" @click="downloadEx('rater')">예시</i>
                        <button type="submit" class="btn btn-primary mx-2 uploadFile">업로드</button>
                        <button type="button" class="btn btn-danger mx-2 deleteFile" @click="removeRaters(userNo)">삭제</button>
                        <button type="button" class="btn btn-success mx-2 addFile" @click="isModalViewed = true">개별 추가</button>
                        <button type="button" class="btn btn-info mx-2 send" style="float:right;" @click="sendLink">메일전송</button>
                    </div>
                </div>
            </form>
            <br>
            <div class="d-flex flex-column justify-content-center align-items-between mt-2">
                <div class="d-flex justify-content-center align-items-between">
                    <!-- <div class="container border border-dark mx-2">  -->
                    <div class="container mx-2"> 
                        <table class="noto table" style="font-size: 16px">
                            <thead style="background-color: #f5f5f5; color: #111">
                                <tr>
                                    <th>번호</th>
                                    <th>이름</th>
                                    <th>방번호</th>
                                    <th>메일</th>
                                    <th>전화</th>
                                    <th style="color: #f5f5f5;">수정</th>
                                    <th style="color: #f5f5f5;">삭제</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="rater in raters" :key="rater.raterNo">
                                    <td>{{ rater.raterNo }} </td>
                                    <td>{{ rater.raterName }}</td>
                                    <td>{{ rater.roomNo  }}</td>
                                    <td>{{ rater.raterEmail  }}</td>
                                    <td>{{ rater.raterPhone }}</td>
                                    <td><input type="button" value="상세" @click="goRaterDetail({raterNo:rater.raterNo, roomNo: rater.roomNo})"></td>
                                    <td><input type="button" value="삭제" @click="removeRater(rater.raterNo)"></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <br>
            
            <div id="modal" v-if="isModalViewed">
                <div id="overlay" class="jumbotron vertical-center" @click="isModalViewed = false"/>
                    <div id="modal-card">
                        <!-- <div style="text-align: left">
                            <div style="font-size: x-large"><b>면접을 종료하시겠습니까?</b></div>
                            <div style="color: darkgrey">퇴장 후에는 재입장이 불가능합니다.</div>
                        </div> -->
                        <form @submit.prevent="uploadRater(credentials)" style="width: 60vh; margin-top:30px;">
                            <div class="form-group row">
                                <label for="RaterName" class="col-4 col-form-label">이름: </label>
                                <input v-model="credentials.raterName" class="form-control inputNew col-8" id="RaterName" type="text" placeholder="면접관 이름을 입력하세요..." required>
                            </div>
                            <div class="form-group row">
                                <label for="RaterEmail" class="col-4 col-form-label">이메일: </label>
                                <input v-model="credentials.raterEmail" class="form-control inputNew col-8" id="RaterEmail" type="email" placeholder="이메일을 입력하세요..." required>
                            </div>
                            <div class="form-group row">
                                <label for="RaterPhone" class="col-4 col-form-label">전화번호: </label>
                                <input v-model="credentials.raterPhone" class="form-control inputNew col-8" id="RaterPhone" type="tel" placeholder="010-0000-0000" pattern="[0-9]{3}-[0-9]{4}-[0-9]{4}" required>
                            </div>
                            <div class="form-group row">
                                <label for="RoomNo" class="col-4 col-form-label">방번호(있는 방번호 입력해야)</label>
                                <input v-model="credentials.roomNo" class="form-control inputNew col-8" id="RoomNo" type="text" placeholder="방번호를 입력하세요..." required>
                            </div>
                            <!-- <div>
                                <label for="">면접자 번호</label>
                                <input v-model="credentials.raterNo" type="text" placeholder="면접자 번호">
                            </div> -->
                            <div class="form-group row">
                                <label for="userNo" class="col-4 col-form-label">관리자 번호: </label>
                                <input v-model="credentials.userNo" class="form-control inputNew col-8" id="userNo" type="text" placeholder="관리자 번호" required>
                            </div>
                            <!-- <button class="btn btn-primary mx-2 uploadFile" type="submit">개별 업로드</button> -->
                        </form>
                        <br />
                        <div style="display: inline-block; float: right">
                            <button type="button" @click="isModalViewed = false" class="btn btn-modal" style="background-color: white; color: black; border-color: darkgrey">
                              취소
                            </button>
                            <button @click="uploadRater(credentials); isModalViewed=false" class="btn btn-modal" style="background-color: #30475e; color: white">
                              추가
                            </button>
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
import drf from '@/api/drf'

export default {
    name: 'RaterManView',
    data() {
      return {
        isModalViewed: false,
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
        },
        mailList: [],
      }
    },
    computed: {
      ...mapGetters(['token', 'raters', 'userNo', 'groupNo',])
    },
    methods: {
      ...mapActions(['fetchRaters', 'removeRaters', 'goRoom','removeRater', 'downloadEx']),
      goRaterDetail({raterNo, roomNo}) {
        this.$router.push({ name: 'rater', params: {raterNo: raterNo, roomNo: roomNo }})
      },
      sendLink() {
        for(var i=0; i<this.raters.length; i++){
            this.mailList.push({'email': this.raters[i].raterEmail, 'roomNo': this.raters[i].roomNo})
        }
        this.goRoom({ mailList: this.mailList, person: 1})
      },
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
            url: drf.applicants.saveRaters() ,
            // url: '/interview/raterAll',
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
            alert("업로드가 완료되었습니다.")
        })
        .catch((err) => {
            console.log(err)
        })
      },
      uploadRater(credentials) {
        console.log('One Rater upload')
        axios({
            url: drf.applicants.saveRater(),
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
        font-size: 12px;

        
    }

    .container {
        background-color: #fff;
        padding: 1px;
        overflow: auto;
    }

    .filebox .upload-name {
        display: inline-block;
        vertical-align: middle;
        border: 1px solid #dddddd;
        width: 40%;
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
        background-color: #f05454;
        border-block-color: #f05454;
        color: #fff;
    }

    .addFile{
        background: green;
        /* border-block-color: green; */
    }

    .list-group{
        border: none;
    }

    .col-form-label{
        padding-left: 0px;
        padding-right: 0px;
        font-size:12px;
    }

    .send {
        background-color: rgb(89, 167, 227);
        border-block-color: rgb(89, 167, 227);
        color: #fff;
    }

    /* Modal */
    #modal,
    #overlay {
    width: 100%;
    height: 100%;
    position: fixed;
    left: 0;
    top: 0;
    z-index: 9997;
    }
    #overlay {
    opacity: 0.5;
    background-color: black;
    }
    #modal-card {
    position: relative;
    max-width: 80vh;
    margin: auto;
    margin-top: 200px;
    padding: 3%;
    background-color: white;
    z-index: 9998;
    opacity: 1;
    border-radius: 0.5rem;
    width: 80vh;
    height: auto;
    overflow: hidden;
    }

    .btn-modal {
    z-index: 9999;
    margin: 0 10px;
    }

    .btn-modal:hover {
    letter-spacing: 0px;
    transform: scale(1.2);
    cursor: pointer;
    }

    th {
        text-align: center;
    }
</style>
