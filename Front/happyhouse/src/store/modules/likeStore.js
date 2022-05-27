import {
  registLike,
  deleteLike,
  getLikeHouseList,
  getLike,
  deleteLikeByAptCode,
} from "@/api/like.js";

const likeStore = {
  namespaced: true,
  state: {
    likeList: [],
    likeHouses: [],
    likehouse: null,
    like: null,
  },

  mutations: {
    SET_LIKE_LIST: (state, list) => {
      state.likeList = list;
    },
    SET_LIKE: (state, like) => {
      state.like = like;
    },
    SET_LIKE_HOUSE: (state, house) => {
      state.likehouse = house;
    },
    SET_LIKE_HOUSE_LIST: (state, houses) => {
      state.likeHouses = houses;
    },
  },
  actions: {
    getUserLikeList({ commit }, user_id) {
      getLikeHouseList(
        user_id,
        ({ data }) => {
          console.log("~~");
          console.log(data);
          commit("SET_LIKE_HOUSE_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getUserLike({ commit }, params) {
      getLike(
        params,
        ({ data }) => {
          console.log("~~");
          console.log(data);
          if (data !== "") {
            commit("SET_LIKE", data);
          } else {
            commit("SET_LIKE", null);
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
    registLike({ commit, dispatch }, likeDto) {
      console.log(likeDto);
      registLike(
        likeDto,
        (response) => {
          if (response.data.message === "success") {
            dispatch("getUserLike", likeDto);
            //getLike(likeDto);
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
    deleteLike({ commit, dispatch }, likeDto) {
      console.log(likeDto);
      deleteLike(
        likeDto.no,
        (response) => {
          if (response.data.message === "success") {
            dispatch("getUserLike", likeDto);
            //getLike(likeDto);
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
    deleteUserLikeByAptCode({ commit, dispatch }, likeDto) {
      console.log(likeDto);
      deleteLikeByAptCode(
        likeDto,
        (response) => {
          if (response.data.message === "success") {
            dispatch("getUserLikeList", likeDto.user_id);
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
  },
};

export default likeStore;
