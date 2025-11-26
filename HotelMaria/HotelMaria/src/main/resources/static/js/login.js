// Pega o formulário
const form = document.getElementById("loginForm");

form.addEventListener("submit", function (event) {
    event.preventDefault(); // evita recarregar a página

    const email = document.getElementById("email").value;
    const senha = document.getElementById("senha").value;

    // Cria FormData para enviar via POST
    const formData = new FormData();
    formData.append("email", email);
    formData.append("senha", senha);

    // Envia requisição POST para o backend com Factory Method
    fetch("/login", {
        method: "POST",
        body: formData
    })
    .then(response => response.json())
    .then(data => {
        if (data.sucesso) {
            // Login bem-sucedido - redireciona para a página específica do tipo de usuário
            console.log("Tipo de usuário:", data.tipoUsuario);
            console.log("Página principal:", data.paginaPrincipal);
            
            // Redireciona para a página correta baseado no tipo de usuário
            window.location.href = data.paginaPrincipal;
        } else {
            // Login falhou - exibe mensagem de erro
            alert(data.mensagem || "E-mail ou senha incorretos!");
        }
    })
    .catch(error => {
        console.error("Erro na requisição:", error);
        alert("Erro ao tentar fazer login. Tente novamente.");
    });
});