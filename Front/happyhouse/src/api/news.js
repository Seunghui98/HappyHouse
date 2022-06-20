import { apiInstance } from "./index.js";

const api = apiInstance();

async function getNewsList(keyword, success, fail) {
  await api
    .get(`/news/${keyword}`)
    .then(success)
    .catch(fail);
}
export { getNewsList };
