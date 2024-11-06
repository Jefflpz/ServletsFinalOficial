const editarSetorForm = document.getElementById('editarSetor');
const setorEditado = document.getElementById('editar');
let idSetor = null;

document.querySelectorAll('.view-password').forEach(button => {
    button.addEventListener('click', function () {
        const passwordCell = this.parentElement.previousElementSibling;
        passwordCell.textContent = passwordCell.textContent === '' ? 'admin123' : '';
    });
});

document.querySelectorAll('.delete').forEach(button => {
    button.addEventListener('click', function () {
        this.closest('.crud-row').remove();
    });
});
document.addEventListener("DOMContentLoaded", function () {
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
    inserirSetorButton.addEventListener('click', function () {
        togglePopup(popupInsert);
    });

    // Evento para cada botão de edição abrir o popup de edição
    editButtons.forEach(button => {
        button.addEventListener('click', (e) => {
            togglePopup(popupEdit, e);
        });
    });

    // Cancelar popups
    cancelButton.addEventListener('click', function () {
        popupInsert.style.display = 'none';
    });
    cancelEditButton.addEventListener('click', function () {
        popupEdit.style.display = 'none';
    });

    function togglePopup(popup, e) {
        popup.style.display = popup.style.display === 'none' ? 'flex' : 'none';
        let nomeSetor = document.getElementById('editar');
        nomeSetor.value = e.currentTarget.getAttribute('data-setor');

        idSetor = e.currentTarget.getAttribute('data-uuid');
        console.log(id);
    }

    // Filtro de pesquisa
    const form = filterBar.querySelector('form');
    form.addEventListener('submit', function (event) {
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

    editarSetorForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        const formData = {
            setor: setorEditado.value,
            uuid: idSetor
        }
        const response = await fetch('http://localhost:8080/CRUD_Site_war_exploded/alterarNomeSetor', {
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
