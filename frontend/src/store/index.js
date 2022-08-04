import { createStore } from 'vuex'
import accounts from './modules/accounts'
import boards from './modules/boards'
import interviews from './modules/interviews'
import applicants from './modules/applicants'


export default createStore({
  state: {
  },
  getters: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
    accounts, boards, interviews, applicants,
  }
})

