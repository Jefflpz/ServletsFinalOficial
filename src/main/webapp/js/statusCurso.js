
document.addEventListener("DOMContentLoaded", function () {

    const inserirADM = document.querySelector('.inserir-adm');
    const editADM = document.querySelectorAll('.edit');
    const cancelADM = document.querySelector('.bt-cancelar');
    const cancelADMedi = document.querySelector('.bt-cancelar-edit');

    const popupADM = document.getElementById('popupID');
    const popupADMedit = document.getElementById('popupIDadm');

    let i = 0; // Corrigido: declaração do índice
    for (i = 0; i < editADM.length; i++) {
        editADM[i].addEventListener('click', togglePopupedit);
    }

    cancelADM.addEventListener('click', cancelPopup);
    cancelADMedi.addEventListener('click', cancelPopupedit);

    inserirADM.addEventListener('click', togglePopup);

    function togglePopup() {
        popupADM.style.display = popupADM.style.display === 'none' ? 'flex' : 'none';
    }

    function togglePopupedit() {
        popupADMedit.style.display = popupADMedit.style.display === 'none' ? 'flex' : 'none';
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
            erro.textContent = 'ADM ou senha inválida(o)!';
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