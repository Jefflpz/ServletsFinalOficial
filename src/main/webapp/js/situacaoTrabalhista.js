const editarSituacaoTrabalhistaForm = document.getElementById('editarSituacaoTrabalhista');
const situacaoTrabalhistaEditada = document.getElementById('editar');
let idSituacaoTrabalhista = null;

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
    if (filterButton) {
        filterButton.addEventListener('click', toggleFilterBar);
    }

    function toggleFilterBar() {
        if (filterBar) {
            filterBar.style.display = filterBar.style.display === 'none' ? 'flex' : 'none';
        }
    }

    // Popup de inserção e edição de situação
    const inserirSituacaoButton = document.querySelector('.inserir-situacao');
    const editSituacaoButtons = document.querySelectorAll('.edit');
    const cancelSituacaoButton = document.querySelector('.bt-cancelar');
    const cancelSituacaoEditButton = document.querySelector('.bt-cancelar-edit');

    const popupSituacao = document.getElementById('popupID');
    const popupSituacaoEdit = document.getElementById('popupIDSituacaoTrabalhista');

    // Verifica se os elementos existem antes de adicionar o evento
    if (inserirSituacaoButton) {
        inserirSituacaoButton.addEventListener('click', togglePopup);
    }

    editSituacaoButtons.forEach(button => {
        button.addEventListener('click', (e) => togglePopupEdit(e));
    });

    if (cancelSituacaoButton) {
        cancelSituacaoButton.addEventListener('click', closePopup);
    }
    if (cancelSituacaoEditButton) {
        cancelSituacaoEditButton.addEventListener('click', closePopupEdit);
    }

    function togglePopup() {
        if (popupSituacao) {
            popupSituacao.style.display = popupSituacao.style.display === 'none' ? 'flex' : 'none';
        }
    }

    function togglePopupEdit(e) {
        if (popupSituacaoEdit) {
            popupSituacaoEdit.style.display = popupSituacaoEdit.style.display === 'none' ? 'flex' : 'none';
            let nomeSituacaoTrabalhista = document.getElementById('editar');
            nomeSituacaoTrabalhista.value = e.currentTarget.getAttribute('data-nomeSituacaoTrabalhista');

            idSituacaoTrabalhista = e.currentTarget.getAttribute('data-uuid');
        }
    }

    function closePopup() {
        if (popupSituacao) {
            popupSituacao.style.display = 'none';
        }
    }

    function closePopupEdit() {
        if (popupSituacaoEdit) {
            popupSituacaoEdit.style.display = 'none';
        }
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

        // Ajuste no número de itens a serem agrupados na grid
        for (let i = 0; i < gridItems.length; i += 3) { // Se for 3 itens por linha, ajuste conforme necessário
            const registro = gridItems[i];
            const situacao = gridItems[i + 1];
            const acoes = gridItems[i + 2];

            let shouldDisplay = false;
            if (selectedField === 'todos') {
                shouldDisplay = true;
            } else if (selectedField === 'registro-filtro') {
                shouldDisplay = registro.textContent.toLowerCase().includes(searchTerm);
            } else if (selectedField === 'situacao-filtro') {
                shouldDisplay = situacao.textContent.toLowerCase().includes(searchTerm);
            }

            if (shouldDisplay) {
                registro.style.display = 'flex';
                situacao.style.display = 'flex';
                acoes.style.display = 'flex';
                found = true;
            }
        }

        if (!found) {
            alert('Nenhum item encontrado.');
        }
    });

    editarSituacaoTrabalhistaForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        const formData = {
            situacaoTrabalhista: situacaoTrabalhistaEditada.value,
            uuid: idSituacaoTrabalhista,
        }
        const response = await fetch('http://localhost:8080/CRUD_Site_war_exploded/alterarSituacaoTrabalhista', {
            method: 'POST',
            body: JSON.stringify(formData),
            headers: {
                'Content-Type': 'application/json',
            }
        });

        console.log(response);
        if (response.ok) {
            window.alert('Informações atualizadas com sucesso!');
            location.reload();
        } else {
            const result = await response.json();
            window.alert(result.message);
        }
    });
});
