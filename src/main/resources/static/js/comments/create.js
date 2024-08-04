export function handleCommentSubmit() {
    const comment = createCommentObject();
    console.log(comment);          // 댓글 객체 출력
    sendCommentToServer(comment);
}


function createCommentObject() {
    return {
        nickname: document.querySelector("#new-comment-nickname").value,
        body: document.querySelector("#new-comment-body").value,
        articleId: document.querySelector("#new-comment-article-id").value
    };
}


function sendCommentToServer(comment) {
      const url = "/api/articles/" + comment.articleId + "/comments";
      fetch(url, {
          method: "POST",
          headers: {
                "Content-Type": "application/json"
          },
          body: JSON.stringify(comment)
      })
      .then(handleServerResponse)
      .catch(handleError);
}

function handleServerResponse(response) {
      const msg = (response.ok) ? "comment registered." : "fail to register comment!";
      alert(msg);
      // 현재 페이지 새로 고침
      window.location.reload();
}

function handleError(error) {
    console.error("Error:", error);
    alert("An error occurred while submitting the comment.");
}
