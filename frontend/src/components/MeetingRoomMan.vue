<template>
<form v-if="section===false" @submit.prevent="">
  <div >
  <div>현재 생성된 세부세션방이 없습니다</div>
  <button class="w-btn w-btn-green" type="submit" @click="createRoom">세부세션 생성</button>
  </div>
</form>
<div v-if="clickSection">
  <label for="sections">새부세션 방 갯수:</label>
  <input v-model="state.count" type="number" placeholder="갯수를 입력하세요" required>
  <button @click="createSection">생성하기</button>
  <button @click="cancleRoom">취소하기</button>
</div>
<div v-if="section">
  <button class="w-btn-add w-btn-green-add" style="left: 400px;top: 50px;right: 100px;" @click="addSection">세부세션 추가하기</button>
  <ul class="infinite-list" style="overflow:auto auto;padding-left: 17%;">
    <li v-for="i in state.count" @click="clickConference(i)" class="infinite-list-item" :key="i" >
      <ConferenceName />
    </li>
  </ul>
</div>
<!-- 버튼예시 -->

</template>

<script>
import ConferenceName from './ConferenceName.vue'
import { reactive } from 'vue'
import { useRouter } from 'vue-router'

export default {
  name: 'MeetingRoomMan',

  components: {
    ConferenceName
  },
  data() {
    return {
      clickSection: false,
      section: false,
    }
  },
  methods: {
    createRoom() {
      this.clickSection = true
    },
    cancleRoom() {
      this.clickSection = false;
      this.state.count = 0;
    },
    createSection() {
      this.section= true;
      this.clickSection= false;
    },
    addSection() {
      console.log(this.state.count)
      this.state.count +=1
    }
  },




  setup () {
    const router = useRouter()

    const state = reactive({
      count: 0
    })

    const load = function () {
      state.count += 4
    }

    const clickConference = function (id) {
      router.push({
        name: 'ConferenceDetail',
        params: {
          conferenceId: id
        }
      })
    }

    return { state, load, clickConference }
  },
  
  
}
</script>

<style>
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
    background-color: #F05454;
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
    background-color: #F05454;
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
</style>