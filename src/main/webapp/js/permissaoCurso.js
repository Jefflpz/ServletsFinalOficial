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
