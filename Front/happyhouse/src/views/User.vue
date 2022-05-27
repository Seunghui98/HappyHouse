<template>
  <div>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "User",
};
</script>

<style scoped>
.login-logo {
  text-align: center;
}
.login-form-border {
  text-align: center;
  margin-left: 30%;
  width: 300px;
  height: 300px;
  padding: 20px;
  border: dashed #8cbded;
}
.login-button {
  background-color: #8cbded;
  border-color: #8cbded;
}
.login-login {
  color: #8cbded;
}
.login-form-form {
  margin-top: 30px;
}
.login-alert {
  margin-top: 30px;
  margin-left: 10px;
}
</style>
