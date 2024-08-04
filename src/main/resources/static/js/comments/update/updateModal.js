export function handleModalUpdate() {
    // Button that triggered the modal
    const triggerBtn = event.relatedTarget;

    const comment = getCommentFromBtn(triggerBtn);

    updateModal(comment);

}

function getCommentFromBtn(triggerBtn) {
    return {
        id: triggerBtn.getAttribute('data-bs-id'),
        nickname: triggerBtn.getAttribute('data-bs-nickname'),
        body: triggerBtn.getAttribute('data-bs-body'),
        articleId: triggerBtn.getAttribute('data-bs-article-id')
    };
}

function updateModal(comment) {
    document.querySelector("#edit-comment-id").value = comment.id;
    document.querySelector("#edit-comment-nickname").value = comment.nickname;
    document.querySelector("#edit-comment-body").value = comment.body;
    document.querySelector("#edit-comment-article-id").value = comment.articleId;
}