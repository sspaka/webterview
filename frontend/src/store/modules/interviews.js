// import drf from '@/api/drf'
// import drf from '@/api/drf'
// import drf from '@/api/drf'
import router from '@/router'
import axios from 'axios'
// import drf from '@/api/drf'
// import router from '@/router'

// import _ from 'lodash'

export default {
  state: {
    groupStart: '',
    groupEnd: '',
    groupBlind: '',
    userNo: '',
    groupNo: '',
    ranking: {}
  },
  getters: {
    groupNo: state => state.groupNo
  },
  mutations: {
    SET_START_TIME: (state,groupStart) => state.groupStart = groupStart,
    SET_END_TIME: (state,groupEnd) => state.groupEnd = groupEnd,
    SET_BLINDYN: (state, groupBlind) => state.groupBlind = groupBlind,
    SET_USERNO: (state,userNo) => state.userNo = userNo,
    SET_GROUPNO: (state,groupNo) => state.groupNo = groupNo
  },
  
  actions: {
    createdInterview ({ commit, getters }, credentials) {
      credentials.groupStart += ':00'
      credentials.groupEnd += ':00'
      console.log(credentials)
      axios({
        // url: drf.admins.createGroup(),
        url: '/admin/createGroup',
        method: 'post',
        headers: getters.authHeader,
        data: credentials,
      })
        .then(res => {
          console.log(res.data)
          commit('SET_START_TIME', res.data.group.groupStart)
          commit('SET_END_TIME', res.data.group.groupEnd)
          commit('SET_BLINDYN', res.data.group.groupBlind)
          commit('SET_USERNO', res.data.group.userNo)
          commit('SET_GROUPNO', res.data.group.groupNo)
          console.log(this.state.interviews.groupNo)
        })
        .catch(err => 
          console.error(err))
    },

    ////////////////////순위표를 백에 요청하는 함수를 짜야하지만 api가 아직////////////////
    finishInterview() {
      console.log('finish interview')
      alert('면접이 종료되었습니다. 순위표를 확인하세요!')
      router.push({name: 'ranking'})
    },
    /////////////여서부터 시작//
    createRooms( { getters }, room ) {
      console.log(room)
      axios({
        // url:drf.admins.createRoom(),
        url:'/admin/createRoom',
        method: 'post',
        headers: getters.authHeader,
        data: room,
      })
        .then(res => {
          console.log(res.data)
          // commit('SET_ROOM',res.data.)
        })

    }
  },
}