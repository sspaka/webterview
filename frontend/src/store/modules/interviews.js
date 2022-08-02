// import drf from '@/api/drf'
import router from '@/router'
// import axios from 'axios'
// import drf from '@/api/drf'
// import router from '@/router'

// import _ from 'lodash'

export default {
  state: {
    start_time: '',
    end_time: '',
    blindYn: '',
    ranking: {}
  },
  getters: {
  },
  mutations: {
    SET_START_TIME: (state,start_time) => state.start_time = start_time,
    SET_END_TIME: (state,end_time) => state.end_time = end_time,
    SET_BLINDYN: (state, blindYn) => state.blindYn = blindYn
  },
  // 여기서부터 건들기
  actions: {
    createdInterview (a, credentials) {
      console.log(credentials)
      console.log('a')
      console.log(credentials.startInterview)
///////////////////////////////////axios에 url이 없어서 오류가 났었음 api 생성되면 그때 확인!/////////////////////
      // axios({
      //   // url 적어야함
      //   method: 'post',
      //   data: credentials,
      // })
      //   .then(res => {
      //     console.log(res.data)
      //     commit('SET_START_TIME', res.data.start_time)
      //     commit('SET_END_TIME', res.data.end_time)
      //     commit('SET_BLINDYN', res.data.blindYn)
      //   })
      //   .catch(err => 
      //     console.error(err))
    },

    ////////////////////순위표를 백에 요청하는 함수를 짜야하지만 api가 아직////////////////
    finishInterview() {
      console.log('finish interview')
      alert('면접이 종료되었습니다. 순위표를 확인하세요!')
      router.push({name: 'ranking'})
    }
  },
}