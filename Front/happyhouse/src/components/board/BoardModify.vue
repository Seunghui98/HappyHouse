<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>게시글 수정</h3></b-alert>
      </b-col>
    </b-row>
    <board-input-item type="modify" />
  </b-container>
</template>

<script>
import BoardInputItem from "../BoardInputItem.vue";

export default {
  name: "noticeModify",
  components: {
    BoardInputItem,
  },
};
</script>

<style></style>
