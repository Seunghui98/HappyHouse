<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>공지사항 수정</h3></b-alert>
      </b-col>
    </b-row>
    <input-item-notice type="modify" />
  </b-container>
</template>

<script>
import InputItemNotice from "../InputItemNotice.vue";

export default {
  name: "noticeModify",
  components: {
    InputItemNotice,
  },
};
</script>

<style></style>
