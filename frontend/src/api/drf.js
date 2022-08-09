// const HOST = 'https://i7c205.p.ssafy.io/'
const HOST = "http://localhost:8080/api/";

const ACCOUNTS = "user/";
const BOARD = "board/";
const ADMIN = "admin/";
const INTERVIEW = "interview/";
const APPLICANT = "applicant/";
const API = "api/";
//const COMMENTS = 'comment/'
const SCORE = "score/"

export default {
  accounts: {
    //login: () => HOST + ACCOUNTS + 'login/',
    login: () => HOST + ACCOUNTS + "login/",
    //logout: () => HOST + ACCOUNTS + 'logout/',
    signup: () => HOST + ACCOUNTS + "register/",
    sendmail: () => HOST + ACCOUNTS + "sendmail/",
    // Token 으로 현재 user 판단
    currentUserInfo: () => HOST + ACCOUNTS + "info/",
    // username으로 프로필 제공
    profile: (useremail) => HOST + ACCOUNTS + "info/" + useremail,
    // profile 수정
    modify: () => HOST + ACCOUNTS + "modify/",
    // 아이디(이메일 찾기)
    findMail: () => HOST + ACCOUNTS + "findMail/",
    // 회원탈퇴
    delete: (useremail) => HOST + ACCOUNTS + "delete/" + useremail,
    // 비밀번호 재설정
    saveNewPw: () => HOST + ACCOUNTS + "saveNewPw/",

    // 이메일 중복확인
    overlap: () => HOST + ACCOUNTS + "overlap/",

    // 비밀번호 확인 -> 탈퇴
    matchPw: () => HOST + ACCOUNTS + "matchPw/",
  },
  boards: {
    boards: () => HOST + BOARD,
    board: (boardNo) => HOST + BOARD + `${boardNo}/`,
    modify: () => HOST + BOARD + "modify/",
    comments: () => HOST + BOARD + "comment/",
    comment: (commentNo) => HOST + BOARD + `${commentNo}/`,
    //likeComment: (reviewPk, commentPk) => HOST + BOARD + `${reviewPk}/` + COMMENTS + `${commentPk}/` + 'like/',
  },
  admins: {
    // 면접생성
    createGroup: () => HOST + ADMIN + "createGroup/",

    // 면접수정
    modifyGroup: () => HOST + ADMIN + "modifyGroup/",

    //면접방 생성
    createRoom: () => HOST + ADMIN + "createRoom/",

    // 면접끝내기
    deleteGroup: (groupNo) => HOST + ADMIN + `${groupNo}/`,

    //면접방 삭제
    deleteRoom: (roomNo) => HOST + ADMIN +"room/"+`${roomNo}/`,
  },
  applicants: {
    applicants: (groupNo) =>
      HOST + INTERVIEW + APPLICANT + "group/" + `${groupNo}`,
  },

  interviews: {
    // FORM 관련 주석처리
    sendInfo: () => HOST + INTERVIEW + "confirm/",
  },

  api: {
    // 인증 문자 번호 보내기
    sendsms: () => HOST + API + "sms/",
  },

  Score: {
    //면접관들 점수를 한곳에 모아놨다가, 한방에 back에 보냄
    saveScore: () => HOST + SCORE + "/save",
  }
};
