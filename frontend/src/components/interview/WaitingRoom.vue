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
                  v-model="certified.phoneNum"
                  placeholder="전화번호를 입력하세요"
                  required
                />
              </div>
              <button type="submit" class="btn-identification">
                본인 확인
              </button>
              <!-- <button type="submit" class="btn-identification">
                본인 확인
              </button> -->
              <button
                v-if="isValid"
                type="button"
                @click="sendsms"
                class="btn-certifiedNum"
              >
                인증번호 받기
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
                  v-model="certified.phoneNum"
                  placeholder="전화번호를 입력하세요"
                  required
                />
              </div>
              <button type="submit" class="btn-identification">
                본인 확인
              </button>
              <!-- <button type="submit" class="btn-identification">본인확인</button> -->
              <button
                v-if="isValid"
                type="button"
                @click="sendsms"
                class="btn-certifiedNum"
              >
                인증번호 받기
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
            <button type="button" @click="phoneCodeCheck">인증번호 확인</button>
          </div>
          <p v-if="phoneCodeConfirm" style="color: red" class="my-2">
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
import axios from "axios";
import { mapGetters, mapActions } from "vuex";

export default {
  name: "ConferenceDetail",
  data() {
    return {
      // 면접관/지원자 선택
      // picked: "선택되지 않음",

      // 휴대폰 번호 인증 일치 여부
      phoneCodeConfirm: false,
      // 인증번호변수
      phoneCode: "",
      rightCode: "",

      // 통합 - 타입, 이름, 전화번호
      certified: {
        // type: this.picked,
        type: "",
        name: "",
        phoneNum: "",
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
    ...mapGetters(["infoError, isValid"]),
  },
  methods: {
    ...mapActions([
      // "sendRaterInfo",
      // "sendApplicantInfo",
      "sendInfo",
      // "raterOverlapInfo",
      // "applicantOverlapInfo",
    ]),
    // raterValid() {
    //   this.checkInfo.name = this.certified.raterName;
    //   this.checkInfo.phoneNum = this.certified.raterPhoneNum;
    // },
    // raterOverlap() {
    //   this.raterOverlapInfo(this.raterCertified);
    // },
    // applicantOverlap() {
    //   this.applicantOverlapInfo(this.applicantCertified);
    // },
    sendsms() {
      axios({
        // url: drf.interviews.sendInfo(),
        url: "/interview/sms",
        method: "post",
        data: {
          recipientPhoneNumber: this.certified.phoneNum,
          title: "[webterview]",
          content: Math.random() * (99999 - 10000 + 1) + 10000, // 5자리 랜덤 숫자
        },
      })
        .then((res) => {
          console.log(res.data);
          this.rightCode = res.data;
        })
        .catch((err) => {
          console.error(err.response.data);
        });
    },
    phoneCodeCheck() {
      if (this.phoneCode == this.rightCode) {
        this.phoneCodeConfirm = true;

        // 면접자면 면접자 화면으로 연결시켜주고
        if (this.certified.type == "rater") {
          this.$router.push("/interviewer");
        }
        // 지원자면 카메라 대기화면으로 연결 시켜준다
        if (this.certified.type == "applicant") {
          this.$router.push("/interviewee/wait");
        }
      }
    },

    // 임시 test
    GoRaterRoom() {
      this.$router.push("/interviewer");
    },
    GoApplicantRoom() {
      this.$router.push("/interviewee");
    },
  },
};
</script>

<style></style>
