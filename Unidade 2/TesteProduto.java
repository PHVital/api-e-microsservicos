import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TesteProduto {

    public static void main(String[] args) {
        // Criando a lista com 8 produtos (Categorias: Eletrônicos e Livros)
        List<Produto> produtos = Arrays.asList(
            new Produto("Smartphone", 1500.0, "Eletrônicos"),
            new Produto("Notebook", 3500.0, "Eletrônicos"),
            new Produto("Fones de Ouvido", 200.0, "Eletrônicos"),
            new Produto("Livro Java", 120.0, "Livros"),
            new Produto("Livro Python", 90.0, "Livros"),
            new Produto("Kindle", 450.0, "Eletrônicos"),
            new Produto("Livro Clean Code", 150.0, "Livros"),
            new Produto("Teclado Mecânico", 600.0, "Eletrônicos")
        );

        System.out.println("=== RESOLUÇÃO DOS EXERCÍCIOS - UNIDADE 2 ===\n");

        // a. Use forEach e estrutura if para imprimir produtos da categoria "Eletrônicos"
        System.out.println("--- Letra A: Eletrônicos (forEach + if) ---");
        produtos.forEach(p -> {
            if ("Eletrônicos".equals(p.getCategoria())) {
                System.out.println("- " + p.getNome());
            }
        });

        // a. Refazendo usando stream() e filter()
        System.out.println("\n--- Letra A: Eletrônicos (stream + filter) ---");
        produtos.stream()
                .filter(p -> "Eletrônicos".equals(p.getCategoria()))
                .forEach(p -> System.out.println("- " + p.getNome()));

        // b. Lista com preços > 500.0 usando filter() e map()
        System.out.println("\n--- Letra B: Preços > 500.0 (filter + map) ---");
        List<Double> precosMaiores = produtos.stream()
                .filter(p -> p.getPreco() > 500.0)
                .map(Produto::getPreco)
                .collect(Collectors.toList());
        System.out.println(precosMaiores);

        // c. Valor total do estoque da categoria "Livros" (mapToDouble + sum)
        System.out.println("\n--- Letra C: Valor total de Livros ---");
        double totalLivros = produtos.stream()
                .filter(p -> "Livros".equals(p.getCategoria()))
                .mapToDouble(Produto::getPreco)
                .sum();
        System.out.println("Total Livros: R$ " + totalLivros);

        // f. Obtendo Lista de Nomes (Resolvendo antes da Letra E para não barrar a execução com a Exception)
        System.out.println("\n--- Letra F: Lista de Nomes (Expressão Lambda) ---");
        List<String> nomesLambda = produtos.stream()
                .map(p -> p.getNome())
                .collect(Collectors.toList());
        System.out.println(nomesLambda);

        System.out.println("\n--- Letra F: Lista de Nomes (Reference Method) ---");
        List<String> nomesMethodRef = produtos.stream()
                .map(Produto::getNome)
                .collect(Collectors.toList());
        System.out.println(nomesMethodRef);

        // e. Chamar buscarProdutoPorNome com ifPresent e orElseThrow
        System.out.println("\n--- Letra E: Buscar Produto Existente ('Notebook') ---");
        buscarProdutoPorNome(produtos, "Notebook").ifPresent(p -> 
            System.out.println("Produto encontrado com sucesso: " + p)
        );

        System.out.println("\n--- Letra E: Buscar Produto Inexistente ('Mesa') ---");
        System.out.println("Atenção: Uma RuntimeException será lançada abaixo...");
        Produto pInexistente = buscarProdutoPorNome(produtos, "Mesa")
            .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
    }

    // d. Método buscarProdutoPorNome retornando Optional
    public static Optional<Produto> buscarProdutoPorNome(List<Produto> produtos, String nome) {
        return produtos.stream()
                .filter(p -> p.getNome().equals(nome))
                .findFirst();
    }
}
