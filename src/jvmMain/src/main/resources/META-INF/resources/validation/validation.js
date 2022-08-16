function commentValidated(id, validatedAction, event) {
    console.log(id + " has been validated, action is " + validatedAction);
    let targetRow = document.getElementById(id);
    //targetRow.classList.add("treated")
    targetRow.remove()

    let validationBody = new URLSearchParams();
    validationBody.append("action",validatedAction);

    let validationPost = new Request('/live/comments/validation/'+id,
        {
            method: 'POST',
            body: validationBody
        });
    fetch(validationPost)
        .then(response => console.log(response));
}

function bulkValidate(count){
    let comments = document.getElementById("validationBody")
        .querySelectorAll("tr");
    for (let i = 0; i < comments.length && i < count; i++) {
        let itemId = comments[i].id;
        commentValidated(itemId,"NOTHING",null)
    }
}

function createCorrectionCell(id) {
    let buy = document.createElement("button");
    buy.textContent = "€";
    buy.classList.add("validationButton");
    buy.addEventListener("click", clickEvent => {
        commentValidated(id, "BUY", clickEvent);
    });

    let review = document.createElement("button");
    review.textContent = "👁";
    review.classList.add("validationButton");
    review.addEventListener("click", clickEvent => {
        commentValidated(id, "REVIEW", clickEvent);
    });

    let question = document.createElement("button");
    question.textContent = "?";
    question.classList.add("validationButton");
    question.addEventListener("click", clickEvent => {
        commentValidated(id, "QUESTION", clickEvent);
    });

    let nothing = document.createElement("button");
    nothing.textContent = "∅";
    nothing.classList.add("validationButton");
    nothing.addEventListener("click", clickEvent => {
        commentValidated(id, "NOTHING", clickEvent);
    });

    let correction = document.createElement("td");
    correction.appendChild(buy);
    correction.appendChild(review);
    correction.appendChild(question);
    correction.appendChild(nothing);
    correction.classList.add("correctionBar")
    return correction;
}
function addComment(commentData){
    let commentTable = document.getElementById("validationBody");
    let comment = JSON.parse(commentData);
    let id = comment.id;
    let newRow = document.createElement("tr");
    newRow.id = id;
    newRow.appendChild(createCorrectionCell(id));

    let name = document.createElement("td");
    name.textContent = comment.user.name;
    name.addEventListener("click",clickEvent => {
        navigator.clipboard.writeText(name.textContent);
    });
    newRow.appendChild(name);

    let item = document.createElement("td");
    item.textContent = comment.item;
    item.classList.add("itemNumber")
    newRow.appendChild(item);

    let action = document.createElement("td");
    action.textContent = "✔" + comment.action;
    action.classList.add("validationButton");
    action.addEventListener("click", clickEvent => {
        commentValidated(id, comment.action, clickEvent);
    });
    newRow.appendChild(action);

    let fullComment = document.createElement("td");
    fullComment.textContent = comment.fullComment;
    newRow.appendChild(fullComment);

    commentTable.appendChild(newRow);
}
function newComment(event) {
    addComment(event.data);
}
