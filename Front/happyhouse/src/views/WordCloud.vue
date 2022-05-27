<template>
  <div id="app">
    <wordcloud
      :data="heatList"
      nameKey="dongName"
      valueKey="cnt"
      :color="myColors"
      :showTooltip="true"
      :wordClick="wordClickHandler"
    ></wordcloud>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
import wordcloud from "vue-wordcloud";
const heatStore = "heatStore";

export default {
  name: "app",
  components: {
    wordcloud,
  },
  methods: {
    ...mapActions(heatStore, ["getUserHeatList"]),
    wordClickHandler(name, value, vm) {
      console.log("wordClickHandler", name, value, vm);
    },
  },
  created() {
    this.getUserHeatList();
  },
  computed: {
    ...mapState(heatStore, ["heatList"]),
  },
  data() {
    return {
      myColors: ["#1f77b4", "#629fc9", "#94bedb", "#c9e0ef"],
      defaultWords: [
        {
          name: "Cat",
          value: 26,
        },
        {
          name: "fish",
          value: 19,
        },
        {
          name: "things",
          value: 18,
        },
        {
          name: "look",
          value: 16,
        },
        {
          name: "two",
          value: 15,
        },
        {
          name: "fun",
          value: 9,
        },
        {
          name: "know",
          value: 9,
        },
        {
          name: "good",
          value: 9,
        },
        {
          name: "play",
          value: 6,
        },
      ],
    };
  },
};
</script>

<style scoped>
#app {
  width: 100%;
  height: 100%;
}
</style>
