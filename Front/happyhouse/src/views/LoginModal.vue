<template>
  <modal
    name="login-modal"
    transition="pop-out"
    :width="modalWidth"
    :focus-trap="true"
    :height="400"
  >
    <div class="box">
      <div class="box-part" id="bp-left">
        <div class="partition" id="partition-register">
          <div class="partition-title">Login</div>
          <div class="partition-form" style="margin-top: 42px">
            <form autocomplete="false">
              <div class="autocomplete-fix">
                <input disabled type="password" />
              </div>

              <input
                id="n-username"
                v-model="user.userid"
                type="text"
                placeholder="아이디"
              />
              <input
                id="n-password"
                v-model="user.userpwd"
                type="password"
                placeholder="비밀번호"
              />
            </form>

            <div style="margin-top: 42px"></div>

            <div class="button-set">
              <button id="goto-signin-btn" @click="confirm">로그인</button>
              <button id="register-btn" @click="moveRegister">회원가입</button>
            </div>

            <!-- <button class="large-btn github-btn">
              connect with
              <span>github</span>
            </button>
            <button class="large-btn facebook-btn">
              connect with
              <span>facebook</span>
            </button> -->
          </div>
        </div>
      </div>
      <div class="box-part" id="bp-right">
        <img src="../assets/img/loginimg.jpg" />
        <div class="box-messages"></div>
      </div>
    </div>
  </modal>
</template>
<script>
import { mapState, mapActions } from "vuex";
const userStore = "userStore";
const MODAL_WIDTH = 656;
export default {
  name: "LoginModal",
  data() {
    return {
      modalWidth: MODAL_WIDTH,
      user: {
        userid: null,
        userpwd: null,
      },
    };
  },
  created() {
    this.modalWidth =
      window.innerWidth < MODAL_WIDTH ? MODAL_WIDTH / 2 : MODAL_WIDTH;
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
    signIn() {
      alert("Sign in");
    },
    register() {
      alert("Register");
    },
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
        this.closeByName();
        this.$router.push({ name: "index" });
      }
    },
    movePage() {
      this.$router.push({ name: "userlogin" });
    },
    moveRegister() {
      this.closeByName();
      this.$router.push({ name: "userRegist" });
    },
    closeByName() {
      this.$modal.hide("login-modal");
    },
  },
};
</script>
<style lang="scss">
$background_color: #404142;
$github_color: #dba226;
$facebook_color: #3880ff;
.box {
  background: white;
  overflow: hidden;
  width: 656px;
  height: 400px;
  border-radius: 2px;
  box-sizing: border-box;
  box-shadow: 0 0 40px black;
  color: #8b8c8d;
  font-size: 0;
  .box-part {
    display: inline-block;
    position: relative;
    vertical-align: top;
    box-sizing: border-box;
    height: 100%;
    width: 50%;
    &#bp-right {
      background: url("/static/panorama.jpg") no-repeat top left;
      border-left: 1px solid #eee;
    }
  }
  .box-messages {
    position: absolute;
    left: 0;
    bottom: 0;
    width: 100%;
  }
  .box-error-message {
    position: relative;
    overflow: hidden;
    box-sizing: border-box;
    height: 0;
    line-height: 32px;
    padding: 0 12px;
    text-align: center;
    width: 100%;
    font-size: 11px;
    color: white;
    background: #f38181;
  }
  .partition {
    width: 100%;
    height: 100%;
    .partition-title {
      box-sizing: border-box;
      padding: 30px;
      width: 100%;
      text-align: center;
      letter-spacing: 1px;
      font-size: 20px;
      font-weight: 300;
    }
    .partition-form {
      padding: 0 20px;
      box-sizing: border-box;
    }
  }
  input[type="password"],
  input[type="text"] {
    display: block;
    box-sizing: border-box;
    margin-bottom: 4px;
    width: 100%;
    font-size: 12px;
    line-height: 2;
    border: 0;
    border-bottom: 1px solid #dddedf;
    padding: 4px 8px;
    font-family: inherit;
    transition: 0.5s all;
  }
  button {
    background: white;
    border-radius: 4px;
    box-sizing: border-box;
    padding: 10px;
    letter-spacing: 1px;
    font-family: "Open Sans", sans-serif;
    font-weight: 400;
    min-width: 140px;
    margin-top: 8px;
    color: #8b8c8d;
    cursor: pointer;
    border: 1px solid #dddedf;
    text-transform: uppercase;
    transition: 0.1s all;
    font-size: 10px;
    &:hover {
      border-color: mix(#dddedf, black, 90%);
      color: mix(#8b8c8d, black, 80%);
    }
  }
  .large-btn {
    width: 100%;
    background: white;
    span {
      font-weight: 600;
    }
    &:hover {
      color: white !important;
    }
  }
  .button-set {
    margin-bottom: 8px;
  }
  #register-btn,
  #signin-btn {
    margin-left: 8px;
  }
  .facebook-btn {
    border-color: $facebook_color;
    color: $facebook_color;
    &:hover {
      border-color: $facebook_color;
      background: $facebook_color;
    }
  }
  .github-btn {
    border-color: $github_color;
    color: $github_color;
    &:hover {
      border-color: $github_color;
      background: $github_color;
    }
  }
  .autocomplete-fix {
    position: absolute;
    visibility: hidden;
    overflow: hidden;
    opacity: 0;
    width: 0;
    height: 0;
    left: 0;
    top: 0;
  }
}
.pop-out-enter-active,
.pop-out-leave-active {
  transition: all 0.5s;
}
.pop-out-enter,
.pop-out-leave-active {
  opacity: 0;
  transform: translateY(24px);
}
</style>
