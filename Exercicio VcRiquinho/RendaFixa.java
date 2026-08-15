public class RendaFixa extends ProdutoInvestimento {
    private double rendimentoMensalFixo;
    private int carenciaDias;

    public RendaFixa(String nome, String descricao, double rendimentoMensalFixo, int carenciaDias) {
        super(nome, descricao);
        this.rendimentoMensalFixo = rendimentoMensalFixo;
        this.carenciaDias = carenciaDias;
    }

    @Override
    public double calcularRendimento(double valor, int dias) {
        return valor * (rendimentoMensalFixo / 30.0) * dias;
    }

    @Override
    public boolean isElegivelTaxa(int dias) {
        return dias >= carenciaDias;
    }

    public double getRendimentoMensalFixo() { return rendimentoMensalFixo; }
    public void setRendimentoMensalFixo(double r) { this.rendimentoMensalFixo = r; }

    public int getCarenciaDias() { return carenciaDias; }
    public void setCarenciaDias(int c) { this.carenciaDias = c; }
}
