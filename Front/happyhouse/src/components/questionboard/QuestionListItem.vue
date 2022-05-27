<template>
  <b-tr>
    <b-td>{{ no }}</b-td>
    <b-th class="text-left">
      <router-link :to="{ name: 'questionDetail', params: { no: no } }">
        {{ title }}
      </router-link>
    </b-th>
    <b-td>{{ userid }}</b-td>
    <b-td>{{ writedate | dateFormat }}</b-td>
    <b-td>
      <b-badge pill variant="primary" v-if="!answer">답변대기</b-badge>
      <b-badge pill variant="success" v-else>답변완료</b-badge>
    </b-td>
  </b-tr>
</template>

<script>
import moment from "moment";
export default {
  name: "QuestionListItem",
  props: {
    no: Number,
    userid: String,
    title: String,
    writedate: String,
    answer: Boolean,
  },

  filters: {
    dateFormat(writedate) {
      return moment(new Date(writedate)).format("YY.MM.DD");
    },
  },
};
</script>

<style></style>
