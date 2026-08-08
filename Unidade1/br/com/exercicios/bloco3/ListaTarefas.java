package br.com.exercicios.bloco3;

import java.util.ArrayList;
import java.util.Scanner;

public class ListaTarefas {
    public static void main(String[] args) {
        ArrayList<String> tarefas = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        System.out.println("--- Gerenciador de Tarefas ---");
        while (opcao != 4) {
            System.out.println("\n1. Adicionar Tarefa\n2. Remover Tarefa\n3. Listar Tarefas\n4. Sair");
            System.out.print("Escolha uma opção: ");
            
            if (!scanner.hasNextInt()) {
                scanner.nextLine();
                continue;
            }
            
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Digite a tarefa: ");
                    String tarefa = scanner.nextLine();
                    tarefas.add(tarefa);
                    System.out.println("Tarefa adicionada com sucesso!");
                    break;
                case 2:
                    System.out.print("Digite o índice da tarefa a remover (0 a " + (tarefas.size() - 1) + "): ");
                    int indice = scanner.nextInt();
                    if (indice >= 0 && indice < tarefas.size()) {
                        String removida = tarefas.remove(indice);
                        System.out.println("Tarefa '" + removida + "' removida com sucesso!");
                    } else {
                        System.out.println("Índice inválido.");
                    }
                    break;
                case 3:
                    System.out.println("\n--- Lista de Tarefas ---");
                    for (int i = 0; i < tarefas.size(); i++) {
                        System.out.println(i + ". " + tarefas.get(i));
                    }
                    break;
                case 4:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
