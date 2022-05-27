<template>
  <div class="wrapper">
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />

    <div class="main main-raised">
      <div class="section section-basic">
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "LikeHouse",
};
</script>

<style scoped>
.underline-hotpink {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 27, 139, 0.3) 30%
  );
}
</style>
