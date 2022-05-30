<template>
  <div>
    <div id="wrapper">
      <div id="map" style="width: 100%; height: 100vh;"></div>
      <div id="searchBox" class="card">
        <div>
          <div class="p-2">
            <div class="py-1 px-2 d-flex">
              <div class="d-flex">
                <i class="fa fa-search"></i>
                <!-- <h6 class="ps-2">검색 방법을 선택하세요</h6> -->
              </div>
              <div class="form-check pe-3">
                <input
                  value="D"
                  v-model="searchType"
                  class="form-check-input"
                  type="radio"
                  id="searchByDong"
                />
                <label class="form-check-label" for="searchByDong">
                  동 검색
                </label>
              </div>
              <!-- <div class="form-check">
                <input
                  value="K"
                  v-model="searchType"
                  class="form-check-input"
                  type="radio"
                  id="searchByKeyword"
                />
                <label class="form-check-label" for="searchByKeyword">
                  키워드 검색
                </label>
              </div> -->
            </div>
          </div>
          <div
            v-if="searchType == 'D'"
            class="pb-2 d-flex justify-content-evenly"
          >
            <div class="col-lg-4 col-md-6 col-sm-6">
              <fieldset>
                <select
                  v-model="sidoCode"
                  @change="gugunList"
                  class="array-select form-control form-select"
                  aria-label="example"
                >
                  <option
                    v-for="(item, index) in sidos"
                    :key="index"
                    :value="item.value"
                  >
                    {{ item.text }}
                  </option>
                </select>
              </fieldset>
            </div>
            <div class="col-lg-4 col-md-6 col-sm-6">
              <fieldset>
                <select
                  v-model="gugunCode"
                  @change="dongList"
                  class="array-select form-control form-select"
                  aria-label="example"
                >
                  <option
                    v-for="(item, index) in guguns"
                    :key="index"
                    :value="item.value"
                  >
                    {{ item.text }}
                  </option>
                </select>
              </fieldset>
            </div>
            <div class="col-lg-4 col-md-6 col-sm-6">
              <fieldset>
                <select
                  v-model="dongCode"
                  class="array-select form-control form-select"
                  aria-label="example"
                  @change="getAptsByDongCode"
                >
                  <option
                    v-for="(item, index) in dongs"
                    :key="index"
                    :value="item.value"
                  >
                    {{ item.text }}
                  </option>
                </select>
              </fieldset>
            </div>
          </div>
          <div v-if="searchType == 'K'" class="input-group pb-2 px-3">
            <input
              @keyup.enter="onKeywordSearch"
              type="text"
              v-model="inputKeyword"
              class="form-control d-inline-block"
              placeholder="건물명 또는 동을 입력하세요"
            />
            <button
              @click="onKeywordSearch"
              class="btn btn-primary d-inline-block"
              type="button"
              style="width:80px"
            >
              <b-icon-search></b-icon-search>
            </button>
          </div>
        </div>
      </div>
      <side-bar></side-bar>
    </div>
  </div>
</template>

