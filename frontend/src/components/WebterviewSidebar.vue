<template>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <!-- <div class="w3-sidebar w3-bar-block" style="width:25%"> -->
  <div class="menu">
    <label for="expand-menu"><div>메뉴</div></label>
    <input type="checkbox" id="expand-menu" name="expand-menu">
    <ul>
      <li>
      <router-link to="/webterview/meetingroom_man">미팅룸관리</router-link>
      </li>
      <li>
      <router-link to="/webterview/applicant_man">지원자관리</router-link>
      </li>
      <li>
      <router-link to="/webterview/qna">Q&A</router-link>
      </li>
      <li>
      <router-link :to="{ name: 'profile', params: { useremail } }">프로필</router-link>
      </li>
      <li>
      <input type="button" value="로그아웃" @click="logout">
      </li>
    </ul>
  </div>


</template>

<script>
import { mapGetters, mapActions } from 'vuex'

export default {
    name: 'WebterviewSidebar',
    computed: {
      ...mapGetters(['email']),
      useremail() {
        return this.email
      },
    },
    methods: {
      ...mapActions(['logout']),
    },
  }
</script>

<style scoped>
.menu ::before{
    font-family: 'Material Icons';
    font-size: 1.5em;
    float: left;
    clear: left;
}
.menu label::before{ content: '\e5d2'; }
.menu li:nth-child(1) a::before{ content: '\f02e'; }
.menu li:nth-child(2) a::before{ content: '\e8d6'; }
.menu li:nth-child(3) a::before{ content: '\e88a'; }
.menu li:nth-child(4) a::before{ content: '\e8cc'; }
.menu li:nth-child(5) a::before{ content: '\e87d'; }

#expand-menu { /* 체크박스 폼 요소 감춤 */
    display: none;
}
#expand-menu:checked ~ ul { /* 체크박스 체크되었으면 메뉴 목록 표시 - 반응형 표시용 */
    display: block;
    height: auto;
}

.menu {
    display: block;
    width: 200px;
    background-color: #000;
    color: #fff;
    border-radius: 20px;
    padding: 10px;
    box-sizing: border-box;
    overflow: hidden; /* 반응형 애니메이션용 */
    transition: all 0.5s ease; /* 반응형 애니메이션 */
}
.menu ul {
    list-style: none;
    margin: 0;
    padding: 0;
}
.menu a, .menu > label {
    display: block;
    height: 25px;
    padding: 8px;
    cursor: pointer;
    color: #fff;
    text-decoration: none;
}

.menu a:hover {
    color: #000;
}
.menu ul li:hover, .menu > label:hover {
    background-color: #fff;
    color: #000;
    border-radius: 10px;
}
.menu div {
    line-height: 1.5;
    font-size: 1em;
    font-family: 'Noto Sans KR';
    padding: 0 0 0 50px; /* 아이콘과 텍스트 사이 여백 */
}
</style>