import { getNewsList } from "@/api/news.js";

const newsStore = {
  namespaced: true,
  state: {
    newsList: [],
  },

  mutations: {
    SET_NEWS_LIST: (state, list) => {
      state.newsList = list;
    },
  },
  actions: {
    getNews({ commit }, keyword) {
      getNewsList(
        keyword,
        ({ data }) => {
          console.log("~~");
          console.log(data);
          commit("SET_NEWS_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
  },
};

export default newsStore;
