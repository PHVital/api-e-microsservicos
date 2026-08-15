package br.com.exercicios.bloco2;

public class SaldoInsuficienteException extends Exception {
    public SaldoInsuficienteException(String message) {
        super(message);
    }
}

class ContaBancaria {
    private double saldo;

    public ContaBancaria(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    public void sacar(double valor) throws SaldoInsuficienteException {
        if (valor > saldo) {
            throw new SaldoInsuficienteException("Saldo insuficiente. Tentativa de saque: " + valor + ", Saldo disponível: " + saldo);
        }
        saldo -= valor;
        System.out.println("Saque de " + valor + " realizado com sucesso. Saldo restante: " + saldo);
    }
}

class TesteExcecao {
    public static void main(String[] args) {
        ContaBancaria conta = new ContaBancaria(100.0);

        try {
            conta.sacar(50.0);
            conta.sacar(200.0);
        } catch (SaldoInsuficienteException e) {
            System.out.println("Exceção capturada: " + e.getMessage());
        }
    }
}
