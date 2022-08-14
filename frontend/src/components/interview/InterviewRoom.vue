<template>
  <header>
    <h1>
      <a href="#" class="logo"
        ><img src="../../../public/resources/images/logo.png" width="240"
      /></a>
    </h1>
  </header>
  <div class="wrap-main-waitingroom">
    <div class="card-body shadow-lg" style="margin-left: 30%; margin-right: 30%; margin-top: 5%; padding-bottom: 5%;">
    <div>
    <div class="card-body col-lg-12">
      <div class="waitingroom-head">본인 확인</div>
    </div>
    <div class="card-body col-lg-12">
      <div class="verify">
        <!-- 면접관 -->
        <form @submit.prevent="sendInfo(certified); showInfo();">
          <label for="rater" class="radio-button" style="margin-right:10%;">
          <input
            type="radio"
            id="rater"
            value="rater"
            v-model="certified.type"
          /><span>면접관</span>
          </label>
          <!-- <br /> -->
          <label for="applicant" class="radio-button" style="margin-left:10%;">
          <input
            type="radio"
            id="applicant"
            value="applicant"
            v-model="certified.type"
          /><span>지원자</span>
          </label>
          <label for="applicant"></label>
          <br />
          <!-- <span>당신은 [{{ certified.type }}]입니다.</span> -->
          <!-- 면접관 선택 -->
          <div
            v-if="certified.type === 'rater'"
            style="display: flex; justify-content: center; flex-direction: row"
          >
            <div class="info-checked-container">
              <div>
                <!-- <label>면접관 이름</label> -->
                <input
                  type="text"
                  class="identification-rater-name"
                  id="RaterName"
                  v-model="certified.name"
                  placeholder="이름을 입력하세요.."
                  required
                />
              </div>
              &nbsp;
              <div>
                <!-- <label>면접관 전화번호</label> -->
                <input
                  type="text"
                  class="identification-rater-phone"
                  id="RaterPhoneNum"
                  v-model="certified.phone"
                  placeholder="010-0000-0000.."
                  required
                />
              </div>
              
              <button type="submit" class="btn-identification mx-2" style="margin-top: 8px;margin-bottom: 8px;">
                정보 확인
              </button>
              
              <div>
                <span
                  v-if="isValid === true"
                  style="color: red; margin-top: 8px"
                  >면접관 확인이 완료되었습니다.
                </span>
                <span
                  v-if="isValid === false && cnt >=1"
                  style="color: red; margin-top: 8px"
                  >면접관 정보가 존재하지 않습니다.</span
                >
                <span>
              <button
                v-if="isValid"
                type="button"
                @click="[removeHyphen(), sendsms(certified)]"
                class="btn-certifiedNum"
              >
                인증번호받기
              </button>
              </span>
              </div>
              <!-- <span>
              <button
                v-if="isValid"
                type="button"
                @click="[removeHyphen(), sendsms(certified)]"
                class="btn-certifiedNum"
              >
                인증번호받기
              </button>
              </span> -->
            </div>
          </div>

          <!-- 지원자 선택 -->
          <div
            v-if="certified.type === 'applicant'"
            style="display: flex; justify-content: center; flex-direction: row"
          >
            <div class="info-checked-container">
              <div>
                <!-- <label>지원자 이름</label> -->
                <input
                  type="text"
                  class="identification-applicant-name"
                  id="ApplicantName"
                  v-model="certified.name"
                  placeholder="이름을 입력하세요.."
                  required
                />
              </div>
              &nbsp;
              <div>
                <!-- <label>지원시 접수한 전화번호</label> -->
                <input
                  type="text"
                  class="identification-applicant-phone"
                  id="ApplicantPhoneNum"
                  v-model="certified.phone"
                  placeholder="010-0000-0000.."
                  required
                />
              </div>
              <button type="submit" class="btn-identification mx-2" style="margin-top: 8px;margin-bottom: 8px;">
                정보 확인
              </button>
              <div>
                <span v-if="isValid" style="color: red; margin-top: 8px"
                  >지원자 확인이 완료되었습니다.</span
                >
                <span
                  v-if="isValid === false &&cnt >=1 "
                  style="color: red; margin-top: 8px"
                  >지원자 정보가 존재하지 않습니다.</span
                >
                <span>
              <button
                v-if="isValid === true"
                type="button"
                @click="[removeHyphen(), sendsms(certified)]"
                class="btn-certifiedNum mx-2"
              >
                인증번호받기
              </button>
              </span>
              </div>
              <!-- <span>
              <button
                v-if="isValid === true"
                type="button"
                @click="[removeHyphen(), sendsms(certified)]"
                class="btn-certifiedNum mx-2"
              >
                인증번호받기
              </button>
              </span> -->
            </div>
          </div>
        </form>

        <!-- 인증번호 확인 -->
        <div v-if="isValid === true" class="info-check">
          <div style="margin-top: 15%;">
            <!-- <label>인증번호</label> -->
            <input
              class="input-certification-number"
              type="text"
              v-model="phoneCode"
              placeholder="input code..."
            />
            <button
              type="button"
              class="btn-finalNumCheck"
              @click="phoneCodeCheck"
              style="margin-left: 10px;"
            >
              확인
            </button>
          </div>
          <p v-if="phoneCodeConfirm === false" style="color: red" class="my-2">
            코드가 일치하지 않습니다.
          </p>
        </div>
      </div>
    </div>

    <!-- {{ $route.params.roomNo + "번 방 상세 보기 페이지" }}
    <div class="row justify-content-center">
      <div class="col-6">
        <router-link
          :to="{
            name: 'RInterviewView',
            params: { roomCode: roomCode, raterNo: '123' },
          }"
        >
          <button type="button" class="btn btn-primary">Interviewer</button>
        </router-link>
      </div>
      <div class="col-6">
        <router-link
          :to="{
            name: 'AInterviewView',
            params: { roomCode: roomCode, applicantNo: '1', email: 'kim@ssafy.com' },
          }"
        >
          <button type="button" class="btn btn-warning">Applicant</button>
        </router-link>
      </div>
    </div> -->
    </div>
    </div>
  </div>
