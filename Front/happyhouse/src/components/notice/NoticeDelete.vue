<template>
  <div>
    <b-container class="bv-example-row mt-3">
      <b-row>
        <b-col>
          <b-alert show><h3>글목록</h3></b-alert>
        </b-col>
      </b-row>
      <b-row>
        <b-col><b-alert show variant="danger">삭제처리중...</b-alert></b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import http from "@/api/http";
export default {
  name: "noticeDelete",
  created() {
    http.delete(`/notice/notice/${this.$route.params.no}`).then(({ data }) => {
      let msg = "삭제 처리 중 문제가 발생하였습니다.";
      if (data === "success") {
        msg = "삭제 완료되었습니다.";
      }
      alert(msg);
      this.$router.push({ name: "noticeList" });
    });
  },
};
</script>

<style></style>
