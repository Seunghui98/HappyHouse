<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit" @reset="onReset">
        <b-form-group
          id="subject-group"
          label="제목:"
          label-for="subject"
          description="제목을 입력하세요."
        >
          <b-form-input
            id="subject"
            v-model="notice.title"
            type="text"
            required
            placeholder="제목 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group id="content-group" label="내용:" label-for="content">
          <b-form-textarea
            id="content"
            v-model="notice.content"
            placeholder="내용 입력..."
            rows="10"
            max-rows="15"
          ></b-form-textarea>
        </b-form-group>

        <b-button
          type="submit"
          variant="primary"
          class="m-1"
          v-if="this.type === 'register'"
        >
          공지 작성
        </b-button>
        <b-button type="submit" variant="primary" class="m-1" v-else>
          공지 수정
        </b-button>
        <b-button type="reset" variant="danger" class="m-1">초기화</b-button>
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
import http from "@/api/http";
import axios from "axios";
export default {
  name: "InputItemNotice",
  data() {
    return {
      notice: {
        no: 0,
        id: "ssafy",
        title: "",
        content: "",
      },
      isUserid: false,
    };
  },
  props: {
    type: { type: String },
  },
  created() {
    if (this.type === "modify") {
      http.get(`/notice/${this.$route.params.no}`).then(({ data }) => {
        // this.article.articleno = data.article.articleno;
        // this.article.userid = data.article.userid;
        // this.article.subject = data.article.subject;
        // this.article.content = data.article.content;
        this.notice.no = data.no;
        this.notice.id = data.userid;
        this.notice.title = data.title;
        this.notice.content = data.content;
        //this.question = data;
      });
      this.isUserid = true;
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.notice.id &&
        ((msg = "작성자 입력해주세요"), (err = false), this.$refs.id.focus());
      err &&
        !this.notice.title &&
        ((msg = "제목 입력해주세요"),
        (err = false),
        this.$refs.subject.focus());
      err &&
        !this.notice.content &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.content.focus());

      if (!err) alert(msg);
      else
        this.type === "register" ? this.registArticle() : this.modifyArticle();
    },
    onReset(event) {
      event.preventDefault();
      this.notice.no = 0;
      this.notice.title = "";
      this.notice.content = "";
      this.$router.push({ name: "NoticeList" });
    },
    registArticle() {
      const API_URL = `http://localhost/notice/notice`;
      const noticedto = {
        title: this.notice.title,
        content: this.notice.content,
        createtime: this.notice.createtime,
      };
      console.log(noticedto);
      axios({
        url: API_URL,
        method: "POST",
        data: noticedto,
      }).then(({ data }) => {
        let msg = "등록 처리시 문제가 발생했습니다.";
        if (data === "success") {
          msg = "등록이 완료되었습니다.";
        }
        alert(msg);
        this.moveList();
      });
    },
    modifyArticle() {
      const API_URL = `http://localhost/notice/${this.question.no}`;
      axios({
        url: API_URL,
        method: "PUT",
        params: this.notice,
      })
        .then((data) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          console.log(data);
          if (data.data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.$router.push({ name: "noticeList" });
        })
        .catch((err) => {
          console.log(err);
        });
    },
    moveList() {
      this.$router.push({ name: "noticeList" });
    },
  },
};
</script>

<style></style>
