public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE LANCHONETE QUASE TRÊS LANCHES ===\n");

        Pizza pizza = new Pizza(45.90, "15/10/2023", 850.0, "Frango com Catupiry", "Recheada", "Tomate");
        Lanche lanche = new Lanche(28.50, "12/10/2023", 350.0, "Australiano", "Blend de Carne", "Maionese Verde");
        Salgadinho salgadinho = new Salgadinho(7.50, "10/10/2023", 120.0, "Frito", "Mandioca", "Carne Seca");

        Pedido pedido = new Pedido("Carlos Silva", 4.50);

        pedido.adicionarItem(pizza);
        pedido.adicionarItem(lanche);
        pedido.adicionarItem(salgadinho);

        pedido.mostrarFatura();

        System.out.println("\n--- Fluxo de Pagamento ---");
        pedido.receberPagamento(100.00);
    }
}
