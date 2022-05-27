<template>
  <div class="wrapper">
    <br />
    <br />

    <div class="main main-raised">
      <div class="section section-basic">
        <div class="container my-5">
          <div class="row mb-3">
            <div class="col">
              <ul class="breadcrumb bg-transparent mb-0">
                <li class="breadcrumb-item">
                  <router-link to="/" class="text-decoration-none">
                    Home
                  </router-link>
                </li>
                <li class="breadcrumb-item active">부동산 뉴스 모아모아</li>
              </ul>
            </div>
          </div>

          <div class="row">
            <div
              class="col-md-3 mb-5"
              v-for="(item, index) in newsList"
              v-bind:key="index"
            >
              <img src="./news.png" />
              <b-card
                :title="item.title"
                img-alt="Image"
                img-top
                tag="article"
                style="max-width: 20rem;height: 20rem;"
                class="mb-2"
              >
                <b-card-text>
                  <a :href="item.link">{{ item.description }}</a>
                </b-card-text>
                <div></div>
              </b-card>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions } from "vuex";
const newsStore = "newsStore";
export default {
  created() {
    this.getNews("부동산");
  },
  watch: {
    newsList: function() {},
  },
  computed: {
    ...mapState(newsStore, ["newsList"]),
  },
  methods: {
    ...mapActions(newsStore, ["getNews"]),
  },
};
</script>

<style></style>
