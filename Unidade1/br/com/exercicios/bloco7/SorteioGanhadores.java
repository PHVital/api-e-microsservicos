package br.com.exercicios.bloco7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SorteioGanhadores {
    public static void main(String[] args) {
        List<String> participantes = new ArrayList<>();
        participantes.add("Ana");
        participantes.add("João");
        participantes.add("Ana");
        participantes.add("Carlos");
        participantes.add("Maria");
        participantes.add("João");
        participantes.add("Beto");

        System.out.println("Participantes (com repetição): " + participantes);

        Set<String> unicos = new HashSet<>(participantes);
        List<String> listaUnicos = new ArrayList<>(unicos);

        System.out.println("Participantes únicos: " + listaUnicos);

        if (listaUnicos.size() < 3) {
            System.out.println("Não há participantes suficientes para sortear 3 nomes.");
            return;
        }

        Collections.shuffle(listaUnicos);

        System.out.println("Ganhadores sorteados:");
        for (int i = 0; i < 3; i++) {
            System.out.println((i + 1) + "º Lugar: " + listaUnicos.get(i));
        }
    }
}
