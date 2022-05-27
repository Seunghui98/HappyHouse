import {
  registComment,
  deleteComment,
  getCommentList,
  modifyComment,
} from "@/api/comment.js";

const commentStore = {
  namespaced: true,
  state: {
    commentList: [],
    comment: null,
  },

  mutations: {
    SET_COMMENT_LIST: (state, list) => {
      state.commentList = list;
    },
    SET_COMMENT: (state, comment) => {
      state.comment = comment;
    },
  },

  actions: {
    getUserCommentList({ commit }, Board_no) {
      getCommentList(
        Board_no,
        ({ data }) => {
          console.log("~~");
          console.log(data);
          commit("SET_COMMENT_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    registUserComment({ commit }, comment) {
      console.log(comment);
      registComment(
        comment,
        (response) => {
          dispatch("getUserCommentList", comment.Board_no);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    deleteUserComment({ commit }, comment_no) {
      deleteComment(
        comment_no,
        (response) => {},
        (error) => {
          console.log(error);
        },
      );
    },

    modifyUserComment({ commit }, comment) {
      modifyComment(
        comment,
        (response) => {
          if (response.data.message === "success") {
            if (response.data.message === "success") {
              dispatch("getUserCommentList", comment.Board_no);
            }
          }
        },
        () => {},
      );
    },
  },
};

export default commentStore;
