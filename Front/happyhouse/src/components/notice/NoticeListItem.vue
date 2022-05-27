<template>
  <b-tr>
    <b-td>{{ no }}</b-td>
    <b-th class="text-left">
      <router-link :to="{ name: 'noticeDetail', params: { no: no } }">
        {{ title }}
      </router-link>
    </b-th>
    <b-td>운영자</b-td>
    <b-td>{{ createtime | dateFormat }}</b-td>
  </b-tr>
</template>

<script>
import moment from "moment";
export default {
  name: "NoticeListItem",
  props: {
    no: Number,
    title: String,
    createtime: String,
  },
  filters: {
    dateFormat(createtime) {
      return moment(new Date(createtime)).format("YY.MM.DD");
    },
  },
};
</script>

<style></style>
