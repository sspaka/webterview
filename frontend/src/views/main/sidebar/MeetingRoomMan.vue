<template>
  <div>
   <form @submit.prevent="okGroup(); openGroupBtn()">
    <!-- 그룹 생성하는 버튼 -->
    <div v-if="openGroup===false" class="card shadow-lg p-3 mb-5 bg-body rounded" style="margin-left: 20%; margin-right: 10%; margin-top: 10%">
      <div class="card shadow-lg p-3 mb-5 bg-body rounded" style="margin-left: 10%; margin-right: 10%; margin-top: 10%">
      <span class="start" style="margin-top: 15px; margin-bottom:15px;">
        <span class="txt3">시작일:&nbsp;&nbsp;&nbsp;&nbsp;</span>
        <input id="start_date" type="datetime-local" v-model="credentials.groupStartDate" required>
        <p>{{ credentials.groupStartDate }}</p>
      </span>
      <span class="end" style="margin-top: 15px; margin-bottom:15px;">
        <span class="txt3">종료일:&nbsp;&nbsp;&nbsp;&nbsp;</span>
        <input id="end_date" type="datetime-local" v-model="credentials.groupEndDate" required>
        <p>{{ credentials.groupEndDate }}</p>
      </span>
      <label for="blind">
        <span class="txt3">블라인트 테스트 </span> 
        <input v-model="credentials.groupBlind" type="checkbox" id="blind" name="blind" value="true"/>
        <p>{{ credentials.groupBlind }}</p>
        <p>{{credentials.userNo}}</p>
        <p>{{ userNo }}</p>
      </label>
        <!-- 클릭시 db를 바꿔주는 함수까지 가게 끔 해야 함 -->
        <div>
          <button type="submit" class="w-btn w-btn-green">면접 생성하기</button>
        </div>
      </div>
      </div>
   </form>


    <div v-if="openGroup">
    <!-- <form v-if="cardForm===true" @submit.prevent=""> -->
      <form @submit.prevent="">
        <div v-if="section===false" class="card shadow-lg p-3 mb-5 bg-body rounded" style="margin-left: 20%; margin-right: 10%; margin-top: 15%">
          <div v-if="clickSection===false">
          <div style="margin-top: 100px;margin-bottom: 100px;">현재 생성된 면접방이 없습니다</div>
          <div>
            <button class="w-btn w-btn-green" type="submit" @click="createRoom">면접방 생성</button>
          </div>
          </div>
          <!-- 방 갯수 생성,삭제 -->
          <div v-if="clickSection">
            <label for="sections">sections:</label>&ensp;
            <input class="section-number" style="margin-top: 100px;margin-bottom: 100px;" v-model="state.count" type="number" min="1" required>
            <div>
              <button class="w-btn w-btn-green" @click="cancleRoom">취소하기</button>
              <button class="w-btn w-btn-green" @click="createSection">생성하기</button>
            </div>
          </div>
        </div>
      </form>
    </div>

    <div v-if="section">
      <div class="buttons d-flex">
        <button class="w-btn-add w-btn-green-add" @click="addSection">면접방 추가하기</button>
        <div v-if="section">
          <form @submit.prevent="finishInterview(); ok()">
            <button class="w-btn-delete w-btn-green-delete">면접종료</button>
          </form>
        </div>
      </div>
      <ul class="infinite-list" style="overflow:auto auto;padding-left: 17%;">
          <li v-for="i in state.count" @click="clickConference(i)" class="infinite-list-item" :key="i" >
            <ConferenceName />
          </li>
      </ul>
    </div>
  </div>

<!-- 버튼예시 -->

</template>

<script>
import ConferenceName from "../../../components/ConferenceName.vue";
import { reactive } from "vue";
import { useRouter } from "vue-router";
import { mapActions, mapGetters } from "vuex"

