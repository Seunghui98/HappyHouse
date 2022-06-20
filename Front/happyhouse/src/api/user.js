import { apiInstance } from "./index.js";

const api = apiInstance();

async function login(user, success, fail) {
  await api
    .post(`/user/login`, { id: user.userid, password: user.userpwd })
    .then(success)
    .catch(fail);
}

async function findById(userid, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api
    .get(`user/info/${userid}`)
    .then(success)
    .catch(fail);
}

async function modifyUserInfo(user, success, fail) {
  await api
    .put(`/user`, {
      id: user.id,
      name: user.name,
      address: user.address,
      phoneNumber: user.phoneNumber,
    })
    .then(success)
    .catch(fail);
}

async function registUserInfo(user, success, fail) {
  await api
    .post(`/user`, {
      id: user.id,
      password: user.password,
      name: user.name,
      address: user.address,
      phoneNumber: user.phoneNumber,
    })
    .then(success)
    .catch(fail);
}

async function modifyUserPassword(user, success, fail) {
  await api
    .put(`/user/password`, {
      id: user.id,
      password: user.password,
    })
    .then(success)
    .catch(fail);
}

async function deleteUser(id, success, fail) {
  await api
    .delete(`/user/password`, id)
    .then(success)
    .catch(fail);
}

export {
  login,
  findById,
  modifyUserInfo,
  modifyUserPassword,
  deleteUser,
  registUserInfo,
};
