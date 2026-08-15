public abstract class Prato {
    private double precoVenda;
    private String dataValidade;
    private double peso;

    public Prato(double precoVenda, String dataValidade, double peso) {
        this.precoVenda = precoVenda;
        this.dataValidade = dataValidade;
        this.peso = peso;
    }

    public double getPrecoVenda() { return precoVenda; }
    public void setPrecoVenda(double precoVenda) { this.precoVenda = precoVenda; }

    public String getDataValidade() { return dataValidade; }
    public void setDataValidade(String dataValidade) { this.dataValidade = dataValidade; }

    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }
}
