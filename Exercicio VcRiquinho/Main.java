import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<Cliente> repositorioClientes = new ArrayList<>();
    private static List<ProdutoInvestimento> repositorioProdutos = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("=== SISTEMA ESCRITÓRIO DE INVESTIMENTOS VCRIQUINHO ===");


        System.out.println("\n--- 1. Criando Produtos de Investimento (CRUD) ---");
        RendaFixa tesouroDireto = new RendaFixa("Tesouro Selic", "Investimento seguro atrelado à Selic", 0.008, 90);
        RendaVariavel fundoImobiliario = new RendaVariavel("Fundo Imobiliário XP", "Cotas de shopping centers", 0.012);

        repositorioProdutos.add(tesouroDireto);
        repositorioProdutos.add(fundoImobiliario);


        System.out.println("Produtos cadastrados no sistema:");
        for (ProdutoInvestimento p : repositorioProdutos) {
            System.out.println("- " + p.toString());
        }


        System.out.println("\n--- 2. Criando Clientes e Contas (CRUD) ---");
        PessoaFisica pf = new PessoaFisica("João Silva", "111.222.333-44", "joao@email.com");
        PessoaJuridica pj = new PessoaJuridica("Tech Solutions Ltda", "12.345.678/0001-99", "contato@tech.com");

        repositorioClientes.add(pf);
        repositorioClientes.add(pj);


        ContaCDI contaCdiPf = new ContaCDI("CCDI-1001");
        contaCdiPf.depositar(5000.0);
        pf.adicionarConta(contaCdiPf);

        ContaInvestimento contaInvPj = new ContaInvestimento("CINV-2001");
        contaInvPj.depositar(100000.0);
        contaInvPj.adicionarProduto(tesouroDireto);
        contaInvPj.adicionarProduto(fundoImobiliario);
        pj.adicionarConta(contaInvPj);


        System.out.println("Clientes cadastrados no sistema:");
        for (Cliente c : repositorioClientes) {
            System.out.println("- " + c.toString());
        }


        System.out.println("\n--- 3. Simulações de Rendimentos ---");
        double taxaCDIMensal = 0.01;

        System.out.println("\n>>> Simulação para o cliente: " + pf.getNome() + " <<<");
        for (Conta conta : pf.getContas()) {
            conta.realizarSimulacao(30, taxaCDIMensal, pf);
        }

        System.out.println("\n>>> Simulação para o cliente: " + pj.getNome() + " <<<");
        for (Conta conta : pj.getContas()) {

            conta.realizarSimulacao(60, taxaCDIMensal, pj);
            System.out.println();

            conta.realizarSimulacao(180, taxaCDIMensal, pj);
        }


        System.out.println("\n--- 4. Atualização e Exclusão (Finalizando CRUD) ---");
        System.out.println("Excluindo produto Fundo Imobiliário...");
        repositorioProdutos.remove(fundoImobiliario);
        contaInvPj.removerProduto(fundoImobiliario);

        System.out.println("Atualizando nome do cliente Pessoa Física...");
        pf.setNome("João da Silva Atualizado");

        System.out.println("Lista atualizada de produtos (qtd): " + repositorioProdutos.size());
        System.out.println("Nome atualizado do cliente PF: " + pf.getNome());
    }
}
