import { apiInstance } from "./index.js";

const api = apiInstance();

function registLike(like, success, fail) {
  api
    .post(`/like`, {
      user_id: like.user_id,
      apt_code: like.apt_code,
    })
    .then(success)
    .catch(fail);
}

function deleteLike(no, success, fail) {
  api
    .delete(`/like`, { params: { no: no } })
    .then(success)
    .catch(fail);
}

function getLikeHouseList(user_id, success, fail) {
  api
    .get(`/like/list/${user_id}`)
    .then(success)
    .catch(fail);
}

function getLike(params, success, fail) {
  console.log(params);
  api
    .get(`/like/${params.apt_code}/${params.user_id}`)
    .then(success)
    .catch(fail);
}

function deleteLikeByAptCode(params, success, fail) {
  console.log(params);
  api
    .delete(`/like/${params.apt_code}/${params.user_id}`)
    .then(success)
    .catch(fail);
}

export {
  registLike,
  deleteLike,
  getLikeHouseList,
  getLike,
  deleteLikeByAptCode,
};
