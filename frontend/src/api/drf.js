const HOST = 'https://i7c205.p.ssafy.io/'

const ACCOUNTS = 'user/'
const BOARD = 'board/'
const COMMENTS = 'comment/'

export default {
  accounts: {
    //login: () => HOST + ACCOUNTS + 'login/',
    login: () => HOST + ACCOUNTS + 'login/',
    //logout: () => HOST + ACCOUNTS + 'logout/',
    signup: () => HOST + ACCOUNTS + 'register/',
    sendmail: () => HOST + ACCOUNTS + 'sendmail/',
    // Token 으로 현재 user 판단
    currentUserInfo: () => HOST + ACCOUNTS + 'info/',
    // username으로 프로필 제공
    profile: useremail => HOST + ACCOUNTS + 'info/' + useremail,
    // profile 수정
    modify: () => HOST + ACCOUNTS + 'modify/',
    // 아이디(이메일 찾기)
    findMail: () => HOST + ACCOUNTS + 'findMail/',
    // 회원탈퇴
    delete: useremail => HOST + ACCOUNTS + 'delete/' + useremail,
    // 비밀번호 재설정
    saveNewPw: () => HOST + ACCOUNTS + 'saveNewPw/',

    // 이메일 중복확인
    overlap: () => HOST + ACCOUNTS + 'overlap/',

    // 비밀번호 확인 -> 탈퇴
    matchPw: () => HOST + ACCOUNTS + 'matchPw/',

  },
  boards: {
    boards: () => HOST + BOARD,
    board: boardNo => HOST + BOARD + `${boardNo}/`,
    comment: boardNo => HOST + BOARD + `${boardNo}/` + COMMENTS, // 수정 가능성?
    //comment: (reviewPk, commentPk) => HOST + BOARD + `${reviewPk}/` + COMMENTS + `${commentPk}/`,
    //likeComment: (reviewPk, commentPk) => HOST + BOARD + `${reviewPk}/` + COMMENTS + `${commentPk}/` + 'like/',
  },
  // movies: {
  //   movies: () => HOST + MOVIES,
  //   movieDetail: moviePk => HOST + MOVIES + `${moviePk}/`,
  //   movieRecommend: (movieId) => HOST + MOVIES + 'recommended/' + `${movieId}`,
  // }

}
