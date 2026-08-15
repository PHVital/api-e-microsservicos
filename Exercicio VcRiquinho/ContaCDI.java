public class ContaCDI extends ContaBancaria {
    public ContaCDI(String numeroConta) {
        super(numeroConta);
    }
    
    @Override
    public void realizarSimulacao(int dias, double taxaCDIMensal, Cliente cliente) {
        validarPeriodoSimulacao(dias);
        
        double taxaDiaria = taxaCDIMensal / 30.0;
        double rendimento = saldo * taxaDiaria * dias;
        double taxaServico = rendimento * 0.0007; 
        
        System.out.println("=== Simulação Conta CDI (" + dias + " dias) ===");
        System.out.printf("Saldo inicial: R$ %.2f\n", saldo);
        System.out.printf("Rendimento Bruto: R$ %.2f\n", rendimento);
        System.out.printf("Taxa de Serviço (0,07%%): R$ %.2f\n", taxaServico);
        System.out.printf("Líquido: R$ %.2f\n", (rendimento - taxaServico));
    }
}
