<template>
<div class="limiter">
    <div class="container-login100 shadow-lg">
      <div class="wrap-login100" style="margin-left: 20%; margin-right: 5%;">
            <div class="head mb-4">지원자 관리</div>
            <div class="d-flex flex-column justify-content-center align-items-between mt-2">
                <div class="d-flex justify-content-center align-items-between">
                    <div class="col-6 container border border-dark mx-2"> 
                        <h2>지원자 파일 업로드aaaaaaaa</h2>
                        <form  @submit.prevent="uploadApplicant">
                            <div class="d-flex justify-content-center">
                                <label for=""></label>
                                <input type="file" id='file' accept=".xls,.xlsx">
                                <button type="submit" class="btn btn-primary">업로드</button>
                            </div>
                        </form>
                        <div>목록쓰
                            <ul class="list-group" style="overflow: scroll; height: 60vh">
                                <li class="list-group-item">An item</li>
                                <li class="list-group-item">A second item</li>
                                <li class="list-group-item">A third item</li>
                                <li class="list-group-item">A fourth item</li>
                                <li class="list-group-item">And a fifth one</li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-6 container border border-dark mx-2"> 
                        <h2>지원자 정보aaaaaaaa</h2>
                        <div>상세 정보 (수정..)
                            
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
        
      }
    },
    computed: {
      ...mapGetters([])
    },
    methods: {
      ...mapActions([]),
      uploadApplicant() {
        console.log('Applicant upload')
        var frm = new FormData();
        var excelFile = document.getElementById("file");
        frm.append("file", excelFile.files[0]);
        frm.append("groupNo", 1)
        axios.post('http://localhost:8080/??/', frm, {
        headers: {
            'Content-Type': 'multipart/form-data'
        }
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
</style>