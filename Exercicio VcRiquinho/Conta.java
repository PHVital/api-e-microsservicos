public interface Conta {
    void depositar(double valor);
    void sacar(double valor);
    double getSaldo();
    void realizarSimulacao(int dias, double taxaCDIMensal, Cliente cliente);
}
