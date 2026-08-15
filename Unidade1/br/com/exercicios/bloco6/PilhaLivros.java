package br.com.exercicios.bloco6;

import java.util.ArrayDeque;
import java.util.Deque;

public class PilhaLivros {
    public static void main(String[] args) {
        Deque<String> pilha = new ArrayDeque<>();

        System.out.println("Empilhando livros...");
        pilha.push("O Senhor dos Anéis");
        pilha.push("Dom Quixote");
        pilha.push("1984");

        System.out.println("Livro removido (pop): " + pilha.pop());

        System.out.println("Livro no topo da pilha (peek): " + pilha.peek());

        System.out.println("Estado atual da pilha: " + pilha);
    }
}
