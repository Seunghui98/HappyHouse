<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>공지사항</h3></b-alert>
      </b-col>
    </b-row>
    <input-item-notice type="register" />
  </b-container>
</template>

<script>
import InputItemNotice from "../InputItemNotice.vue";

export default {
  name: "noticeWrite",
  components: {
    InputItemNotice,
  },
};
</script>

<style></style>
