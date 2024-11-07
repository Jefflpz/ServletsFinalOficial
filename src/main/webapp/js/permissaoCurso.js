document.addEventListener("DOMContentLoaded", function() {
    const filterButton = document.querySelector('.filtrar');
    const filterBar = document.getElementById('filter-bar');
    filterButton.addEventListener('click', toggleFilterBar);

    function toggleFilterBar() {
        filterBar.style.display = filterBar.style.display === 'none' ? 'flex' : 'none';
    }

    const editADM = document.querySelectorAll('.view');
    const cancelADM = document.querySelector('.close-button');
    const popupADM = document.getElementById('popupID');

    editADM.forEach(button => {
        button.addEventListener('click', (e) => togglePopupedit(e));
    });

    if (cancelADM) {
        cancelADM.addEventListener('click', cancelPopup);
    }

    function togglePopupedit(e) {
        popupADM.style.display = popupADM.style.display === 'none' ? 'flex' : 'none';
        let uuid = document.getElementById('uuid');
        uuid.value = e.currentTarget.getAttribute('data-id');
        uuid.disabled = true;
        let tipo = document.getElementById('id_tipo');
        tipo.value = e.currentTarget.getAttribute('data-tipo');
        tipo.disabled = true;
        let nome = document.getElementById('nome');
        nome.value = e.currentTarget.getAttribute('data-nome');
        nome.disabled = true;
        let idEmpresa = document.getElementById('id_empresa');
        idEmpresa.value = e.currentTarget.getAttribute('data-idEmpresa');
        idEmpresa.disabled = true;
        let descricao = document.getElementById('descricao');
        descricao.value = e.currentTarget.getAttribute('data-descricao');
        descricao.disabled = true;
    }

    function cancelPopup() {
        popupADM.style.display = 'none';
    }

    const form = filterBar.querySelector('form');
    if (form) {
        form.addEventListener('submit', function(event) {
            event.preventDefault();

            const selectedField = this['filter-field'].value;
            const searchTerm = this['search'].value.trim(); // Remove espaços extras

            if (!selectedField) return;

            const gridItems = document.querySelectorAll('.grid-container .grid-item');
            gridItems.forEach(item => item.style.display = 'none');

            let found = false;

            for (let i = 0; i < gridItems.length; i += 4) {
                const registro = gridItems[i];
                const data = gridItems[i + 1];
                const permissao = gridItems[i + 2];
                const acoes = gridItems[i + 3];

                let shouldDisplay = false;
                if (selectedField === 'todos') {
                    shouldDisplay = true;
                } else if (selectedField === 'registro-filtro') {
                    shouldDisplay = registro.textContent.toLowerCase().includes(searchTerm.toLowerCase());
                } else if (selectedField === 'data-filtro') {
                    shouldDisplay = data.textContent.trim() === searchTerm; // Compara a data diretamente, sem alterar o formato
                } else if (selectedField === 'permissao-filtro') {
                    shouldDisplay = permissao.textContent.toLowerCase().includes(searchTerm.toLowerCase());
                }

                if (shouldDisplay) {
                    registro.style.display = 'flex';
                    data.style.display = 'flex';
                    permissao.style.display = 'flex';
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
