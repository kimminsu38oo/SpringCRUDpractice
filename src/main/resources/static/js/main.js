import { handleCommentSubmit } from './comments/create.js';
import { handleModalUpdate } from './comments/update/updateModal.js';
import { handleCommentUpdate } from './comments/update/update.js';


document.addEventListener('DOMContentLoaded', function() {
    const commentCreateBtn = document.querySelector("#comment-create-btn");
    const commentEditBtn = document.querySelector("#comment-edit-modal");
    const commentUpdateBtn = document.querySelector("#comment-update-btn");

    if (commentCreateBtn) {
        commentCreateBtn.addEventListener("click", handleCommentSubmit);
    }

    if (commentEditBtn) {
        commentEditBtn.addEventListener("show.bs.modal",handleModalUpdate);
    }

    if (commentUpdateBtn) {
        commentUpdateBtn.addEventListener("click", handleCommentUpdate);
    }
});

