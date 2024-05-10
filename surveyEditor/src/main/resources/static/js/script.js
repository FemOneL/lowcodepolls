document.addEventListener('DOMContentLoaded', function () {
  var contentComponents = [];
  var surveyId;
  var simplePoll = 'SIMPLE';

  var components = [simplePoll];

  function init() {
    const editData = document.getElementById('editData')
    if (editData?.value) {
      importData(editData.value);
    }
    renderComponents();
    renderContentComponents();
    initSortable();
  }

  function addOption(container, index, value = undefined, answerId = undefined) {
    const containerDiv = document.getElementById(container);
    const newOptionDiv = document.createElement('div');
    const newOptionInput = document.createElement('input');

    newOptionInput.classList.add('form-control');
    newOptionInput.type = 'text';
    newOptionInput.classList.add('answerOption-' + index);
    if (answerId) {
      newOptionInput.setAttribute('answerId', answerId);
    }

    if (value) {
      newOptionInput.value = value;
    } else {
      newOptionInput.placeholder = 'Варіант відповіді'
    }

    newOptionDiv.classList.add('input-group');
    newOptionDiv.classList.add('mb-3');
    newOptionDiv.appendChild(newOptionInput);

    containerDiv.appendChild(newOptionDiv);
  }

  function renderComponents() {
    var componentsList = document.getElementById('components_list');
    componentsList.innerHTML = '';
    components.forEach(function (component) {
      var li = document.createElement('li');
      li.innerHTML = `<div class="card card-outline-secondary text-xs-center">
                        <div class="card-block">${component}</div>
                      </div>`;
      componentsList.appendChild(li);
    });
  }

  function populatePoll(component, index) {
    const question = document.getElementById('question-' + index);

    if (component.question !== undefined && component.question.trim() !== '') {
      question.value = component.question;
    }

    Array.from(component.answers).forEach((answer) => {
      addOption('answers-' + index, index, answer.answer, answer.id);
    });
  }

  function renderContentComponents() {
    var contentComponentsList = document.getElementById('content_components_list');
    contentComponentsList.innerHTML = '';

    contentComponents.forEach(function (component, index) {
      component.componentId = 'component-' + index;
      var li = document.createElement('li');
      li.innerHTML = getSimplePollElement(component, index);
      contentComponentsList.appendChild(li);
      populatePoll(component, index);
      document.getElementById('addAnswer-' + index).addEventListener('click', () => addOption('answers-' + index, index));
    });
  }

  window.removeComponent = function (index) {
    collectData();
    contentComponents.splice(index, 1);
    renderContentComponents();
  };

  window.editComponent = function (component) {
    alert("Pendiente");
  };

  function collectData() {
    Array.from(contentComponents).forEach((component) => {
      const componentId = component.componentId;
      const index = componentId.charAt(componentId.length - 1);
      const question = document.getElementById('question-' + index);
      const answers = document.getElementsByClassName('answerOption-' + index);

      component.question = question.value;
      component.answers = [];
      Array.from(answers).forEach((currentAnswer) => {
        let answer = {
          answer: currentAnswer.value
        }
        let answerId = currentAnswer.getAttribute('answerId');
        if (answerId) {
          answer.id = answerId;
        }
        component.answers.push(answer);
      });
    });
  }

  function initSortable() {
    $("#components_list, #content_components_list").sortable({
      connectWith: "#content_components_list",
      placeholder: "placeholder",
      remove: function (e, li) {
        console.log(contentComponents);
        collectData();
        let component = {
          componentId: undefined,
          type: 'SIMPLE',
          question: '',
          answers: []
        };
        contentComponents.splice(li.item.index(), 0, component);
        renderContentComponents();
        $(this).sortable('cancel');
      },
      update: function (evt, ui) {
        if (ui.item.parent().attr('id') === 'content_components_list' && ui.item.parent().attr('id') === ui.item.data('start_list_id')) {
          const div = ui.item.get(0).querySelector('div');
          const from = contentComponents.findIndex(item => item.componentId === div.id);
          const to = ui.item.index();

          const [item] = contentComponents.splice(from, 1);

          contentComponents.splice(to, 0, item);

          Array.from(contentComponents).forEach((component, index) => {
            component.componentId = `component-${index}`;
          });

          console.log(contentComponents);
          renderContentComponents();
        }
      },
      start: function (evt, ui) {
        ui.item.data('start_list_id', ui.item.parent().attr('id'));

        movingIndex = ui.item.index();
      },
    }).disableSelection();
  }

  function importData(data) {
    contentComponents = [];
    const parsedData = JSON.parse(data);
    if (parsedData?.id) {
      surveyId = parsedData.id;
    }
    contentComponents = parsedData.questions;

    contentComponents.sort((a, b) => {
      let numA = parseInt(a.componentId.charAt(a.componentId.length - 1));
      let numB = parseInt(b.componentId.charAt(b.componentId.length - 1));

      return numA - numB;
    });

    renderContentComponents();
  }

  $('#importData').click(() => {
    let mock = `{
      "questions":[
         {
            "componentId":"component-0",
            "type":"SIMPLE",
            "question":"question?",
            "answers":[]
         },
         {
            "componentId":"component-1",
            "type":"SIMPLE",
            "question":"not a question?",
            "answers":[
               {
                  "answer":"yes"
               },
               {
                  "answer":"no"
               }
            ]
         }
      ]
   }`;
    importData(mock);
  });

  function saveSurvey(jsonString) {
    const url = "http://localhost:8081/save";
    fetch(url, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: jsonString
    })
      .then(response => {
        if (!response.ok) {
          console.error('error!');
          return;
        }
        return response.json();
      })
      .then(data => {
        console.log('saved');
        window.location.replace(`http://localhost:8081/editor/${data.id}/edit`);
      })
      .catch(error => {
        console.error('There was a problem with your fetch operation:', error);
      });
  }

  $('#saveButton').click(function () {
    let hasEmptyFields = false;
    $('input:not([type=hidden])').removeClass('input-error');

    $('input:not([type=hidden])').each(function () {
      if (!$(this).val()) {
        $(this).addClass('input-error');
        hasEmptyFields = true;
      }
    });

    if (hasEmptyFields) {
      console.log("Є пусті поля.");
      return;
    }

    collectData();
    let survey = {
      id: surveyId,
      questions: contentComponents
    }
    let sendJson = JSON.stringify(survey);
    saveSurvey(sendJson);
  });

  function getSimplePollElement(component, index) {
    return `<div id='component-${index}' class="card card-outline-secondary text-xs-center">
    <div class="card-block">
        <a class="btn btn-danger" href="#" onclick="removeComponent(${index})">
          <i class="fa fa-trash-o fa-lg fa-fw"></i>
        </a>
        <label>${component.type}</label>
        <div class="form-group">
            <input id='question-${index}' type="text" class="form-control" id="questionInput" placeholder="Введіть питання">
        </div>
        <div id="answers-${index}" class="form-group">

        </div>
        <input id="addAnswer-${index}" type="button" value="Додати варіант відповіді">
    </div>
  </div>`;
  }

  init();
});