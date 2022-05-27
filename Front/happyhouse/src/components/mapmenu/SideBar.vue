<template>
  <div v-if="listVisible" id="showList" class="card p-0 bg-warning">
    <!-- 아파트 정보 요약 -->
    <div class="bg-white mb-2">
      <div
        class="p-3 border-bottom d-flex justify-content-between align-items-center"
      >
        <h4 class="m-0" v-if="houseDetail !== null">
          {{ houseDetail.aptName }}
        </h4>
        <!-- <HeartBtn
          v-if="isAuth && level == 2"
          class="px-1"
          :enabled="houseList[curIndex].bookmark"
          @changeHeartBtn="onBookmarkHouse"
        /> -->
        <vue-star
          animate="animated rubberBand"
          color="#F05654"
          style="margin-left:75%;"
          v-if="userInfo !== null"
        >
          <img
            slot="icon"
            v-if="heart == false"
            class="heartst"
            @click="handleClick"
            src="../../assets/img/unheart.png"
          />

          <img
            slot="icon"
            v-else
            @click="handleClick"
            class="heartst"
            src="../../assets/img/heart.png"
          />
        </vue-star>
      </div>

      <div class="px-3 border-bottom">
        <div class="border-bottom d-flex py-2">
          <div class="text-secondary w-25" v-if="houseDetail !== null">
            주소
          </div>
          <div>
            {{ houseDetail.sidoName }} {{ houseDetail.gugunName }}
            {{ houseDetail.dongName }} {{ houseDetail.jibun }}
          </div>
        </div>
        <div class="border-bottom d-flex py-2">
          <div class="text-secondary w-25 " v-if="houseDetail !== null">
            건축년도
          </div>
          <div>{{ houseDetail.buildYear }}</div>
        </div>
        <div class="d-flex py-2">
          <div class="text-secondary w-25" v-if="houseDetail !== null">
            최근 가격
          </div>
          <div>{{ houseDetail.recentPrice }} 만원</div>
        </div>
      </div>
    </div>

    <!-- 실거래가 -->
    <div class="bg-white mb-2">
      <div class="border-bottom"><h5 class="p-3 m-0">실거래가</h5></div>
      <div>
        <table class="w-100">
          <thead class="bg-dark text-white">
            <tr>
              <td class="ps-3 py-1">거래일</td>
              <td>거래가격</td>
              <td>면적</td>
              <td>층수</td>
            </tr>
          </thead>
          <tbody class="px-2" v-if="dealList.length > 0">
            <tr
              v-for="(deal, index) in dealList"
              :key="index"
              class="border-bottom"
            >
              <td class="ps-3 py-2">{{ deal.dealYear }}</td>
              <td>{{ deal.dealAmount }} 만원</td>
              <td>{{ deal.area }}</td>
              <td>{{ deal.floor }} 층</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
const houseStore = "houseStore";
const userStore = "userStore";
const likeStore = "likeStore";
export default {
  name: "sideBar",
  data() {
    return {
      // curInfoWindow: {},
      infoWindows: [],
      curInfoWindow: null,
      index: -1,
      dealList: [],
      reviewList: [],
      ongoingList: [],
      ongoingCount: 0,
      reviewInsertModal: null,
      houseDetail: null,
      heart: false,
      prevRoute: null,
    };
  },
  computed: {
    ...mapState(houseStore, ["house", "houses", "listVisible", "deals"]),
    ...mapState(userStore, ["userInfo"]),
    ...mapState(likeStore, ["likeList", "like"]),
  },
  watch: {
    listVisible: function() {
      if (this.houses.length > 0) {
      }
    },
    deals: function() {
      this.dealList = this.deals;
    },
    house: function() {
      this.houseDetail = this.house;
      if (this.house !== null && this.userInfo !== null) {
        const params = {
          apt_code: this.house.aptCode,
          user_id: this.userInfo.id,
        };
        console.log(params);
        this.getUserLike(params);
      }
    },
    like: function() {
      console.log(this.like);
      if (this.like === null) {
        this.heart = false;
      } else {
        this.heart = true;
      }
    },
  },
  methods: {
    ...mapActions(likeStore, [
      "getUserLikeList",
      "registLike",
      "deleteLike",
      "getUserLike",
    ]),
    handleClick() {
      console.log("??");
      if (this.heart) {
        this.heart = false;
        const likeDto = {
          no: this.like.no,
          user_id: this.userInfo.id,
          user_apt_code: this.house.aptCode,
        };
        console.log(likeDto);
        this.deleteLike(likeDto);
      } else {
        this.heart = true;
        const likeDto = {
          user_id: this.userInfo.id,
          apt_code: this.house.aptCode,
        };
        console.log(likeDto);
        this.registLike(likeDto);
      }
      console.log(this.heart);
    },
  },
};
</script>

<style>
#showList {
  position: absolute;

  top: 100px;
  bottom: 20px;
  right: 20px;

  width: 400px;
  padding: 10px;

  z-index: 100;
  /* background-color:rgba(255, 244, 244, 0.8); */
  /* opacity: 0.5; */

  overflow-y: auto;
}
.bi-plus-circle {
  font-size: 1.5rem;
}
.bi-plus-circle:hover {
  color: dodgerblue;
}

.heartst {
  width: 30px;
  height: 30px;
}
</style>
