import java.util.ArrayList;
import java.util.List;

public abstract class Cliente {
    protected String nome;
    protected String email;
    protected List<Conta> contas = new ArrayList<>();

    public Cliente(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public void adicionarConta(Conta conta) {
        contas.add(conta);
    }

    public List<Conta> getContas() { return contas; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public abstract double getTaxaInvestimentoAutomatico();
    public abstract String getDocumento();

    @Override
    public String toString() {
        return nome + " (" + getDocumento() + ") - Contas ativas: " + contas.size();
    }
}
