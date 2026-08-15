package br.com.exercicios.bloco7;

import java.util.LinkedList;

public class HistoricoNavegacao {
    private LinkedList<String> historico = new LinkedList<>();
    private int indiceAtual = -1;

    public void visitar(String url) {
        while (historico.size() > indiceAtual + 1) {
            historico.removeLast();
        }
        historico.add(url);
        indiceAtual++;
        System.out.println("Visitando: " + url);
    }

    public void voltar() {
        if (indiceAtual > 0) {
            indiceAtual--;
            System.out.println("Voltando para: " + historico.get(indiceAtual));
        } else {
            System.out.println("Não é possível voltar. Já estamos na primeira página.");
        }
    }

    public void avancar() {
        if (indiceAtual < historico.size() - 1) {
            indiceAtual++;
            System.out.println("Avançando para: " + historico.get(indiceAtual));
        } else {
            System.out.println("Não é possível avançar. Já estamos na última página.");
        }
    }

    public static void main(String[] args) {
        HistoricoNavegacao nav = new HistoricoNavegacao();

        nav.visitar("google.com");
        nav.visitar("github.com");
        nav.visitar("stackoverflow.com");

        nav.voltar();
        nav.voltar();
        nav.avancar();

        nav.visitar("youtube.com");
        nav.avancar();
        nav.voltar();
    }
}
