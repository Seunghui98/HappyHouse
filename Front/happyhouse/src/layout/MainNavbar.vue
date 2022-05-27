<template>
  <md-toolbar
    id="toolbar"
    md-elevation="0"
    class="md-transparent md-absolute"
    :class="extraNavClasses"
    :color-on-scroll="colorOnScroll"
  >
    <div class="md-toolbar-row md-collapse-lateral">
      <div class="md-toolbar-section-start">
        <h3 class="md-title">Happy House</h3>
      </div>
      <div class="md-toolbar-section-end">
        <md-button
          class="md-just-icon md-simple md-toolbar-toggle"
          :class="{ toggled: toggledClass }"
          @click="toggleNavbarMobile()"
        >
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </md-button>

        <div class="md-collapse">
          <div class="md-collapse-wrapper">
            <mobile-menu nav-mobile-section-start="false">
              <!-- Here you can add your items from the section-start of your toolbar -->
            </mobile-menu>
            <md-list>
              <md-list-item v-if="showDownload">
                <router-link :to="{ name: 'index' }" class="link">
                  <b-icon icon="house" font-scale="1.5"></b-icon>
                  HOME
                </router-link>
              </md-list-item>

              <md-list-item v-if="showDownload">
                <router-link :to="{ name: 'noticeList' }" class="link">
                  <b-icon icon="exclamation-octagon" font-scale="1.5"></b-icon>
                  공지사항
                </router-link>
              </md-list-item>

              <md-list-item target="_blank" v-if="showDownload">
                <router-link :to="{ name: 'boardList' }" class="link">
                  <b-icon icon="chat-dots" font-scale="1.5"></b-icon>
                  자유게시판
                </router-link>
              </md-list-item>

              <md-list-item target="_blank" v-if="showDownload">
                <router-link :to="{ name: 'questionList' }" class="link">
                  <b-icon icon="question-circle" font-scale="1.5"></b-icon>
                  Q&A 게시판
                </router-link>
              </md-list-item>

              <md-list-item target="_blank" v-if="showDownload">
                <router-link :to="{ name: 'mapMenu' }" class="link">
                  <b-icon icon="coin" font-scale="1.5"></b-icon>
                  실거래가 조회
                </router-link>
              </md-list-item>

              <md-list-item target="_blank" v-if="showDownload && userInfo">
                <router-link :to="{ name: 'LikeHouse' }" class="link">
                  <b-icon icon="bookmark-heart" font-scale="1.5"></b-icon>
                  관심지역 둘러보기
                </router-link>
              </md-list-item>
              <li class="md-list-item" v-if="userInfo">
                <a
                  href="javascript:void(0)"
                  class="md-list-item-router md-list-item-container md-button-clean dropdown"
                >
                  <div class="md-list-item-content">
                    <drop-down direction="down">
                      <md-button
                        slot="title"
                        class="md-button md-button-link md-white md-simple"
                        data-toggle="dropdown"
                      >
                        <b-icon icon="person-circle" font-scale="1.5"></b-icon>
                        <p>{{ userInfo.name }}님</p>
                      </md-button>
                      <ul
                        class="dropdown-menu"
                        :class="{ 'dropdown-menu-right': responsive }"
                      >
                        <li>
                          <a href="#" class="dropdown-item">
                            <router-link to="/mypage" class="link">
                              마이페이지
                            </router-link>
                          </a>
                        </li>
                        <li>
                          <a
                            href="#"
                            class="dropdown-item"
                            @click.prevent="onClickLogout"
                          >
                            로그아웃
                          </a>
                        </li>
                      </ul>
                    </drop-down>
                  </div>
                </a>
              </li>

              <li class="md-list-item" v-else>
                <a
                  href="javascript:void(0)"
                  class="md-list-item-router md-list-item-container md-button-clean dropdown"
                >
                  <div class="md-list-item-content">
                    <drop-down direction="down">
                      <md-button
                        slot="title"
                        class="md-button md-button-link md-white md-simple"
                        data-toggle="dropdown"
                      >
                        <b-icon icon="person-circle" font-scale="1.5"></b-icon>
                        <p>로그인</p>
                      </md-button>
                      <ul
                        class="dropdown-menu"
                        :class="{ 'dropdown-menu-right': responsive }"
                      >
                        <li>
                          <a
                            class="dropdown-item link"
                            @click="$modal.show('login-modal')"
                          >
                            로그인
                          </a>
                          <!-- <a href="#" class="dropdown-item"> -->

                          <!-- <router-link
                              :to="{ name: 'userLogin' }"
                              class="link"
                            >
                              로그인
                            </router-link> -->
                          <!-- </a> -->
                        </li>
                        <li>
                          <a href="#" class="dropdown-item">
                            <router-link
                              :to="{ name: 'userRegist' }"
                              class="link"
                            >
                              회원가입
                            </router-link>
                          </a>
                        </li>
                      </ul>
                    </drop-down>
                  </div>
                </a>
              </li>
            </md-list>
          </div>
        </div>
      </div>
    </div>
    <login-modal></login-modal>
  </md-toolbar>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import LoginModal from "../views/LoginModal.vue";
