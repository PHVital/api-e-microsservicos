package br.edu.ifsp.boliche;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JogoTest {

    private Jogo jogo;

    @BeforeEach
    void setUp() {
        jogo = new Jogo();
    }

    private void jogarMuitas(int quantidade, int pinos) {
        for (int i = 0; i < quantidade; i++) {
            jogo.jogar(pinos);
        }
    }

    private void jogarSpare() {
        jogo.jogar(5);
        jogo.jogar(5);
    }

    @Test
    void nenhumPinoDerrubadoValeZero() {
        jogarMuitas(20, 0);
        assertEquals(0, jogo.pontuacao());
    }

    @Test
    void umPinoPorJogadaValeVinte() {
        jogarMuitas(20, 1);
        assertEquals(20, jogo.pontuacao());
    }

    @Test
    void umSpareValeDezMaisProximaJogada() {
        jogarSpare();
        jogo.jogar(3);
        jogarMuitas(17, 0);
        assertEquals(16, jogo.pontuacao());
    }
}
