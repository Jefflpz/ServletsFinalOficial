document.addEventListener("DOMContentLoaded", function() {
    const filterButton = document.querySelector('.filtrar');
    const filterBar = document.getElementById('filter-bar');
    const editADM = document.querySelectorAll('.view');
    const cancelADM = document.querySelector('.close-button');
    const popupADM = document.getElementById('popupID');
    const form = filterBar ? filterBar.querySelector('form') : null;

    // Alterna a exibição da barra de filtro
    filterButton.addEventListener('click', toggleFilterBar);

    function toggleFilterBar() {
        filterBar.style.display = filterBar.style.display === 'none' ? 'flex' : 'none';
    }

    // Configura o popup para abrir e fechar
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
        nome.value = e.currentTarget.getAttribute('data-descricao');
        nome.disabled = true;
        let idEmpresa = document.getElementById('id_empresa');
        idEmpresa.value = e.currentTarget.getAttribute('data-idEmpresa');
        idEmpresa.disabled = true;
        let descricao = document.getElementById('descricao');
        descricao.value = e.currentTarget.getAttribute('data-nome');
        descricao.disabled = true;
    }

    function cancelPopup() {
        popupADM.style.display = 'none';
    }

    // Função para comparar data no formato dd/MM/yyyy
    function matchDate(text, searchTerm) {
        const [day, month, year] = text.split('/');
        const formattedDate = `${year}-${month}-${day}`; // yyyy-MM-dd
        return formattedDate.includes(searchTerm);
    }

    // Configura o evento de filtro no formulário
    if (form) {
        form.addEventListener('submit', function(event) {
            event.preventDefault();

            const selectedField = this['filter-field'].value;
            const searchTerm = this['search'].value.trim().toLowerCase();

            if (!selectedField) return;

            const gridItems = document.querySelectorAll('.grid-container .grid-item');
            gridItems.forEach(item => item.style.display = 'none');

            let found = false;

            // Itera sobre os itens em blocos de quatro para cada registro
            for (let i = 0; i < gridItems.length; i += 4) {
                const registro = gridItems[i];
                const data = gridItems[i + 1];
                const permissao = gridItems[i + 2];
                const acoes = gridItems[i + 3];

                let shouldDisplay = false;
                if (selectedField === 'todos') {
                    shouldDisplay = true;
                } else if (selectedField === 'registro-filtro') {
                    shouldDisplay = registro.textContent.toLowerCase().includes(searchTerm);
                } else if (selectedField === 'data-filtro') {
                    shouldDisplay = matchDate(data.textContent.trim(), searchTerm);
                } else if (selectedField === 'permissao-filtro') {
                    shouldDisplay = permissao.textContent.toLowerCase().includes(searchTerm);
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
