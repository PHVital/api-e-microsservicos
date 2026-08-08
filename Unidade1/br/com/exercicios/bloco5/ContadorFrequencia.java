package br.com.exercicios.bloco5;

import java.util.HashMap;
import java.util.Map;

public class ContadorFrequencia {
    public static void main(String[] args) {
        String texto = "java é legal java é poderoso e java é divertido";
        HashMap<String, Integer> frequencia = new HashMap<>();

        String[] palavras = texto.split(" ");
        for (String palavra : palavras) {
            frequencia.put(palavra, frequencia.getOrDefault(palavra, 0) + 1);
        }

        System.out.println("Texto: " + texto);
        System.out.println("Frequência de cada palavra:");
        for (Map.Entry<String, Integer> entry : frequencia.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
