<template>
<img class="logo" src="@/assets/logo.png" alt="Logo" style="position: absolute; top: 20px; left:20px; width: 200px;" @click="logo">
<div class="limiter d-flex justify-content-center">
  <div class="container-login100 shadow-lg">
  <div class="card shadow-lg p-3 mb-5 bg-body rounded" style="width: 900px; margin-top: 7.500;margin-left: 7.500;margin-top: 75px;margin-left: 75px;margin-right: 75px;margin-bottom: 75px;">
  <div class="head">
    아이디 찾기
  </div>
  <div class="card-body col-lg-12">
    <form @submit.prevent="findmail(credentials)">
      <!-- <div>
        <label class="col-lg-4 col-form-label" for="val-username">Username <span class="text-danger">*</span></label>
        <input class="btn" id="UserNameyn" v-model="credentials.name" type="text" placeholder="이름을 입력하세요" required>
      </div> -->

      <!-- name -->
      <div class="form-group row">
          <label class="col-lg-4 col-form-label" for="val-username">Username <span class="text-danger">*</span></label>
          <div class="col-lg-6">
              <input v-model="credentials.name" type="text"  class="form-control inputNew" id="val-username" name="val-username" placeholder="Enter a username.." required>
              <span class="focus-input100"></span>
          </div>
      </div>
      &nbsp;
      
      <!-- <div>
        <input class="btn" id="UserPhoneyn" v-model="credentials.phone" type="tel" placeholder="010-0000-0000" required>
      </div> -->
      
      <!-- phone -->
      <div class="form-group row">
          <label class="col-lg-4 col-form-label" for="val-phoneus">Phone (KR) <span class="text-danger">*</span></label>
          <div class="col-lg-6">
              <input v-model="credentials.phone" type="tel" class="form-control inputNew" id="val-phoneus" name="val-phoneus" placeholder="010-0000-0000" required>
          </div>
      </div>
      <div>
      <button type="submit" class="btn btn" style="margin-top: 10px;  background-color: #30475E; color: white; border-radius: 25px;">Submit</button>
      </div>

    </form>
    <div class="show-id" v-if="email.message==='success'">고객님의 아이디(이메일)은 <b style="color: red;">{{ email.userEmail }}</b> 입니다.</div>
    </div>
    <router-link to="/" ><p class="return" @click="erase">이전</p></router-link>
    </div>
  </div>
  </div>
</template>

<script>
import router from '@/router'
import { mapActions, mapGetters } from 'vuex'

export default {
  name: 'FindIdView',
  data() {
    
    return {
      isStatusOn: false,
      credentials: {
        name: '',
        phone: '',

      },
    }
  },
  computed: {
    ...mapGetters(['authError', 'email'])
  },
  methods: {
    ...mapActions(['findmail']),

    onReset(event) {
      event.preventDefault()
      // Reset our form values
      this.credentials.name = ''
      this.credentials.phone = ''
    },
    erase() {
      this.email.message = "fail"
    },
    logo() {
      router.push({ name: 'home' })
    }
  }
}
</script>

<style>
#UserNameyn{
  border:none;
  outline:none;
  color: #636e72;
  font-size:16px;
  height:25px;
  background: none;
}

#UserPhoneyn {
  border:none;
  outline:none;
  color: #636e72;
  font-size:16px;
  height:25px;
  background: none;
}

.submit-for-id {
  position:relative;
  left:40%;
  transform: translateX(-50%);
  margin-bottom: 40px;
  width:80%;
  height:40px;
  background: linear-gradient(125deg,#F05454,#30475E,#121212);
  background-position: left;
  background-size: 200%;
  color:#F5F5F5;
  font-weight: bold;
  border:none;
  cursor:pointer;
  transition: 0.4s;
  display:inline;
}

.show-id {
  font-family: Poppins-Regular;
  font-size: 13px;
  line-height: 1.5;
  color: #999999;
  margin-top: 5px;
}

.return{
  text-decoration: none;
  color: crimson;
}

</style>