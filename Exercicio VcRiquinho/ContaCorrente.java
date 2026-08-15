public class ContaCorrente extends ContaBancaria {
    public ContaCorrente(String numeroConta) {
        super(numeroConta);
    }
    
    @Override
    public void realizarSimulacao(int dias, double taxaCDIMensal, Cliente cliente) {
        validarPeriodoSimulacao(dias);
        
        System.out.println("=== Simulação Conta Corrente (" + dias + " dias) ===");
        System.out.println("Conta Corrente não gera rendimento.");
        System.out.println("Saldo atual: R$ " + String.format("%.2f", saldo));
    }
}
