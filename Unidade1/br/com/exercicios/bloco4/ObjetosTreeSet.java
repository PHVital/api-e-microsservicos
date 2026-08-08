package br.com.exercicios.bloco4;

import java.util.TreeSet;

class Produto implements Comparable<Produto> {
    private String nome;
    private double preco;

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() { return nome; }
    public double getPreco() { return preco; }

    @Override
    public int compareTo(Produto outro) {
        return Double.compare(this.preco, outro.preco);
    }

    @Override
    public String toString() {
        return "Produto{nome='" + nome + "', preco=" + preco + "}";
    }
}

public class ObjetosTreeSet {
    public static void main(String[] args) {
        TreeSet<Produto> produtos = new TreeSet<>();

        produtos.add(new Produto("Notebook", 3500.00));
        produtos.add(new Produto("Mouse", 150.00));
        produtos.add(new Produto("Teclado", 250.00));
        produtos.add(new Produto("Monitor", 1200.00));

        System.out.println("Produtos ordenados por preço:");
        for (Produto p : produtos) {
            System.out.println(p);
        }
    }
}
