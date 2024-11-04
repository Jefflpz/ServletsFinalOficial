document.querySelectorAll('.view-password').forEach(button => {
    button.addEventListener('click', function() {
        const passwordCell = this.parentElement.previousElementSibling;
        passwordCell.textContent = passwordCell.textContent === '' ? 'admin123' : '';
    });
});

document.querySelectorAll('.delete').forEach(button => {
    button.addEventListener('click', function() {
        this.closest('.crud-row').remove();
    });
});

document.addEventListener("DOMContentLoaded", function() {
    const filterButton = document.querySelector('.filtrar');
    const filterBar = document.getElementById('filtrar-bar');
    filterButton.addEventListener('click', toggleFilterBar);

    function toggleFilterBar() {
        filterBar.style.display = filterBar.style.display === 'none' ? 'flex' : 'none';
    }

    const inserirSetor = document.querySelector('.inserir-setor');
    const editSetor = document.querySelectorAll('.edit');
    const cancelSetor = document.querySelector('.bt-cancelar');
    const cancelSetorEdit = document.querySelector('.bt-cancelar-edit');

    const popupSetor = document.getElementById('popupID');
    const popupSetorEdit = document.getElementById('popupIDsetor');

    let i = 0; // Corrigido: declaração do índice
    for (i = 0; i < editSetor.length; i++) {
        editSetor[i].addEventListener('click', togglePopupEdit);
    }

    cancelSetor.addEventListener('click', cancelPopup);
    cancelSetorEdit.addEventListener('click', cancelPopupEdit);

    inserirSetor.addEventListener('click', togglePopup);

    function togglePopup() {
        popupSetor.style.display = popupSetor.style.display === 'none' ? 'flex' : 'none';
    }

    function togglePopupEdit() {
        popupSetorEdit.style.display = popupSetorEdit.style.display === 'none' ? 'flex' : 'none';
    }

    function cancelPopup() {
        popupSetor.style.display = 'none';
    }
    function cancelPopupEdit() {
        popupSetorEdit.style.display = 'none';
    }

    const form = filterBar.querySelector('form');
    form.addEventListener('submit', function(event) {
        event.preventDefault();

        const selectedField = this['filter-field'].value;
        const searchTerm = this['search'].value.toLowerCase();

        if (!selectedField) return; // Verificação extra

        const gridItems = document.querySelectorAll('.grid-container .grid-item');
        gridItems.forEach(item => item.style.display = 'none');

        let found = false;

        for (let i = 0; i < gridItems.length; i += 4) {
            const registro = gridItems[i];
            const UUID = gridItems[i + 1];
            const nome = gridItems[i + 2];
            const acoes = gridItems[i + 3];

            let shouldDisplay = false;
            if (selectedField === 'todos') {
                shouldDisplay = true;
            } else if (selectedField === 'registro-filtro') {
                shouldDisplay = registro.textContent.toLowerCase().includes(searchTerm);
            } else if (selectedField === 'UUID-filtro') {
                shouldDisplay = UUID.textContent.toLowerCase().includes(searchTerm);
            } else if (selectedField === 'nome-filtro') {
                shouldDisplay = nome.textContent.toLowerCase().includes(searchTerm);
            }

            if (shouldDisplay) {
                registro.style.display = 'flex';
                UUID.style.display = 'flex';
                nome.style.display = 'flex';
                acoes.style.display = 'flex';
                found = true;
            }
        }

        if (!found) {
            alert('Nenhum item encontrado.');
        }
    });
});