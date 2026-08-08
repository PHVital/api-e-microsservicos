package br.com.exercicios.bloco4;

import java.util.TreeSet;

public class NomesOrdemAlfabetica {
    public static void main(String[] args) {
        TreeSet<String> nomes = new TreeSet<>();

        nomes.add("Zeca");
        nomes.add("Ana");
        nomes.add("Maria");
        nomes.add("João");
        nomes.add("Beto");

        System.out.println("Nomes adicionados: Zeca, Ana, Maria, João, Beto");
        System.out.println("Iterando sobre o TreeSet:");
        for (String nome : nomes) {
            System.out.println(nome);
        }
    }
}
