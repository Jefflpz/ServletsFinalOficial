document.querySelectorAll('.view-password').forEach(button => {
  button.addEventListener('click', function() {
      const passwordCell = this.parentElement.previousElementSibling;
      if (passwordCell.textContent === '*****') {
          passwordCell.textContent = 'admin123'; // Exemplo de senha, isso pode ser dinamizado
      } else {
          passwordCell.textContent = '*****';
      }
  });
});

document.querySelectorAll('.delete').forEach(button => {
  button.addEventListener('click', function() {
      this.closest('.crud-row').remove();
  });
});
document.addEventListener("DOMContentLoaded", function() {
    const filterButton = document.querySelector('.filtrar');
    const filterBar = document.getElementById('filter-bar');

    // Exibir ou ocultar a barra de filtro quando o botão for clicado
    filterButton.addEventListener('click', function() {
        filterBar.style.display = (filterBar.style.display === 'none' || filterBar.style.display === '') ? 'flex' : 'none';
    });

    // Lógica de filtragem ao submeter o formulário
    const form = filterBar.querySelector('form');
    form.addEventListener('submit', function(event) {
        event.preventDefault(); // Impede o envio do formulário

        const selectedField = form['filter-field'].value; // Campo selecionado para o filtro
        const searchTerm = form['search'].value.toLowerCase(); // Termo de pesquisa

        const gridItems = document.querySelectorAll('.grid-container .grid-item'); // Itens da grid

        // Esconder todos os itens inicialmente
        gridItems.forEach(item => {
            item.style.display = 'none'; // Oculta todos os itens
        });

        let found = false; // Marca se algum item foi encontrado

        // Iterar pelos itens da grid para verificar quais devem ser exibidos
        for (let i = 0; i < gridItems.length; i += 4) {
            const registro = gridItems[i];
            const idConta = gridItems[i + 1];
            const idVaga = gridItems[i + 2];
            const acoes = gridItems[i + 3];

            let shouldDisplay = false;

            // Verifica qual campo foi selecionado no filtro
            if (selectedField === 'Todos') {
                shouldDisplay = true; // Exibe todos os itens se "Todos" for selecionado
            } else if (selectedField === 'Registro-filtro') {
                shouldDisplay = registro.textContent.toLowerCase().includes(searchTerm); // Filtra por registro
            } else if (selectedField === 'id_conta-filtro') {
                shouldDisplay = idConta.textContent.toLowerCase().includes(searchTerm); // Filtra por id_conta
            } else if (selectedField === 'id_vaga-filtro') {
                shouldDisplay = idVaga.textContent.toLowerCase().includes(searchTerm); // Filtra por id_vaga
            }

            // Exibe o item se corresponder ao critério de busca
            if (shouldDisplay) {
                registro.style.display = 'flex';
                idConta.style.display = 'flex';
                idVaga.style.display = 'flex';
                acoes.style.display = 'flex';
                found = true; // Marca que um item foi encontrado
            }
        }

        // Se nenhum item for encontrado, exibe uma mensagem
        if (!found) {
            alert('Nenhum item encontrado.');
        }
    });
});
