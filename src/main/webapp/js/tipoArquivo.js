const editarTipoArquivoForm = document.getElementById('editarTipoArquivo');
const tipoArquivoEditado = document.getElementById('editar');
let idTipoArquivo = null;

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
            this.closest('.crud-row').remove();
        });
    });

    // Alternar barra de filtro
    const filterButton = document.querySelector('.filtrar');
    const filterBar = document.getElementById('filtrar-bar');
    filterButton.addEventListener('click', toggleFilterBar);

    function toggleFilterBar() {
        filterBar.style.display = filterBar.style.display === 'none' ? 'flex' : 'none';
    }

    // Popups de inserção e edição
    const inserirArquivoButton = document.querySelector('.inserir-arquivo');
    const editArquivoButtons = document.querySelectorAll('.edit');
    const cancelArquivoButton = document.querySelector('.bt-cancelar');
    const cancelArquivoEditButton = document.querySelector('.bt-cancelar-edit');

    const popupArquivo = document.getElementById('popupID');
    const popupArquivoEdit = document.getElementById('popupIDArquivo');

    inserirArquivoButton.addEventListener('click', togglePopup);

    editArquivoButtons.forEach(button => {
        button.addEventListener('click', (e) => togglePopupEdit(e));
    });

    cancelArquivoButton.addEventListener('click', () => {
        popupArquivo.style.display = 'none';
    });

    cancelArquivoEditButton.addEventListener('click', () => {
        popupArquivoEdit.style.display = 'none';
    });

    function togglePopup() {
        popupArquivo.style.display = popupArquivo.style.display === 'none' ? 'flex' : 'none';
    }

    function togglePopupEdit(e) {
        popupArquivoEdit.style.display = popupArquivoEdit.style.display === 'none' ? 'flex' : 'none';
        let nomeTipoArquivo = document.getElementById('editar');
        nomeTipoArquivo.value = e.currentTarget.getAttribute('data-nomeTipoArquivo');

        idTipoArquivo = e.currentTarget.getAttribute('data-uuid');
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

        for (let i = 0; i < gridItems.length; i += 3) { // Ajustado para agrupar em três itens
            const registro = gridItems[i];
            const tipoArquivo = gridItems[i + 1];
            const acoes = gridItems[i + 2];

            let shouldDisplay = false;
            if (selectedField === 'todos') {
                shouldDisplay = true;
            } else if (selectedField === 'registro-filtro') {
                shouldDisplay = registro.textContent.toLowerCase().includes(searchTerm);
            } else if (selectedField === 'tipoArquivo-filtro') {
                shouldDisplay = tipoArquivo.textContent.toLowerCase().includes(searchTerm);
            }

            if (shouldDisplay) {
                registro.style.display = 'flex';
                tipoArquivo.style.display = 'flex';
                acoes.style.display = 'flex';
                found = true;
            }
        }

        if (!found) {
            alert('Nenhum item encontrado.');
        }
    });

    editarTipoArquivoForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        const formData = {
            tipoArquivo: tipoArquivoEditado.value,
            uuid: idTipoArquivo
        }
        const response = await fetch('http://localhost:8080/CRUD_Site_war_exploded/alterarTipoArquivo', {
            method: 'POST', body: JSON.stringify(formData), headers: {
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

