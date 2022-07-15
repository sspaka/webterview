//const HOST = 'http://localhost:8000/api/v1/'
const HOST = 'http://localhost:8080/'

const ACCOUNTS = 'user/'
//const ACCOUNTS = 'accounts/'
// const COMMUNITY = 'community/'
// const COMMENTS = 'comments/'
// const MOVIES = 'movies/'

export default {
  accounts: {
    //login: () => HOST + ACCOUNTS + 'login/',
    login: () => HOST + ACCOUNTS + 'login/',
    logout: () => HOST + ACCOUNTS + 'logout/',
    signup: () => HOST + ACCOUNTS + 'register/',
    sendmail: () => HOST + ACCOUNTS + 'sendmail/',
    // Token 으로 현재 user 판단
    currentUserInfo: () => HOST + ACCOUNTS + 'user/',
    // username으로 프로필 제공
    profile: username => HOST + ACCOUNTS + 'profile/' + username,
  },
  // reviews: {
  //   reviews: () => HOST + COMMUNITY,
  //   review: reviewPk => HOST + COMMUNITY + `${reviewPk}/`,
  //   likeReview: reviewPk => HOST + COMMUNITY + `${reviewPk}/` + 'like/',
  //   comments: reviewPk => HOST + COMMUNITY + `${reviewPk}/` + COMMENTS,
  //   comment: (reviewPk, commentPk) =>
  //     HOST + COMMUNITY + `${reviewPk}/` + COMMENTS + `${commentPk}/`,
  //   likeComment: (reviewPk, commentPk) =>
  //    HOST + COMMUNITY + `${reviewPk}/` + COMMENTS + `${commentPk}/` + 'like/',
  // },
  // movies: {
  //   movies: () => HOST + MOVIES,
  //   movieDetail: moviePk => HOST + MOVIES + `${moviePk}/`,
  //   movieRecommend: (movieId) => HOST + MOVIES + 'recommended/' + `${movieId}`,
  // }

}
