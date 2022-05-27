<template>
  <div class="wrapper">
    <div class="block-header pt-4 pt-lg-5 pb-0">
      <br />
      <br />
      <div style="margin-left:10%;margin-right:10%;">
        <div class="row mb-3">
          <div class="col">
            <ul class="breadcrumb bg-transparent mb-0">
              <li class="breadcrumb-item">
                <router-link to="/" class="text-decoration-none">
                  Home
                </router-link>
              </li>
              <li class="breadcrumb-item active">마이 페이지</li>
            </ul>
          </div>
        </div>
        <div class="row">
          <div class="col-12">
            <div class="card">
              <div class="card-body">
                <div class="d-flex align-items-center flex-column flex-md-row">
                  <img
                    src="../assets/img/user.png"
                    alt="기본 이미지"
                    class="rounded-circle"
                    id="proflieImg"
                  />
                  <div
                    class="media-body ms-md-5 m-0 mt-4 mt-md-0 text-md-start text-center"
                    style="margin-left:10%;"
                  >
                    <h5 class="font-weight-bold d-inline-block me-2">
                      {{ userInfo.name }}
                    </h5>
                    님 환영합니다.
                    <!-- <div class="text-muted mb-4">
                    <span class="text-dark">가입일</span> : {{ regDt.year }}-{{
                      regDt.month
                    }}-{{ regDt.day }}
                  </div> -->
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="col-12 tab-menu">
            <ul class="nav nav-tabs tab-card mt-3 border-bottom-0">
              <li class="nav-item">
                <router-link
                  to="/mypage/myprofile"
                  :class="[$route.name == 'myProfile' ? 'active' : '']"
                  class="cursor-pointer nav-link"
                  data-toggle="tab"
                  role="tab"
                >
                  프로필
                </router-link>
              </li>
              <li class="nav-item">
                <router-link
                  to="/mypage/myreview"
                  :class="[$route.name == 'myReview' ? 'active' : '']"
                  class="cursor-pointer nav-link"
                  data-toggle="tab"
                  role="tab"
                >
                  리뷰 목록
                </router-link>
              </li>
              <li class="nav-item">
                <router-link
                  to="/mypage/mylike"
                  :class="[$route.name == 'myLike' ? 'active' : '']"
                  class="cursor-pointer nav-link"
                  data-toggle="tab"
                  role="tab"
                >
                  찜 목록
                </router-link>
              </li>
            </ul>

            <router-view></router-view>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

const userStore = "userStore";
export default {
  name: "myPage",
  components: {},
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
};
</script>

<style>
.cursor-pointer {
  cursor: pointer;
}
#proflieImg {
  width: 140px;
  height: 140px;
}
</style>
