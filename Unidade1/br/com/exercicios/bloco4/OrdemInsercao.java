package br.com.exercicios.bloco4;

import java.util.LinkedHashSet;

public class OrdemInsercao {
    public static void main(String[] args) {
        LinkedHashSet<String> diasSemana = new LinkedHashSet<>();

        diasSemana.add("Quarta");
        diasSemana.add("Segunda");
        diasSemana.add("Sexta");
        diasSemana.add("Domingo");
        diasSemana.add("Terça");

        System.out.println("Dias inseridos fora de ordem: Quarta, Segunda, Sexta, Domingo, Terça");
        System.out.println("Iterando sobre o LinkedHashSet:");
        for (String dia : diasSemana) {
            System.out.println(dia);
        }
    }
}
