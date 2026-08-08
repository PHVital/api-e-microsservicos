package br.com.exercicios.bloco5;

import java.util.HashMap;
import java.util.Scanner;

public class Dicionario {
    public static void main(String[] args) {
        HashMap<String, String> dicionario = new HashMap<>();
        dicionario.put("cachorro", "dog");
        dicionario.put("gato", "cat");
        dicionario.put("carro", "car");
        dicionario.put("casa", "house");
        dicionario.put("livro", "book");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite uma palavra em português para traduzir: ");
        String palavra = scanner.nextLine().toLowerCase();

        if (dicionario.containsKey(palavra)) {
            System.out.println("Tradução para inglês: " + dicionario.get(palavra));
        } else {
            System.out.println("Palavra não encontrada no dicionário.");
        }
    }
}
