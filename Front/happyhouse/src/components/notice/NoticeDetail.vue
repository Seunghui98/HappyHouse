<template>
  <div class="container">
    <div
      class="md-layout-item md-size-66 md-xsmall-size-100 ml-auto mr-auto text-center"
    >
      <h2>공지사항</h2>
    </div>
    <b-container class="bv-example-row mt-3">
      <b-row class="mb-1">
        <b-col class="text-left">
          <b-button variant="outline-primary" @click="listArticle">
            목록
          </b-button>
        </b-col>
        <b-col class="text-right" v-if="userInfo && userInfo.id === `admin`">
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
              `<h3>
          ${notice.title} </h3><div><h6>운영자</div><div>${notice.createtime}</h6></div>`
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
    </b-container>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
const userStore = "userStore";
import http from "@/api/http";
export default {
  name: "noticeDetail",
  data() {
    return {
      notice: {},
    };
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
    message() {
      if (this.notice.content)
        return this.notice.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    console.log(this.$route.params.no);
    http.get(`/notice/${this.$route.params.no}`).then(({ data }) => {
      this.notice = data;
    });
  },
  methods: {
    listArticle() {
      this.$router.push({ name: "noticeList" });
    },
    moveModifyArticle() {
      this.$router.replace({
        name: "noticeModify",
        params: { no: this.$route.params.no },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    deleteArticle() {
      if (confirm("정말로 삭제?")) {
        this.$router.replace({
          name: "noticeDelete",
          params: { no: this.notice.no },
        });
      }
    },
  },
};
</script>

<style></style>
