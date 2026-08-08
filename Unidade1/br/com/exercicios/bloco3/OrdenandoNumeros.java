package br.com.exercicios.bloco3;

import java.util.ArrayList;
import java.util.Collections;

public class OrdenandoNumeros {
    public static void main(String[] args) {
        ArrayList<Integer> numeros = new ArrayList<>();
        numeros.add(54);
        numeros.add(12);
        numeros.add(89);
        numeros.add(3);
        numeros.add(27);
        numeros.add(77);
        numeros.add(1);
        numeros.add(45);
        numeros.add(99);
        numeros.add(15);

        System.out.println("Lista original:");
        System.out.println(numeros);

        Collections.sort(numeros);

        System.out.println("Lista ordenada (crescente):");
        System.out.println(numeros);
    }
}
