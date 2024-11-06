const editarStatusCurso = document.getElementById('editarStatusCurso');
const statusCursoEditado = document.getElementById('editar');
let idStatusCurso = null;

document.addEventListener("DOMContentLoaded", function() {
    // Exibir/ocultar senha
    document.querySelectorAll('.view-password').forEach(button => {
        button.addEventListener('click', function() {
            const passwordCell = this.parentElement.previousElementSibling;
            passwordCell.textContent = passwordCell.textContent === '' ? 'admin123' : '';
        });
    });

    // Remover linha (excluir)
    document.querySelectorAll('.delete').forEach(button => {
        button.addEventListener('click', function() {
            const row = this.closest('.crud-row');
            if (row) {
                row.remove();
            }
        });
    });

    // Toggle da barra de filtro
    const filterButton = document.querySelector('.filtrar');
    const filterBar = document.getElementById('filtrar-bar');
    if (filterButton && filterBar) {
        filterButton.addEventListener('click', toggleFilterBar);
    }

    function toggleFilterBar() {
        if (filterBar) {
            filterBar.style.display = filterBar.style.display === 'none' ? 'flex' : 'none';
        }
    }

    // Popup de inserção e edição de status do curso
    const inserirStatusCursoButton = document.querySelector('.inserir-status-curso');
    const editStatusCursoButtons = document.querySelectorAll('.edit');
    const cancelStatusCursoButton = document.querySelector('.bt-cancelar');
    const cancelStatusCursoEditButton = document.querySelector('.bt-cancelar-edit');

    const popupStatusCurso = document.getElementById('popupID');
    const popupStatusCursoEdit = document.getElementById('popupIDStatusCurso');

    // Verifica se os elementos existem antes de adicionar o evento
    if (inserirStatusCursoButton) {
        inserirStatusCursoButton.addEventListener('click', togglePopup);
    }

    editStatusCursoButtons.forEach(button => {
        button.addEventListener('click', (e) => togglePopupEdit(e));
    });

    if (cancelStatusCursoButton) {
        cancelStatusCursoButton.addEventListener('click', closePopup);
    }
    if (cancelStatusCursoEditButton) {
        cancelStatusCursoEditButton.addEventListener('click', closePopupEdit);
    }

    function togglePopup() {
        if (popupStatusCurso) {
            popupStatusCurso.style.display = popupStatusCurso.style.display === 'none' ? 'flex' : 'none';
        }
    }

    function togglePopupEdit(e) {
        if (popupStatusCursoEdit) {
            popupStatusCursoEdit.style.display = popupStatusCursoEdit.style.display === 'none' ? 'flex' : 'none';
        }
    }

    function closePopup() {
        if (popupStatusCurso) {
            popupStatusCurso.style.display = 'none';
        }
    }

    function closePopupEdit() {
        if (popupStatusCursoEdit) {
            popupStatusCursoEdit.style.display = 'none';
        }
    }

    // Filtro de pesquisa
    const form = filterBar.querySelector('form');
    if (form) {
        form.addEventListener('submit', function(event) {
            event.preventDefault();

            const selectedField = this['filter-field'].value;
            const searchTerm = this['search'].value.toLowerCase();

            if (!selectedField) return;

            const gridItems = document.querySelectorAll('.grid-container .grid-item');
            gridItems.forEach(item => item.style.display = 'none');

            let found = false;

            // Ajuste no número de itens a serem agrupados na grid
            for (let i = 0; i < gridItems.length; i += 3) { // Ajuste conforme o número de itens por linha
                const registro = gridItems[i];
                const status = gridItems[i + 1];
                const acoes = gridItems[i + 2];

                let shouldDisplay = false;
                if (selectedField === 'todos') {
                    shouldDisplay = true;
                } else if (selectedField === 'registro-filtro') {
                    shouldDisplay = registro.textContent.toLowerCase().includes(searchTerm);
                } else if (selectedField === 'status-filtro') {
                    shouldDisplay = status.textContent.toLowerCase().includes(searchTerm);
                }

                if (shouldDisplay) {
                    registro.style.display = 'flex';
                    status.style.display = 'flex';
                    acoes.style.display = 'flex';
                    found = true;
                }
            }

            if (!found) {
                alert('Nenhum item encontrado.');
            }
        });
    }
});
