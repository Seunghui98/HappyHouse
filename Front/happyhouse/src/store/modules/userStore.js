import jwt_decode from "jwt-decode";
import {
  login,
  findById,
  modifyUserInfo,
  modifyUserPassword,
  deleteUser,
  registUserInfo,
} from "@/api/user.js";

const userStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    isUpdateError: false,
    isRegistError: false,
    isDeleteError: false,
    userInfo: null,
  },

  getters: {
    checkUserInfo: function(state) {
      return state.userInfo;
    },
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      console.log(state.isLoginError);
      state.isLoginError = isLoginError;
    },
    SET_IS_UPDATE_ERROR: (state, isUpdateError) => {
      console.log(state.isUpdateError);
      state.isUpdateError = isUpdateError;
    },
    SET_IS_REGSIT_ERROR: (state, isRegistError) => {
      console.log(state.isRegistError);
      state.isRegistError = isRegistError;
    },
    SET_IS_DELETE_ERROR: (state, isDeleteError) => {
      console.log(state.isDeleteError);
      state.isDeleteError = isDeleteError;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
    SET_USER_PASSWORD: (state, password) => {
      state.userInfo.password = password;
    },
  },
  actions: {
    async userConfirm({ commit }, user) {
      console.log(user);
      await login(
        user,
        (response) => {
          if (response.data.message === "success") {
            let token = response.data["access-token"];
            let refToken = response.data["refresh-token"]; //refresh-token 정보
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            sessionStorage.setItem("access-token", token);
            sessionStorage.setItem("refresh-token", refToken);
          } else {
            console.log("??");
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
          }
        },
        () => {},
      );
    },
    getUserInfo({ commit }, token) {
      let decode_token = jwt_decode(token);
      findById(
        decode_token.userid,
        (response) => {
          if (response.data.message === "success") {
            commit("SET_USER_INFO", response.data.userInfo);
          } else {
            console.log("유저 정보 없음!!");
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
    modifyInfo({ commit }, user) {
      modifyUserInfo(
        user,
        (response) => {
          if (response.data.message === "success") {
            commit("SET_IS_UPDATE_ERROR", false);
            user.password = state.userInfo.password;
            commit("SET_USER_INFO", user);
            console.log(user);
          } else {
            console.log("??");
            commit("SET_IS_UPDATE_ERROR", true);
          }
        },
        () => {},
      );
    },
    registUser({ commit }, user) {
      registUserInfo(
        user,
        (response) => {
          if (response.data.message === "success") {
            commit("SET_IS_REGSIT_ERROR", false);
          } else {
            commit("SET_IS_REGSIT_ERROR", true);
          }
        },
        () => {},
      );
    },
    changePassword({ commit }, user) {
      modifyUserPassword(
        user,
        (response) => {
          if (response.data.message === "success") {
            commit("SET_USER_PASSWORD", user.password);
            commit("SET_IS_UPDATE_ERROR", false);
          } else {
            console.log("??");
            commit("SET_IS_UPDATE_ERROR", true);
          }
        },
        () => {},
      );
    },
    deleteUserInfo({ commit }, id) {
      deleteUser(
        id,
        (response) => {
          if (response.data.message === "success") {
            commit("SET_USER_INFO", null);
            commit("SET_IS_DELETE_ERROR", false);
          } else {
            commit("SET_IS_DELETE_ERROR", true);
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
  },
};

export default userStore;
