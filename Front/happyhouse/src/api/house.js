import { apiInstance, houseInstance } from "./index.js";

const api = apiInstance();
const house = houseInstance();

function sidoList(success, fail) {
  api
    .get(`/map/sido`)
    .then(success)
    .catch(fail);
}

function gugunList(params, success, fail) {
  api
    .get(`/map/gugun`, { params: params })
    .then(success)
    .catch(fail);
}

function dongList(params, success, fail) {
  api
    .get(`/map/dong`, { params: params })
    .then(success)
    .catch(fail);
}

function dealList(params, success, fail) {
  house
    .get(`/map/deal`, { params: params })
    .then(success)
    .catch(fail);
}

function houseList(success, fail) {
  house
    .get(`/map/apts`)
    .then(success)
    .catch(fail);
}

function houseListBydongCode(params, success, fail) {
  house
    .get(`/map/apt`, { params: params })
    .then(success)
    .catch(fail);
}

function houseListBydongName(params, success, fail) {
  house
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
