<template>
  <div class="wrapper">
    <div class="block-header pt-4 pt-lg-5 pb-0">
      <br />
      <br />
      <div style="margin-left:10%;margin-right:10%;">
        <div class="row">
          <div class="col-12">
            <div class="card">
              <div class="card-body">
                <div class="row">
                  <div class="col-12">
                    <h1 class="h3 m-4">회원 가입</h1>
                    <div class="d-flex flex-column align-items-center">
                      <form class="row g-1 p-0 p-md-4 w-100">
                        <table>
                          <tr>
                            <td
                              style="width: 15%;"
                              class="px-4 border-top border-dark"
                            >
                              <label class="mb-4 form-label" for="name">
                                이름
                                <span class="text-danger">*</span>
                              </label>
                            </td>
                            <td class="px-4">
                              <input
                                v-model="name"
                                id="name"
                                type="text"
                                class="mb-4 form-control form-control-lg"
                              />
                            </td>
                          </tr>
                          <tr>
                            <td class="px-4 border-top border-dark">
                              <label class="mb-4 form-label" for="id">
                                아이디
                                <span class="text-danger">*</span>
                              </label>
                            </td>
                            <td class="px-4">
                              <input
                                v-model="id"
                                id="id"
                                type="text"
                                class="mb-4 form-control form-control-lg"
                              />
                            </td>
                          </tr>
                          <tr>
                            <td class="px-4 border-top border-dark">
                              <label class="mb-4 form-label" for="password">
                                비밀번호
                                <span class="text-danger">*</span>
                              </label>
                            </td>
                            <td class="px-4">
                              <input
                                v-model="password"
                                id="password"
                                type="text"
                                class="mb-4 form-control form-control-lg"
                              />
                            </td>
                          </tr>
                          <!-- <tr>
                  <td class="px-4 border-top border-dark">
                    <label class="mb-4 form-label" for="userId"
                      >이메일 <span class="text-danger">*</span></label
                    >
                  </td>
                  <td class="px-4">
                    <input
                      v-model="userEmail"
                      id="userEmail"
                      type="email"
                      class="mb-4 form-control form-control-lg"
                    />
                  </td>
                </tr> -->
                          <tr>
                            <td class="px-4 border-top border-dark">
                              <label class="mb-4 form-label" for="phoneNumber">
                                휴대전화
                                <span class="text-danger">*</span>
                              </label>
                            </td>
                            <td class="px-4">
                              <input
                                v-model="phoneNumber"
                                id="phoneNumber"
                                type="text"
                                class="mb-4 form-control form-control-lg"
                              />
                            </td>
                          </tr>

                          <tr>
                            <td class="px-4 border-top border-dark">
                              <label class="mb-4 form-label" for="address">
                                주소
                                <span class="text-danger">*</span>
                              </label>
                            </td>
                            <td class="px-4">
                              <input
                                v-model="address"
                                id="address"
                                type="text"
                                class="mb-4 form-control form-control-lg"
                              />
                            </td>
                          </tr>
                        </table>

                        <div class="col-12 text-center my-4">
                          <router-link
                            to="/mypage"
                            class="btn px-4 btn-block btn-secondary lift text-uppercase"
                            style="color:white"
                          >
                            취소
                          </router-link>
                          &nbsp;
                          <a
                            @click="signIn"
                            class="btn px-4 btn-block btn-primary lift text-uppercase"
                            style="color:white"
                          >
                            가입
                          </a>
                        </div>
                      </form>
                    </div>
                  </div>
                </div>
              </div>
            </div>
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
  name: "userRegist",
  components: {},
  data() {
    return {
      id: "",
      password: "",
      name: "",
      address: "",
      phoneNumber: "",
    };
  },
  computed: {
    ...mapState(userStore, ["isRegistError"]),
  },
  methods: {
    ...mapActions(userStore, ["registUser"]),
    signIn() {
      const user = {
        id: this.id,
        password: this.password,
        name: this.name,
        address: this.address,
        phoneNumber: this.phoneNumber,
      };
      this.registUser(user);
      if (!this.isRegistError) {
        this.$swal("가입 성공!", { icon: "success" });
        this.$router.push({ name: "index" });
      } else {
        this.$swal("가입에 실패했습니다. ㅠㅠ", { icon: "warning" });
        return;
      }
    },
  },
};
</script>

<style></style>
