// Simulação de "usuários cadastrados" (poderia vir de um backend ou localStorage)
const usuarios = [
    { email: "mayara@gmail.com", senha: "123456" },
    { email: "adm@gmail.com", senha: "adm123"}
];

// Pega o formulário
const form = document.getElementById("loginForm");

form.addEventListener("submit", function (event) {
    event.preventDefault(); // evita recarregar a página

    const email = document.getElementById("email").value;
    const senha = document.getElementById("senha").value;

    // Verifica se existe o usuário
    const usuarioValido = usuarios.find(
        user => user.email === email && user.senha === senha
    );

    

    if (usuarioValido) {
        alert("Login realizado com sucesso!");
        // redireciona para a tela principal
        window.location.href = "../../templates/usuario/index.html";
        if (email == "adm@gmail.com" && senha == "adm123") {
            window.location.href = "../../templates/indexAdm.html";
        }
    } else {
        alert("E-mail ou senha incorretos!");
    }
});