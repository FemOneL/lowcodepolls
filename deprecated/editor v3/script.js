document.addEventListener('DOMContentLoaded', () => {
    const dragItems = document.querySelectorAll('.drag-item');
    const dropArea = document.getElementById('drop-area');

    dragItems.forEach(item => {
        item.addEventListener('dragstart', handleDragStart);
    });

    dropArea.addEventListener('dragover', handleDragOver);
    dropArea.addEventListener('drop', handleDrop);

    function handleDragStart(e) {
        e.dataTransfer.setData('text/plain', this.id);
    }

    function handleDragOver(e) {
        e.preventDefault();
    }

    function handleDrop(e) {
        e.preventDefault();
        const originalItemId = e.dataTransfer.getData('text/plain');
        const originalItem = document.getElementById(originalItemId);

        const clone = originalItem.cloneNode(true);
        clone.id = `clone_${new Date().getTime()}`;
        clone.draggable = false;

        // Додаємо кнопки для переміщення елемента вгору та вниз
        const moveUpButton = document.createElement('button');
        moveUpButton.innerText = '↑';
        moveUpButton.addEventListener('click', () => moveElement(clone, true));

        const moveDownButton = document.createElement('button');
        moveDownButton.innerText = '↓';
        moveDownButton.addEventListener('click', () => moveElement(clone, false));

        clone.appendChild(moveUpButton);
        clone.appendChild(moveDownButton);

        if (e.target.classList.contains('drop-area')) {
            dropArea.appendChild(clone);
        } else if (e.target.classList.contains('drag-item')) {
            const rect = e.target.getBoundingClientRect();
            const offset = e.clientY - rect.top - rect.height / 2;
            if (offset < 0) {
                e.target.parentNode.insertBefore(clone, e.target);
            } else {
                e.target.parentNode.insertBefore(clone, e.target.nextSibling);
            }
        }
    }

    function moveElement(element, moveUp) {
        if (moveUp) {
            if (element.previousElementSibling) {
                element.parentNode.insertBefore(element, element.previousElementSibling);
            }
        } else {
            if (element.nextElementSibling) {
                element.parentNode.insertBefore(element.nextElementSibling, element);
            }
        }
    }
});