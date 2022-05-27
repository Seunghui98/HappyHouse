<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>질문 작성</h3></b-alert>
      </b-col>
    </b-row>
    <board-input-item type="register" />
  </b-container>
</template>

<script>
import { mapState } from "vuex";
const userStore = "userStore";
import BoardInputItem from "../BoardInputItem.vue";

export default {
  name: "questionCreate",
  components: {
    BoardInputItem,
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
};
</script>

<style></style>
