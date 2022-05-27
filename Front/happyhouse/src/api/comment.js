import { apiInstance } from "./index.js";

const api = apiInstance();

function registComment(comment, success, fail) {
  console.log(comment);
  api
    .post(`/comment/${comment.Board_no}`, {
      user_id: comment.user_id,
      content: comment.content,
      Board_no: parseInt(comment.Board_no),
    })
    .then(success)
    .catch(fail);
}

function getCommentList(Board_no, success, fail) {
  api
    .get(`/comment/${Board_no}`)
    .then(success)
    .catch(fail);
}

function deleteComment(comment_no, success, fail) {
  console.log(comment_no);
  api
    .delete(`/comment/${comment_no}`)
    .then(success)
    .catch(fail);
}
function modifyComment(comment, success, fail) {
  api
    .put(`/comment`, {
      content: comment.content,
      comment_no: comment.comment_no,
    })
    .then(success)
    .catch(fail);
}

export { registComment, deleteComment, getCommentList, modifyComment };
