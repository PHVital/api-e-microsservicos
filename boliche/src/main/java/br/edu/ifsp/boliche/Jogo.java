package br.edu.ifsp.boliche;

public class Jogo {

    private static final int TOTAL_FRAMES = 10;
    private static final int MAX_PINOS = 10;
    private static final int MAX_JOGADAS = 21;

    private final int[] jogadas = new int[MAX_JOGADAS];
    private int jogadaAtual = 0;

    /** Registra uma jogada com a quantidade de pinos derrubados. */
    public void jogar(int pinos) {
        validarPartidaEmAndamento();
        validarQuantidadeDePinos(pinos);
        validarLimiteDoFrame(pinos);
        this.jogadas[this.jogadaAtual++] = pinos;
    }

    /** Pontuação acumulada; avaliada ao final da partida. */
    public int pontuacao() {
        int soma = 0;
        int indice = 0;
        for (int frame = 0; frame < TOTAL_FRAMES; frame++) {
            if (ehStrike(indice)) {
                soma += MAX_PINOS + bonusDeStrike(indice);
                indice += 1;
            } else if (ehSpare(indice)) {
                soma += MAX_PINOS + bonusDeSpare(indice);
                indice += 2;
            } else {
                soma += pinosDoFrame(indice);
                indice += 2;
            }
        }
        return soma;
    }

    /** Indica se a partida já terminou. */
    public boolean encerrado() {
        int indice = indiceDoDecimoFrame();
        if (indice < 0 || indice >= this.jogadaAtual) {
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

    private void validarPartidaEmAndamento() {
        if (encerrado()) {
            throw new IllegalStateException("A partida já foi encerrada.");
        }
    }

    private void validarQuantidadeDePinos(int pinos) {
        if (pinos < 0 || pinos > MAX_PINOS) {
            throw new IllegalArgumentException("A quantidade de pinos deve estar entre 0 e 10.");
        }
    }

    private void validarLimiteDoFrame(int pinos) {
        if (excedeDezPinosNoFrame(pinos)) {
            throw new IllegalArgumentException("A soma dos pinos de um frame não pode ultrapassar 10.");
        }
    }

    private boolean excedeDezPinosNoFrame(int pinos) {
        int indice = 0;
        for (int frame = 0; frame < TOTAL_FRAMES - 1; frame++) {
            if (indice == this.jogadaAtual) {
                return false;
            }
            if (ehStrike(indice)) {
                indice += 1;
            } else {
                if (indice + 1 == this.jogadaAtual) {
                    return this.jogadas[indice] + pinos > MAX_PINOS;
                }
                indice += 2;
            }
        }
        return false;
    }

    private int indiceDoDecimoFrame() {
        int indice = 0;
        for (int frame = 0; frame < TOTAL_FRAMES - 1; frame++) {
            if (indice >= this.jogadaAtual) {
                return -1;
            }
            if (ehStrike(indice)) {
                indice += 1;
            } else {
                if (indice + 1 >= this.jogadaAtual) {
                    return -1;
                }
                indice += 2;
            }
        }
        return indice;
    }

    private boolean ehStrike(int indice) {
        return this.jogadas[indice] == MAX_PINOS;
    }

    private int bonusDeStrike(int indice) {
        return this.jogadas[indice + 1] + this.jogadas[indice + 2];
    }

    private boolean ehSpare(int indice) {
        return this.jogadas[indice] + this.jogadas[indice + 1] == MAX_PINOS;
    }

    private int bonusDeSpare(int indice) {
        return this.jogadas[indice + 2];
    }

    private int pinosDoFrame(int indice) {
        return this.jogadas[indice] + this.jogadas[indice + 1];
    }
}
