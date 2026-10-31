package br.edu.ifsp.boliche;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JogoTest {

    @Test
    void nenhumPinoDerrubadoValeZero() {
        Jogo jogo = new Jogo();
        for (int i = 0; i < 20; i++) {
            jogo.jogar(0);
        }
        assertEquals(0, jogo.pontuacao());
    }
}
