<template>
  <img class="logo" src="@/assets/logo.png" alt="Logo" style="position: absolute; top: 20px; left:20px; width: 200px;" @click="logo">
  <div class="limiter d-flex justify-content-center">
    <div class="container-login100 shadow-lg">
      <div class="card shadow-lg p-3 mb-5 bg-body rounded" style="width: 900px; margin-top: 75px;margin-left: 75px;margin-right: 75px;margin-bottom: 75px;">
        <div class="head mb-4">수정</div>
        <form @submit.prevent="modify(credentials)" @reset="onReset">
          <dl class="row">

            <div class="form-group row">
              <label class="col-lg-4 col-form-label" for="val-username">Username <span class="text-danger">*</span></label>
              <div class="col-lg-6">
                <div class="inputNew d-flex flex-col align-item-center justify-content-center" >
                  <input v-model="credentials.userName" type="text"  class="form-control inputNew" id="val-username" name="val-username" placeholder="Enter a username.." required>
                  <span class="focus-input100"></span>
                </div>
              </div>
            </div>

            <div class="form-group row">
            <label class="col-lg-4 col-form-label">UserDept</label>
            <div class="col-lg-6">
              <div class="inputNew d-flex flex-col align-item-center justify-content-center" >
                <p style="color: black;">{{ credentials.userDept }}</p>
              </div>
            </div>
          </div>

            <div class="form-group row">
            <label class="col-lg-4 col-form-label">UserRole</label>
            <div class="col-lg-6">
              <div class="inputNew d-flex flex-col align-item-center justify-content-center" >
                <p style="color: black;">{{ credentials.userRole }}</p>
              </div>
            </div>
          </div>

            <div class="form-group row">
            <label class="col-lg-4 col-form-label">UserEmail</label>
            <div class="col-lg-6">
              <div class="inputNew d-flex flex-col align-item-center justify-content-center" >
                <p style="color: black;">{{ credentials.userEmail}}</p>
              </div>
            </div>
          </div>

            <div class="form-group row">
              <label class="col-lg-4 col-form-label" for="pw">Password <span class="text-danger">*</span></label>
              <div class="col-lg-6">
                <div class="inputNew d-flex flex-col align-item-center justify-content-center" >
                  <input v-model="credentials.userPw" type="text"  class="form-control inputNew" id="pw" name="val-username" placeholder="Enter New password" required>
                  <span class="focus-input100"></span>
                </div>
              </div>
            </div>

            <div class="form-group row">
              <label class="col-lg-4 col-form-label" for="phone">Phone (kr) <span class="text-danger">*</span></label>
              <div class="col-lg-6">
                <div class="inputNew d-flex flex-col align-item-center justify-content-center" >
                  <input v-model="credentials.userPhone" type="text"  class="form-control inputNew" id="phone" name="val-username" placeholder="Enter a phone Number" required>
                  <span class="focus-input100"></span>
                </div>
              </div>
            </div>

          </dl>
          <button type="submit" class="btn btn" style="margin-top: 10px;  background-color: #30475E; color: white; border-radius: 25px;">수정하기</button>
          <button type="reset" class="btn btn mx-2" style="margin-top: 10px;  background-color: crimson; color: white; border-radius: 25px;">Reset</button>
        </form>
        <router-link class="return my-2" :to="{ name: 'profile', params: { useremail } }">이전</router-link>
        &nbsp;
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
import router from '@/router'

export default {
  name: "ModifyView",
  components: {

  },
  data() {
    return {
        credentials: {
          userDept: "",
          userEmail: "",
          userName: "",
          userNo: 0,
          userPhone: "",
          userPw: "",
          userRole: "",
          userYn: true         
        },
    }
  },
  computed: {
    ...mapGetters(['profile', 'password','email']),
    useremail() {
        return this.email
      },
  },
  methods: {
    ...mapActions(['modify']),
    onReset(event) {
        event.preventDefault()
        // Reset our form values
        //this.credentials.userRole = ''
        //this.credentials.userEmail = ''
        this.credentials.userPw = ''
        this.credentials.userName = ''
        //this.credentials.userDept = ''
        this.credentials.userPhone = ''
      },
      logo() {
      router.push({ name: 'meetingroom_man' })
    }
  },
  created() {
    this.credentials.userEmail = this.profile.userEmail
    this.credentials.userName = this.profile.userName
    this.credentials.userPw = this.password
    this.credentials.userDept = this.profile.userDept
    this.credentials.userPhone = this.profile.userPhone
    this.credentials.userRole = this.profile.userRole
  }
}
</script>

<style>
  .return{
  text-decoration: none;
  color: crimson;
}
</style>