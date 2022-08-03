<template>
<div class="limiter">
    <div class="container-login100 shadow-lg">
      <div class="wrap-login100" style="margin-left: 20%; margin-right: 10%;">
            <div class="d-flex flex-column justify-content-center align-items-between mt-5">
                <div class="head mb-4">면접관 관리</div>
                <div class="d-flex justify-content-center align-items-between">
                    <div class="col-3 container border border-dark mx-2"> 
                        <h2>면접관 목록</h2>
                        <div data-bs-spy="scroll" data-bs-root-margin="0px 0px -40%" data-bs-smooth-scroll="true" class="scrollspy-example bg-light p-3 rounded-2" tabindex="0">
                            <h4 id="scrollspyHeading1">First heading</h4>
                            <p>...</p>
                            <h4 id="scrollspyHeading2">Second heading</h4>
                            <p>...</p>
                            <h4 id="scrollspyHeading3">Third heading</h4>
                            <p>...</p>
                            <h4 id="scrollspyHeading4">Fourth heading</h4>
                            <p>...</p>
                            <h4 id="scrollspyHeading5">Fifth heading</h4>
                            <p>...</p>
                        </div>
                    </div>
                    <div class="col-6 container border border-dark mx-2"> 
                        <h2>평가표</h2>
                        <form  @submit.prevent="uploadScoreSheet">
                            <div class="d-flex justify-content-center">
                                <label for="">면접관 파일</label>
                                <input type="file" id='file' accept=".xls,.xlsx">
                                <button type="submit" class="btn btn-primary">업로드</button>
                            </div>
                        </form>
                        <div>목록쓰
                            <ul class="list-group">
                                <li class="list-group-item">An item</li>
                                <li class="list-group-item">A second item</li>
                                <li class="list-group-item">A third item</li>
                                <li class="list-group-item">A fourth item</li>
                                <li class="list-group-item">And a fifth one</li>
                            </ul>
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
        
      }
    },
    computed: {
      ...mapGetters([])
    },
    methods: {
      ...mapActions([]),
      uploadScoreSheet() {
        console.log('a')
        var frm = new FormData();
        var excelFile = document.getElementById("file");
        frm.append("file", excelFile.files[0]);
        frm.append("groupNo", 1)
        axios.post('http://localhost:8080/score/eval/save', frm, {
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
        padding: 2px;
    }
</style>