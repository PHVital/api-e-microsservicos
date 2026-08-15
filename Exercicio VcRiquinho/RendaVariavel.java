public class RendaVariavel extends ProdutoInvestimento {
    private double rendimentoMensalEsperado;

    public RendaVariavel(String nome, String descricao, double rendimentoMensalEsperado) {
        super(nome, descricao);
        this.rendimentoMensalEsperado = rendimentoMensalEsperado;
    }

    @Override
    public double calcularRendimento(double valor, int dias) {
        return valor * (rendimentoMensalEsperado / 30.0) * dias;
    }

    @Override
    public boolean isElegivelTaxa(int dias) {
        return true;
    }

    public double getRendimentoMensalEsperado() { return rendimentoMensalEsperado; }
    public void setRendimentoMensalEsperado(double r) { this.rendimentoMensalEsperado = r; }
}
