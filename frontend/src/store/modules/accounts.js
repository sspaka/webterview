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
      password: "",
      isEmail: 0,
    },

    getters: {
      isLoggedIn: state => !!state.token,
      currentUser: state => state.currentUser,
      profile: state => state.profile,
      authError: state => state.authError,
      email: state => state.email,
      //authHeader: state => ({ Authorization: `Token ${state.token}` }),
      authHeader: state => ({ 'access-token': `${state.token}`}),
      token: state => state.token,
      password: state => state.password,
      code: state => state.code,
      isEmail: state => state.isEmail,

    },

    mutations: {
      SET_TOKEN: (state, token) => state.token = token,
      SET_EMAIL: (state, email) => state.email = email,
      SET_CURRENT_USER: (state, user) => state.currnetUser = user,
      SET_PROFILE: (state, profile) => state.profile = profile,
      SET_AUTH_ERROR: (state, error) => state.authError = error,
      SET_PASSWORD: (state, password) => state.password = password,
      SET_CODE: (state, code) => state.code = code,
      ADD_ISEMAIL: (state) => state.isEmail += 1,
      SET_ISEMAIL: (state, isEmail) => state.isEmail = isEmail,
      

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
        removeEmail({ commit }) {
          commit('SET_EMAIL', '')
          localStorage.setItem('email', '')
        },
        savePassword({ commit }, password) {
          commit('SET_PASSWORD', password)
          localStorage.setItem('password', password)
        },
        removePassword({ commit }) {
          commit('SET_PASSWORD', '')
          localStorage.setItem('password', '')
        },
        /////////////////////
        saveCode({ commit }, code) {
          commit('SET_CODE', code)
          localStorage.setItem('code', code)
        },
        removeCode({ commit }) {
          commit('SET_CODE', '')
          localStorage.setItem('code', '')
        },
        saveProfile({ commit }, profile) {
          commit('SET_PROFILE', profile)
          localStorage.setItem('profile', profile)
        },
        removeProfile({ commit }) {
          commit('SET_PROFILE', '')
          localStorage.setItem('profile', '')
        },
        removeisEmail({ commit }) {
          commit('SET_ISEMAIL', 0)
          localStorage.setItem('isEmail', 0)
        },


        login ({ commit, dispatch }, credentials) {
            console.log(credentials)
            // POST: 사용자 입력정보를 login url로 보내기
            // 성공시
            // 응답 토큰 저장, 현재 사용자 정보 받기, 메인페이지(방만들기 페이지) 이동
            // 실패시
            //  에러메세지 표시
            axios({
                // url: drf.accounts.login(),
                url: drf.accounts.login(),
                method: 'post',
                data: credentials
            })
              .then(res => {
                if (res.data.message === 'success') {
                  console.log(res.data)
                  const token = res.data["access-token"]
                  const email = credentials.userEmail
                  const password = credentials.userPw
                  dispatch('saveEmail', email)
                  dispatch('saveToken', token)
                  dispatch('savePassword', password)
                  dispatch('fetchCurrentUser')
                  router.push({name: 'meetingroom_man'})
                } 
                else {
                  console.log(res.data)
                  alert('회원 정보가 일치하지 않습니다.')
                }
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
                console.log(res.data)
                dispatch('fetchCurrentUser')
                //####################################### 이름 맞춰봐야함
                router.push({ name: 'home' })
                // ##########################################
                console.log("success")
              })
              .catch(err => {
                //console.error(err.response.data)
                console.error(err)
                commit('SET_AUTH_ERROR', err)
              })
        },
        // 프로필 정보 수정
        modify({ dispatch, getters }, credentials) {
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
                url:drf.accounts.modify(),
                method: 'put',
                data: credentials,
                headers: getters.authHeader,
                //headers: 'eyJ0eXAiOiJKV1QiLCJyZWdEYXRlIjoxNjU4MTk1MDk0MTU4LCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NTgxOTg2OTQsInN1YiI6ImFjY2Vzcy10b2tlbiIsInVzZXJlbWFpbCI6InRlc3RAdGVzdC5jby5rciJ9.Bt-fXjR4Fb6tUWGE9kOGdqdU7yfIOP1C_xs6sz9a5EY',
            })
              .then(res => {
                //const email = credentials.useremail
                console.log(res.data)
                dispatch('fetchCurrentUser')
                dispatch('removeisEmail')
                console.log("success")
                router.push({ name: 'profile', params: { useremail: credentials.userEmail } })
              })
              .catch(err => {
                console.error(err)
                //commit('SET_AUTH_ERROR', err)
              })
        },
        // 이메일 인증코드 보내기
        sendmail({ commit, dispatch }, credentials) {
            axios({
              url:drf.accounts.sendmail(),
              method: 'post',
              data: credentials
            })
              .then(res => {
                dispatch('saveCode', res.data.code)
                commit('ADD_ISEMAIL')
                // ##########################################
                console.log(res.data)
                console.log(res.data.code)
                console.log("send email success")
              })
              .catch(err => {
                console.error(err.response.data)
              })
        },
        deleteisEmail({dispatch}) {
          dispatch('removeisEmail')
        },

        logout({ dispatch }) {
          dispatch('removeToken')
          dispatch('removeEmail')
          dispatch('removeProfile')
          dispatch('removeCode')
          dispatch('removePassword')
          alert('성공적으로 logout!')
          router.push({ name: 'home' })
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

      fetchProfile({ dispatch, getters }, { useremail }) {
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
                console.log(res.data["userInfo"])
                dispatch('saveProfile', res.data["userInfo"])
            })
        },
      deleteUser({ dispatch, getters }, useremail ) {
        console.log(useremail)        
        if (confirm('정말 탈퇴 하시겠습니까?')) {
          axios({
            url: drf.accounts.delete(useremail),
            method: 'delete',
            headers: getters.authHeader,
          })
            .then(() => {
              dispatch('removeToken')
              dispatch('removeEmail')
              dispatch('removeProfile')
              dispatch('removeCode')
              dispatch('removePassword')
              router.push({ name: 'home' })
            })
            .catch(err => console.error(err.response))
        }
      },
      findmail({ commit,dispatch }, credentials) {
        axios({
          url:drf.accounts.findMail(),
          method: 'post',
          data: credentials
        })
          .then(res => {
            const email = res.data
            console.log(res.data)
            dispatch('saveEmail', email)
          })
          .catch(err => {
            console.log(err)
            commit('SET_AUTH_ERROR', err)
          })
      },
      saveNewPw( {commit} ,credentials ) {
        console.log(credentials)
        axios({
          url: drf.accounts.saveNewPw(),
          method: 'put',
          data: credentials,
        })
          .then(res => {
            console.log(res.data)
            alert("비밀번호 변경완료")
            router.push({ name: 'home' })
            console.log("success")
          })
          .catch(err => {
            console.error(err)
            commit('SET_AUTH_ERROR', err)
          })
       },
       overlapEmail( useremail ) {
        // console.log(useremail)
          axios({
            url: drf.accounts.overlap(),
            method: 'post',
            data: {"userEmail": useremail}
          })
            .then(res => {
              console.log(res.data)
            })
            .catch(e => {
              console.log(e)
            })
       }
      
    }
}