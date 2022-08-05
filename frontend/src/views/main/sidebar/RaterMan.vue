<template>
<div class="limiter">
    <div class="container-login100 shadow-lg">
      <div class="wrap-login100" style="margin-left: 20%; margin-right: 5%;">
            <div class="head mb-4">면접관 관리</div>
            <div class="d-flex flex-column justify-content-center align-items-between mt-2">
                <div class="d-flex justify-content-center align-items-between">
                    <div class="container border border-dark mx-2"> 
                        <h2 class="txt3">면접관 파일 업로드</h2>
                        <br>
                        <form  @submit.prevent="uploadRater">
                            <div class="filebox ">
                                <label for="file"></label>
                                <input class="upload-name" type="file" id="file" accept=".xls,.xlsx">
                                <button type="submit" class="btn btn-primary mx-2 uploadFile">업로드</button>
                                <button type="button" class="btn btn-danger mx-2 deleteFile" @click="removeRaters(userNo)">삭제</button>
                            </div>
                        </form>
                        <br>
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
        file: "",
        groupNo: "270"  
      }
    },
    computed: {
      ...mapGetters(['token', 'raters', 'userNo'])
    },
    methods: {
      ...mapActions(['fetchRaters', 'removeRaters']),
      uploadRater() {
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
            console.log(res)
            this.fetchRaters(this.userNo)
        })
        .catch((err) => {
            console.log(err)
        })
      }
    },
    created() {
        this.fetchRaters(this.userNo)
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