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
    SET_COMMENT: (state, comment) => state.comment = comment,
  },

  actions: {
    fetchBoards({ commit, getters }, params) {
      console.log(getters.authHeader)
      axios({
        url: '/board', 
        method: 'get',
        //headers: getters.authHeader,
        params: params,
        headers: { 'Access-Control-Allow-Origin': '*',
        'Content-Type': 'application/json; charset = utf-8',
        'access-token': getters.authHeader['access-token']},
      })
        .then(res => {
          console.log('success board')
          console.log(res.data.boardList.content)
          commit('SET_BOARDS', res.data.boardList.content)
        })
        .catch(err => 
            console.error(err))
    },

    fetchBoard({ commit, getters }, boardNo) {
      axios({
        // url: drf.boards.board(boardNo),
        url: '/board' + '/' + boardNo,
        method: 'get',
        headers: getters.authHeader,
      })
        .then(res => {
          console.log(res.data.board)
          commit('SET_BOARD', res.data.board)
        })
        .catch(err => {
          console.error(err.response)
          if (err.response.status === 404) {
            router.push({ name: 'NotFound404' })
          }
        })
    },

    createBoard({ commit, getters }, board) {
      axios({
        url: '/board',
        method: 'post',
        headers: getters.authHeader,
        data: board,
      })
        .then(res => {
          commit('SET_BOARD', res.data)
          router.push({ name: 'boards' })
        })
    },

    updateBoard({ commit, getters }, payload) {
      axios({
        // url: drf.boards.board(boardNo),
        url: '/board' + '/' + payload.boardNo,
        method: 'put',
        data: payload,
        headers: getters.authHeader,
      })
        .then(res => {
          console.log(getters.board)
          commit('SET_BOARD', res.data)
          // router.push({
          //   name: 'board',
          //   params: { boardNo: getters.board.boardNo }
          // })
          router.push({ name: 'boards'})
        })
    },

    deleteBoard({ commit, getters }, boardNo) {
      if (confirm('정말 삭제하시겠습니까?')) {
        axios({
          // url: drf.boards.board(boardNo),
          url: '/board' + '/' + boardNo,
          method: 'delete',
          headers: getters.authHeader,
        })
          .then(() => {
            commit('SET_BOARD', {})
            router.push({ name: 'boards' })
          })
          .catch(err => console.error(err.response))
      }
    },

    createComment({ commit, getters }, credentials) {
      axios({
        //url: drf.reviews.comments(boardNo),
        url: '/board' + '/comment',
        method: 'post',
        data: credentials,
        headers: getters.authHeader,
      })
        .then(res => {
          commit('SET_BOARD_COMMENTS', res.data.comment)
        })
        .catch(err => console.error(err.response))
    },

    deleteComment({ commit, getters }, { boardNo, commentNo }) {
        if (confirm('정말 삭제하시겠습니까?')) {
          axios({
            url: drf.reviews.comment(boardNo, commentNo),
            // url: '/board' + '/comment' +'/'+commentNo
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
  },
}