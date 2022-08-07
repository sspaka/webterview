// import router from '@/router'
// import axios from 'axios'



export default {
  state: {
    comment: '',
    evaluations: []
  },
  getters: {
    comment: state => state.comment,
    evaluations: state => state.evaluations

  },
  mutations: {
    ADD_COMMENT: (state,comment) => state.comment += comment,
    ADD_RATER_EVALUATION: (state,evaluations) => state.evaluations += evaluations
  },
  actions: {
    uploadScoreSheet({ commit }, credentials ) {
      const comment = credentials.comment
      const raterEvaluations = credentials.raterEvaluations
      commit('ADD_COMMENT', comment)
      commit('ADD_RATER_EVALUATION', raterEvaluations)
      
    },

    
  }
}