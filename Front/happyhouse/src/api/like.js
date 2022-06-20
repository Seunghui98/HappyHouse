import { apiInstance } from "./index.js";

const api = apiInstance();

async function registLike(like, success, fail) {
  await api
    .post(`/like`, {
      user_id: like.user_id,
      apt_code: like.apt_code,
    })
    .then(success)
    .catch(fail);
}

async function deleteLike(no, success, fail) {
  await api
    .delete(`/like`, { params: { no: no } })
    .then(success)
    .catch(fail);
}

async function getLikeHouseList(user_id, success, fail) {
  await api
    .get(`/like/list/${user_id}`)
    .then(success)
    .catch(fail);
}

async function getLike(params, success, fail) {
  await console.log(params);
  api
    .get(`/like/${params.apt_code}/${params.user_id}`)
    .then(success)
    .catch(fail);
}

async function deleteLikeByAptCode(params, success, fail) {
  await console.log(params);
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