const userStore = "userStore";
let resizeTimeout;
function resizeThrottler(actualResizeHandler) {
  // ignore resize events as long as an actualResizeHandler execution is in the queue
  if (!resizeTimeout) {
    resizeTimeout = setTimeout(() => {
      resizeTimeout = null;
      actualResizeHandler();

      // The actualResizeHandler will execute at a rate of 15fps
    }, 66);
  }
}

import MobileMenu from "@/layout/MobileMenu";

export default {
  components: {
    MobileMenu,
    LoginModal,
  },
  props: {
    type: {
      type: String,
      default: "white",
      validator(value) {
        return [
          "white",
          "default",
          "primary",
          "danger",
          "success",
          "warning",
          "info",
        ].includes(value);
      },
    },
    colorOnScroll: {
      type: Number,
      default: 0,
    },
  },
  data() {
    return {
      extraNavClasses: "",
      toggledClass: false,
      responsive: false,
    };
  },
  computed: {
    ...mapState(userStore, ["isLogin", "userInfo"]),
    showDownload() {
      const excludedRoutes = ["login", "landing", "profile"];
      return excludedRoutes.every((r) => r !== this.$route.name);
    },
  },
  methods: {
    ...mapMutations(userStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      // console.log("memberStore : ", ms);
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      sessionStorage.removeItem("refresh-token");
      this.$swal("로그아웃 !", { icon: "info" });
      if (this.$route.path != "/") this.$router.push({ name: "index" });
    },
    bodyClick() {
      let bodyClick = document.getElementById("bodyClick");

      if (bodyClick === null) {
        let body = document.querySelector("body");
        let elem = document.createElement("div");
        elem.setAttribute("id", "bodyClick");
        body.appendChild(elem);

        let bodyClick = document.getElementById("bodyClick");
        bodyClick.addEventListener("click", this.toggleNavbarMobile);
      } else {
        bodyClick.remove();
      }
    },
    toggleNavbarMobile() {
      this.NavbarStore.showNavbar = !this.NavbarStore.showNavbar;
      this.toggledClass = !this.toggledClass;
      this.bodyClick();
    },
    handleScroll() {
      let scrollValue =
        document.body.scrollTop || document.documentElement.scrollTop;
      let navbarColor = document.getElementById("toolbar");
      this.currentScrollValue = scrollValue;
      navbarColor.classList.remove("md-transparent");
      navbarColor.classList.add("md-white");
    },
    scrollListener() {
      resizeThrottler(this.handleScroll);
    },
    scrollToElement() {
      let element_id = document.getElementById("downloadSection");
      if (element_id) {
        element_id.scrollIntoView({ block: "end", behavior: "smooth" });
      }
    },
    onResponsiveInverted() {
      if (window.innerWidth < 500) {
        this.responsive = true;
      } else {
        this.responsive = false;
      }
    },
  },
  mounted() {
    document.addEventListener("scroll", this.scrollListener);
  },
  beforeDestroy() {
    document.removeEventListener("scroll", this.scrollListener);
  },
};
</script>
