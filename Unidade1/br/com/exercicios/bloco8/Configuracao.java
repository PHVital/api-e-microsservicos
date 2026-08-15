package br.com.exercicios.bloco8;

import java.lang.reflect.Field;

public class Configuracao {
    private String urlConexao = "localhost:5432";

    public static void main(String[] args) {
        try {
            Configuracao config = new Configuracao();

            Field field = Configuracao.class.getDeclaredField("urlConexao");
            field.setAccessible(true);

            System.out.println("Valor original: " + field.get(config));
            field.set(config, "db.producao.com:5432");
            System.out.println("Novo valor: " + field.get(config));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
