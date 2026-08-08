package br.com.exercicios.bloco5;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AgendaContatos {
    public static void main(String[] args) {
        HashMap<String, String> agenda = new HashMap<>();
        agenda.put("Joao", "1111-2222");
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        while (opcao != 4) {
            System.out.println("\n1. Adicionar Contato\n2. Buscar Contato\n3. Listar Todos\n4. Sair");
            System.out.print("Escolha: ");
            
            if (!scanner.hasNextInt()) {
                scanner.nextLine();
                continue;
            }
            
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nomeAdd = scanner.nextLine();
                    System.out.print("Telefone: ");
                    String telAdd = scanner.nextLine();
                    agenda.put(nomeAdd, telAdd);
                    System.out.println("Adicionado.");
                    break;
                case 2:
                    System.out.print("Nome para buscar: ");
                    String nomeBusca = scanner.nextLine();
                    if (agenda.containsKey(nomeBusca)) {
                        System.out.println("Telefone: " + agenda.get(nomeBusca));
                    } else {
                        System.out.println("Não encontrado.");
                    }
                    break;
                case 3:
                    System.out.println("--- Contatos ---");
                    for (Map.Entry<String, String> entry : agenda.entrySet()) {
                        System.out.println(entry.getKey() + ": " + entry.getValue());
                    }
                    break;
                case 4:
                    System.out.println("Saindo.");
                    break;
                default:
                    System.out.println("Inválido.");
            }
        }
    }
}
