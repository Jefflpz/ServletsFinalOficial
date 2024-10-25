const form = document.getElementById('formLogin');
const senhaInput = document.getElementById('senha');
const erroSenha = document.getElementById('erroSenha');
const erroAdm = document.getElementById('erroAdm');
const usuarioInput = document.getElementById('usuario');

const mostrarSenha = document.querySelector('.mostrar-senha img');
const olhoAberto = 'Icone_olhoA.png';
const olhoFechado = 'Icone_olhoB.png';

// Regex para validação
const patternSenha = /^(?=.[A-Z])(?=.\d)(?=.[a-z])(?=.[áàâãéèêíïóôõöú])?(?=.*[\!\@\#\$\%\^\&\\(\)\_\-\+\=\[\]\{\}\|\;\:\'\"\,\.\<\>\/\?]).{8,}$/;
const patternAdm = /^adm((?=.[A-Z]{1,})?|(?=.[a-z]{1,}))(?=.\d{1,})?(?=.[!@#$%^&*\(\)_\-+=[]\{\}\|;:'",\.<>\/\?])?.{3,}/;

mostrarSenha.addEventListener('click', () => {
    if (senhaInput.type === 'password') {
        senhaInput.type = 'text';
        mostrarSenha.src = olhoFechado;
        mostrarSenha.alt = 'Ocultar senha';
    } else {
        senhaInput.type = 'password';
        mostrarSenha.src = olhoAberto;
        mostrarSenha.alt = 'Mostrar senha';
    }
});

// usuarioInput.addEventListener('input', (e) => {
//     if(e.va != 'a'){
//         usuarioInput.classList.add('isInvalid')
//         window.alert('O nome do usuário deve começar com "adm"')
//     }
// })

// Validação do login
form.addEventListener('submit', (e) => {
    e.preventDefault();

    erroAdm.textContent = '';
    erroSenha.textContent = '';

    let isValid = true;

    // Validação do ADM
    if (!patternAdm.test(usuarioInput.value)) {
        erroAdm.textContent = 'ADM inválido! O nome não atende aos padrões requisitados.';
        isValid = false;
    }

    // Validação da senha
    if (!patternSenha.test(senhaInput.value)) {
        erroSenha.textContent = 'Senha inválida! A senha não atende aos padrões requisitados.';
        isValid = false;
    }

    if (isValid) {
        window.alert('Login realizado com sucesso!');
}});