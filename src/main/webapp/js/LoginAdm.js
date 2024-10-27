const form = document.getElementById('formLogin');
const senhaInput = document.getElementById('senha');
const erroSenha = document.getElementById('erroSenha');
const erroAdm = document.getElementById('erroAdm');
const usuarioInput = document.getElementById('usuario');

const mostrarSenha = document.querySelector('.mostrar-senha img');
const olhoAberto = 'img/Icone_olhoA.png';
const olhoFechado = 'img/Icone_olhoB.png';

// Regex para validação
const patternSenha = /^(?=.*[A-Z])(?=.*\d)(?=.[a-z])(?=.[áàâãéèêíïóôõöú])?(?=.*[\!\@\#\$%\^\&\(\)\_\-\+\=\[\]\{\}\|\;\:\'\"\,\.\<\>\/\?]).{8,}$/;
const patternAdm = /^adm((?=.[A-Z]{1,})?|(?=.[a-z]{1,}))(?=.\d{1,})?(?=.[!@#$%^&*()_\-+=[]\{}\|;:'",\.<>\/\?])?.{3,}/;


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

// usuarioInput.addEventListener('submit', (e) => {
//     if(e.va != 'a'){
//         usuarioInput.classList.add('isInvalid')
//         window.alert('O nome do usuário deve começar com "adm"')
//     }
// })

// Validação do login
form.addEventListener('submit', async (e) => {
    e.preventDefault();

    erroAdm.textContent = '';
    erroSenha.textContent = '';
    senhaInput.classList.remove('isInvalid');
    usuarioInput.classList.remove('isInvalid');
    let isValid = true;

    // Validação do ADM
    if (!patternAdm.test(usuarioInput.value) || !patternSenha.test(senhaInput.value)) {
        erroSenha.textContent = 'ADM ou senha inválida(o)!';
        usuarioInput.classList.add('isInvalid');
        senhaInput.classList.add('isInvalid');
        isValid = false;
    }

    if (isValid) {
        const formData = {
            adm: usuarioInput.value,
            senha: senhaInput.value
        }
        const response = await fetch('http://localhost:8080/CRUD_Site_war_exploded/login', {
            method: 'POST',
            body: JSON.stringify(formData),
            headers: {
                'Content-Type': 'application/json',
            }
        });

        console.log(response);
        if (response.ok) {
            window.location.href = 'http://localhost:8080/CRUD_Site_war_exploded/listarAdm';
            window.alert('Login realizado com sucesso!');
        } else {
            const result = await response.json();
            window.alert(result.message);
        }
    }
});