document.getElementById('addQuestion').addEventListener('click', function () {
    const editor = document.getElementById('editor');
    const questionId = `question-${editor.childNodes.length + 1}`;
    const questionContainer = document.createElement('div');
    questionContainer.classList.add('editor-element');
    questionContainer.classList.add('draggable');
    questionContainer.setAttribute('id', questionId);
    questionContainer.style.left = '10px';
    questionContainer.style.top = `${editor.childNodes.length * 60}px`;

    const questionText = document.createElement('input');
    questionText.type = 'text';
    questionText.placeholder = 'Enter your question here';
    questionContainer.appendChild(questionText);

    const answerOption = createAnswerOption(questionId);
    questionContainer.appendChild(answerOption);

    const addOptionButton = document.createElement('button');
    addOptionButton.textContent = '+ Add Option';
    addOptionButton.type = 'button';
    addOptionButton.onclick = function () {
        const newOption = createAnswerOption(questionId);
        questionContainer.insertBefore(newOption, addOptionButton);
    };
    
    const deleteQuestionButton = document.createElement('div');
    deleteQuestionButton.innerHTML = '&times;';
    deleteQuestionButton.style.position = 'absolute';
    deleteQuestionButton.style.top = '0';
    deleteQuestionButton.style.right = '5px';
    deleteQuestionButton.style.cursor = 'pointer';
    deleteQuestionButton.style.fontSize = '20px';
    deleteQuestionButton.style.fontWeight = 'bold';
    deleteQuestionButton.onclick = function () {
        editor.removeChild(questionContainer);
    };

    questionContainer.appendChild(deleteQuestionButton);

    questionContainer.appendChild(addOptionButton);

    editor.appendChild(questionContainer);
    makeDraggable(questionContainer, editor);
    // makeResizable(questionContainer);
});