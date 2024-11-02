
document.addEventListener("DOMContentLoaded", function () {

    const inserirSetor = document.querySelector('.inserir-setor');
    const editSetor = document.querySelectorAll('.edit');
    const cancel = document.querySelector('.bt-cancelar');
    const canceledit = document.querySelector('.bt-cancelar-edit');

    const popupADM = document.getElementById('popupID');
    const popupSetoredit = document.getElementById('popupIDsetor');

    let i = 0; // Corrigido: declaração do índice
    for (i = 0; i < editSetor.length; i++) {
        editSetor[i].addEventListener('click', togglePopupedit);
    }

    cancel.addEventListener('click', cancelPopup);
    canceledit.addEventListener('click', cancelPopupedit);

    inserirSetor.addEventListener('click', togglePopup);

    function togglePopup() {
        popupADM.style.display = popupADM.style.display === 'none' ? 'flex' : 'none';
    }

    function togglePopupedit() {
        popupSetoredit.style.display = popupADMedit.style.display === 'none' ? 'flex' : 'none';
    }

    function cancelPopup() {
        popupADM.style.display = 'none';
    }

    function cancelPopupedit() {
        popupSetoredit.style.display = 'none';
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
            const response = await fetch('http://localhost:8080/CRUD_Site_war_exploded/inserirSetor', {
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