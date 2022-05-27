<template>
  <div class="container my-5">
    <div class="row mb-3">
      <div class="col">
        <ul class="breadcrumb bg-transparent mb-0">
          <li class="breadcrumb-item">
            <router-link to="/" class="text-decoration-none">
              Home
            </router-link>
          </li>
          <li class="breadcrumb-item active">관심 지역</li>
        </ul>
      </div>
    </div>
    <!-- 게시글 리스트 -->
    <div class="row">
      <div
        class="col-md-3 mb-5"
        v-for="(item, index) in likeHouses"
        v-bind:key="index"
      >
        <div class="card h-100">
          <div
            class="cursor-pointer hover-show position-relative"
            @click="showModal(index)"
          >
            <img src="../../assets/img/house.jpg" class="card-img-top" />
          </div>
          <div class="card-body">
            <div style="margin-left:90%;">
              <button
                type="button"
                class="btn-close btn-close"
                aria-label="Close"
                @click="deleteLike(item.aptCode)"
              ></button>
            </div>
            <div class="col-mb-2">
              <h4 class="card-title">{{ item.aptName }}</h4>
            </div>
            <div class="col-mt-2">
              <h6 class="card-text">
                주소 : {{ item.sidoname }} {{ item.gugunname }}
                {{ item.dongName }} {{ item.jibun }}
              </h6>
            </div>
            <p class="mt-2 mb-0">최근 시세: {{ item.recentPrice }}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
import LikeHouse from "./LikeHouse.vue";
const likeStore = "likeStore";
const userStore = "userStore";
const houseStore = "houseStore";
export default {
  name: "LikeList",
  computed: {
    ...mapState(userStore, ["userInfo"]),
    ...mapState(likeStore, ["likeHouses", "likehouse"]),
  },
  watch: {
    likeHouses: function() {},
  },
  created() {
    this.getUserLikeList(this.userInfo.id);
  },
  methods: {
    ...mapActions(likeStore, ["getUserLikeList", "deleteUserLikeByAptCode"]),
    ...mapMutations(likeStore, ["SET_LIKE_HOUSE"]),
    ...mapActions(houseStore, ["getDealList"]),
    showModal(index) {
      this.SET_LIKE_HOUSE(this.likeHouses[index]);
      this.getDealList(this.likeHouses[index].aptCode);
      console.log(this.likehouse);
      this.$modal.show(
        LikeHouse,
        { text: "This text is passed as a property" },
        { width: "1000px", height: "600px" },
      );
    },
    deleteLike(apt_code) {
      const likeDto = {
        user_id: this.userInfo.id,
        apt_code: apt_code,
      };
      this.deleteUserLikeByAptCode(likeDto);
    },
  },
};
</script>

<style></style>
