<template>
  <div class="wrapper">
    <parallax class="page-header header-filter" :style="headerStyle">
      <div class="md-layout">
        <div class="md-layout-item">
          <div class="image-wrapper">
            <div class="brand indextitle">
              <h1>Happy House</h1>
              <h3>Welcome To Our Happy House!</h3>
            </div>
            <div class="input-group w-100 " style="margin-top: 30px;">
              <input
                @keyup.enter="search"
                type="text"
                v-model="keyword"
                class="form-control form-control-lg"
                placeholder="원하시는 동을 입력해주세요"
                style="width: 400px;"
              />
              <button
                @click="search"
                class="btn btn-warning"
                type="button"
                style="width: 80px;"
              >
                <b-icon-search></b-icon-search>
              </button>
            </div>
          </div>
        </div>
      </div>
    </parallax>
    <div class="main main-raised">
      <div class="section section-basic">
        <div class="contain"></div>
        <div class="row g-3 my-0" style="margin-left:30px;margin-right: 30px;">
          <div class="overflow-hidden col-lg-4 col-md-12">
            <div class="border-bottom" style="display: flex;">
              <h4>뉴스</h4>
              <div style="margin-left:74%;">
                <b-button variant="light">
                  <router-link :to="{ name: 'NewsPage' }" class="link">
                    더보기
                  </router-link>
                </b-button>
              </div>
            </div>
            <div
              class="list-group col-lg-12"
              v-for="(item, index) in news"
              :key="index"
            >
              <a
                :href="item.link"
                class="list-group-item list-group-item-action"
                target="_blank"
              >
                {{ item.title }}
              </a>
            </div>
          </div>
          <div class="col-lg-4 col-md-12">
            <div class="border-bottom" style="display: flex;">
              <h4>공지사항</h4>
              <div style="margin-left:65%;">
                <b-button variant="light">
                  <router-link :to="{ name: 'notice' }" class="link">
                    더보기
                  </router-link>
                </b-button>
              </div>
            </div>
            <div
              class="list-group col-lg-12"
              v-for="(item, index) in noticeList"
              :key="index"
            >
              <div class="list-group-item list-group-item-action">
                {{ item.title }}
              </div>
            </div>
          </div>
          <div class="col-lg-4 col-md-12">
            <div class="border-bottom" style="display: flex;">
              <h4>최신 관심동</h4>
            </div>
            <div>
              <word-cloud></word-cloud>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions } from "vuex";
import WordCloud from "./WordCloud.vue";
const houseStore = "houseStore";
const newsStore = "newsStore";
const heatStore = "heatStore";
import http from "@/api/http";
export default {
  components: { WordCloud },
  name: "index",
  bodyClass: "index-page",
  props: {
    image: {
      type: String,
      default: require("@/assets/img/header-bg.jpg"),
    },
  },
  data() {
    return {
      firstname: null,
      email: null,
      password: null,
      leafShow: false,
      keyword: "",
      news: [],
      notices: [],
      noticeList: [],
    };
  },
  created() {
    this.getNews("부동산");
    http.get(`/notice`).then(({ data }) => {
      console.log(data);
      this.notices = data;
    });
  },
  watch: {
    newsList: function() {
      if (this.newsList.length > 0) {
        this.news = [];
        for (let i = 0; i < 5; i++) {
          this.news.push(this.newsList[i]);
        }
      }
    },
    notices: function() {
      if (this.notices.length > 0) {
        this.noticeList = [];
        for (let i = 0; i < 5; i++) {
          this.noticeList.push(this.notices[i]);
        }
      }
    },
  },
  methods: {
    ...mapActions(heatStore, ["registUserHeat"]),
    ...mapMutations(houseStore, ["SET_KEYWORD"]),
    ...mapActions(newsStore, ["getNews"]),
    leafActive() {
      if (window.innerWidth < 768) {
        this.leafShow = false;
      } else {
        this.leafShow = true;
      }
    },
    search() {
      if (this.keyword == "") {
        this.$swal("검색어를 입력하세요.", { icon: "warning" });
        return;
      }
      this.registUserHeat(this.keyword);
      this.SET_KEYWORD(this.keyword);

      this.$router.push("/mapmenu");
    },
  },
  computed: {
    headerStyle() {
      return {
        backgroundImage: `url(${this.image})`,
      };
    },
    signupImage() {
      return {
        backgroundImage: `url(${this.signup})`,
      };
    },
    ...mapState(newsStore, ["newsList"]),
  },
  mounted() {
    this.leafActive();
    window.addEventListener("resize", this.leafActive);
  },
  beforeDestroy() {
    window.removeEventListener("resize", this.leafActive);
  },
};
</script>

<style lang="scss">
.section-download {
  .md-button + .md-button {
    margin-left: 5px;
  }
}

.indextitle {
  font-family: "Permanent Marker", cursive;
}

@media all and (min-width: 991px) {
  .btn-container {
    display: flex;
  }
}

.carousel-item {
  height: 500px;
}

.carousel-item img {
  position: absolute;
  top: 0;
  left: 0;
  min-height: 500px;
}

.text-re {
  color: rgb(221, 221, 221);
  text-shadow: 1px 1px 2px black, 0 0 25px blue, 0 0 5px darkblue;
  /* text-shadow: 3px 3px 3px rgb(223, 223, 223); */
  font-size: 80px;
  word-spacing: -25px;
  letter-spacing: -5px;
  font-family: "Merriweather", serif;
}
.news-link {
  text-decoration: none;
  color: #333;
}
</style>
