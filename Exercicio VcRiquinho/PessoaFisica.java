public class PessoaFisica extends Cliente {
    private String cpf;

    public PessoaFisica(String nome, String cpf, String email) {
        super(nome, email);
        this.cpf = cpf;
    }

    @Override
    public double getTaxaInvestimentoAutomatico() {
        return 0.001;
    }

    @Override
    public String getDocumento() {
        return "CPF: " + cpf;
    }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
}
