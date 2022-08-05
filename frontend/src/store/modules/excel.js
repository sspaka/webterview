// import router from '@/router'
import axios from 'axios'
// import drf from '@/api/drf'

export default {
    state: {
      applicants: [],
      applicant: {},
      raters: [],
      rater: {},
      evalSheet: [],
    },

    getters: {
      applicants: state => state.applicants,
      applicant: state => state.applicant,
      raters: state => state.raters,
      rater: state => state.rater,
      evalSheet: state => state.evalSheet,
    },

    mutations: {
      SET_APPLICANTS: (state, applicants) => state.applicants = applicants,
      SET_APPLICANT: (state, applicant) => state.applicant = applicant,
      SET_RATERS: (state, raters) => state.raters = raters,
      SET_RATER: (state, rater) => state.rater = rater,
      SET_EVALSHEET: (state, evalSheet) => state.evalSheet = evalSheet,
    },

    actions: {
        saveApplicants({ commit }, applicants) {
            commit('SET_APPLICANTS', applicants)
            localStorage.setItem('applicants', applicants)
        },
        saveApplicant({ commit }, applicant) {
          commit('SET_APPLICANT', applicant)
          localStorage.setItem('applicant', applicant)
        },
        saveRaters({ commit }, raters) {
          commit('SET_RATERS', raters)
          localStorage.setItem('raters', raters)
        },
        saveRater({ commit }, rater) {
          commit('SET_RATER', rater)
          localStorage.setItem('rater', rater)
        },
        saveEvalSheet({ commit }, evalSheet) {
          commit('SET_EVALSHEET', evalSheet)
          localStorage.setItem('evalSheet', evalSheet)
        },
        removeApplicants({ commit, getters }, groupNo) {
            console.log('remove applicants' + groupNo)
            axios({
                // url: drf.applicants.applicants(),
                url: '/interview'+'/applicant'+'/delete',
                method: 'delete',
                params: {
                    groupNo: groupNo
                },
                headers: {
                    'access-token': getters.authHeader['access-token'],
                }
            })
              .then(res => {
                console.log(res.data.applicantList)
                commit('SET_APPLICANTS', '')
                localStorage.setItem('applicants', '')
              })
              .catch(err => {
                console.error(err)
              })
        },
        fetchApplicants({ dispatch, getters }, groupNo) {
            console.log('fetch applicants in group' + groupNo)
            axios({
                // url: drf.applicants.applicants(),
                url: '/interview'+'/applicant'+'/group',
                method: 'get',
                params: {
                    groupNo: groupNo
                },
                headers: {
                    'access-token': getters.authHeader['access-token'],
                }
            })
              .then(res => {
                console.log(res.data.applicantList)
                dispatch('saveApplicants', res.data.applicantList )
              })
              .catch(err => {
                console.error(err)
              })
        },
        fetchApplicant({ dispatch }, applicantEmail) {
          console.log('fetch applicant!')
          console.log(applicantEmail)
          axios({
              // url: drf.applicants.applicants(),
              url: '/interview'+'/applicant'+'/info/' + applicantEmail,
              method: 'get',
          })
            .then(res => {
              console.log(res.data)
              if (res.data.message === 'success') {
                console.log(res.data.applicant)
                dispatch('saveApplicant', res.data.applicant)
              }
            })
            .catch(err => {
              console.error(err)
            })
        },
        fetchRaters({ dispatch, getters }, userNo) {
          console.log('fetch raters')
          axios({
              // url: drf.applicants.applicants(),
              url: '/interview'+'/raterList/' + userNo ,
              method: 'get',
              headers: {
                  'access-token': getters.authHeader['access-token'],
              }
          })
            .then(res => {
              console.log(res.data.list)
              dispatch('saveRaters', res.data.list )
            })
            .catch(err => {
              console.error(err)
            })
        },
        fetchRater({ dispatch, getters }, raterNo) {
          console.log('fetch rater!')
          console.log(raterNo)
          axios({
              // url: drf.applicants.applicants(),
              url: '/interview'+'/detailRater/' + raterNo,
              method: 'get',
              headers: {
                'access-token': getters.authHeader['access-token'],
              }
          })
            .then(res => {
              console.log(res.data)
              if (res.data.message === 'OK') {
                console.log(res.data.rater)
                dispatch('saveRater', res.data.rater)
              }
            })
            .catch(err => {
              console.error(err)
            })
        },
        removeRaters({ commit, getters }, userNo) {
          console.log('remove raters' + userNo)
          axios({
              // url: drf.applicants.applicants(),
              url: '/interview'+'/alldelete'+'/'+userNo ,
              method: 'delete',
              // params: {
              //     userNo: userNo
              // },
              headers: {
                  'access-token': getters.authHeader['access-token'],
              }
          })
            .then(res => {
              console.log(res.data)
              commit('SET_RATERS', '')
              localStorage.setItem('raters', '')
            })
            .catch(err => {
              console.error(err)
            })
        },
        removeEvalSheet({ commit, getters }, groupNo) {
          console.log('remove Evaluation Sheet' + groupNo)
          axios({
              // url: drf.applicants.applicants(),
              url: '/score'+'/eval' +'/delete',
              method: 'delete',
              params: {
                  groupNo: groupNo
              },
              headers: {
                  'access-token': getters.authHeader['access-token'],
              }
          })
            .then(res => {
              console.log(res.data)
              commit('SET_EVALSHEET', '')
              localStorage.setItem('evalSheet', '')
            })
            .catch(err => {
              console.error(err)
            })
        },
        fetchEvalSheet({ dispatch, getters }, groupNo) {
          console.log('fetch eval sheet' + groupNo)
          axios({
              // url: drf.applicants.applicants(),
              url: '/score'+'/eval',
              method: 'get',
              params: {
                  groupNo: groupNo
              },
              headers: {
                  'access-token': getters.authHeader['access-token'],
              }
          })
            .then(res => {
              console.log(res.data.list)
              dispatch('saveEvalSheet', res.data.list)
            })
            .catch(err => {
              console.error(err)
            })
        },
    }
}