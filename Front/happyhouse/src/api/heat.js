import { apiInstance } from "./index.js";

const api = apiInstance();

function registHeat(dongName, success, fail) {
  api
    .post(`/heat/${dongName}`)
    .then(success)
    .catch(fail);
}

function getHeatList(success, fail) {
  api
    .get(`/heat`)
    .then(success)
    .catch(fail);
}

export { registHeat, getHeatList };
