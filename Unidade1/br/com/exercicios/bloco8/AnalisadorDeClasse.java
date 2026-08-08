package br.com.exercicios.bloco8;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

class Produto {
    private int codigo;
    public String nome;
    protected double preco;

    public Produto(int codigo, String nome, double preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
    }

    private double calcularImposto() {
        return preco * 0.1;
    }
}

public class AnalisadorDeClasse {
    public static void inspecionar(Object obj) {
        Class<?> clazz = obj.getClass();
        
        System.out.println("Nome da Classe: " + clazz.getName());
        
        System.out.println("\nAtributos:");
        for (Field f : clazz.getDeclaredFields()) {
            System.out.println("- " + f.getName() + " (Tipo: " + f.getType().getSimpleName() + ")");
        }
        
        System.out.println("\nMétodos:");
        for (Method m : clazz.getDeclaredMethods()) {
            System.out.println("- " + m.getName() + " (Retorno: " + m.getReturnType().getSimpleName() + ")");
        }
    }

    public static void main(String[] args) {
        Produto p = new Produto(101, "Notebook Gamer", 8500.0);
        AnalisadorDeClasse.inspecionar(p);
    }
}
