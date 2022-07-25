import { createRouter, createWebHistory } from 'vue-router'
// views
import HomeView from '../views/login/HomeView.vue'
import SignupView from '../views/user/SignupView.vue'
import ProfileView from '../views/main/sidebar/ProfileView.vue'
import ModifyView from '../views/user/ModifyView.vue'
import FindIdView from '../views/user/FindIdView.vue'
import FindPasswordView from '../views/user/FindPasswordView.vue'
import WebterviewView from '../views/main/WebterviewView.vue'
// components
import QnaSide from '../components/QnaSide.vue'
import MeetingRoomMan from '../components/MeetingRoomMan.vue'
import ApplicantMan from '../components/ApplicantMan.vue'
import ConferenceDetail from '../components/ConferenceDetail.vue'
//board
import BoardWriteView from '../views/BoardWriteView.vue'
import BoardDetailView from '../views/BoardDetailView.vue'
import BoardEditView from '../views/BoardEditView.vue'
 
const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/signup',
    name: 'signup',
    component: SignupView
    // component: () => import(/* webpackChunkName: "about" */ '../views/SignupView.vue')
  },
  {
    path: '/profile/:useremail',
    name: 'profile',
    component: ProfileView
  },
  {
    path: '/modify',
    name: 'modify',
    component: ModifyView
  },
  {
    path: '/findid',
    name: 'findid',
    component: FindIdView
  },
  {
    path: '/findpw',
    name: 'findpw',
    component: FindPasswordView
  },
  // 페이지 내에 components들만 변환시키기 위해 children을 시용
  {
    path: '/webterview',
    name: 'webterview',
    component: WebterviewView,
    children: [
      {
        path: '/webterview/meetingroom_man',
        name: 'meetingroom_man',
        component: MeetingRoomMan
      },
      {
        path: '/webterview/applicant_man',
        name: 'applicant_man',
        component: ApplicantMan
      },
      {
        path: '/webterview/boards',
        name: 'boards',
        component: QnaSide,
      }
    ]
  },
  {
    path: '/webterview/meetingroom_man/detailnumber',
    name: 'ConferenceDetail',
    component: ConferenceDetail
  },
  {
    path: '/webterview/boards/write',
    name: 'BoardWrite',
    component: BoardWriteView
  },
  {
    path:'/webterview/boards/:boardNo',
    name: 'board',
    component: BoardDetailView
  },
  {
    path: '/webterview/boards/:boardNo/edit',
    name: 'boardEdit',
    component: BoardEditView
  },

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

/*
Navigation Guard 설정
  (이전 페이지에서 있던 에러 메시지 삭제)

  로그인(Authentication)이 필요 없는 route 이름들 저장(/login, /signup)

  0. router 에서 이동 감지

  1. 현재 이동하고자 하는 페이지가 로그인이 필요한지 확인
  
  2. 로그인이 필요한 페이지인데 로그인이 되어있지 않다면
    로그인 페이지(/login)로 이동

  3. 로그인이 되어 있다면
    원래 이동할 곳으로 이동
  
  4. 로그인이 되어있는데 /login, /signup 페이지로 이동한다면
    메인 페이지(/)로 이동
    

*/

// router.beforeEach((to, from, next) => {
//   // 이전 페이지에서 발생한 에러메시지 삭제
//   store.commit('SET_AUTH_ERROR', null)

//   const { isLoggedIn } = store.getters

//   const noAuthPages = ['login', 'signup', 'movies']

//   const isAuthRequired = !noAuthPages.includes(to.name)

//   if (isAuthRequired && !isLoggedIn) {
//     alert('Require Login. Redirecting..')
//     next({ name: 'login' })
//   } else {
//     next()
//   }

//   if (!isAuthRequired && isLoggedIn) {
//     next({ name: 'movies' })
//   }
// })

export default router
