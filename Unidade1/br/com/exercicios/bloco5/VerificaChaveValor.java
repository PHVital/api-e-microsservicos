package br.com.exercicios.bloco5;

import java.util.HashMap;

public class VerificaChaveValor {
    public static void main(String[] args) {
        HashMap<String, String> agenda = new HashMap<>();
        agenda.put("Joao", "1111-2222");
        agenda.put("Maria", "3333-4444");

        String nomeProcurado = "Joao";
        String telefoneProcurado = "9999-9999";

        System.out.println("Verificando nome '" + nomeProcurado + "': " + agenda.containsKey(nomeProcurado));
        System.out.println("Verificando telefone '" + telefoneProcurado + "': " + agenda.containsValue(telefoneProcurado));
        
        System.out.println("Verificando telefone '3333-4444': " + agenda.containsValue("3333-4444"));
    }
}
