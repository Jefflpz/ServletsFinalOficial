const novoAdmin = document.getElementById('novoAdm');
const novaSenha = document.getElementById('novoSenha');
const cadastroForm = document.getElementById('cadastroForm');
const erro = document.getElementById('erro');
const deletar = document.getElementById('delete');

// Regex para validação
const patternSenha = /^(?=.*[A-Z])(?=.*\d)(?=.*[a-z])(?=.*[áàâãéèêíïóôõöú])?(?=.*[\!\@\#\$%\^\&\(\)\_\-\+\=\[\]\{\}\|\;\:\'\"\,\.\<\>\/\?]).{8,}$/;
const patternAdm = /^adm((?=.[A-Z]{1,})?|(?=.[a-z]{1,}))(?=.\d{1,})?(?=.[!@#$%^&*()_\-+=[]\{}\|;:'",\.<>\/\?])?.{3,}/;

document.querySelectorAll('.view-password').forEach(button => {
    button.addEventListener('click', function () {
        const passwordCell = this.parentElement.previousElementSibling;
        passwordCell.textContent = passwordCell.textContent === '' ? 'admin123' : '';
    });
});

document.addEventListener("DOMContentLoaded", function () {
    const filterButton = document.querySelector('.filtrar');
    const filterBar = document.getElementById('filtrar-bar');
    filterButton.addEventListener('click', toggleFilterBar);

    function toggleFilterBar() {
        filterBar.style.display = filterBar.style.display === 'none' ? 'flex' : 'none';
    }

    const inserirADM = document.querySelector('.inserir-adm');
    const editADM = document.querySelectorAll('.edit');
    const cancelADM = document.querySelector('.bt-cancelar');
    const cancelADMedi = document.querySelector('.bt-cancelar-edit');

    const popupADM = document.getElementById('popupID');
    const popupADMedit = document.getElementById('popupIDadm');

    let i = 0; // Corrigido: declaração do índice
    for (i = 0; i < editADM.length; i++) {
        editADM[i].addEventListener('click', (e) => togglePopupedit(e));
    }

    cancelADM.addEventListener('click', cancelPopup);
    cancelADMedi.addEventListener('click', cancelPopupedit);

    inserirADM.addEventListener('click', (e) => {   togglePopup (e)});

    function togglePopup(e) {
        popupADM.style.display = popupADM.style.display === 'none' ? 'flex' : 'none';
    }

    function togglePopupedit(e) {
        popupADMedit.style.display = popupADMedit.style.display === 'none' ? 'flex' : 'none';
        let usernameEdit = document.getElementById('login');
        usernameEdit.value = e.currentTarget.getAttribute('data-username');
    }

    function cancelPopup() {
        popupADM.style.display = 'none';
    }
    function cancelPopupedit() {
        popupADMedit.style.display = 'none';
    }

    // Validação do login
    cadastroForm.addEventListener('submit', async (e) => {
        e.preventDefault();

        erro.textContent = '';
        let isValid = true;

        // Validação do ADM
        if (!patternAdm.test(novoAdmin.value) || !patternSenha.test(novaSenha.value)) {
            erro.textContent = 'Administrador ou senha inválida(o)!';
            isValid = false;
        }

        if (isValid) {
            const formData = {
                adm: novoAdmin.value,
                senha: novaSenha.value
            }
            const response = await fetch('http://localhost:8080/CRUD_Site_war_exploded/inserirAdm', {
                method: 'POST',
                body: JSON.stringify(formData),
                headers: {
                    'Content-Type': 'application/json',
                }
            });

            console.log(response);
            if (response.ok) {
                window.alert('Cadastro realizado com sucesso!');
                location.reload();
            } else {
                const result = await response.json();
                window.alert(result.message);
            }
        }});
});


