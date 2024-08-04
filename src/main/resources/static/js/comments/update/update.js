export function handleCommentUpdate() {

    const comment = createCommentObject();
    console.log(comment);          // 댓글 객체 출력
    sendCommentToServer(comment);
}

function createCommentObject() {
    return {
        id: document.querySelector("#edit-comment-id").value,
        nickname: document.querySelector("#edit-comment-nickname").value,
        body: document.querySelector("#edit-comment-body").value,
        articleId: document.querySelector("#edit-comment-article-id").value
    };
}


function sendCommentToServer(comment) {
    const url = "/api/comments/" + comment.id;
    fetch(url, {
        method: "PATCH",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(comment)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Server responded with an error');
        }
        return response.json();
    })
    .then(data => {
        alert("Comment edited successfully");
        window.location.reload();
    })
    .catch(error => {
        console.error('Error:', error);
        alert("Failed to update comment");
    });
}
