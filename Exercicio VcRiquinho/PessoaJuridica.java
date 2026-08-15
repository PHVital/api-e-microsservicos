public class PessoaJuridica extends Cliente {
    private String cnpj;
    
    public PessoaJuridica(String nome, String cnpj, String email, Conta contaInicial) {
        super(nome, email, contaInicial);
        this.cnpj = cnpj;
    }
    
    @Override
    public double getTaxaInvestimentoAutomatico() {
        return 0.0015; 
    }
    
    @Override
    public String getDocumento() {
        return "CNPJ: " + cnpj;
    }
    
    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }
}
