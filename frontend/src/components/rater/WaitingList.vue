<template>
    <div
      id="overlay"
      class="jumbotron vertical-center"
      @click="isModalViewed = false"
    />
    <div id="modal-card">
      <div style="text-align: left">
        <div style="font-size: x-large"><b>ddd</b></div>
        <div style="color: darkgrey">퇴장 후에는 재입장이 불가능합니다.</div>
      </div>
      <br />
      <div style="display: inline-block;">
        <button
          @click="listClose"
          class="btn btn-modal"
          style="background-color: white; color: black; border-color: darkgrey"
        >
          취소
        </button>
        <button
          @click="leaveSession"
          class="btn btn-modal"
          style="background-color: #f05454; color: white"
        >
          확인
        </button>
      </div>
    </div>
</template>

<script>
export default {
  data() {
    return {
      isListViewed: undefined,
    }
  },
  methods: {
    listClose() {
      this.isListViewed = true;
      this.$emit('list', this.isListViewed);
    }
  }

}
</script>

<style>
/* Modal */
#modal,
#overlay {
  width: 100%;
  height: 100%;
  position: fixed;
  left: 0;
  top: 0;
  z-index: 9997;
}
#overlay {
  opacity: 0.5;
  background-color: black;
}
#modal-card {
  position: relative;
  max-width: 30%;
  margin: auto;
  margin-top: 200px;
  padding: 3%;
  background-color: white;
  z-index: 9998;
  opacity: 1;
  border-radius: 0.5rem;
  width: auto;
  height: auto;
  overflow: hidden;
}

.btn-modal {
  z-index: 9999;
  margin: 0 10px;
}

.btn-modal:hover {
  letter-spacing: 0px;
  transform: scale(1.2);
  cursor: pointer;
}
</style>