</template>

<script>
// import axios from "axios";
// import CryptoJS from "vue-cryptojs";
import { mapGetters, mapActions } from "vuex";
// import usesens from "../interview/js/usesens";

export default {
  name: "WaitingRoom",
  data() {
    return {
      roomCode: "",
      raterNo: "", // 면접관 raterNo
      email: "", // 지원자 applicantEmail
      // 면접관/지원자 선택
      // picked: "선택되지 않음",

      // 휴대폰 번호 인증 일치 여부
      phoneCodeConfirm: true,
      // 인증번호변수
      phoneCode: "",
      cnt: 0,
      // rightPhoneCode: "",
      // rightCode: "",

      // 통합 - 타입, 이름, 전화번호
      certified: {
        // type: this.picked,
        type: "",
        name: "",
        // phoneNum: "",
        phone: "",
        codeNum: Math.floor(Math.random() * (99999 - 10000 + 1) + 10000),
      },

      // // 면접관/지원자 이름과 전화번호
      // raterCertified: {
      //   raterName: "",
      //   raterPhoneNum: "",
      // },
      // applicantCertified: {
      //   applicantName: "",
      //   applicantPhoneNum: "",
      // },
    };
  },
  computed: {
    ...mapGetters([
      "infoError",
      "isValid",
      "rightCode",
      "raterCode",
      "applicantEmail",
      "applicantNo"
    ]),
  },
  methods: {
    ...mapActions(["sendInfo", "sendsms"]),
    removeHyphen() {
      // console.log(this.certified.phone);
      this.certified.phone = this.certified.phone.replace(/-/g, "");
      return this.certified.phone;
    },
    phoneCodeCheck() {
      console.log("phoneCodeCheck(): phoneCode(v-model)=", this.phoneCode);
      // if(this.phoneCode == this.certified.codeNum)
      if (this.phoneCode != this.certified.codeNum) {
        this.phoneCodeConfirm = false;
      } else {
        // this.phoneCodeConfirm = true
        if (this.certified.type == "rater") {
          // 면접자면 면접자 화면으로 연결시켜주고
          console.log("면접자 번호: " + this.raterCode);
          this.$router.push({
            name: "RInterviewView",
            params: { roomCode: this.roomCode, raterNo: this.raterCode },
          });
        }
        if (this.certified.type == "applicant") {
          // 지원자면 카메라 대기화면으로 연결 시켜준다
          console.log("지원자 이메일: " + this.applicantEmail);
          this.$router.push({
            name: "AInterviewView",
            params: { roomCode: this.roomCode, applicantNo: this.applicantNo, email: this.applicantEmail },
          });
        }
      }
    },
    showInfo() {
      this.cnt+=1
    }
  },

  created() {
    this.roomCode = this.$route.params.roomCode;
    console.log(this.roomCode);
  },
};
</script>

<style>
.btn-identification {
  background-color: #f05454;
  color: white;
  width: 100px;
  border-radius: 15px;
}

.btn-identification:hover {
  background-color: #fe3e3e;
  color: white;
}

.btn-finalNumCheck {
  background-color: #f05454;
  color: white;
  width: 100px;
  border-radius: 15px;
}

.btn-finalNumCheck:hover {
  background-color: #fe3e3e;
  color: white;
  border-radius: 15px;
}

.btn-certifiedNum {
  background-color: #30475e;
  color: white;
  width: 100px;
  border-radius: 15px;
  font-size: 10px;
  padding: 5px;
}
.btn-certifiedNum:hover {
  background-color: #42576c;
  color: white;
}

/* input[type="radio"] {
  filter: invert(30%) hue-rotate(100deg) brightness(2.0);
} */

.identification-rater-name, .identification-rater-phone, .identification-applicant-name, .identification-applicant-phone{
  border:none;
  border-radius: 10px;
}

.input-certification-number {
  border-radius: 15px;
}

.radio-button input[type="radio"] {
  display: none;
}

.radio-button input[type="radio"] + span {
  border-radius: 5px;
  display: inline-block;
  padding: 15px 10px;
  border: 1px solid #dfdfdf;
  background-color: #ffffff;
  text-align: center;
  cursor: pointer;
}
 
 .radio-button input[type="radio"]:checked + span {
  background-color: #3b5b7b;
  color: #ffffff;
}

.radio-button input[type="radio"]:hover + span {
  background-color: #8ca0b3
}
</style>
