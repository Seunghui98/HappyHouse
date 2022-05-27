<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>자유게시판</h3></b-alert>
      </b-col>
    </b-row>
    <board-input-item type="register" />
  </b-container>
</template>

<script>
import BoardInputItem from "../BoardInputItem.vue";

export default {
  name: "boardWrite",
  components: {
    BoardInputItem,
  },
};
</script>

<style></style>
