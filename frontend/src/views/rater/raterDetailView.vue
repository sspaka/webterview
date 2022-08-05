<template>
<div class="limiter">
    <div class="container-login100 shadow-lg">
      <div class="wrap-login100" style="margin-left: 20%; margin-right: 5%;">
            <div class="head mb-4">면접관 상세정보 {{rater.raterEmail}}</div>
            <div class="d-flex flex-column justify-content-center align-items-between mt-2">
                <div class="d-flex justify-content-center align-items-between">
                    <div class="container border border-dark mx-2"> 
                        <div>
                            <div class="" style="overflow: scroll; height: 60vh; width: 60vh;">
                                {{ rater }}
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
        raterNo: this.$route.params.raterNo
      }
    },
    computed: {
      ...mapGetters(['rater'])
    },
    methods: {
      ...mapActions(['fetchRater', 'updateRaters']),
      
    },
    created() {
        console.log(this.raterNo)
        this.fetchRater(this.raterNo)
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