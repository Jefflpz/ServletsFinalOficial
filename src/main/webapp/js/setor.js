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

    const inserirSetorButton = document.querySelector('.inserir-setor');
    const editButtons = document.querySelectorAll('.edit');
    const cancelButton = document.querySelector('.bt-cancelar');
    const cancelEditButton = document.querySelector('.bt-cancelar-edit');

    const popupInsert = document.getElementById('popupID');
    const popupEdit = document.getElementById('popupIDsetor');

    // Evento para abrir o popup de inserção
    inserirSetorButton.addEventListener('click', function() {
        togglePopup(popupInsert);
    });

    // Evento para cada botão de edição abrir o popup de edição
    editButtons.forEach(button => {
        button.addEventListener('click', function() {
            togglePopup(popupEdit);
        });
    });

    // Cancelar popups
    cancelButton.addEventListener('click', function() {
        popupInsert.style.display = 'none';
    });
    cancelEditButton.addEventListener('click', function() {
        popupEdit.style.display = 'none';
    });

    function togglePopup(popup) {
        popup.style.display = popup.style.display === 'none' ? 'flex' : 'none';
    }

    // Filtro de pesquisa
    const form = filterBar.querySelector('form');
    form.addEventListener('submit', function(event) {
        event.preventDefault();

        const selectedField = this['filter-field'].value;
        const searchTerm = this['search'].value.toLowerCase();

        if (!selectedField) return; // Verificação extra

        const gridItems = document.querySelectorAll('.grid-container .grid-item');
        gridItems.forEach(item => item.style.display = 'none');

        let found = false;

        for (let i = 0; i < gridItems.length; i += 3) {
            const registro = gridItems[i];
            const setor = gridItems[i + 1];
            const acoes = gridItems[i + 2];

            let shouldDisplay = false;
            if (selectedField === 'todos') {
                shouldDisplay = true;
            } else if (selectedField === 'registro-filtro') {
                shouldDisplay = registro.textContent.toLowerCase().includes(searchTerm);
            } else if (selectedField === 'setor-filtro') {
                shouldDisplay = setor.textContent.toLowerCase().includes(searchTerm);
            }

            if (shouldDisplay) {
                registro.style.display = 'flex';
                setor.style.display = 'flex';
                acoes.style.display = 'flex';
                found = true;
            }
        }

        if (!found) {
            alert('Nenhum item encontrado.');
        }
    });
});
