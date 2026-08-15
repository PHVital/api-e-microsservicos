import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<Cliente> repositorioClientes = new ArrayList<>();
    private static List<ProdutoInvestimento> repositorioProdutos = new ArrayList<>();
    
    public static void main(String[] args) {
        System.out.println("=== SISTEMA ESCRITÓRIO DE INVESTIMENTOS VCRIQUINHO ===");
        
        // ----------------------------------------------------
        // 1. CRUD DE PRODUTOS DE INVESTIMENTO
        // ----------------------------------------------------
        System.out.println("\n--- CRUD DE PRODUTOS ---");
        // CREATE
        System.out.println("[CREATE] Adicionando produtos...");
        RendaFixa tesouroDireto = new RendaFixa("Tesouro Selic", "Investimento seguro", 0.008, 90);
        RendaVariavel fundoImobiliario = new RendaVariavel("Fundo Imobiliário XP", "Shopping centers", 0.012);
        RendaFixa produtoRemover = new RendaFixa("CDB Banco Y", "CDB Liquidez Diária", 0.01, 30);
        
        repositorioProdutos.add(tesouroDireto);
        repositorioProdutos.add(fundoImobiliario);
        repositorioProdutos.add(produtoRemover);
        
        // READ
        System.out.println("\n[READ] Produtos cadastrados:");
        for (ProdutoInvestimento p : repositorioProdutos) {
            System.out.println("  - " + p.getNome() + " | " + p.getDescricao());
        }
        
        // UPDATE
        System.out.println("\n[UPDATE] Atualizando rendimento e descrição do Tesouro Selic...");
        tesouroDireto.setRendimentoMensalFixo(0.0085);
        tesouroDireto.setDescricao("Investimento seguro atrelado à Selic (Atualizado)");
        System.out.println("  Produto atualizado: " + tesouroDireto.getNome() + " | " + tesouroDireto.getDescricao());


        // ----------------------------------------------------
        // 2. CRUD DE CLIENTES E ASSOCIAÇÃO
        // ----------------------------------------------------
        System.out.println("\n--- CRUD DE CLIENTES ---");
        
        // CREATE (Conta Inicial Obrigatória)
        System.out.println("[CREATE] Criando clientes (Conta Inicial Obrigatória primeiro)...");
        
        ContaCDI contaCdiPf = new ContaCDI("CCDI-1001");
        contaCdiPf.depositar(5000.0);
        PessoaFisica pf = new PessoaFisica("João Silva", "111.222.333-44", "joao@email.com", contaCdiPf);
        
        ContaCorrente ccPf = new ContaCorrente("CC-1002");
        ccPf.depositar(1500.0);
        pf.adicionarConta(ccPf);
        
        ContaInvestimento contaInvPj = new ContaInvestimento("CINV-2001");
        contaInvPj.depositar(100000.0);
        contaInvPj.adicionarProduto(tesouroDireto);
        contaInvPj.adicionarProduto(fundoImobiliario);
        contaInvPj.adicionarProduto(produtoRemover); // Será removido no DELETE
        
        PessoaJuridica pj = new PessoaJuridica("Tech Solutions Ltda", "12.345.678/0001-99", "contato@tech.com", contaInvPj);
        
        PessoaFisica clienteRemover = new PessoaFisica("Cliente Teste", "000", "teste@teste.com", new ContaCorrente("CC-9999"));
        
        repositorioClientes.add(pf);
        repositorioClientes.add(pj);
        repositorioClientes.add(clienteRemover);
        
        // READ
        System.out.println("\n[READ] Clientes cadastrados:");
        for (Cliente c : repositorioClientes) {
            System.out.println("  - " + c.toString());
        }
        
        // UPDATE
        System.out.println("\n[UPDATE] Atualizando e-mail da Pessoa Jurídica...");
        pj.setEmail("novo.contato@tech.com");
        System.out.println("  Cliente atualizado: " + pj.getNome() + " | Novo E-mail: " + pj.getEmail());
        
        // DELETE CLIENTE
        System.out.println("\n[DELETE CLIENTE] Removendo o 'Cliente Teste'...");
        repositorioClientes.remove(clienteRemover);
        System.out.println("  Clientes restantes no repositório (qtd): " + repositorioClientes.size());
        
        // DELETE PRODUTO (Voltando ao Produto para provar a remoção da ContaInvestimento)
        System.out.println("\n[DELETE PRODUTO] Removendo o produto 'CDB Banco Y'...");
        repositorioProdutos.remove(produtoRemover);
        contaInvPj.removerProduto(produtoRemover);
        System.out.println("  Produto removido do repositório global e da carteira da ContaInvestimento do cliente.");


        // ----------------------------------------------------
        // 3. SIMULAÇÕES DE RENDIMENTOS (30, 60, 90, 180 dias)
        // ----------------------------------------------------
        System.out.println("\n--- SIMULAÇÕES DE RENDIMENTOS ---");
        double taxaCDIMensal = 0.01; 
        
        System.out.println("\n>>> Simulações para o cliente: " + pf.getNome() + " <<<");
        for (Conta conta : pf.getContas()) {
            if (conta instanceof ContaCorrente) {
                conta.realizarSimulacao(30, taxaCDIMensal, pf);
            } else if (conta instanceof ContaCDI) {
                conta.realizarSimulacao(90, taxaCDIMensal, pf);
            }
        }
        
        System.out.println("\n>>> Simulações para o cliente: " + pj.getNome() + " <<<");
        for (Conta conta : pj.getContas()) {
            System.out.println("\n>> Testando 60 dias (Menor que a carência do Tesouro Selic que é 90 dias):");
            conta.realizarSimulacao(60, taxaCDIMensal, pj); 
            
            System.out.println("\n>> Testando 180 dias (Fora da carência, taxa aplicada sobre todos):");
            conta.realizarSimulacao(180, taxaCDIMensal, pj);
        }
        
        // ----------------------------------------------------
        // 4. TESTE DE VALIDAÇÃO DE PERÍODO (Regra de Negócio)
        // ----------------------------------------------------
        System.out.println("\n--- TESTANDO VALIDAÇÃO DE PERÍODO INVÁLIDO ---");
        try {
            contaInvPj.realizarSimulacao(45, taxaCDIMensal, pj);
        } catch (IllegalArgumentException e) {
            System.out.println("Exceção capturada com sucesso: " + e.getMessage());
        }
        
        System.out.println("\n--- TESTANDO VALIDAÇÃO DE CONTA INICIAL NULA ---");
        try {
            PessoaFisica pfInvalida = new PessoaFisica("Inválido", "123", "inv", null);
        } catch (IllegalArgumentException e) {
            System.out.println("Exceção capturada com sucesso ao criar cliente sem conta: " + e.getMessage());
        }
    }
}
