import {
  sidoList,
  gugunList,
  dongList,
  houseList,
  dealList,
  houseListBydongName,
  houseListBydongCode,
} from "@/api/house.js";

const houseStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "시 선택" }],
    guguns: [{ value: null, text: "구군 선택" }],
    dongs: [{ value: null, text: "동 선택" }],
    houses: [],
    deals: [],
    house: null,
    listVisible: false,
    searchKeyword: null,
  },

  getters: {},

  mutations: {
    SET_SIDO_LIST: (state, sidos) => {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST: (state, guguns) => {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    SET_DONG_LIST: (state, dongs) => {
      dongs.forEach((dong) => {
        state.dongs.push({ value: dong.dongCode, text: dong.dongName });
      });
    },
    CLEAR_SIDO_LIST: (state) => {
      state.sidos = [{ value: null, text: "시 선택" }];
    },
    CLEAR_GUGUN_LIST: (state) => {
      state.guguns = [{ value: null, text: "구군 선택" }];
    },
    CLEAR_DONG_LIST: (state) => {
      state.dongs = [{ value: null, text: "동 선택" }];
    },
    SET_HOUSE_LIST: (state, houses) => {
      state.houses = houses;
    },
    SET_DETAIL_HOUSE: (state, house) => {
      state.house = house;
    },
    SET_KEYWORD(state, keyword) {
      state.searchKeyword = keyword;
    },
    SET_LIST_VISIBLE(state, visible) {
      console.log(visible);
      state.listVisible = visible;
    },
    SET_HOUSE: (state, house) => {
      state.house = house;
    },
    SET_DEAL_LIST: (state, deals) => {
      state.deals = deals;
    },
  },

  actions: {
    getSido: ({ commit }) => {
      sidoList(
        ({ data }) => {
          // console.log(data);
          commit("SET_SIDO_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getGugun: ({ commit }, sidoCode) => {
      const params = {
        sido: sidoCode,
      };
      gugunList(
        params,
        ({ data }) => {
          // console.log(commit, response);
          commit("SET_GUGUN_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getDong: ({ commit }, gugunCode) => {
      const params = {
        gugun: gugunCode,
      };
      dongList(
        params,
        ({ data }) => {
          // console.log(commit, response);
          commit("SET_DONG_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getHouseList: ({ commit }) => {
      houseList(
        ({ data }) => {
          console.log("~~");
          console.log(data);
          commit("SET_HOUSE_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },

    getDealList: ({ commit }, aptCode) => {
      const params = {
        aptCode: aptCode,
      };
      dealList(
        params,
        ({ data }) => {
          // console.log(commit, response);
          commit("SET_DEAL_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },

    getHouseListByDongName: ({ commit }, dongName) => {
      const params = {
        dongName: dongName,
      };
      houseListBydongName(
        params,
        ({ data }) => {
          console.log("~~");
          console.log(data);
          commit("SET_HOUSE_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },

    getHouseListByDongCode: ({ commit }, dongCode) => {
      const params = {
        dong: dongCode,
      };
      houseListBydongCode(
        params,
        ({ data }) => {
          console.log("~~");
          console.log(data);
          commit("SET_LIST_VISIBLE", false);
          commit("SET_HOUSE_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },

    detailHouse: ({ commit }, house) => {
      // 나중에 house.일련번호를 이용하여 API 호출
      commit("SET_DETAIL_HOUSE", house);
    },
  },
  getters: {
    getHouse: function(state) {
      return state.house;
    },
    getDealList: function(state) {
      return state.dealList;
    },
  },
};

export default houseStore;
