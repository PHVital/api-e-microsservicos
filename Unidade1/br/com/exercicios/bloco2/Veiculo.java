package br.com.exercicios.bloco2;

public class Veiculo {
    protected String marca;
    protected String modelo;

    public Veiculo(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        return "Veiculo { marca='" + marca + "', modelo='" + modelo + "' }";
    }
}

class CarroHeranca extends Veiculo {
    private int numeroDePortas;

    public CarroHeranca(String marca, String modelo, int numeroDePortas) {
        super(marca, modelo);
        this.numeroDePortas = numeroDePortas;
    }

    @Override
    public String toString() {
        return "Carro { marca='" + marca + "', modelo='" + modelo + "', numeroDePortas=" + numeroDePortas + " }";
    }
}

class Moto extends Veiculo {
    private int cilindradas;

    public Moto(String marca, String modelo, int cilindradas) {
        super(marca, modelo);
        this.cilindradas = cilindradas;
    }

    @Override
    public String toString() {
        return "Moto { marca='" + marca + "', modelo='" + modelo + "', cilindradas=" + cilindradas + "cc }";
    }
}

class TesteHeranca {
    public static void main(String[] args) {
        Veiculo v = new Veiculo("Genérica", "VeiculoPadrão");
        CarroHeranca c = new CarroHeranca("Honda", "Civic", 4);
        Moto m = new Moto("Yamaha", "MT-07", 689);

        System.out.println(v);
        System.out.println(c);
        System.out.println(m);
    }
}
