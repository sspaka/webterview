import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap";
import VueGridLayout from "vue3-grid-layout";
import Camera from "simple-vue-camera";
import AOS from 'aos';
import "aos/dist/aos.css";
// import VueMoment from "vue-moment";

// css
import "@/views/login/vendor/bootstrap/css/bootstrap.min.css";
import "@/views/login/fonts/font-awesome-4.7.0/css/font-awesome.min.css";
import "@/views/login/vendor/animate/animate.css";
import "@/views/login/vendor/css-hamburgers/hamburgers.min.css";
import "@/views/login/vendor/select2/select2.min.css";
import "@/views/login/css/main.css";
import "@/views/login/css/util.css";


createApp(App)
  .use(store)
  .use(router)
  .use(VueGridLayout) 
  .use("camera", Camera)
  .use(AOS)
  .mount("#app");

createApp.use(require("vue-moment"));

// const app = createApp(App)
// app.use(BootstrapVue3)
// app.use(router)
// app.mount('#app')
