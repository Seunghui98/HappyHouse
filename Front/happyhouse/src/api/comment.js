import { apiInstance } from "./index.js";

const api = apiInstance();

async function registComment(comment, success, fail) {
  console.log(comment);
  await api
    .post(`/comment/${comment.Board_no}`, {
      user_id: comment.user_id,
      content: comment.content,
      Board_no: parseInt(comment.Board_no),
    })
    .then(success)
    .catch(fail);
}

async function getCommentList(Board_no, success, fail) {
  await api
    .get(`/comment/${Board_no}`)
    .then(success)
    .catch(fail);
}

async function deleteComment(comment_no, success, fail) {
  console.log(comment_no);
  await api
    .delete(`/comment/${comment_no}`)
    .then(success)
    .catch(fail);
}
async function modifyComment(comment, success, fail) {
  await api
    .put(`/comment`, {
      content: comment.content,
      comment_no: comment.comment_no,
    })
    .then(success)
    .catch(fail);
}

export { registComment, deleteComment, getCommentList, modifyComment };
