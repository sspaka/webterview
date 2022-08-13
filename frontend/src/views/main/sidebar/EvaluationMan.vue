<template>
<div class="limiter">
    <div class="container-login100 shadow-lg">
      <div class="wrap-login100" style="margin-left: 20%; margin-right: 5%;">
            <div class="headLine2">평가표 관리</div>

            <br>
            <div class="d-flex flex-column justify-content-center align-items-between mt-2">
                <div class="d-flex justify-content-center align-items-between">
                    <div class="container border border-dark mx-2"> 
                        <div>
                            <div class="list-group" style="overflow: scroll; height: 60vh; width: 60vh;">
                                
                                <div v-for="question in evalSheet" :key="question.evaluationNo">
                                    <div class="d-flex justify-content-center">
                                        <div class="my-1" style="width: 100%">
                                            <div class="d-flex justify-content-between">
                                                <small>{{ question.evaluationNo}}</small>
                                                <h5 class="mb-1">{{ question.evaluationQuestion}}</h5>
                                            </div>
                                            <p class="mb-1">{{ question.evaluationType  }}</p>
                                            <small>{{ question.groupNo}}</small>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                </div>
            </div>
            <form  @submit.prevent="uploadEvalSheet">
                <div class="filebox ">
                    <label for="file"></label>
                    <input class="form-control form-control-sm" type="file" id="file" accept=".xls,.xlsx">
                    <div style="margin-top: 15px;">
                        <button type="submit" class="btn btn-primary mx-2 uploadFile">업로드</button>
                        <button type="button" class="btn btn-danger mx-2 deleteFile" @click="removeEvalSheet(this.groupNo)">삭제</button>
                    </div>
                </div>
            </form>
      </div>
    </div>
</div>
  
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
import axios from 'axios'
import drf from '@/api/drf'

export default {
    name: 'EvalSheetManView',
    data() {
      return {
        file: "",
        // groupNo: "270"  
      }
    },
    computed: {
      ...mapGetters(['token', 'evalSheet', 'groupNo'])
    },
    methods: {
      ...mapActions(['fetchEvalSheet', 'removeEvalSheet']),
      uploadEvalSheet() {
        if (this.groupNo === "") {
            alert('면접을 먼저 생성하세요')
            return 
        }
        console.log('Evaluation Sheet upload')
        var formData = new FormData();
        var excelFile = document.getElementById("file");
        formData.append("file", excelFile.files[0]);
        formData.append("groupNo", this.groupNo)
        axios({
            url: drf.applicants.saveEval(),
            // url: 'api/score/eval/save',
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
            this.fetchEvalSheet(this.groupNo)
        })
        .catch((err) => {
            console.log(err)
        })
      }
    },
    created() {
        this.fetchEvalSheet(this.groupNo)
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
    }

</style>