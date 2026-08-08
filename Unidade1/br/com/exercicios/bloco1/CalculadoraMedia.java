package br.com.exercicios.bloco1;

import java.util.Locale;
import java.util.Scanner;

public class CalculadoraMedia {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);
        
        System.out.print("Digite a primeira nota: ");
        double nota1 = scanner.nextDouble();
        
        System.out.print("Digite a segunda nota: ");
        double nota2 = scanner.nextDouble();
        
        System.out.print("Digite a terceira nota: ");
        double nota3 = scanner.nextDouble();
        
        double media = (nota1 + nota2 + nota3) / 3;
        System.out.printf(Locale.US, "Média aritmética: %.2f\n", media);
        
        if (media >= 7) {
            System.out.println("Status: Aprovado");
        } else if (media >= 5 && media < 7) {
            System.out.println("Status: Recuperação");
        } else {
            System.out.println("Status: Reprovado");
        }
    }
}
