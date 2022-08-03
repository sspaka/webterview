// import drf from '@/api/drf'
// import drf from '@/api/drf'
import router from '@/router'
import axios from 'axios'
// import drf from '@/api/drf'
// import router from '@/router'

// import _ from 'lodash'

export default {
  state: {
    groupStartDate: '',
    groupEndDate: '',
    groupBlind: '',
    userNo: '',
    ranking: {}
  },
  getters: {
  },
  mutations: {
    SET_START_TIME: (state,groupStartDate) => state.groupStartDate = groupStartDate,
    SET_END_TIME: (state,groupEndDate) => state.groupEndDate = groupEndDate,
    SET_BLINDYN: (state, groupBlind) => state.groupBlind = groupBlind,
    SET_USERNO: (state,userNo) => state.userNo = userNo
  },
  
  actions: {
    createdInterview ({ commit, getters }, credentials) {
      credentials.groupStartDate += ':00'
      credentials.groupEndDate += ':00'
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
          commit('SET_START_TIME', res.data.groupStartDate + ':00')
          commit('SET_END_TIME', res.data.groupEndDate + ':00')
          commit('SET_BLINDYN', res.data.groupBlind)
          commit('SET_USERNO', res.data.userNo)
        })
        .catch(err => 
          console.error(err))
    },

    ////////////////////순위표를 백에 요청하는 함수를 짜야하지만 api가 아직////////////////
    finishInterview() {
      console.log('finish interview')
      alert('면접이 종료되었습니다. 순위표를 확인하세요!')
      router.push({name: 'ranking'})
    }
  },
}