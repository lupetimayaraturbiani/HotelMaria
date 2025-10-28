
// Salva a escolha e redireciona para pagamento
function selecionarDoacao(valor) {
    localStorage.setItem("doacaoSelecionada", valor);
    window.location.href = "pagamento.html";
}

// Para "Outro valor"
function selecionarOutroValor() {
    let valor = prompt("Digite o valor que deseja doar (ex: R$ 150,00):");
    if (valor && valor.trim() !== "") {
        localStorage.setItem("doacaoSelecionada", valor);
        window.location.href = "pagamento.html";
    }
}

// Preenche automaticamente no pagamento
window.onload = function () {
    let campoValor = document.getElementById("valor-doacao");
    if (campoValor) {
        let valor = localStorage.getItem("doacaoSelecionada");
        if (valor) {
            campoValor.value = valor;
        }
    }
};