package br.com.exercicios.bloco2;

public class Circulo {
    private double raio;

    public Circulo(double raio) {
        setRaio(raio);
    }

    public double getRaio() {
        return raio;
    }

    public void setRaio(double raio) {
        if (raio <= 0) {
            throw new IllegalArgumentException("O raio deve ser maior que zero.");
        }
        this.raio = raio;
    }

    public double calcularArea() {
        return Math.PI * raio * raio;
    }

    public static void main(String[] args) {
        Circulo c = new Circulo(5.0);
        System.out.printf("Área do círculo de raio %.1f: %.2f\n", c.getRaio(), c.calcularArea());

        System.out.println("Testando exceção para raio inválido:");
        try {
            c.setRaio(-2.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Exceção capturada: " + e.getMessage());
        }
    }
}
