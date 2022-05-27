<template>
  <div class="body_area py-2 col-12">
    <div>
      <div class="row">
        <div class="col-12">
          <h1 class="h3 m-4">내정보 수정</h1>
          <div class="d-flex flex-column align-items-center">
            <form class="row g-1 p-0 p-md-4 w-100">
              <table>
                <tr>
                  <td style="width: 15%;" class="px-4 border-top border-dark">
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
                      readonly
                      v-model="id"
                      id="id"
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
                  style="color:white;"
                >
                  취소
                </router-link>
                &nbsp;
                <a
                  @click="modifyProfile"
                  class="btn px-4 btn-block btn-primary lift text-uppercase"
                  style="color:white;"
                >
                  수정
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
import { mapState, mapActions, mapMutations } from "vuex";
const userStore = "userStore";
export default {
  name: "modifyProfile",
  data() {
    return {
      id: "",
      name: "",
      phoneNumber: "",
      address: "",
    };
  },
  created() {
    this.id = this.userInfo.id;
    this.name = this.userInfo.name;
    this.phoneNumber = this.userInfo.phoneNumber;
    this.address = this.userInfo.address;
  },
  computed: {
    ...mapState(userStore, ["userInfo", "isUpdateError"]),
  },
  watch: {},
  methods: {
    ...mapActions(userStore, ["modifyInfo"]),
    ...mapMutations(userStore, ["SET_USER_INFO"]),
    modifyProfile() {
      const user = {
        id: this.id,
        name: this.name,
        phoneNumber: this.phoneNumber,
        address: this.address,
      };
      console.log(user);
      this.modifyInfo(user);
      if (!this.isUpdateError) {
        this.SET_USER_INFO(user);
        this.$swal("수정 성공!", { icon: "success" });
        this.$router.push({ name: "myPage" });
        return;
      } else {
        this.$swal("수정 실패했습니다. ㅠㅠ", { icon: "warning" });
        return;
      }
    },
  },
};
</script>

<style></style>
