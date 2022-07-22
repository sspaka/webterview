<template>
  <ul class="infinite-list" style="overflow:auto">
    <li v-for="i in state.count" @click="clickConference(i)" class="infinite-list-item" :key="i" >
      <ConferenceName />
    </li>
  </ul>
  <!-- style="overflow:auto" -->
  <!-- v-infinite-scroll="load"  -->
</template>

<script>
import ConferenceName from './ConferenceName.vue'
import { reactive } from 'vue'
import { useRouter } from 'vue-router'

export default {
  // name: 'MeetingRoomMan',

  components: {
    ConferenceName
  },

  setup () {
    const router = useRouter()

    const state = reactive({
      count: 12
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
  }
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
</style>