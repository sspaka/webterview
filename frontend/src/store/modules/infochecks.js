// import router from "@/router";
import axios from "axios";
import drf from "@/api/drf";

export default {
  state: {
    // 일단 Error관련 코드 없으니까 지워도 되는데 유예
    infoError: null,
    isValid: false,
    // contentNum: "",
    rightCode: "",
    raterCode: "",
    ApplicantEmail: "",
    isApplicantCheck: false,
  },
  getters: {
    infoError: (state) => state.infoError,
    isValid: (state) => state.isValid,
    // contentNum: (state) => state.contentNum,
    rightCode: (state) => state.rightCode,
    raterCode: (state) => state.raterCode,
    applicantEmail: (state) => state.ApplicantEmail,
    isApplicantCheck: (state) => state.isApplicantCheck,
  },
  mutations: {
    SET_INFO_ERROR: (state, error) => (state.infoError = error),
    SET_VALID: (state, isValid) => (state.isValid = isValid),
    SET_CODE: (state, rightCode) => (state.rightCode = rightCode),
    SET_RATER: (state, raterCode) => (state.raterCode = raterCode),
    SET_EMAIL: (state, applicantEmail) =>
      (state.applicantEmail = applicantEmail),
    SET_CHECK: (state, isApplicantCheck) =>
      (state.isApplicantCheck = isApplicantCheck),
  },
  actions: {
    // FORM
    sendInfo({ dispatch, commit }, certified) {
      console.log(certified);
      axios({
        // url: drf.interviews.sendInfo(),
        url: "/interview/confirm",
        method: "post",
        data: certified,
      })
        .then((res) => {
          // {message : success / fail}
          // 성공 : 정보가 DB에 존재한다.
          // 중복 검사할때
          if (res.data.message === "success") {
            console.log(res.data);
            dispatch("checkInfo", true);

            if(res.data.type === "rater") {
              commit("SET_RATER", res.data.rater.raterNo);
              console.log(res.data.rater.raterNo);
            } else {
              commit("SET_EMAIL", res.data.applicant.ApplicantEmail);
            }
          } else {
            console.log("유효한 면접관/지원자가 없습니다"); 
            dispatch("checkInfo", false);
          }
          // console.log("send info success");
        })
        .catch((err) => {
          console.error(err.response.data);
        });
    },
    checkInfo({ commit }, isValid) {
      commit("SET_VALID", isValid);
      localStorage.setItem("isValid", isValid);
    },
    // sendApplicantInfo(applicantCertified) {
    //   console.log(applicantCertified);
    //   axios({
    //     url: drf.interviews.sendInfo(),
    //     method: "post",
    //     data: applicantCertified,
    //   })
    //     .then((res) => {
    //       // {message : success / fail}
    //       // 성공 : 정보가 DB에 존재한다.
    //       // 중복 검사할때
    //       if (res.data.message === "success") {
    //         console.log(res.data);
    //       } else {
    //         console.log("sendInfo else문입니다");
    //       }
    //       // console.log("send info success");
    //     })
    //     .catch((err) => {
    //       console.error(err.response.data);
    //     });
    // },
    // checkInfo({ commit }, isOverlap) {
    //   commit("SET_OVERLAP", isover);
    // },
    // 면접관의 정보가 DB에 존재하는지 알 수 있다.
    raterOverlapInfo({ commit, dispatch }, raterCertified) {
      console.log(raterCertified);
      if (
        raterCertified.raterName === "" ||
        raterCertified.raterPhoneNum === ""
      ) {
        alert("정보는 공백으로 채울 수 없습니다. 정보를 입력하세요.");
        return;
      }
      axios({
        url: drf.interviews.sendInfo(),
        method: "post",
        data: raterCertified,
      })
        .then((res) => {
          if (res.data.message === "면접관 정보 DB에 존재 X") {
            console.log(res.data);
            dispatch("checkInfo", true);
            console.log(true);
          } else {
            // DB에 존재 O
            console.log(res.data);
            console.log(false);
            console.log("면접관 번호: " + res.data.rater.raterNo);
            dispatch("checkInfo", false);
            commit("SET_RATER", res.data.rater.raterNo);
          }
        })
        .catch((err) => {
          console.error(err);
          commit("SET_INFO_ERROR", err);
        });
    },
    // 지원자의 정보가 DB에 존재하는지 알 수 있다.
    applicantOverlapInfo({ commit, dispatch }, applicantCertified) {
      console.log(applicantCertified);
      if (
        applicantCertified.applicantName === "" ||
        applicantCertified.applicantPhoneNum === ""
      ) {
        alert("정보는 공백으로 채울 수 없습니다. 정보를 입력하세요.");
        return;
      }
      axios({
        url: drf.interviews.sendInfo(),
        method: "post",
        data: applicantCertified,
      })
        .then((res) => {
          if (res.data.message === "지원자 정보 DB에 존재 X") {
            console.log(res.data);
            dispatch("checkInfo", true);
            console.log(true);
          } else {
            // DB에 존재 O
            console.log(res.data);
            console.log(false);
            dispatch("checkInfo", false);
            console.log(res.data.applicant.applicantEmail);
            commit("SET_EMAIL", res.data.applicant.applicantEmail);
          }
        })
        .catch((err) => {
          console.error(err);
          commit("SET_INFO_ERROR", err);
        });
    },
    sendsms({ dispatch }, certified) {
      console.log(certified.phone);
      console.log(certified.codeNum);
      axios({
        // url: drf.interviews.sendInfo(),
        // url: "http://localhost:8080/api/sms",
        // url: "https://sens.apigw.ntruss.com/sms/v2/services/ncp:sms:kr:290257082169:webterview/messages",
        url: "/naverapi/sms",
        method: "post",
        data: {
          // title: "[webterview]",
          recipientPhoneNumber: certified.phone,
          content: certified.codeNum, // 5자리 랜덤 숫자
          // content: Math.floor(Math.random() * (99999 - 10000 + 1) + 10000), // 5자리 랜덤 숫자
        },
        headers: {
          "Content-Type": "application/json",
          "access-token": localStorage.getItem("token"),
        },
      })
        .then((res) => {
          console.log("성공했다");
          console.log(res.data);
          // this.rightCode = res.data;
          dispatch("showcode", "test");
        })
        .catch((err) => {
          console.log("실패했다");
          console.error(err.response.data);
        });
    },
    showcode({ commit }, rightCode) {
      commit("SET_CODE", rightCode);
      localStorage.setItem("rightCode", rightCode);
      // console.log(rightCode); // content 가 찍힌다 (숫자가 아닌 content literally)
    },
    setEmail({ commit }, applicantEmail) {
      console.log("setEmail: " + applicantEmail);
      commit("SET_EMAIL", applicantEmail);
    },
    setCheck({ commit }, isApplicantCheck) {
      console.log("setCheck: " + isApplicantCheck);
      commit("SET_CHECK", isApplicantCheck);
    },
  },
};
