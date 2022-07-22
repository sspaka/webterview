<template>
  <form @submit.prevent="onSubmit">
    <div>
      <label for="title"> </label>
      <input v-model="newReview.title" type="text" id="title" class="inputBox mx-3" placeholder="제목"/>
    </div>
    <br>
    <div>
      <label for="content"></label>
      <textarea class="mx-3" v-model="newReview.content" type="text" id="content" placeholder="내용"></textarea>
    </div>
    <div>
      <button class="create-button mb-2 mx-3">작성 {{ action }}</button>
    </div>
  </form>
</template>


<script>
import { mapActions, mapGetters } from 'vuex'

  export default {
    name: 'boardForm',
    data() {
      return {
        newBoard: {
          boardTitle: '',
          boardContent: '',
          userNo: '',
        },
      }
    },
    computed: {
      ...mapGetters(['boards'])
    },
    methods: {
      ...mapActions(['createBoard', 'updateBoard','fetchBoards']),
      onSubmit() {
        if (this.action === 'create') {
          this.createBoard(this.newBoard)
        } else if (this.action === 'update') {
          const payload = {
            pk: this.board.pk,
            ...this.newBoard,
          }
          this.updateBoard(payload)
        }
      },
    },
    created() {
      this.fetchBoards()
    },
  }
</script>


<style>
/* 여기는 게시물제목 적는곳 css */
.inputBox {
  background-color: rgb(246, 242, 242);
  /* border: none; */
  border: 2px solid coral;
  outline-color: coral;
  width: 300px;
  height: 35px;
  border-radius: 10px;
}
/* 여기는 게시물 내용 적는곳 css */
#content {
  background-color: rgb(246, 242, 242);
  border: 2px solid coral;
  outline-color: coral;
  width: 450px;
  height: 300px;
  border-radius: 10px;
}



/* IE */
.select {
  width: 150px;
  height: 35px;
  background: url('https://freepikpsd.com/media/2019/10/down-arrow-icon-png-7-Transparent-Images.png') calc(100% - 5px) center no-repeat;
  background-size: 20px;
  padding: 5px 30px 5px 10px;
  border-radius: 4px;
  outline: 0 none;
}
.select option {
  background: black;
  color: #fff;
  padding: 3px 0;
}

.selectBox {
  position: relative;
  width: 150px;
  height: 35px;
  border-radius: 4px;
  border: 2px solid coral;
}
.selectBox .select {
  width: inherit;
  height: inherit;
  background: transparent;
  border: 0 none;
  outline: 0 none;
  padding: 0 5px;
  position: relative;
  z-index: 3;
}
.selectBox .select option {
  background: coral;
  color: #fff;
  padding: 3px 0;
  font-size: 16px;
}
.selectBox .icoArrow {
  position: absolute; 
  top: 0; 
  right: 0; 
  z-index: 1; 
  width: 35px; 
  height: inherit;
  border-left: 2px solid coral;
  display: flex;
  justify-content: center;
  align-items: center;
}

.selectBox .icoArrow img {
  width: 50%;
  transition: .3s;
}

.selectBox .select:focus + .icoArrow img {
  transform: rotate(180deg);
}

.create-button {
  width: 100px;
  background: coral;
  border: 2px solid coral;
  border-radius: 10px;
}
</style>