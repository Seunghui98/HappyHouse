import { registHeat, getHeatList } from "@/api/heat.js";

const heatStore = {
  namespaced: true,
  state: {
    heatList: [],
  },

  mutations: {
    SET_HEAT_LIST: (state, list) => {
      state.heatList = list;
    },
  },
  actions: {
    getUserHeatList({ commit }) {
      getHeatList(
        ({ data }) => {
          console.log("~~");
          console.log(data);
          commit("SET_HEAT_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },

    registUserHeat({ commit }, dongName) {
      registHeat(
        dongName,
        (response) => {
          if (response.data.message === "success") {
            getHeatList();
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
  },
};

export default heatStore;
