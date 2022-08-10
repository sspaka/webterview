<template>
  <div class="wrap-main-waitingroom">
    <div class="card-body col-lg-12">
      <div class="waitingroom-head">본인 확인</div>
    </div>
    <div class="card-body col-lg-12">
      <div class="verify">
        <!-- 면접관 -->
        <form @submit.prevent="sendInfo(certified)">
          <!-- <input type="radio" id="rater" value="면접관" v-model="picked" /> -->
          <input
            type="radio"
            id="rater"
            value="rater"
            v-model="certified.type"
          />
          <label for="rater">면접관</label>
          <br />
          <input
            type="radio"
            id="applicant"
            value="applicant"
            v-model="certified.type"
          />
          <label for="applicant">지원자</label>
          <br />
          <!-- <span>당신은 [{{ certified.type }}]입니다.</span> -->
          <!-- 면접관 선택 -->
          <div
            v-if="certified.type === 'rater'"
            style="display: flex; justify-content: center; flex-direction: row"
          >
            <div class="info-checked-container">
              <div>
                <label>면접관 이름</label>
                <input
                  type="text"
                  class="identification-rater-name"
                  id="RaterName"
                  v-model="certified.name"
                  placeholder="이름을 입력하세요"
                  required
                />
              </div>
              <div>
                <label>면접관 전화번호</label>
                <input
                  type="text"
                  class="identification-rater-phone"
                  id="RaterPhoneNum"
                  v-model="certified.phone"
                  placeholder="ex. 010-1234-1234"
                  required
                />
              </div>
              <button type="submit" class="btn-identification mx-2">
                면접관 확인
              </button>
              <div>
                <span
                  v-if="isValid === true"
                  style="color: red; margin-top: 8px"
                  >면접관 확인이 완료되었습니다.
                </span>
                <span
                  v-if="isValid === false"
                  style="color: red; margin-top: 8px"
                  >면접관 정보가 존재하지 않습니다.</span
                >
              </div>
              <button
                v-if="isValid"
                type="button"
                @click="[removeHyphen(), sendsms(certified)]"
                class="btn-certifiedNum"
              >
                인증번호받기
              </button>
            </div>
          </div>

          <!-- 지원자 선택 -->
          <div
            v-if="certified.type === 'applicant'"
            style="display: flex; justify-content: center; flex-direction: row"
          >
            <div class="info-checked-container">
              <div>
                <label>지원자 이름</label>
                <input
                  type="text"
                  class="identification-applicant-name"
                  id="ApplicantName"
                  v-model="certified.name"
                  placeholder="이름을 입력하세요"
                  required
                />
              </div>
              <div>
                <label>지원시 접수한 전화번호</label>
                <input
                  type="text"
                  class="identification-applicant-phone"
                  id="ApplicantPhoneNum"
                  v-model="certified.phone"
                  placeholder="전화번호를 입력하세요"
                  required
                />
              </div>
              <button type="submit" class="btn-identification mx-2">
                지원자 확인
              </button>
              <div>
                <span v-if="isValid" style="color: red; margin-top: 8px"
                  >지원자 확인이 완료되었습니다.</span
                >
                <span
                  v-if="isValid === false"
                  style="color: red; margin-top: 8px"
                  >지원자 정보가 존재하지 않습니다.</span
                >
              </div>

              <button
                v-if="isValid === true"
                type="button"
                @click="[removeHyphen(), sendsms(certified)]"
                class="btn-certifiedNum mx-2"
              >
                인증번호받기
              </button>
            </div>
          </div>
        </form>

        <!-- 인증번호 확인 -->
        <div class="info-check">
          <div>
            <label>인증번호</label>
            <input
              type="text"
              v-model="phoneCode"
              placeholder="인증번호를 입력해주세요"
            />
            <button
              type="button"
              class="btn-finalNumCheck"
              @click="phoneCodeCheck"
            >
              인증번호확인
            </button>
          </div>
          <p v-if="phoneCodeConfirm === false" style="color: red" class="my-2">
            코드가 일치하지 않습니다.
          </p>
        </div>
      </div>
    </div>

    {{ $route.params.conferenceId + "번 방 상세 보기 페이지" }}
    <div class="row justify-content-center">
      <div class="col-6">
        <button type="button" class="btn btn-primary" @click="GoRaterRoom()">
          Interviewer
        </button>
      </div>
      <div class="col-6">
        <button
          type="button"
          class="btn btn-warning"
          @click="GoApplicantRoom()"
        >
          Applicant
        </button>
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
      // 면접관/지원자 선택
      // picked: "선택되지 않음",

      // 휴대폰 번호 인증 일치 여부
      phoneCodeConfirm: true,
      // 인증번호변수
      phoneCode: "",
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
    ...mapGetters(["infoError", "isValid", "rightCode"]),
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
          this.$router.push("/interviewer");
        }
        if (this.certified.type == "applicant") {
          // 지원자면 카메라 대기화면으로 연결 시켜준다
          this.$router.push("/interviewee/wait");
        }
      }
    },

    // 임시 test
    GoRaterRoom() {
      this.$router.push("/interviewer");
    },
    GoApplicantRoom() {
      this.$router.push("/interviewee/wait");
    },
  },
};
</script>

<style>
.btn-identification {
  background-color: #f05454;
  color: white;
  width: 100px;
}

.btn-identification:hover {
  background-color: #fe3e3e;
  color: white;
}

.btn-finalNumCheck {
  background-color: #f05454;
  color: white;
  width: 100px;
}

.btn-finalNumCheck:hover {
  background-color: #fe3e3e;
  color: white;
}

.btn-certifiedNum {
  background-color: #30475e;
  color: white;
  width: 100px;
}
.btn-certifiedNum:hover {
  background-color: #42576c;
  color: white;
}
</style>
