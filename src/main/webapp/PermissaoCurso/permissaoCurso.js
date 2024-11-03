document.addEventListener("DOMContentLoaded", function() {
    const filterButton = document.querySelector('.filtrar');
    const filterBar = document.getElementById('filter-bar'); // Corrigido o ID aqui
    filterButton.addEventListener('click', toggleFilterBar);

    function toggleFilterBar() {
        filterBar.style.display = filterBar.style.display === 'none' ? 'flex' : 'none';
    }

    const editADM = document.querySelectorAll('.view');
    const cancelADM = document.querySelector('.close-button');

    const popupADM = document.getElementById('popupID');

    // Corrigido: iteração adequada sobre 'editADM'
    editADM.forEach(button => {
        button.addEventListener('click', togglePopupedit);
    });

    if (cancelADM) {
        cancelADM.addEventListener('click', cancelPopup);
    }

    function togglePopupedit() {
        popupADM.style.display = popupADM.style.display === 'none' ? 'flex' : 'none';
    }

    function cancelPopup() {
        popupADM.style.display = 'none';
    }

    const form = filterBar.querySelector('form');
    if (form) {
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
                const idConta = gridItems[i + 1];
                const idCurso = gridItems[i + 2];
                const acoes = gridItems[i + 3];

                let shouldDisplay = false;
                if (selectedField === 'todos') {
                    shouldDisplay = true;
                } else if (selectedField === 'registro-filtro') {
                    shouldDisplay = registro.textContent.toLowerCase().includes(searchTerm);
                } else if (selectedField === 'id_conta-filtro') {
                    shouldDisplay = idConta.textContent.toLowerCase().includes(searchTerm);
                } else if (selectedField === 'id_curso-filtro') {
                    shouldDisplay = idCurso.textContent.toLowerCase().includes(searchTerm);
                }

                if (shouldDisplay) {
                    registro.style.display = 'flex';
                    idConta.style.display = 'flex';
                    idCurso.style.display = 'flex';
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
