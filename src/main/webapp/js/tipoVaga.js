document.addEventListener("DOMContentLoaded", function() {
    // Alternar visibilidade da senha
    document.querySelectorAll('.view-password').forEach(button => {
        button.addEventListener('click', function() {
            const passwordCell = this.parentElement.previousElementSibling;
            passwordCell.textContent = passwordCell.textContent === '' ? 'admin123' : '';
        });
    });

    // Remover linha ao clicar no botão de exclusão
    document.querySelectorAll('.delete').forEach(button => {
        button.addEventListener('click', function() {
            const row = this.closest('.crud-row');
            if (row) {
                row.remove();
            }
        });
    });

    // Alternar barra de filtro
    const filterButton = document.querySelector('.filtrar');
    const filterBar = document.getElementById('filtrar-bar');
    filterButton.addEventListener('click', toggleFilterBar);

    function toggleFilterBar() {
        filterBar.style.display = filterBar.style.display === 'none' ? 'flex' : 'none';
    }

    // Popups de inserção e edição de vaga
    const inserirVagaButton = document.querySelector('.inserir-vaga');
    const editVagaButtons = document.querySelectorAll('.edit');
    const cancelVagaButton = document.querySelector('.bt-cancelar');
    const cancelVagaEditButton = document.querySelector('.bt-cancelar-edit');

    const popupVaga = document.getElementById('popupID');
    const popupVagaEdit = document.getElementById('popupIDTipoVaga');

    if (inserirVagaButton) {
        inserirVagaButton.addEventListener('click', togglePopup);
    }

    editVagaButtons.forEach(button => {
        button.addEventListener('click', togglePopupEdit);
    });

    if (cancelVagaButton) {
        cancelVagaButton.addEventListener('click', closePopup);
    }
    if (cancelVagaEditButton) {
        cancelVagaEditButton.addEventListener('click', closePopupEdit);
    }

    function togglePopup() {
        popupVaga.style.display = popupVaga.style.display === 'none' ? 'flex' : 'none';
    }

    function togglePopupEdit() {
        popupVagaEdit.style.display = popupVagaEdit.style.display === 'none' ? 'flex' : 'none';
    }

    function closePopup() {
        popupVaga.style.display = 'none';
    }

    function closePopupEdit() {
        popupVagaEdit.style.display = 'none';
    }

    // Filtro de pesquisa
    const form = filterBar.querySelector('form');
    form.addEventListener('submit', function(event) {
        event.preventDefault();

        const selectedField = this['filter-field'].value;
        const searchTerm = this['search'].value.toLowerCase();

        if (!selectedField) return;

        const gridItems = document.querySelectorAll('.grid-container .grid-item');
        gridItems.forEach(item => item.style.display = 'none');

        let found = false;

        for (let i = 0; i < gridItems.length; i += 3) {
            const registro = gridItems[i];
            const tipoVaga = gridItems[i + 1];
            const acoes = gridItems[i + 2];

            let shouldDisplay = false;
            if (selectedField === 'todos') {
                shouldDisplay = true;
            } else if (selectedField === 'registro-filtro') {
                shouldDisplay = registro.textContent.toLowerCase().includes(searchTerm);
            } else if (selectedField === 'tipoVaga-filtro') {
                shouldDisplay = tipoVaga.textContent.toLowerCase().includes(searchTerm);
            }

            if (shouldDisplay) {
                registro.style.display = 'flex';
                tipoVaga.style.display = 'flex';
                acoes.style.display = 'flex';
                found = true;
            }
        }

        if (!found) {
            alert('Nenhum item encontrado.');
        }
    });
});
