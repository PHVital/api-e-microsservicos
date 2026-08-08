package br.com.exercicios.bloco7;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class InvertendoFrase {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite uma frase: ");
        String frase = scanner.nextLine();

        String[] palavras = frase.split(" ");
        Deque<String> pilha = new ArrayDeque<>();

        for (String palavra : palavras) {
            pilha.push(palavra);
        }

        System.out.print("Frase invertida: ");
        while (!pilha.isEmpty()) {
            System.out.print(pilha.pop() + " ");
        }
        System.out.println();
    }
}
