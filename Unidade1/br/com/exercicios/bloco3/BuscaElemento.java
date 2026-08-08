package br.com.exercicios.bloco3;

import java.util.ArrayList;
import java.util.Scanner;

public class BuscaElemento {
    public static void main(String[] args) {
        ArrayList<String> cidades = new ArrayList<>();
        cidades.add("São Paulo");
        cidades.add("Rio de Janeiro");
        cidades.add("Belo Horizonte");
        cidades.add("Curitiba");
        cidades.add("Porto Alegre");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome de uma cidade para buscar: ");
        String cidadeBusca = scanner.nextLine();

        if (cidades.contains(cidadeBusca)) {
            int indice = cidades.indexOf(cidadeBusca);
            System.out.println("A cidade '" + cidadeBusca + "' está presente na lista no índice: " + indice);
        } else {
            System.out.println("A cidade '" + cidadeBusca + "' NÃO foi encontrada na lista.");
        }
    }
}
