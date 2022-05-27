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
export default {
  name: "noticeDelete",
};
</script>

<style></style>
