package br.com.exercicios.bloco5;

import java.util.LinkedHashMap;
import java.util.Map;

public class OrdemCadastro {
    public static void main(String[] args) {
        LinkedHashMap<Integer, String> produtos = new LinkedHashMap<>();

        produtos.put(105, "Mesa");
        produtos.put(101, "Cadeira");
        produtos.put(103, "Luminária");
        produtos.put(102, "Sofá");
        produtos.put(104, "Estante");

        System.out.println("Iterando sobre o LinkedHashMap (deve manter a ordem de inserção):");
        for (Map.Entry<Integer, String> entry : produtos.entrySet()) {
            System.out.println("Código: " + entry.getKey() + " | Produto: " + entry.getValue());
        }
    }
}
