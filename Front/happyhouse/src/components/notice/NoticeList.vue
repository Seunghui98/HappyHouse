<template>
  <div class="container">
    <div
      class="md-layout-item md-size-66 md-xsmall-size-100 ml-auto mr-auto text-center"
    >
      <h2>공지사항</h2>
    </div>

    <br />
    <br />
    <b-table-simple hover responsive>
      <b-thead head-variant="dark">
        <b-tr>
          <b-th>No</b-th>
          <b-th>제목</b-th>
          <b-th>작성자</b-th>
          <b-th>작성일</b-th>
        </b-tr>
      </b-thead>
      <tbody>
        <notice-list-item
          v-for="notice in notices"
          :key="notice.notice"
          v-bind="notice"
        />
      </tbody>
    </b-table-simple>

    <pagi-nation style="width:100px;margin: 0 auto;"></pagi-nation>
    <br />
    <b-button
      @click="moveCreate()"
      style="width:100px;margin: 0 auto;"
      v-if="userInfo && userInfo.id === `admin`"
    >
      작성
    </b-button>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
const userStore = "userStore";
import http from "@/api/http";
import PagiNation from "../Pagination";
import NoticeListItem from "./NoticeListItem";
export default {
  name: "NoticeList",
  components: {
    PagiNation,
    NoticeListItem,
  },
  data() {
    return {
      notices: [],
      fields: [
        { key: "no", label: "No" },
        { key: "title", label: "제목" },
        { key: "id", label: "작성자" },
        { key: "createtime", label: "작성날짜" },
      ],
    };
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  created() {
    http.get(`/notice`).then(({ data }) => {
      console.log(data);
      this.notices = data;
    });
  },
  methods: {
    moveCreate() {
      this.$router.push({ name: "noticeWrite" });
    },
  },
};
</script>

<style></style>