export default {
  name: "MeetingRoomMan",

  components: {
    ConferenceName,
  },
  data() {
    return {
      clickSection: false,
      section: false,
      cardForm: true,
      openGroup: false,
      credentials: {
        groupStartDate: '',
        groupEndDate: '',
        groupBlind: false,
        userNo: ''
        // userNo: ''
      }
    };
  },
  computed: {
    // ...mapState([])
    ...mapGetters(['profile', 'userNo'])
  },
  methods: {
    ...mapActions(['createdInterview', 'finishInterview']),
    createRoom() {
      this.clickSection = true;
      this.cardForm = false;
    },
    cancleRoom() {
      this.section = false;
      this.clickSection = false;
      this.state.count = 1;
    },
    createSection() {
      this.section = true;
      this.clickSection = false;
    },
    addSection() {
      console.log(this.state.count);
      this.state.count += 1;
    },
    openGroupBtn() {
      console.log('group created')
      // 이제 여기에 클릭시 db에 true로 바꿔달라 요청하는 코드 만들어야됨
      // store에 작성하면됨
      this.openGroup = true;
    },
    ok() {
      this.section = false
      this.openGroup = false
    },
    okGroup() {
      this.credentials.userNo= this.userNo
      this.createdInterview(this.credentials)

    }
  },

  setup() {
    const router = useRouter();

    const state = reactive({
      count: 0,
    });
    

    const load = function () {
      state.count += 4;
    };

    const clickConference = function (id) {
      router.push({
        name: "ConferenceDetail",
        params: {
          conferenceId: id,
        },
      });
    };

    return { state, load, clickConference };
  },
  // created() {
  //   this.credentials.userNo = this.profile.userNo
  // }
};
</script>

<style scoped>

.session {
  border: 1px bold #121212;
}
/* card */
.card {
}
.infinite-list {
  padding-left: 0;
  max-height: calc(100% - 35px);
}

@media (min-width: 701px) and (max-width: 1269px) {
  .infinite-list {
    min-width: 700px;
  }
}

@media (min-width: 1270px) {
  .infinite-list {
    min-width: 1021px;
  }
}

.infinite-list .infinite-list-item {
  min-width: 335px;
  max-width: 25%;
  display: inline-block;
  cursor: pointer;
}
/* 버튼 */
.w-btn {
  position: relative;
  border: none;
  display: inline-block;
  padding: 8px 16px;
  border-radius: 15px;
  font-family: "paybooc-Light", sans-serif;
  box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
  text-decoration: none;
  font-weight: 600;
  transition: 0.25s;
}
.w-btn-green {
  background-color: #f05454;
  color: #f8e3e3;
}

button {
  margin: 20px;
}

.w-btn:hover {
  letter-spacing: 0px;
  transform: scale(1.2);
  cursor: pointer;
}

.w-btn:active {
  transform: scale(1.5);
}

/* 세부세션 추가 버튼 */
.w-btn-add {
  position: relative;
  border: none;
  display: inline-block;
  padding: 8px 16px;
  border-radius: 15px;
  font-family: "paybooc-Light", sans-serif;
  box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
  text-decoration: none;
  font-weight: 600;
  transition: 0.25s;
}
.w-btn-green-add {
  background-color: #f05454;
  color: #f8e3e3;
}

button {
  margin: 20px;
}

.w-btn-add:hover {
  letter-spacing: 0px;
  transform: scale(1.2);
  cursor: pointer;
}

.w-btn-add:active {
  transform: scale(1.5);
}
/* 면접종료 버튼 */
.w-btn-delete {
  /* position: fixed;
  bottom: 0;
  right: 5%;  */
  border: none;
  display: inline-block;
  padding: 8px 16px;
  border-radius: 15px;
  font-family: "paybooc-Light", sans-serif;
  box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
  text-decoration: none;
  font-weight: 600;
  transition: 0.25s;
}
.w-btn-green-delete {
  background-color: #f05454;
  color: #f8e3e3;
}

button {
  margin: 20px;
}

.w-btn-delete:hover {
  letter-spacing: 0px;
  transform: scale(1.2);
  cursor: pointer;
}

.w-btn-delete:active {
  transform: scale(1.5);
}

/* input */
input {
  text-align: center;
  border: 1px solid #6c757d;
}

.number-input input[type="number"] {
  -webkit-appearance: textfield !important;
  -moz-appearance: textfield !important;
  appearance: textfield !important;
}

.number-input input[type="number"]::-webkit-inner-spin-button,
.number-input input[type="number"]::-webkit-outer-spin-button {
  -webkit-appearance: none;
}

.number-input {
  margin-bottom: 20px;
  padding-top: 20px !important;
  }

/* 숫자 input */
.section-number {
  color: #1b3b5b;
  border-color: #1b3b5b;
  border-radius: 15px;

}

.buttons {
  top: 5px;
  margin-left: 18%;
}


</style>
