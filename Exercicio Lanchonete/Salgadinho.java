public class Salgadinho extends Prato {
    private String tipo;
    private String massa;
    private String recheio;

    public Salgadinho(double precoVenda, String dataValidade, double peso, String tipo, String massa, String recheio) {
        super(precoVenda, dataValidade, peso);
        this.tipo = tipo;
        this.massa = massa;
        this.recheio = recheio;
    }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getMassa() { return massa; }
    public void setMassa(String massa) { this.massa = massa; }

    public String getRecheio() { return recheio; }
    public void setRecheio(String recheio) { this.recheio = recheio; }
}
