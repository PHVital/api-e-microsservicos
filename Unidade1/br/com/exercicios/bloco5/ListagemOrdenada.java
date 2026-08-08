package br.com.exercicios.bloco5;

import java.util.Map;
import java.util.TreeMap;

public class ListagemOrdenada {
    public static void main(String[] args) {
        TreeMap<String, Double> notas = new TreeMap<>();
        notas.put("Ziraldo", 8.5);
        notas.put("Alice", 9.0);
        notas.put("Marcos", 7.5);
        notas.put("Bruno", 6.0);
        notas.put("Carlos", 10.0);

        System.out.println("Iterando sobre o TreeMap (deve ordenar pela chave - alfabética):");
        for (Map.Entry<String, Double> entry : notas.entrySet()) {
            System.out.println("Aluno: " + entry.getKey() + " | Nota: " + entry.getValue());
        }
    }
}
