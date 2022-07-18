<template>
  <div>로그인시 보여지는 화면!</div>
  <WebterviewMid/>
  <WebterviewSidebar/>
</template>

<script>
import WebterviewMid from '@/components/WebterviewMid.vue'
import WebterviewSidebar from '@/components/WebterviewSidebar.vue'
export default {
    name: 'WebterviewView',
    // 이부분 components no use 에러떠서 주석처리해놓음
    components: { WebterviewMid, WebterviewSidebar  },
    data() {
        return {

        }
    },
    computed: {

    },
    methods: {

    }

}
</script>

<style>

</style>