<template>
  <b-tr>
    <b-td>{{ num }}</b-td>
    <b-th class="text-left">
      <router-link :to="{ name: 'boardDetail', params: { num: num } }">
        {{ title }}
      </router-link>
    </b-th>
    <b-td>{{ id }}</b-td>
    <b-td>{{ createtime | dateFormat }}</b-td>
  </b-tr>
</template>

<script>
import moment from "moment";
export default {
  name: "NoticeListItem",
  props: {
    num: Number,
    id: String,
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
