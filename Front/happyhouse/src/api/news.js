import { apiInstance } from "./index.js";

const api = apiInstance();

function getNewsList(keyword, success, fail) {
  api
    .get(`/news/${keyword}`)
    .then(success)
    .catch(fail);
}
export { getNewsList };
