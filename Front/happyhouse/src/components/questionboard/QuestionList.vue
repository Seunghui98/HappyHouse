<template>
  <div class="container">
    <div
      class="md-layout-item md-size-66 md-xsmall-size-100 ml-auto mr-auto text-center"
    >
      <h2>Q&A 게시판</h2>
    </div>

    <br />
    <br />
    <!-- 부트스트랩뷰로 테이블 만들기 -->
    <b-table-simple hover responsive>
      <b-thead head-variant="dark">
        <b-tr>
          <b-th>번호</b-th>
          <b-th>제목</b-th>
          <b-th>작성자</b-th>
          <b-th>작성일</b-th>
          <b-th>답변상태</b-th>
        </b-tr>
      </b-thead>
      <tbody>
        <!-- 하위 component인 ListRow에 데이터 전달(props) -->
        <question-list-item
          v-for="question in questions"
          :key="question.no"
          v-bind="question"
        />
      </tbody>
    </b-table-simple>

    <pagi-nation style="width:100px;margin: 0 auto;"></pagi-nation>
    <br />
    <b-button
      @click="moveCreate()"
      style="width:100px;margin: 0 auto;"
      v-if="userInfo"
    >
      질문작성
    </b-button>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
const userStore = "userStore";
import http from "@/api/http";
import PagiNation from "../Pagination";
import QuestionListItem from "./QuestionListItem";
export default {
  name: "QuestionList",
  components: {
    PagiNation,
    QuestionListItem,
  },
  data() {
    return {
      questions: [],
      fields: [
        { key: "no", label: "번호" },
        { key: "title", label: "질문 제목" },
        { key: "userid", label: "작성자" },
        { key: "writedate", label: "작성날짜" },
        { key: "answer", label: "답변상태" },
      ],
    };
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  created() {
    http.get(`/qna/question`).then(({ data }) => {
      console.log("??");
      console.log(data);
      this.questions = data;
    });
  },
  methods: {
    moveCreate() {
      this.$router.push({ name: "questionCreate" });
    },
  },
};
</script>

<style></style>
