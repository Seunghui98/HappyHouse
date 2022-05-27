<template>
  <div id="modal">
    <div class="col-12">
      <div class="d-flex">
        <div
          id="map"
          style="width: 400px; height: 400px;margin-left: 5%;margin-top: 5%;"
        ></div>
        <!-- 실거래가 -->
        <div class="bg-white mb-2" style="margin-left: 5%;width: 400px;">
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
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
const likeStore = "likeStore";
const houseStore = "houseStore";
export default {
  name: "LikeHouse",
  data() {
    return {
      dealList: [],
    };
  },
  computed: {
    ...mapState(houseStore, ["deals"]),
    ...mapState(likeStore, ["likehouse"]),
  },
  watch: {
    deals: function() {
      this.dealList = this.deals;
    },
  },
  methods: {
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(this.likehouse.lat, this.likehouse.lng),
        level: 5,
      };
      this.map = new kakao.maps.Map(container, options);
      // 마커가 표시될 위치입니다
      var markerPosition = new kakao.maps.LatLng(
        this.likehouse.lat,
        this.likehouse.lng,
      );

      // 마커를 생성합니다
      var marker = new kakao.maps.Marker({
        position: markerPosition,
      });

      // 마커가 지도 위에 표시되도록 설정합니다
      marker.setMap(this.map);

      let infoContents = `<div style="width:150px;text-align:center;padding:6px 0;">${this.likehouse.aptName}</div>`;

      let infoWindow = new kakao.maps.InfoWindow({
        content: infoContents,
      });

      // 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
      infoWindow.open(this.map, marker);
    },
  },
  mounted() {
    console.log("??");
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");

      script.addEventListener("load", () => {
        console.log("loaded", window.kakao);
        kakao.maps.load(this.initMap);
      });

      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=2868ed705319b1f4db9d256b28473d86";

      document.head.appendChild(script);
    }
  },
};
</script>

<style>
#modal {
  width: 1000px;
  height: 1000px;
}
</style>
