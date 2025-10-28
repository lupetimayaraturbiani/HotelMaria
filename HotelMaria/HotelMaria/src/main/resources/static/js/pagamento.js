// pagamento.js

// Espera o DOM carregar
document.addEventListener('DOMContentLoaded', () => {
    const form = document.querySelector('form');
    const pagamentoRadios = document.querySelectorAll('input[name="pagamento"]');
    const numeroCartao = document.getElementById('numero-cartao');
    const nomeTitular = document.getElementById('nome-titular');
    const cpfTitular = document.getElementById('cpf-titular');
    const vencimento = document.getElementById('vencimento');
    const cvv = document.getElementById('cvv');

    // Função para ativar/desativar campos de cartão
    function toggleCamposCartao() {
        const formaPagamento = document.querySelector('input[name="pagamento"]:checked').value;
        const usarCartao = formaPagamento === 'debito' || formaPagamento === 'credito';

        numeroCartao.disabled = !usarCartao;
        nomeTitular.disabled = !usarCartao;
        cpfTitular.disabled = !usarCartao;
        vencimento.disabled = !usarCartao;
        cvv.disabled = !usarCartao;
    }

    // Inicializa os campos de acordo com o pagamento selecionado
    toggleCamposCartao();

    // Atualiza campos ao mudar forma de pagamento
    pagamentoRadios.forEach(radio => {
        radio.addEventListener('change', toggleCamposCartao);
    });

    // Validação simples ao enviar
    form.addEventListener('submit', (e) => {
        e.preventDefault(); // Evita envio real

        const formaPagamento = document.querySelector('input[name="pagamento"]:checked').value;

        if ((formaPagamento === 'debito' || formaPagamento === 'credito') &&
            (!numeroCartao.value || !nomeTitular.value || !cpfTitular.value || !vencimento.value || !cvv.value)) {
            alert('Por favor, preencha todos os campos do cartão.');
            return;
        }

        // Mensagem de sucesso
        alert('Obrigado pela sua doação!');

        // Aqui você poderia enviar os dados para o servidor via fetch/ajax
        form.reset();
        toggleCamposCartao(); // Reajusta campos
    });
});
