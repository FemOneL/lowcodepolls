function makeDraggable(element, container) {
  let active = false;
  let currentX;
  let currentY;
  let initialX;
  let initialY;
  let xOffset = 0;
  let yOffset = 0;

  element.addEventListener("mousedown", dragStart, false);
  document.addEventListener("mouseup", dragEnd, false);
  document.addEventListener("mousemove", drag, false);

  function dragStart(e) {
    initialX = e.clientX - xOffset;
    initialY = e.clientY - yOffset;

    if (e.target === element) {
      active = true;
      element.style.cursor = 'grabbing';
    }
  }

  function dragEnd(e) {
    initialX = currentX;
    initialY = currentY;
    active = false;
    element.style.cursor = 'grab';
    // Після закінчення перетягування перевіряємо, чи потрібно збільшити контейнер
    adjustContainerHeight();
  }

  function drag(e) {
    if (active) {

      e.preventDefault();

      currentX = e.clientX - initialX;
      currentY = e.clientY - initialY;

      // Обмеження переміщення в межах контейнера
      const elementRect = element.getBoundingClientRect();


      // Дозволяємо збільшення контейнера при перетягуванні вниз
      if (currentY + initialY + elementRect.height > container.offsetHeight) {
        container.style.height = `${currentY + initialY + elementRect.height}px`;
      }

      xOffset = currentX;
      yOffset = currentY;

      setTranslate(currentX, currentY, element);
    }
  }

  function setTranslate(xPos, yPos, el) {
    el.style.transform = "translate3d(" + xPos + "px, " + yPos + "px, 0)";
  }

function adjustContainerHeight() {
  // Перевіряємо, чи елементи вийшли за межі контейнера після перетягування
  const elements = container.querySelectorAll('.draggable');
  let maxBottom = 0;
  elements.forEach(el => {
    const rect = el.getBoundingClientRect();
    const containerTop = container.getBoundingClientRect().top;
    const elBottomRelativeToContainer = rect.bottom - containerTop;
    if (elBottomRelativeToContainer > maxBottom) {
      maxBottom = elBottomRelativeToContainer;
    }
  });

  // Додаємо невеликий відступ, щоб елементи не прилипали до нижньої межі контейнера
  maxBottom += 10; // Відступ в пікселях

  // Зменшуємо висоту контейнера, якщо між останнім елементом і нижньою межею контейнера є вільний простір
  if (maxBottom < container.offsetHeight) {
    container.style.height = `${maxBottom}px`;
  }
}
}

function makeResizable(element) {
  const resizer = document.createElement('div');
  resizer.style.width = '10px';
  resizer.style.height = '10px';
  resizer.style.background = 'red';
  resizer.style.position = 'absolute';
  resizer.style.right = '0';
  resizer.style.bottom = '0';
  resizer.style.cursor = 'se-resize';
  element.appendChild(resizer);

  let original_width = 0;
  let original_height = 0;
  let original_mouse_x = 0;
  let original_mouse_y = 0;

  resizer.addEventListener('mousedown', function (e) {
    e.preventDefault();
    original_width = parseFloat(getComputedStyle(element, null).getPropertyValue('width').replace('px', ''));
    original_height = parseFloat(getComputedStyle(element, null).getPropertyValue('height').replace('px', ''));
    original_mouse_x = e.clientX;
    original_mouse_y = e.clientY;
    document.addEventListener('mousemove', resize);
    document.addEventListener('mouseup', stopResize);
  });

  function resize(e) {
    const dx = e.clientX - original_mouse_x;
    const dy = e.clientY - original_mouse_y;
    const newWidth = original_width + dx;
    const newHeight = original_height + dy;
    element.style.width = newWidth + 'px';
    element.style.height = newHeight + 'px';
  }

  function stopResize() {
    document.removeEventListener('mousemove', resize);
  }
}

function createAnswerOption(questionId) {
  const answerOption = document.createElement('div');

  const radioInput = document.createElement('input');
  radioInput.type = 'radio';
  radioInput.name = questionId;
  answerOption.appendChild(radioInput);

  const answerText = document.createElement('input');
  answerText.type = 'text';
  answerText.placeholder = 'Answer option';
  answerOption.appendChild(answerText);

  return answerOption;
}

document.getElementById('save').addEventListener('click', function () {
  const editorElements = document.querySelectorAll('.editor-element');
  const elementsData = Array.from(editorElements).map(element => {
      return {
          id: element.id,
          classList: Array.from(element.classList),
          style: {
              left: element.style.left,
              top: element.style.top,
              width: element.style.width,
              height: element.style.height
          },
          // Додайте будь-які інші властивості, які вам потрібно зберегти
      };
  });

  const json = JSON.stringify(elementsData, null, 2);
  console.log(json); // Показуємо JSON у консолі для перевірки

  // Тут ви можете зберегти JSON у файл, відправити на сервер або використати як потрібно
});