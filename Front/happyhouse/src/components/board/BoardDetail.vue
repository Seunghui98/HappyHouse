<template>
  <div class="container">
    <div
      class="md-layout-item md-size-66 md-xsmall-size-100 ml-auto mr-auto text-center"
    >
      <h2>자유게시판</h2>
    </div>
    <b-container class="bv-example-row mt-3">
      <b-row class="mb-1">
        <b-col class="text-left">
          <b-button variant="outline-primary" @click="listArticle">
            목록
          </b-button>
        </b-col>
        <b-col class="text-right" v-if="userInfo && userInfo.id === board.id">
          <b-button
            variant="outline-info"
            size="sm"
            @click="moveModifyArticle"
            class="mr-2"
          >
            수정
          </b-button>
          <b-button variant="outline-danger" size="sm" @click="deleteArticle">
            삭제
          </b-button>
        </b-col>
      </b-row>
      <b-row class="mb-1">
        <b-col>
          <b-card
            :header-html="
              `
          ${board.title} </h3><div><h6>${board.id}</div><div>${board.createtime}</h6></div>`
            "
            class="mb-2"
            border-variant="dark"
            no-body
          >
            <b-card-body class="text-left">
              <div v-html="message"></div>
            </b-card-body>
          </b-card>
        </b-col>
      </b-row>

      <div>
        <div class="wrapper-main">
          <div class="left-align">
            <div v-for="(item, index) in commentList" v-bind:key="index">
              <b-card :title="item.user_id" :sub-title="item.comment_time">
                <b-card-text>
                  {{ item.content }}
                </b-card-text>
                <div v-if="userInfo && userInfo.id === item.user_id">
                  <a class="card-link" @click="deleteComment(item.comment_no)">
                    삭제
                  </a>
                  <b-link class="card-link" @click="showInput(index)">
                    수정
                  </b-link>
                </div>
              </b-card>
              <div v-if="show_idx == index">
                <b-form-textarea
                  id="textarea"
                  v-model="updatecomment"
                  placeholder="댓글을 입력하세요."
                  rows="3"
                  max-rows="6"
                ></b-form-textarea>
                <b-row>
                  <b-col class="mt-1" align="right">
                    <b-button
                      variant="warning"
                      @click="updateComment(item.comment_no)"
                    >
                      수정
                    </b-button>
                  </b-col>
                </b-row>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div v-if="userInfo">
        <b-form-textarea
          id="textarea"
          v-model="newcomment"
          placeholder="댓글을 입력하세요."
          rows="3"
          max-rows="6"
        ></b-form-textarea>
        <b-row>
          <b-col class="mt-1" align="right">
            <b-button variant="warning" @click="writeComment">등록</b-button>
          </b-col>
        </b-row>
      </div>
    </b-container>
  </div>
</template>

<script>
import http from "@/api/http";
const userStore = "userStore";
import { mapState, mapActions } from "vuex";
const commentStore = "commentStore";
export default {
  name: "boardDetail",
  data() {
    return {
      board: {},
      comment: {},
      comments: {},
      newcomment: "",
      updatecomment: "",
      show_idx: -1,
      board_no: this.$route.params.num,
    };
  },
  watch: {
    commentList: function() {},
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
    ...mapState(commentStore, ["commentList"]),
    message() {
      if (this.board.content)
        return this.board.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    this.getUserCommentList(this.$route.params.num);
    http.get(`/board/${this.$route.params.num}`).then(({ data }) => {
      console.log(data);
      this.board = data;
    });
  },
  mounted() {},
  methods: {
    ...mapActions(commentStore, [
      "getUserCommentList",
      "registUserComment",
      "deleteUserComment",
      "modifyUserComment",
    ]),
    listArticle() {
      this.$router.push({ name: "boardList" });
    },
    moveModifyArticle() {
      this.$router.replace({
        name: "boardModify",
        params: { num: this.$route.params.num },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    deleteArticle() {
      if (confirm("정말로 삭제?")) {
        this.$router.replace({
          name: "boardDelete",
          params: { num: this.board.num },
        });
      }
    },
    deleteComment(comment_no) {
      console.log(comment_no);
      this.deleteUserComment(comment_no);
      this.$router.go();
    },
    showInput(idx) {
      this.show_idx = idx;
    },
    writeComment() {
      const comment = {
        user_id: this.userInfo.id,
        Board_no: this.board_no,
        content: this.newcomment,
      };
      console.log(comment);
      this.registUserComment(comment);
      this.$router.go();
    },
    updateComment(comment_no) {
      const comment = {
        comment_no: comment_no,
        user_id: this.userInfo.id,
        Board_no: this.board_no,
        content: this.updatecomment,
      };
      this.modifyUserComment(comment);
      this.$router.go();
    },
  },
};
</script>

<style></style>
