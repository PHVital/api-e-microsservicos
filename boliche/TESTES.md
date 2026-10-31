# Lista de Testes Planejada — Pontuação do Boliche (TDD)

Esta lista descreve a sequência planejada de testes para guiar o desenvolvimento orientado a testes (TDD) da classe `Jogo`, partindo do caso degenerado/mais simples até as regras completas e validações.

## 1. Casos Básicos (Frames Abertos)
- [x] **Nenhum pino (Gutter game):** 20 jogadas de 0 pinos -> pontuação `0`, jogo `encerrado() == true` ao final.
- [ ] **Jogo recém-criado não está encerrado:** Antes de completar os frames, `encerrado()` deve retornar `false`.
- [x] **Um pino por jogada:** 20 jogadas de 1 pino -> pontuação `20`, jogo encerrado ao final.

## 2. Bônus de Spare e Strike
- [ ] **Um spare:** jogadas `5, 5, 3` seguidas de 17 jogadas de `0` -> pontuação `16`.
- [ ] **Um strike:** jogada `10`, seguida de `3, 4` e 16 jogadas de `0` -> pontuação `24` (total de 19 jogadas na partida).
- [ ] **Spare em todos os frames:** 21 jogadas de `5` (inclui 1 jogada extra no 10º frame) -> pontuação `150`, jogo encerrado após a 21ª jogada.
- [ ] **Jogo perfeito:** 12 jogadas de `10` (inclui 2 jogadas extras no 10º frame) -> pontuação `300`, jogo encerrado após a 12ª jogada.
- [ ] **Partida mista completa:** `10` · `7, 3` · `9, 0` · `10` · `0, 8` · `8, 2` · `0, 6` · `10` · `10` · `10, 8, 1` -> pontuação `167`, jogo encerrado ao final.

## 3. Regras de Validação (V1 a V3)
- [ ] **V1 (Pinos negativos):** Jogada com menos de 0 pinos (ex.: `-1`) lança `IllegalArgumentException`.
- [ ] **V1 (Mais de 10 pinos):** Jogada com mais de 10 pinos (ex.: `11`) lança `IllegalArgumentException`.
- [ ] **V2 (Soma do frame > 10 nos frames 1 a 9):** Segunda jogada de um frame normal que faça a soma ultrapassar 10 pinos (ex.: `7` e depois `4`) lança `IllegalArgumentException`.
- [ ] **V3 (Jogada após o fim da partida):**
  - [ ] Após 20 jogadas sem strike ou spare no 10º frame, tentar jogar novamente lança `IllegalStateException`.
  - [ ] Após a jogada extra de um spare no 10º frame, tentar jogar novamente lança `IllegalStateException`.
  - [ ] Após as duas jogadas extras de um strike no 10º frame, tentar jogar novamente lança `IllegalStateException`.
