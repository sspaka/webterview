<template>
<div class="limiter">
    <div class="container-login100 shadow-lg">
      <div class="wrap-login100" style="margin-left: 20%; margin-right: 5%;">
            <div class="head mb-4">평가표 관리</div>
            <div class="d-flex flex-column justify-content-center align-items-between mt-2">
                <div class="d-flex justify-content-center align-items-between">
                    <div class="col-6 container border border-dark mx-2"> 
                        <h2 class="txt3">평가표 파일 업로드</h2>
                        <br>
                        <form  @submit.prevent="uploadApplicant">
                            <div class="filebox ">
                                <label for="file"></label>
                                <input class="upload-name" type="file" id="file" accept=".xls,.xlsx">
                                <button type="submit" class="btn btn-primary mx-2">업로드</button>
                            </div>
                        </form>
                        <br>
                        <div>
                            <ul class="list-group" style="overflow: scroll; height: 60vh">
                                <li class="list-group-item"><a href="">문항1</a></li>
                                <li class="list-group-item"><a href="">문항2</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-6 container border border-dark mx-2"> 
                        <h2 class="txt3">평가표 상세정보 및 수정?</h2>
                        <br>
                        <div class="detail">
                            상세 정보 (수정..)
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
    name: 'EvalManView',
    data() {
      return {
        file: "",
        groupNo: "2"  
      }
    },
    computed: {
      ...mapGetters(['token'])
    },
    methods: {
      ...mapActions([]),
      changeFileName() {
        // var fileName = document.getElementById("file")
        // fileName + = " "
      },
      uploadApplicant() {
        console.log('Applicant upload')
        var formData = new FormData();
        var excelFile = document.getElementById("file");
        formData.append("file", excelFile.files[0]);
        formData.append("groupNo", this.groupNo)
        // formData.append("groupNo", "1")
        console.log(excelFile)
        //console.log(formData.getAll())
        axios({
            url: '/score/eval/save',
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
        })
        .catch((err) => {
            console.log(err)
        })
      }
    },
    
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

</style>