<template>
  <div class="body_area py-2">
    <div>
      <div class="row">
        <div class="col-12">
          <h1 class="h3 m-4">비밀번호 변경</h1>
          <div class="d-flex flex-column align-items-center">
            <form class="row g-1 p-0 p-md-4 w-100">
              <table>
                <tr>
                  <td style="width: 15%;" class="px-4 border-top border-dark">
                    <label class="mb-4 form-label" for="password">
                      비밀번호
                      <span class="text-danger">*</span>
                    </label>
                  </td>
                  <td class="px-4">
                    <input
                      v-model="password"
                      id="password"
                      type="password"
                      class="mb-4 form-control form-control-lg"
                    />
                  </td>
                </tr>
                <tr>
                  <td class="px-4 border-top border-dark">
                    <label class="mb-4 form-label" for="passwordChk">
                      비밀번호 확인
                      <span class="text-danger">*</span>
                    </label>
                  </td>
                  <td class="px-4">
                    <input
                      v-model="passwordChk"
                      id="passwordChk"
                      type="password"
                      class="mb-4 form-control form-control-lg"
                    />
                  </td>
                </tr>
              </table>

              <div class="col-12 text-center my-4">
                <router-link
                  to="/mypage"
                  class="btn px-4 btn-block btn-secondary lift"
                  style="color:white;"
                >
                  취소
                </router-link>
                &nbsp;
                <a
                  @click="changePass"
                  class="btn px-4 btn-block btn-primary lift text-uppercase"
                  style="color:white;"
                >
                  변경
                </a>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
const userStore = "userStore";
export default {
  name: "modifyPassword",
  data() {
    return {
      password: "",
      passwordChk: "",
    };
  },
  computed: {
    ...mapState(userStore, ["userInfo", "isUpdateError"]),
  },
  methods: {
    ...mapActions(userStore, ["modifyPassword", "changePassword"]),
    changePass() {
      if (this.password === "" || this.passwordChk === "") {
        this.$swal("비밀번호를 입력해주세요.", { icon: "warning" });
      } else if (this.password === this.passwordChk) {
        const user = {
          id: this.userInfo.id,
          password: this.password,
        };
        this.changePassword(user);
        if (!this.isUpdateError) {
          this.$swal("수정 성공!", { icon: "success" });
          this.$router.push({ name: "myPage" });
        } else {
          this.$swal("수정 실패했습니다. ㅠㅠ", { icon: "warning" });
          return;
        }
      } else {
        this.$swal("비밀번호가 일치하지 않습니다.", {
          icons: "error",
        });
      }
    },
  },
};
</script>

<style></style>