<script>
import dotenv from "dotenv";
import SideBar from "@/components/mapmenu/SideBar.vue";
import { mapState, mapActions, mapMutations } from "vuex";
const houseStore = "houseStore";
const userStore = "userStore";
const likeStore = "likeStore";
const heatStore = "heatStore";
dotenv.config();
export default {
  name: "kakaoMap",
  components: {
    SideBar,
  },
  data() {
    return {
      map: null,
      markers: [],
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
      latitude: 0,
      longitude: 0,
      searchType: "D",
      selectGuName: "default",
      selectDongName: "default",
      inputKeyword: "",
      eventFrom: "",
      // curInfoWindow: {},
      infoWindows: [],
      curInfoWindow: null,
      index: -1,
      dealInfo: [],
      reviewList: [],
      ongoingList: [],
      ongoingCount: 0,
      heart: true,
      reviewInsertModal: null,

      prevRoute: null,
    };
  },
  computed: {
    ...mapState(houseStore, [
      "sidos",
      "guguns",
      "dongs",
      "houses",
      "searchKeyword",
      "listVisible",
    ]),
    ...mapState(userStore, ["userInfo"]),
  },
  created() {
    // 모든 Apts 정보 가져오기

    if (this.searchKeyword !== null) {
      this.getApts();
    }
    this.getSido();
  },

  watch: {
    houses: function() {
      if (this.houses.length > 0) {
        this.displayMarker(this.houses);
      }
    },
  },

  methods: {
    ...mapActions(houseStore, [
      "getSido",
      "getGugun",
      "getDong",
      "getHouseList",
      "getHouseListByDongName",
      "getHouseListByDongCode",
      "getDealList",
    ]),
    ...mapMutations(houseStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
      "SET_LIST_VISIBLE",
      "SET_HOUSE",
    ]),
    ...mapActions(heatStore, ["registUserHeat"]),
    ...mapMutations(likeStore, ["SET_LIKE"]),
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.50149403497245, 127.03962751156699),
        level: 5,
      };
      this.map = new kakao.maps.Map(container, options);
      if (this.houses.length > 0) {
        this.displayMarker(this.houses);
      }
    },
    gugunList() {
      console.log(this.sidoCode);
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },

    dongList() {
      console.log(this.gugunCode);
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      if (this.gugunCode) this.getDong(this.gugunCode);
    },

    getApts() {
      console.log(this.searchKeyword);
      this.getHouseListByDongName(this.searchKeyword);
    },

    getAptsByDongCode() {
      if (this.dongCode !== null) {
        var dongName = "";
        for (let i = 0; i < this.dongs.length; i++) {
          if (this.dongs[i].value === this.dongCode) {
            dongName = this.dongs[i].text;
            break;
          }
        }

        this.registUserHeat(dongName);
        this.getHouseListByDongCode(this.dongCode);
      }
    },

    displayMarker(list) {
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
        this.markers = [];
      }
      console.log(list);
      var positions = [];

      for (var i = 0; i < list.length; i++) {
        positions.push({
          title: list[i].aptName,
          latlng: new kakao.maps.LatLng(list[i].lat, list[i].lng),
          aptCode: list[i].aptCode,
        });
      }

      var imageSrc = require("../../assets/img/marker.png"), // 마커이미지의 주소입니다
        imageSize = new kakao.maps.Size(24, 35), // 마커이미지의 크기입니다
        imageOption = { offset: new kakao.maps.Point(20, 35) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
      var markerImage = new kakao.maps.MarkerImage(
        imageSrc,
        imageSize,
        imageOption,
      );
      positions.forEach((position) => {
        const marker = new kakao.maps.Marker({
          map: this.map,
          position: position.latlng,
          title: position.title,
          img: markerImage,
          aptCode: position.aptCode,
        });
        this.markers.push(marker);
      });

      // 4. 지도를 이동시켜주기
      const bounds = positions.reduce(
        (bounds, position) => bounds.extend(position.latlng),
        new kakao.maps.LatLngBounds(),
      );

      this.addInfoWindow();
      this.map.setBounds(bounds);
    },
    addInfoWindow() {
      this.markers.forEach((marker, index) => {
        let item = this.houses[index];
        let infoContents = `<div style="width:150px;text-align:center;padding:6px 0;">${item.aptName}</div>`;

        let infoWindow = new kakao.maps.InfoWindow({
          content: infoContents,
        });

        let $this = this;
        kakao.maps.event.addListener(marker, "mouseover", function() {
          infoWindow.open($this.map, marker);
        });
        kakao.maps.event.addListener(marker, "mouseout", function() {
          infoWindow.close();
        });

        kakao.maps.event.addListener(marker, "click", function() {
          $this.SET_LIST_VISIBLE(true);
          $this.showHouseDetail(index);
          console.log($this.listVisible);
        });
      });
    },

    showHouseDetail(index) {
      this.index = index;
      this.SET_HOUSE(this.houses[index]);
      const aptCode = this.houses[index].aptCode;
      this.getDealList(aptCode);
    },
  },

  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");

      script.addEventListener("load", () => {
        console.log("loaded", window.kakao);
        kakao.maps.load(this.initMap);
      });

      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=";

      document.head.appendChild(script);
    }
  },
};
</script>

<style scoped>
/* .test {
  margin-left: 300px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100vh;
} */

#wrapper {
  position: relative;
}
#searchBox {
  position: absolute;

  top: 100px;
  /* bottom : 10px; */
  left: 20px;

  width: 400px;
  /* height: 400px; */
  padding: 10px;

  z-index: 100;
  /* background-color:rgba(255, 244, 244, 0.8); */
  /* opacity: 0.5; */
  background-color: rgba(255, 255, 255, 0.7);

  overflow-y: auto;
}
</style>
