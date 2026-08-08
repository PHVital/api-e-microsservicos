package br.com.exercicios.bloco7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Produto {
    String nome;
    Produto(String nome) { this.nome = nome; }
    @Override public String toString() { return nome; }
}

public class CatalogoProdutos {
    public static void main(String[] args) {
        Map<String, List<Produto>> catalogo = new HashMap<>();

        List<Produto> eletronicos = new ArrayList<>();
        eletronicos.add(new Produto("Smartphone"));
        eletronicos.add(new Produto("Notebook"));
        catalogo.put("Eletrônicos", eletronicos);

        List<Produto> vestuario = new ArrayList<>();
        vestuario.add(new Produto("Camiseta"));
        vestuario.add(new Produto("Calça Jeans"));
        catalogo.put("Vestuário", vestuario);

        String categoriaBuscada = "Eletrônicos";
        System.out.println("Produtos na categoria '" + categoriaBuscada + "':");
        if (catalogo.containsKey(categoriaBuscada)) {
            for (Produto p : catalogo.get(categoriaBuscada)) {
                System.out.println("- " + p);
            }
        } else {
            System.out.println("Categoria não encontrada.");
        }
    }
}
