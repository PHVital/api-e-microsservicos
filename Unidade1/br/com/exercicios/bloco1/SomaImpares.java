package br.com.exercicios.bloco1;

public class SomaImpares {
    public static void main(String[] args) {
        int[] numeros = {2, 5, 8, 11, 14, 15, 20, 21};
        int somaImpares = 0;

        for (int numero : numeros) {
            if (numero % 2 != 0) {
                somaImpares += numero;
            }
        }

        System.out.println("Array: [2, 5, 8, 11, 14, 15, 20, 21]");
        System.out.println("Soma dos números ímpares: " + somaImpares);
    }
}
