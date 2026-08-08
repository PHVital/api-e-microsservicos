package br.com.exercicios.bloco3;

import java.util.LinkedList;

public class FilaAtendimento {
    public static void main(String[] args) {
        LinkedList<String> fila = new LinkedList<>();

        fila.addLast("João");
        fila.addLast("Maria");
        fila.addLast("José");
        fila.addLast("Ana");
        fila.addLast("Carlos");

        System.out.println("Fila inicial: " + fila);

        System.out.println("Atendendo: " + fila.removeFirst());
        System.out.println("Atendendo: " + fila.removeFirst());

        System.out.println("Fila após 2 atendimentos: " + fila);

        fila.addFirst("Prioritário 1 (Idoso)");
        fila.addFirst("Prioritário 2 (Gestante)");

        System.out.println("Ordem final da fila de atendimento:");
        System.out.println(fila);
    }
}
