import java.util.ArrayList;
import java.util.List;

public abstract class Cliente {
    protected String nome;
    protected String email;
    protected List<Conta> contas = new ArrayList<>();
    
    public Cliente(String nome, String email, Conta contaInicial) {
        if (contaInicial == null) {
            throw new IllegalArgumentException("Um cliente deve possuir obrigatoriamente pelo menos uma conta inicial.");
        }
        this.nome = nome;
        this.email = email;
        this.contas.add(contaInicial);
    }
    
    public void adicionarConta(Conta conta) {
        if (conta == null) {
            throw new IllegalArgumentException("A conta não pode ser nula.");
        }
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
