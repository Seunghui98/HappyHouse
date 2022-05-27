<template>
  <div class="container">
    <div
      class="md-layout-item md-size-66 md-xsmall-size-100 ml-auto mr-auto text-center"
    >
      <h2>Q&A 게시판 - 질문글</h2>
    </div>
    <b-container class="bv-example-row mt-3">
      <b-row class="mb-1">
        <b-col class="text-left">
          <b-button variant="outline-primary" @click="listArticle">
            목록
          </b-button>
        </b-col>
        <b-col
          class="text-right"
          v-if="userInfo && userInfo.id === question.userid"
        >
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
              `<h3>${question.no}.
          ${question.title} </h3><div><h6>${question.userid}</div><div>${question.writedate}</h6></div>`
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

      <div class="root">
        <div v-if="isAnswer">
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
          <div
            class="wrapper-main"
            v-if="userInfo !== null && userInfo.id === `admin`"
          >
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
                      v-model="content"
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
    </b-container>
  </div>
</template>

<script>
import axios from "axios";
import { mapState } from "vuex";
const userStore = "userStore";
import http from "@/api/http";
import QuestionAnswer from "./QuestionAnswer.vue";
export default {
  name: "questionDetail",
  data() {
    return {
      question: {},
      user: {},
      content: "",
      isAnswer: false,
    };
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
    message() {
      if (this.question.content)
        return this.question.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    console.log(this.$route.params.no);
    http.get(`/qna/question/${this.$route.params.no}`).then(({ data }) => {
      this.question = data;
    });

    const API_URL = `http://localhost/qna/answer/${this.$route.params.no}`;
    axios({
      url: API_URL,
      method: "GET",
    }).then(({ data }) => {
      // this.answer.content = data.content;
      // this.answer.question_no = data.question_no;
      // this.answer.writetime = data.createtime;
      console.log(data);
      if (data.content !== null) {
        this.isAnswer = true;
        const ans = {
          content: data.content,
          writedate: data.writedate,
          question_no: data.no,
        };
        this.answer = ans;
      } else {
        this.isAnswer = false;
      }
    });
  },
  methods: {
    listArticle() {
      this.$router.push({ name: "questionList" });
    },
    moveModifyArticle() {
      this.$router.replace({
        name: "questionModify",
        params: { no: this.$route.params.no },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    deleteArticle() {
      if (confirm("정말로 삭제?")) {
        this.$router.replace({
          name: "questionDelete",
          params: { no: this.question.no },
        });
      }
    },
    registAnswer() {
      this.question.userid = this.userInfo.id;
      const answer = {
        content: this.content,
        question_no: this.$route.params.no,
      };
      const API_URL = `http://localhost/qna/answer`;
      axios({
        url: API_URL,
        method: "POST",
        params: answer,
      }).then(({ data }) => {
        if (data === "success") {
          this.$swal("답변 등록이 완료 !", { icon: "success" });
          this.$router.push({
            name: "questionList",
          });
          return;
        } else {
          this.$swal("답변 수정 실패 ㅠㅠ", { icon: "warning" });
          return;
        }
      });
    },
  },
};
</script>

<style></style>
