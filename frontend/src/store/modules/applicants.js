// import router from '@/router'
import axios from 'axios'
// import drf from '@/api/drf'

export default {
    state: {
      applicants: [],
      applicant: {},
    },

    getters: {
      applicants: state => state.applicants,
      applicant: state => state.applicant,
    },

    mutations: {
      SET_APPLICANTS: (state, applicants) => state.applicants = applicants,
      SET_APPLICANT: (state, applicant) => state.applicant = applicant,
    },

    actions: {
        saveApplicants({ commit }, applicants) {
            commit('SET_APPLICANTS', applicants)
            localStorage.setItem('applicants', applicants)
        },
        removeApplicants({ commit }) {
            commit('SET_APPLICANTS', '')
            localStorage.setItem('applicants', '')
        },
        fetchApplicants({ dispatch, getters }, groupNo) {
            console.log(groupNo)
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
    }
}