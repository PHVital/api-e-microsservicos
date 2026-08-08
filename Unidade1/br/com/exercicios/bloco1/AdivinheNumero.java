package br.com.exercicios.bloco1;

import java.util.Random;
import java.util.Scanner;

public class AdivinheNumero {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int numeroSecreto = random.nextInt(100) + 1;
        int palpite = 0;
        int tentativas = 0;

        System.out.println("Tente adivinhar o número entre 1 e 100!");

        while (palpite != numeroSecreto) {
            System.out.print("Seu palpite: ");
            palpite = scanner.nextInt();
            tentativas++;

            if (palpite < numeroSecreto) {
                System.out.println("Dica: Muito baixo!");
            } else if (palpite > numeroSecreto) {
                System.out.println("Dica: Muito alto!");
            } else {
                System.out.println("Parabéns! Você acertou em " + tentativas + " tentativa(s).");
            }
        }
    }
}
