package br.edu.ifsp.boliche;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    private void jogarStrike() {
        jogo.jogar(10);
    }

    @Test
    void nenhumPinoDerrubadoValeZero() {
        assertFalse(jogo.encerrado());
        jogarMuitas(20, 0);
        assertEquals(0, jogo.pontuacao());
        assertTrue(jogo.encerrado());
    }

    @Test
    void umPinoPorJogadaValeVinte() {
        jogarMuitas(19, 1);
        assertFalse(jogo.encerrado());
        jogo.jogar(1);
        assertEquals(20, jogo.pontuacao());
        assertTrue(jogo.encerrado());
    }

    @Test
    void umSpareValeDezMaisProximaJogada() {
        jogarSpare();
        jogo.jogar(3);
        jogarMuitas(17, 0);
        assertEquals(16, jogo.pontuacao());
        assertTrue(jogo.encerrado());
    }

    @Test
    void umStrikeValeDezMaisDuasProximasJogadas() {
        jogarStrike();
        jogo.jogar(3);
        jogo.jogar(4);
        jogarMuitas(16, 0);
        assertEquals(24, jogo.pontuacao());
        assertTrue(jogo.encerrado());
    }

    @Test
    void spareEmTodosOsFramesValeCentoECinquenta() {
        jogarMuitas(20, 5);
        assertFalse(jogo.encerrado());
        jogo.jogar(5);
        assertEquals(150, jogo.pontuacao());
        assertTrue(jogo.encerrado());
    }

    @Test
    void jogoPerfeitoValeTrezentos() {
        jogarMuitas(11, 10);
        assertFalse(jogo.encerrado());
        jogo.jogar(10);
        assertEquals(300, jogo.pontuacao());
        assertTrue(jogo.encerrado());
    }
}
