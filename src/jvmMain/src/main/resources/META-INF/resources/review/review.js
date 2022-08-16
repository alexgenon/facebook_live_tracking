function reviewMessage(reviewItemData){
    let item=JSON.parse(reviewItemData);
    let cell=document.getElementById(item);
    cell.classList.add("toReview")
}

function generateQuestionHTMLID(question){
    return question.id;
}
function questionAnswered(questionElement,clickEvent){
    console.log(questionElement.id + " has been answered");
    questionElement.remove()

    let validationPost = new Request('/reviews/questions/'+questionElement.id+'/answered',
        {
            method: 'POST'
        });
    fetch(validationPost)
        .then(response => console.log(response));
}

function elementReviewed(cell,clickEvent){
    console.log(cell.id+" has been reviewed");
    cell.classList.remove("toReview");
    let validationPost= new Request('/reviews/items/'+cell.id+'/reviewed',
        {
            method: 'POST'
        });
    fetch(validationPost)
        .then(response => console.log(response));
}

function createQuestionElement(question){
    let questionElement = document.createElement("article")
    let questionHTMLID = generateQuestionHTMLID(question);
    questionElement.id= questionHTMLID;
    questionElement.classList.add("questionArticle");
    questionElement.addEventListener("click",clickEvent => questionAnswered(questionElement,clickEvent));

    let author=document.createElement("span");
    author.classList.add("author");
    author.textContent=question.author;
    questionElement.appendChild(author);
    let questionText=document.createElement("span");
    questionText.classList.add("question");
    questionText.textContent=question.questionText;
    questionElement.appendChild(questionText);
    return questionElement;
}

function reviewQuestion(questionData){
    console.log("call to reviewQuestion");
    console.log(questionData)
    let question = JSON.parse(questionData)
    var questionElement = document.getElementById(generateQuestionHTMLID(question))
    let questionList=document.getElementById("questionPanel")

    if(question.status == "UNANSWERED"){
        console.log("New question "+question.id);
        if(questionElement==null){
            questionList.appendChild(createQuestionElement(question))
        }
    } else if(question.status == "ANSWERED") {
        console.log("Question "+question.id+" has been answered");
        if(questionElement!=null){
            questionList.removeChild(questionElement);
        }
    }
}

function createReviewTable(){
    let tableBody = document.getElementById("reviewTableBody");
    for(i=0;i<10;i++){
        let row=document.createElement("tr");
        for(j=1;j<=10;j++){
            let cell=document.createElement("td");
            cell.id=i*10+j;
            cell.textContent=cell.id;
            cell.addEventListener("click",clickEvent => elementReviewed(cell,clickEvent));
            row.appendChild(cell);
        }
        tableBody.appendChild(row);
    }
}