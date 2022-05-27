// =========================================================
// * Vue Material Kit - v1.2.2
// =========================================================
//
// * Product Page: https://www.creative-tim.com/product/vue-material-kit
// * Copyright 2019 Creative Tim (https://www.creative-tim.com)
// * Licensed under MIT (https://github.com/creativetimofficial/vue-material-kit/blob/master/LICENSE.md)
//
// * Coded by Creative Tim
//
// =========================================================
//
// * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

import Vue from "vue";
import App from "./App.vue";
import router from "./router/router";
import store from "./store";
import VueSwal from "vue-swal";
import VueStar from "vue-star";

import "@fortawesome/fontawesome-free/js/all.js";
Vue.component("VueStar", VueStar);
import vmodal from "vue-js-modal";
Vue.use(vmodal);
import { library } from "@fortawesome/fontawesome-svg-core";
/* 
   import specific icons 
   원하는 아이콘을 개별적으로 import
*/
import { faUserSecret } from "@fortawesome/free-solid-svg-icons";
/* import font awesome 컴포넌트 */
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";
import "bootstrap";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
import "bootstrap/dist/css/bootstrap.min.css";
import Tabs from "vue-nav-tabs/dist/vue-tabs.js";
import "vue-nav-tabs/themes/vue-tabs.css";
import MagicGrid from "vue-magic-grid";

Vue.use(MagicGrid);
Vue.use(Tabs);

// Make BootstrapVue available throughout your project
Vue.use(BootstrapVue);
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin);
Vue.use(VueSwal);

import MaterialKit from "./plugins/material-kit";
/* 위에서 import한 아이콘들을 Core library에 등록 */
library.add(faUserSecret);

/* font awesome 컴포넌트를 전역으로 등록 */
Vue.component("font-awesome-icon", FontAwesomeIcon);

Vue.config.productionTip = false;

Vue.use(MaterialKit);

const NavbarStore = {
  showNavbar: false,
};

Vue.mixin({
  data() {
    return {
      NavbarStore,
    };
  },
});

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
