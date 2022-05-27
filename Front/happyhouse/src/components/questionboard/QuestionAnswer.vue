<template>
  <div class="root">
    <div v-if="answer !== null">
      <h2 class="bold center" style="color:#3aac5d;">답변</h2>
      <b-row class="mb-1">
        <b-col>
          <b-card
            :header-html="
              `<div><h3>관리자</h3></div><div>${answer.writedate}</h6></div>`
            "
            class="mb-2"
            border-variant="dark"
            no-body
          >
            <b-card-body class="text-left">
              <div v-html="answer.content"></div>
            </b-card-body>
          </b-card>
        </b-col>
      </b-row>
    </div>
    <div v-else>
      <div class="wrapper-main" v-if="userInfo.id === `admin`">
        <div class="left-align">
          <h2 class="bold center" style="color:#3aac5d;">답변 작성</h2>
          <b-container fluid>
            <b-row>
              <b-col sm="2">
                <label for="textarea-auto-height">내용</label>
              </b-col>
              <b-col sm="10">
                <b-form-textarea
                  id="textarea-auto-height"
                  placeholder="답변 내용을 작성해주세요..."
                  rows="3"
                  max-rows="8"
                  v-model="answer.content"
                ></b-form-textarea>
              </b-col>
            </b-row>
          </b-container>
          <b-button
            variant="outline-primary"
            style="margin-left:93%;margin-top: 1%;"
            @click="registAnswer"
          >
            등록
          </b-button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import axios from "axios";
import { mapState } from "vuex";
const userStore = "userStore";
export default {
  props: ["answer"],
  data() {
    return {
      // answer: {
      //   userid: "",
      //   content: "",
      //   writedate: "",
      //   question_no: 0,
      // },
      isAnswer: false,
    };
  },

  computed: {
    ...mapState(userStore, ["userInfo"]),
    message() {
      if (this.answer.content)
        return this.answer.content.split("\n").join("<br>");
      return "";
    },
  },
  methods: {
    registAnswer() {
      this.question.userid = this.userInfo.id;
      const answer = {
        content: this.answer.content,
        question_no: this.question.no,
      };
      const API_URL = `http://localhost/qna/answer`;
      axios({
        url: API_URL,
        method: "POST",
        params: answer,
      }).then(({ data }) => {
        let msg = "등록 처리시 문제가 발생했습니다.";
        if (data === "success") {
          msg = "등록이 완료되었습니다.";
        }
        alert(msg);
        this.moveList();
      });
    },
    getAnswer() {},
  },
  filters: {
    dateFormat(writedate) {
      return moment(new Date(writedate)).format("YY.MM.DD");
    },
  },
};
</script>
<style scoped></style>
