<template>
<div class="limiter">
    <div class="container-login100 shadow-lg">
      <div class="wrap-login100" style="margin-left: 20%; margin-right: 5%;">
            <div class="head mb-4">면접관 상세정보 {{rater.raterEmail}}</div>
            <div class="d-flex flex-column justify-content-center align-items-between mt-2">
                <div class="d-flex justify-content-center align-items-between">
                    <div class="container border border-dark mx-2"> 
                        <div> 
                            <button type="button" @click="removeRater(rater.raterNo)">면접관 삭제</button>
                            <div class="" style="overflow: scroll; height: 60vh; width: 60vh;">
                                {{ rater }}
                                {{ credentials }}
                                <div class="d-flex justify-content-center">
                                    <div class="my-1">
                                        <div class="d-flex justify-content-between">
                                            <h5 class="mb-1">{{ rater.raterName }}</h5>
                                            <small>{{ rater.raterNo }}</small>
                                        </div>
                                        <p class="mb-1">{{ rater.raterEmail  }}</p>
                                        <p class="mb-1">{{ rater.roomNo  }}</p>
                                        <small>전화번호 {{ rater.raterPhone }}</small>
                                    </div>
                                </div>
                            </div>
                            <form @submit.prevent="modifyRater(credentials)">
                                <label for="roomNo">면접장 번호</label>
                                <input type="text" v-model="credentials.roomNo">
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
    name: 'RaterDetailView',
    data() {
      return {
        raterNo: this.$route.params.raterNo,
        credentials: {          
            raterEmail: "",
            raterName: "",
            raterNo: 0,
            raterPhone: "",
            roomNo: 0,
            userNo: 0
        }
      }
    },
    computed: {
      ...mapGetters(['rater'])
    },
    methods: {
      ...mapActions(['fetchRater', 'updateRaters','removeRater', 'modifyRater']),
      
      
    },
    created() {
        console.log(this.raterNo)
        this.fetchRater(this.raterNo)
        this.credentials.raterEmail= this.rater.raterEmail,
        this.credentials.raterName=this.rater.raterName,
        this.credentials.raterNo= this.rater.raterNo,
        this.credentials.raterPhone= this.rater.raterPhone,
        this.credentials.roomNo= this.rater.roomNo,
        this.credentials.userNo= this.rater.userNo
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