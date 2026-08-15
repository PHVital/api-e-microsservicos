public abstract class ProdutoInvestimento {
    protected String nome;
    protected String descricao;

    public ProdutoInvestimento(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public abstract double calcularRendimento(double valor, int dias);
    public abstract boolean isElegivelTaxa(int dias);

    @Override
    public String toString() {
        return nome + " - " + descricao;
    }
}
