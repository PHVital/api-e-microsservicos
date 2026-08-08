package br.com.exercicios.bloco4;

import java.util.HashSet;

public class UnicidadeEmails {
    public static void main(String[] args) {
        HashSet<String> emails = new HashSet<>();

        emails.add("joao@teste.com");
        emails.add("maria@teste.com");
        emails.add("carlos@teste.com");
        
        System.out.println("Adicionando e-mail repetido (joao@teste.com)...");
        boolean adicionou = emails.add("joao@teste.com");

        System.out.println("Conseguiu adicionar? " + adicionou);
        System.out.println("Tamanho do Set: " + emails.size());
        System.out.println("E-mails: " + emails);
    }
}
