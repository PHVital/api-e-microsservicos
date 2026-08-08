package br.com.exercicios.bloco2;

public class Carro {
    private String marca;
    private String modelo;
    private int ano;

    public Carro(String marca, String modelo, int ano) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAno() {
        return ano;
    }

    public void exibirInfo() {
        System.out.println("Carro: " + marca + " " + modelo + " (" + ano + ")");
    }

    public static void main(String[] args) {
        Carro c = new Carro("Toyota", "Corolla", 2022);
        c.exibirInfo();
    }
}
