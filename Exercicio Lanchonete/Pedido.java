import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private String nomeCliente;
    private double taxaServico;
    private List<Prato> itensConsumidos;

    public Pedido(String nomeCliente, double taxaServico) {
        this.nomeCliente = nomeCliente;
        this.taxaServico = taxaServico;
        this.itensConsumidos = new ArrayList<>();
    }

    public void adicionarItem(Prato prato) {
        this.itensConsumidos.add(prato);
    }

    public double calcularTotal() {
        double total = 0.0;
        for (Prato prato : itensConsumidos) {
            total += prato.getPrecoVenda();
        }
        return total + taxaServico;
    }

    public void mostrarFatura() {
        System.out.println("========== NOTA FISCAL ==========");
        System.out.println("Cliente: " + nomeCliente);
        System.out.println("---------------------------------");
        System.out.println("Itens consumidos:");
        for (Prato prato : itensConsumidos) {
            System.out.println("- " + prato.getClass().getSimpleName() + ": R$ " + String.format("%.2f", prato.getPrecoVenda()));
        }
        System.out.println("---------------------------------");
        System.out.println("Taxa de Serviço: R$ " + String.format("%.2f", taxaServico));
        System.out.println("Total a pagar: R$ " + String.format("%.2f", calcularTotal()));
        System.out.println("=================================");
    }

    public void receberPagamento(double valorRecebido) {
        double total = calcularTotal();
        if (valorRecebido >= total) {
            double troco = valorRecebido - total;
            System.out.println("Valor Recebido: R$ " + String.format("%.2f", valorRecebido));
            System.out.println("Troco a devolver: R$ " + String.format("%.2f", troco));
        } else {
            System.out.println("Valor insuficiente para o pagamento! Faltam R$ " + String.format("%.2f", (total - valorRecebido)));
        }
    }
}
