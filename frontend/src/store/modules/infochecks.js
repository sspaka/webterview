// import router from "@/router";
import axios from "axios";
import drf from "@/api/drf";

export default {
  state: {
    // 일단 Error관련 코드 없으니까 지워도 되는데 유예
    infoError: null,
    isValid: false,
  },
  getters: {
    infoError: (state) => state.infoError,
    isValid: (state) => state.isValid,
  },
  mutations: {
    SET_INFO_ERROR: (state, error) => (state.infoError = error),
    SET_VALID: (state, isValid) => (state.isValid = isValid),
  },
  actions: {
    // FORM
    sendInfo({ dispatch }, certified) {
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
            console.log("성공했다");
            dispatch("checkInfo", true);
          } else {
            console.log("유효한 면접관/지원자가 없습니다");
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
            dispatch("checkInfo", false);
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
          }
        })
        .catch((err) => {
          console.error(err);
          commit("SET_INFO_ERROR", err);
        });
    },
  },
};
