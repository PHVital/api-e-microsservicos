package br.edu.ifsp.boliche;

public class Jogo {

    private final int[] jogadas = new int[21];
    private int jogadaAtual = 0;

    /** Registra uma jogada com a quantidade de pinos derrubados. */
    public void jogar(int pinos) {
        this.jogadas[this.jogadaAtual++] = pinos;
    }

    /** Pontuação acumulada; avaliada ao final da partida. */
    public int pontuacao() {
        int soma = 0;
        int indice = 0;
        for (int frame = 0; frame < 10; frame++) {
            if (this.jogadas[indice] + this.jogadas[indice + 1] == 10) {
                soma += 10 + this.jogadas[indice + 2];
                indice += 2;
            } else {
                soma += this.jogadas[indice] + this.jogadas[indice + 1];
                indice += 2;
            }
        }
        return soma;
    }

    /** Indica se a partida já terminou. */
    public boolean encerrado() {
        return false;
    }
}
