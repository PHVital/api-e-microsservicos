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
            if (ehStrike(indice)) {
                soma += 10 + bonusDeStrike(indice);
                indice += 1;
            } else if (ehSpare(indice)) {
                soma += 10 + bonusDeSpare(indice);
                indice += 2;
            } else {
                soma += pinosDoFrame(indice);
                indice += 2;
            }
        }
        return soma;
    }

    private boolean ehStrike(int indice) {
        return this.jogadas[indice] == 10;
    }

    private int bonusDeStrike(int indice) {
        return this.jogadas[indice + 1] + this.jogadas[indice + 2];
    }

    private boolean ehSpare(int indice) {
        return this.jogadas[indice] + this.jogadas[indice + 1] == 10;
    }

    private int bonusDeSpare(int indice) {
        return this.jogadas[indice + 2];
    }

    private int pinosDoFrame(int indice) {
        return this.jogadas[indice] + this.jogadas[indice + 1];
    }

    /** Indica se a partida já terminou. */
    public boolean encerrado() {
        int indice = 0;
        for (int frame = 0; frame < 9; frame++) {
            if (indice >= this.jogadaAtual) {
                return false;
            }
            if (ehStrike(indice)) {
                indice += 1;
            } else {
                if (indice + 1 >= this.jogadaAtual) {
                    return false;
                }
                indice += 2;
            }
        }
        if (indice >= this.jogadaAtual) {
            return false;
        }
        if (ehStrike(indice)) {
            return this.jogadaAtual >= indice + 3;
        }
        if (this.jogadaAtual >= indice + 2 && ehSpare(indice)) {
            return this.jogadaAtual >= indice + 3;
        }
        return this.jogadaAtual >= indice + 2;
    }
}
