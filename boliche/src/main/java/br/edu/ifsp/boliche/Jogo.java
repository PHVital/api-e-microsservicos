package br.edu.ifsp.boliche;

public class Jogo {

    private int pontuacao = 0;

    /** Registra uma jogada com a quantidade de pinos derrubados. */
    public void jogar(int pinos) {
        this.pontuacao += pinos;
    }

    /** Pontuação acumulada; avaliada ao final da partida. */
    public int pontuacao() {
        return this.pontuacao;
    }

    /** Indica se a partida já terminou. */
    public boolean encerrado() {
        return false;
    }
}
