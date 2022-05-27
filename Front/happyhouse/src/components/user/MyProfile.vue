<template>
  <div class="body_area py-2">
    <div>
      <div class="row">
        <div class="col-12">
          <h1 class="h3 m-4">기본 정보</h1>
          <div class="card p-3">
            <div
              class="d-flex flex-wrap flex-md-nowrap justify-content-between"
            >
              <div class="doctor-avatar order-0 order-md-1">
                <button
                  @click="modifyInfo"
                  style="font-size: 14px;"
                  class="btn px-2 py-1 btn-animate-2 fill"
                >
                  내정보 수정
                </button>
                <button
                  @click="changePassword"
                  style="font-size: 14px;"
                  class="btn px-2 py-1 btn-animate-2 fill"
                >
                  비밀번호 변경
                </button>
              </div>
              <div class="doctor-detail order-1 order-md-0">
                <ul class="resume-box">
                  <li>
                    <div class="icon text-center">
                      <i
                        class="bi bi-person-badge-fill"
                        style="font-size: 1.3rem;"
                      ></i>
                    </div>
                    <div class="fw-bold mb-0">아이디</div>
                    <span>{{ userInfo.id }}</span>
                  </li>
                  <li>
                    <div class="icon text-center"></div>
                    <div class="fw-bold mb-0">휴대 전화</div>
                    <span>{{ userInfo.phoneNumber }}</span>
                  </li>
                  <li>
                    <div class="icon text-center"></div>
                    <div class="fw-bold mb-0">주소</div>
                    <span>{{ userInfo.address }}</span>
                  </li>
                  <!-- <li>
                    <div class="icon text-center">
                      <i class="fa fa-envelope"></i>
                    </div>
                    <div class="fw-bold mb-0">E-Mail</div>
                    <span>{{ email }}</span>
                  </li> -->
                </ul>
              </div>
            </div>
          </div>
          <button
            @click="deleteUser"
            style="font-size: 14px;"
            class="btn btn-danger mt-4 mb-4 px-2 py-1 lift float-end"
          >
            회원 탈퇴
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
const userStore = "userStore";
export default {
  name: "MyProfile",
  components: {},
  computed: {
    ...mapState(userStore, ["userInfo", "isDeleteError"]),
  },
  watch: {
    userInfo: function() {},
  },
  methods: {
    ...mapActions(userStore, ["logout", "deleteUserInfo"]),
    ...mapMutations(userStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    logout() {
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      sessionStorage.removeItem("refresh-token");
      this.$swal("로그아웃 !", { icon: "info" });
      if (this.$route.path != "/") this.$router.push({ name: "index" });
    },
    modifyInfo() {
      this.$swal({
        text: "비밀번호를 입력해 주세요.",
        content: {
          element: "input",
          attributes: {
            placeholder: "비밀번호를 입력하세요",
            type: "password",
          },
        },
        buttons: true,
      }).then((value) => {
        if (value) {
          if (value === this.userInfo.password) {
            this.$router.push("/mypage/modifyProfile");
          } else {
            this.$swal("비밀번호가 일치하지 않습니다.").then(() =>
              this.modifyInfo(),
            );
          }
        } else if (value == "") {
          this.$swal("비밀번호를 입력하세요!").then(() => this.modifyInfo());
        }
      });
    },
    changePassword() {
      this.$swal({
        text: "비밀번호를 입력해 주세요.",
        content: {
          element: "input",
          attributes: {
            placeholder: "비밀번호를 입력하세요",
            type: "password",
          },
        },
        buttons: true,
      }).then((value) => {
        if (value) {
          if (value === this.userInfo.password) {
            this.$router.push("/mypage/modifyPassword");
          } else {
            this.$swal("비밀번호가 일치하지 않습니다.").then(() =>
              this.changePassword(),
            );
          }
        } else if (value == "") {
          this.$swal("비밀번호를 입력하세요!").then(() =>
            this.changePassword(),
          );
        }
      });
    },
    deleteUser() {
      this.$swal({
        title: "정말 탈퇴하시겠습니까?",
        text: "탈퇴하시려면 비밀번호를 입력해 주세요.",
        content: {
          element: "input",
          attributes: {
            placeholder: "비밀번호를 입력하세요",
            type: "password",
          },
        },
        icon: "warning",
        dangerMode: true,
        buttons: true,
      }).then((value) => {
        var url = "";
        url = "/user";
        if (value) {
          if (value === this.password) {
            this.deleteUserInfo(this.userInfo.id);
            if (!this.isDeleteError) {
              this.$swal(
                "탈퇴 완료 !",
                "지금까지 저희 서비스를 이용해주셔서 감사합니다.",
                { icon: "success" },
              ).then(() => this.logout());
            } else {
              this.$swal("탈퇴처리 중 문제가 발생했습니다. ㅠㅠ", {
                icon: "warning",
              });
              return;
            }
          } else {
            this.$swal("비밀번호가 일치하지 않습니다.").then(() =>
              this.deleteUser(),
            );
          }
        } else if (value == "") {
          this.$swal("비밀번호를 입력하세요!").then(() => this.deleteUser());
        }
      });
    },
  },
};
</script>

<style></style>
