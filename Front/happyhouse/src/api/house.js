import { apiInstance, houseInstance } from "./index.js";

const api = apiInstance();
const house = houseInstance();

async function sidoList(success, fail) {
  await api
    .get(`/map/sido`)
    .then(success)
    .catch(fail);
}

async function gugunList(params, success, fail) {
  await api
    .get(`/map/gugun`, { params: params })
    .then(success)
    .catch(fail);
}

async function dongList(params, success, fail) {
  await api
    .get(`/map/dong`, { params: params })
    .then(success)
    .catch(fail);
}

async function dealList(params, success, fail) {
  await house
    .get(`/map/deal`, { params: params })
    .then(success)
    .catch(fail);
}

async function houseList(success, fail) {
  await house
    .get(`/map/apts`)
    .then(success)
    .catch(fail);
}

async function houseListBydongCode(params, success, fail) {
  await house
    .get(`/map/apt`, { params: params })
    .then(success)
    .catch(fail);
}

async function houseListBydongName(params, success, fail) {
  await house
    .get(`/map/apt/dongName`, { params: params })
    .then(success)
    .catch(fail);
}

export {
  sidoList,
  gugunList,
  dongList,
  houseList,
  houseListBydongName,
  houseListBydongCode,
  dealList,
};
