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

    const inserirVaga = document.querySelector('.inserir-vaga');
    const editVaga = document.querySelectorAll('.edit');
    const cancelVaga = document.querySelector('.bt-cancelar');
    const cancelVAGAedi = document.querySelector('.bt-cancelar-edit');

    const popupVaga = document.getElementById('popupID');
    const popupVagaedit = document.getElementById('popupIDVaga');

    let i = 0; // Corrigido: declaração do índice
    for (i = 0; i < editVaga.length; i++) {
        editVaga[i].addEventListener('click', togglePopupedit);
    }

    cancelVaga.addEventListener('click', cancelPopup);
    cancelVAGAedi.addEventListener('click', cancelPopupedit);

    inserirVaga.addEventListener('click', togglePopup);

    function togglePopup() {
        popupVaga.style.display = popupVaga.style.display === 'none' ? 'flex' : 'none';
    }

    function togglePopupedit() {
        popupVagaedit.style.display = popupVagaedit.style.display === 'none' ? 'flex' : 'none';
    }

    function cancelPopup() {
        popupVaga.style.display = 'none';
    }
    function cancelPopupedit() {
        popupVagaedit.style.display = 'none';
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
            const uuid = gridItems[i + 1];
            const nome = gridItems[i + 2];
            const acoes = gridItems[i + 3];

            let shouldDisplay = false;
            if (selectedField === 'todos') {
                shouldDisplay = true;
            } else if (selectedField === 'registro-filtro') {
                shouldDisplay = registro.textContent.toLowerCase().includes(searchTerm);
            } else if (selectedField === 'nome-filtro') {
                shouldDisplay = nome.textContent.toLowerCase().includes(searchTerm);
            } else if (selectedField === 'uuid-filtro') {
                shouldDisplay = uuid.textContent.toLowerCase().includes(searchTerm);
            }

            if (shouldDisplay) {
                registro.style.display = 'flex';
                nome.style.display = 'flex';
                uuid.style.display = 'flex';
                acoes.style.display = 'flex';
                found = true;
            }
        }

        if (!found) {
            alert('Nenhum item encontrado.');
        }
    });
});