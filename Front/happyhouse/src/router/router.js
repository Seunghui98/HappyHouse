import Vue from "vue";
import Router from "vue-router";
import Index from "../views/Index.vue";
import MainNavbar from "../layout/MainNavbar.vue";
import MainFooter from "../layout/MainFooter.vue";
import QABoard from "../views/QABoard.vue";
import MapMenu from "../views/MapMenu.vue";
import MyPage from "../views/MyPage.vue";
import User from "../views/User.vue";
import LikeHouse from "../views/LikeHouse.vue";
import NewsPage from "../views/NewsPage.vue";
import LoginModal from "../views/LoginModal.vue";
import LoginPage from "../views/LoginPage.vue";
import Notice from "../views/Notice.vue";
import Board from "../views/Board.vue";
// import WordCloud from "../views/WordCloud.vue";

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: "/",
      name: "index",
      components: { default: Index, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" },
      },
    },
    {
      path: "/user",
      name: "user",
      components: { default: User, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" },
      },
      redirect: "/user/userlogin",
      children: [
        {
          path: "userlogin",
          name: "userLogin",
          component: () => import("@/components/user/UserLogin.vue"),
        },
        {
          path: "userregist",
          name: "userRegist",
          component: () => import("@/components/user/UserRegist.vue"),
        },
      ],
    },
    {
      path: "/likehouse",
      name: "LikeHouse",

      components: {
        default: LikeHouse,
        header: MainNavbar,
        footer: MainFooter,
      },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" },
      },
      redirect: "/likehouse/list",
      children: [
        {
          path: "list",
          name: "LikeList",
          component: () => import("@/components/likeboard/LikeList.vue"),
        },
      ],
    },

    {
      path: "/newspage",
      name: "NewsPage",

      components: {
        default: NewsPage,
        header: MainNavbar,
        footer: MainFooter,
      },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" },
      },
    },

    {
      path: "/loginpage",
      name: "LoginPage",

      components: {
        default: LoginPage,
      },
    },

    {
      path: "/qaboard",
      name: "qaboard",
      components: { default: QABoard, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" },
      },
      redirect: "/qaboard/questionlist",
      children: [
        {
          path: "questionlist",
          name: "questionList",
          component: () =>
            import("@/components/questionboard/QuestionList.vue"),
        },
        {
          path: "questioncreate",
          name: "questionCreate",
          component: () =>
            import("@/components/questionboard/QuestionCreate.vue"),
        },
        {
          path: "questiondetail/:no",
          name: "questionDetail",
          component: () =>
            import("@/components/questionboard/QuestionDetail.vue"),
        },
        {
          path: "qeustionmodify/:no",
          name: "questionModify",
          component: () =>
            import("@/components/questionboard/QuestionModify.vue"),
        },
        {
          path: "questiondelete/:no",
          name: "questionDelete",
          component: () =>
            import("@/components/questionboard/QuestionDelete.vue"),
        },
      ],
    },
    {
      path: "/mapmenu",
      name: "mapMenu",
      components: { default: MapMenu, header: MainNavbar, footer: MainFooter },
      redirect: "/mapmenu/kakaomap",
      children: [
        {
          path: "kakaomap",
          name: "kakaoMap",
          component: () => import("@/components/mapmenu/KakaoMap.vue"),
        },
      ],
    },
    {
      path: "/mypage",
      name: "myPage",
      components: { default: MyPage, header: MainNavbar, footer: MainFooter },
      redirect: "/mypage/myprofile",
      children: [
        {
          path: "myprofile",
          name: "myProfile",
          component: () => import("@/components/user/MyProfile.vue"),
        },
        {
          path: "myreview",
          name: "myReview",
          component: () => import("@/components/user/MyReview.vue"),
        },
        {
          path: "mylike",
          name: "myLike",
          component: () => import("@/components/user/MyLike.vue"),
        },
        {
          path: "modifyProfile",
          name: "modifyProfile",
          component: () => import("@/components/user/ModifyProfile.vue"),
        },
        {
          path: "modifyPassword",
          name: "modifyPassword",
          component: () => import("@/components/user/ModifyPassword.vue"),
        },
      ],
    },
    {
      path: "/board",
      name: "board",
      components: { default: Board, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" },
      },
      redirect: "/board/boardlist",
      children: [
        {
          path: "boardlist",
          name: "boardList",
          component: () => import("@/components/board/BoardList.vue"),
        },
        {
          path: "boardwrite",
          name: "boardWrite",
          component: () => import("@/components/board/BoardWrite.vue"),
        },
        {
          path: "boarddetail/:num",
          name: "boardDetail",
          component: () => import("@/components/board/BoardDetail.vue"),
        },
        {
          path: "boardmodify/:num",
          name: "boardModify",
          component: () => import("@/components/board/BoardModify.vue"),
        },
        // {
        //   path: "questiondelete/:no",
        //   name: "questionDelete",
        //   component: () =>
        //     import("@/components/questionboard/QuestionDelete.vue"),
        // },
      ],
    },
    {
      path: "/notice",
      name: "notice",
      components: { default: Notice, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" },
      },
      redirect: "/notice/noticeList",
      children: [
        {
          path: "noticelist",
          name: "noticeList",
          component: () => import("@/components/notice/NoticeList.vue"),
        },
        {
          path: "noticewrite",
          name: "noticeWrite",
          component: () => import("@/components/notice/NoticeWrite.vue"),
        },
        {
          path: "noticedetail/:no",
          name: "noticeDetail",
          component: () => import("@/components/notice/NoticeDetail.vue"),
        },
        {
          path: "noticemodify/:no",
          name: "noticeModify",
          component: () => import("@/components/notice/NoticeModify.vue"),
        },
        {
          path: "noticedelete/:no",
          name: "noticeDelete",
          component: () => import("@/components/notice/NoticeDelete.vue"),
        },
      ],
    },
  ],
  scrollBehavior: (to) => {
    if (to.hash) {
      return { selector: to.hash };
    } else {
      return { x: 0, y: 0 };
    }
  },
});
