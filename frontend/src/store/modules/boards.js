import axios from 'axios'
import drf from '@/api/drf'
import router from '@/router'

import _ from 'lodash'
// import accounts from './accounts'

export default {
  // namespaced: true,
  state: {
    boards: [],
    board: {},
    comments: [],
    comment: {},
  },

  getters: {
    boards: state => state.boards,
    board: state => state.board,
    isAuthor: (state, getters) => {
      return state.board.user?.username === getters.currentUser.username
    },
    isBoard: state => !_.isEmpty(state.board),
    comments: state => state.comments,
    comment: state => state.comment,
  },

  mutations: {
    SET_BOARDS: (state, boards) => state.boards = boards,
    SET_BOARD: (state, board) => state.board = board,
    SET_BOARD_COMMENTS: (state, comments) => (state.board.comments = comments),
    SET_COMMENTS: (state, comments) => state.comments = comments,
    SET_COM: (state, com) => state.com = com,
  },

  actions: {
    fetchBoards({ commit, getters }) {
      console.log("dd")
      console.log(getters.authHeader)
      axios({
        url: drf.boards.boards(),
        method: 'get',
        headers: getters.authHeader,
      })
        .then(res => 
            commit('SET_BOARDS', res.data))
        .catch(err => 
            console.error(err))
    },

fetchReview({ commit, getters }, reviewPk) {
  /* 단일 게시글 받아오기
  GET: review URL (token)
    성공하면
      응답으로 받은 게시글들을 state.reviews에 저장
    실패하면
      단순 에러일 때는
        에러 메시지 표시
      404 에러일 때는
        NotFound404 로 이동
  */
  axios({
    url: drf.reviews.review(reviewPk),
    method: 'get',
    headers: getters.authHeader,
  })
    .then(res => commit('SET_REVIEW', res.data))
    .catch(err => {
      console.error(err.response)
      if (err.response.status === 404) {
        router.push({ name: 'NotFound404' })
      }
    })
},

createReview({ commit, getters }, review) {
  /* 게시글 생성
  POST: reviews URL (게시글 입력정보, token)
    성공하면
      응답으로 받은 게시글을 state.review에 저장
      ReviewDetailView 로 이동
    실패하면
      에러 메시지 표시
  */
  axios({
    url: drf.reviews.reviews(),
    method: 'post',
    data: review,
    headers: getters.authHeader,
  })
    .then(res => {
      commit('SET_REVIEW', res.data)
      router.push({
        name: 'review',
        params: { reviewPk: getters.review.pk }
      })
    })
},

updateReview({ commit, getters }, { pk, movie_title, title, content}) {
  /* 게시글 수정
  PUT: review URL (게시글 입력정보, token)
    성공하면
      응답으로 받은 게시글을 state.review에 저장
      ReviewDetailView 로 이동
    실패하면
      에러 메시지 표시
  */
  axios({
    url: drf.reviews.review(pk),
    method: 'put',
    data: { movie_title, title, content },
    headers: getters.authHeader,
  })
    .then(res => {
      commit('SET_REVIEW', res.data)
      router.push({
        name: 'review',
        params: { reviewPk: getters.review.pk }
      })
    })
},

deleteReview({ commit, getters }, reviewPk) {
  /* 게시글 삭제
  사용자가 확인을 받고
    DELETE: review URL (token)
      성공하면
        state.review 비우기
        AritcleListView로 이동
      실패하면
        에러 메시지 표시
  */
  
  if (confirm('정말 삭제하시겠습니까?')) {
    axios({
      url: drf.reviews.review(reviewPk),
      method: 'delete',
      headers: getters.authHeader,
    })
      .then(() => {
        commit('SET_REVIEW', {})
        router.push({ name: 'reviews' })
      })
      .catch(err => console.error(err.response))
  }
},
deleteReview2({ commit, getters }, {reviewPk, movieTitle}) {
  /* 게시글 삭제
  사용자가 확인을 받고
    DELETE: review URL (token)
      성공하면
        state.review 비우기
        AritcleListView로 이동
      실패하면
        에러 메시지 표시
  */
  if (confirm('정말 삭제하시겠습니까?')) {
    axios({
      url: drf.reviews.review(reviewPk),
      method: 'delete',
      headers: getters.authHeader,
    })
      .then(() => {
        commit('SET_REVIEW', {})
        router.push({ name: 'SpecificReview' ,params: { movieTitle: movieTitle } })
      })
      .catch(err => console.error(err.response))
  }
},

likeReview({ commit, getters }, reviewPk) {
  /* 좋아요
  POST: likeReview URL(token)
    성공하면
      state.article 갱신
    실패하면
      에러 메시지 표시
  */
  axios({
    url: drf.reviews.likeReview(reviewPk),
    method: 'post',
    headers: getters.authHeader,
  })
    .then(res => commit('SET_REVIEW', res.data))
    .catch(err => console.error(err.response))
},

    createComment({ commit, getters }, { reviewPk, content }) {
  /* 댓글 생성
  POST: comments URL(댓글 입력 정보, token)
    성공하면
      응답으로 state.review의 comments 갱신
    실패하면
      에러 메시지 표시
  */
  const comment = { content }

  axios({
    url: drf.reviews.comments(reviewPk),
    method: 'post',
    data: comment,
    headers: getters.authHeader,
  })
    .then(res => {
      commit('SET_REVIEW_COMMENTS', res.data)
    })
    .catch(err => console.error(err.response))
},

updateComment({ commit, getters }, { reviewPk, commentPk, content }) {
  /* 댓글 수정
  PUT: comment URL(댓글 입력 정보, token)
    성공하면
      응답으로 state.review의 comments 갱신
    실패하면
      에러 메시지 표시
  */
  const comment = { content }

  axios({
    url: drf.reviews.comment(reviewPk, commentPk),
    method: 'put',
    data: comment,
    headers: getters.authHeader,
  })
    .then(res => {
      commit('SET_REVIEW_COMMENTS', res.data)
    })
    .catch(err => console.error(err.response))
},
deleteComment({ commit, getters }, { reviewPk, commentPk }) {
  /* 댓글 삭제
  사용자가 확인을 받고
    DELETE: comment URL (token)
      성공하면
        응답으로 state.review의 comments 갱신
      실패하면
        에러 메시지 표시
  */
    if (confirm('정말 삭제하시겠습니까?')) {
      axios({
        url: drf.reviews.comment(reviewPk, commentPk),
        method: 'delete',
        data: {},
        headers: getters.authHeader,
      })
        .then(res => {
          commit('SET_REVIEW_COMMENTS', res.data)
        })
        .catch(err => console.error(err.response))
    }
},
fetchComment({ commit, getters }, {reviewPk, commentPk}) {
  /* 단일 게시글 받아오기
  GET: review URL (token)
    성공하면
      응답으로 받은 게시글들을 state.reviews에 저장
    실패하면
      단순 에러일 때는
        에러 메시지 표시
      404 에러일 때는
        NotFound404 로 이동
  */
  axios({
    url: drf.reviews.comment(reviewPk, commentPk),
    method: 'get',
    headers: getters.authHeader,
  })
    .then(res => commit('SET_COM', res.data))
    .catch(err => {
      console.error(err.response)
      if (err.response.status === 404) {
        router.push({ name: 'NotFound404' })
      }
    })
  },
likeComment({ commit, getters }, {reviewPk, commentPk}) {
  /* 좋아요
  POST: likeComment URL(token)
    성공하면
      state.article 갱신
    실패하면
      에러 메시지 표시
  */
  axios({
    url: drf.reviews.likeComment(reviewPk, commentPk),
    method: 'post',
    headers: getters.authHeader,
  })
    .then(res => {
      console.log(res.data)
      commit('SET_COM', res.data)
      router.go()
    })
    .catch(err => console.error(err.response))
},

  },
}