import java.util.ArrayList;
import java.util.List;

public class ContaInvestimento extends ContaBancaria {
    private List<ProdutoInvestimento> produtos = new ArrayList<>();
    
    public ContaInvestimento(String numeroConta) {
        super(numeroConta);
    }
    
    public void adicionarProduto(ProdutoInvestimento p) {
        produtos.add(p);
    }
    
    public void removerProduto(ProdutoInvestimento p) {
        produtos.remove(p);
    }
    
    @Override
    public void realizarSimulacao(int dias, double taxaCDIMensal, Cliente cliente) {
        validarPeriodoSimulacao(dias);
        
        System.out.println("=== Simulação Conta Investimento Automático (" + dias + " dias) ===");
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto selecionado para investimento.");
            return;
        }
        
        double valorPorProduto = saldo / produtos.size();
        double rendimentoTotal = 0;
        double taxaTotal = 0;
        
        System.out.printf("Saldo inicial: R$ %.2f (R$ %.2f alocados por produto)\n", saldo, valorPorProduto);
        
        for (ProdutoInvestimento p : produtos) {
            double rend = p.calcularRendimento(valorPorProduto, dias);
            rendimentoTotal += rend;
            
            if (p.isElegivelTaxa(dias)) {
                taxaTotal += rend * cliente.getTaxaInvestimentoAutomatico();
            } else {
                System.out.println("  [!] Importante: Simulação de rendimento menor que a carência no produto '" + p.getNome() + "'. Não foi considerada taxa de serviço para os rendimentos deste investimento.");
            }
        }
        
        System.out.printf("Rendimento Bruto Total: R$ %.2f\n", rendimentoTotal);
        System.out.printf("Taxa de Serviço Total (%.2f%%): R$ %.2f\n", cliente.getTaxaInvestimentoAutomatico() * 100, taxaTotal);
        System.out.printf("Líquido: R$ %.2f\n", (rendimentoTotal - taxaTotal));
    }
}
