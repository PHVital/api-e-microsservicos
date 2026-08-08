package br.com.exercicios.bloco4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RemovendoDuplicatas {
    public static void main(String[] args) {
        List<Integer> numeros = new ArrayList<>();
        numeros.add(10);
        numeros.add(20);
        numeros.add(10);
        numeros.add(30);
        numeros.add(20);
        numeros.add(40);

        System.out.println("Lista com duplicatas: " + numeros);

        Set<Integer> semDuplicatas = removerDuplicatas(numeros);

        System.out.println("Coleção sem duplicatas (HashSet): " + semDuplicatas);
    }

    public static Set<Integer> removerDuplicatas(List<Integer> lista) {
        return new HashSet<>(lista);
    }
}
