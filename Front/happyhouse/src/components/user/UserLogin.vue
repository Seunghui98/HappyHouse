<template>
  <div class="wrapper">
    <div
      class="section page-header header-filter bg-secondary"
      style="width: 100%; height: 90vh;"
    >
      <div class="container">
        <div class="md-layout">
          <div
            class="md-layout-item md-size-33 md-small-size-66 md-xsmall-size-100 md-medium-size-40 mx-auto"
          >
            <login-card header-color="green">
              <h4 slot="title" class="card-title">로그인</h4>
              <md-button
                slot="buttons"
                href="javascript:void(0)"
                class="md-just-icon md-simple md-white"
              >
                <i class="fab fa-facebook-square"></i>
              </md-button>
              <md-button
                slot="buttons"
                href="javascript:void(0)"
                class="md-just-icon md-simple md-white"
              >
                <i class="fab fa-twitter"></i>
              </md-button>
              <md-button
                slot="buttons"
                href="javascript:void(0)"
                class="md-just-icon md-simple md-white"
              >
                <i class="fab fa-google-plus-g"></i>
              </md-button>
              <p slot="description" class="description">Or Be Classical</p>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>email</md-icon>
                <label label-for="userid">아이디 입력...</label>
                <md-input v-model="user.userid" id="userid"></md-input>
              </md-field>
              <md-field class="md-form-group" slot="inputs">
                <md-icon>lock_outline</md-icon>
                <label label-for="userpwd">비밀번호 입력...</label>
                <md-input
                  v-model="user.userpwd"
                  id="userpwd"
                  type="password"
                ></md-input>
              </md-field>
              <md-button
                slot="footer"
                class="md-simple md-success md-lg"
                @click="confirm"
              >
                로그인
              </md-button>
            </login-card>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { LoginCard } from "@/components";
import { mapState, mapActions } from "vuex";
const userStore = "userStore";

export default {
  name: "userLogin",
  components: {
    LoginCard,
  },
  bodyClass: "login-page",
  data() {
    return {
      user: {
        userid: null,
        userpwd: null,
      },
    };
  },
  props: {
    header: {
      type: String,
      default: require("@/assets/img/profile_city.jpg"),
    },
  },
  computed: {
    ...mapState(userStore, ["isLogin", "isLoginError"]),
    headerStyle() {
      return {
        backgroundImage: `url(${this.header})`,
      };
    },
  },
  watch: {
    isLoginError: function() {
      console.log(this.isLoginError);
      if (this.isLoginError) {
        this.$swal("아이디 또는 비밀번호를 확인하세요.", { icon: "warning" });
        return;
      }
    },
  },
  methods: {
    ...mapActions(userStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      await this.userConfirm(this.user);
      if (this.isLoginError) {
        this.$swal("아이디 또는 비밀번호를 확인하세요.", { icon: "warning" });
        return;
      }
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        await this.getUserInfo(token);
        this.$swal("로그인 성공!", { icon: "success" });
        this.$router.push({ name: "index" });
      }
    },
    movePage() {
      this.$router.push({ name: "userlogin" });
    },
  },
};
</script>

<style></style>
