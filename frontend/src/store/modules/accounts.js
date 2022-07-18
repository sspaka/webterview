import router from '@/router'
import axios from 'axios'
import drf from '@/api/drf'

export default {
    state: {
      token: localStorage.getItem('token') || '' ,
      //백엔드 확인
      email: localStorage.getItem('email') || '' ,
      //
      currentUser: {},
      profile: {},
      authError: null,
      code: "",
    },

    getters: {
      isLoggedIn: state => !!state.token,
      currentUser: state => state.currentUser,
      profile: state => state.profile,
      authError: state => state.authError,
      //authHeader: state => ({ Authorization: `Token ${state.token}` }),
      authHeader: state => ({ Authorization: `Token ${state.token}` }),
      
      //////////////////////
      code: state => state.code,

    },

    mutations: {
      SET_TOKEN: (state, token) => state.token = token,
      SET_EMAIL: (state, email) => state.email = email,
      SET_CURRENT_USER: (state, user) => state.currnetUser = user,
      SET_PROFILE: (state, profile) => state.profile = profile,
      SET_AUTH_ERROR: (state, error) => state.authError = error,
      ///////////////////////////////
      SET_CODE: (state, code) => state.code = code,
      

    },

    actions: {
        saveToken({ commit }, token) {
            commit('SET_TOKEN', token)
            localStorage.setItem('token', token)
        },

        removeToken({ commit }) {
            commit('SET_TOKEN', '')
            localStorage.setItem('token', '')
        },
        saveEmail({ commit },email) {
            commit('SET_EMAIL',email)
            localStorage.setItem('email', email)
        },
        /////////////////////
        saveCode({ commit }, code) {
          commit('SET_CODE', code)
          localStorage.setItem('code', code)
        },

        login ({ commit, dispatch }, credentials) {
          console.log(credentials)
            // POST: 사용자 입력정보를 login url로 보내기
            // 성공시
            // 응답 토큰 저장, 현재 사용자 정보 받기, 메인페이지(방만들기 페이지) 이동
            // 실패시
            //  에러메세지 표시
            axios({
                url: drf.accounts.login(),
                method: 'post',
                data: credentials
            })
              .then(res => {
                console.log(res.data)
                const token = res.data["access-token"]
                dispatch('saveToken', token)
                dispatch('fetchCurrentUser')
                //####################################### 이름 맞춰봐야함
                router.push({name: 'webterview'})
                // ##########################################
              })
              .catch(err => {
                console.error(err)
                commit('SET_AUTH_ERROR', err)
              })
        },

        signup({ commit, dispatch }, credentials) {
          console.log(credentials)
            /* 
            POST: 사용자 입력정보를 signup URL로 보내기
            성공하면
                응답 토큰 저장
                현재 사용자 정보 받기
                메인 페이지(방만들기 페이지)로 이동
            실패하면
                에러 메시지 표시
      */
            axios({
                url:drf.accounts.signup(),
                method: 'post',
                data: credentials
            })
              .then(res => {
                const token = res.data.key
                dispatch('saveToken', token)
                dispatch('fetchCurrentUser')
                //####################################### 이름 맞춰봐야함
                router.push({ name: 'webterview' })
                // ##########################################
                console.log("success")
              })
              .catch(err => {
                //console.error(err.response.data)
                console.error(err)
                commit('SET_AUTH_ERROR', err)
              })
        },
        // 이메일 인증코드 보내기
        sendmail({ dispatch }, credentials) {
            axios({
              url:drf.accounts.sendmail(),
              method: 'post',
              data: credentials
            })
              .then(res => {
                dispatch('saveCode', res.data.code)
                // ##########################################
                console.log(res.data)
                console.log(res.data.code)
                console.log("send email success")
              })
              .catch(err => {
                console.error(err.response.data)
              })
        },

        logout({ getters, dispatch }) {
            /* 
                POST: token을 logout URL로 보내기
                성공하면
                    토큰 삭제
                    사용자 알람
                    LoginView로 이동
                실패하면
                    에러 메시지 표시
            */
           axios({
            url: drf.accounts.logout(),
            method: 'post',
            header: getters.authHeader
           })
             .then(() => {
                dispatch('removeToken')
                alert('성공적으로 logout!')
                router.push({ name: 'home' })
             })
             .error(err => {
                console.error(err.response)
             })
        },

        fetchCurrentUser({ commit, getters, dispatch }) {
            /*
            GET: 사용자가 로그인 했다면(토큰이 있다면)
                currentUserInfo URL로 요청보내기
                성공하면
                    state.cuurentUser에 저장
                실패하면(토큰이 잘못되었다면)
                    기존 토큰 삭제
                    LoginView로 이동
            */
           if (getters.isLoggedIn) {
            axios({
                url: drf.accounts.currentUserInfo(),
                method: 'get',
                headers: getters.authHeader,
            })
              .then(res => commit('SET_CURRENT_USER', res.data))
              .catch(err => {
                if (err.response.status ===401) {
                    dispatch('removeToken')
                    router.push({ name: 'home' })
                }
              })
           }
        },

        fetchProfile({ commit, getters }, { useremail }) {
            /*
      GET: profile URL로 요청보내기
        성공하면
          state.profile에 저장
      */
          axios({
            url: drf.accounts.profile(useremail),
            method: 'get',
            headers: getters.authHeader,
          })
            .then(res => {
                console.log(res.data)
                commit('SET_PROFILE', res.data)
            })
        },
      //////////////////////////////////////////////////  
      emailConfirm({ commit, dispatch }, emailAddress) {
          /* 
          POST: 사용자 입력정보를 signup URL로 보내기
          성공하면
              응답 이메일 저장
              현재 사용자 정보 받기
          실패하면
              에러 메시지 표시
    */
          axios({
              url:drf.accounts.signup(),
              method: 'post',
              data: emailAddress
          })
            .then(res => {
              //data의 key부분--> email
              const email = res.data
              dispatch('saveEmail', email) // email이 아닌 인증코드를 dispatch로 올려야함
              // dispatch('fetchCurrentUser')

              console.log("success")
            })
            .catch(err => {
              console.error(err.response.data)
              commit('SET_AUTH_ERROR', err.response.data)
            })
      },
      /////////////////////////////////////////////////////////////
    //   codeCheck({ commit, dispatch }, code) {
    //     axios({
    //       url:drf.accounts.(),
    //       method: 'get',
    //       data: code
    //     })
    //       .then(res => {
    //         const code = res.data
    //         dispatch
    //       })
    //   }
    }
}