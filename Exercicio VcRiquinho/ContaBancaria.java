public abstract class ContaBancaria implements Conta {
    protected double saldo;
    protected String numeroConta;
    
    public ContaBancaria(String numeroConta) {
        this.numeroConta = numeroConta;
        this.saldo = 0.0;
    }
    
    public void depositar(double valor) {
        if (valor > 0) saldo += valor;
    }
    
    public void sacar(double valor) {
        if (valor > 0 && valor <= saldo) saldo -= valor;
        else System.out.println("Saldo insuficiente na conta " + numeroConta);
    }
    
    public double getSaldo() { return saldo; }
    public String getNumeroConta() { return numeroConta; }
    
    protected void validarPeriodoSimulacao(int dias) {
        if (dias != 30 && dias != 60 && dias != 90 && dias != 180) {
            throw new IllegalArgumentException("Período de simulação inválido: " + dias + " dias. Permitidos: 30, 60, 90 ou 180.");
        }
    }
}
