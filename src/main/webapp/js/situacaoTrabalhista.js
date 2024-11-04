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

    const inserirSituacao = document.querySelector('.inserir-situacao');
    const editSituacao = document.querySelectorAll('.edit');
    const cancelSituacao = document.querySelector('.bt-cancelar');
    const cancelSituacaoEdit = document.querySelector('.bt-cancelar-edit');

    const popupSituacao = document.getElementById('popupID');
    const popupSituacaoEdit = document.getElementById('popupIDsituacao');

    let i = 0; // Corrigido: declaração do índice
    for (i = 0; i < editSituacao.length; i++) {
        editSituacao[i].addEventListener('click', togglePopupEdit);
    }

    cancelSituacao.addEventListener('click', cancelPopup);
    cancelSituacaoEdit.addEventListener('click', cancelPopupEdit);

    inserirSituacao.addEventListener('click', togglePopup);

    function togglePopup() {
        popupSituacao.style.display = popupSituacao.style.display === 'none' ? 'flex' : 'none';
    }

    function togglePopupEdit() {
        popupSituacaoEdit.style.display = popupSituacaoEdit.style.display === 'none' ? 'flex' : 'none';
    }

    function cancelPopup() {
        popupSituacao.style.display = 'none';
    }
    function cancelPopupEdit() {
        popupSituacaoEdit.style.display = 'none';
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
            const nome = gridItems[i + 1];
            const status = gridItems[i + 2];
            const acoes = gridItems[i + 3];

            let shouldDisplay = false;
            if (selectedField === 'todos') {
                shouldDisplay = true;
            } else if (selectedField === 'registro-filtro') {
                shouldDisplay = registro.textContent.toLowerCase().includes(searchTerm);
            } else if (selectedField === 'nome-filtro') {
                shouldDisplay = nome.textContent.toLowerCase().includes(searchTerm);
            } else if (selectedField === 'status-filtro') {
                shouldDisplay = status.textContent.toLowerCase().includes(searchTerm);
            }

            if (shouldDisplay) {
                registro.style.display = 'flex';
                nome.style.display = 'flex';
                status.style.display = 'flex';
                acoes.style.display = 'flex';
                found = true;
            }
        }

        if (!found) {
            alert('Nenhum item encontrado.');
        }
    });
});