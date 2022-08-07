import { createStore } from 'vuex'
import accounts from './modules/accounts'
import boards from './modules/boards'
import interviews from './modules/interviews'


export default createStore({
  state: {},
  getters: {},
  mutations: {},
  actions: {},
  modules: {
    accounts, boards, interviews
  }
})

