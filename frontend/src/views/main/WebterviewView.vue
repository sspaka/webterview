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