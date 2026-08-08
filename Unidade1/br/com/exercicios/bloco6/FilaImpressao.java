package br.com.exercicios.bloco6;

import java.util.LinkedList;
import java.util.Queue;

public class FilaImpressao {
    public static void main(String[] args) {
        Queue<String> fila = new LinkedList<>();

        fila.add("Documento1.pdf");
        fila.add("Foto.png");
        fila.add("Relatorio.docx");
        fila.add("Planilha.xlsx");
        fila.add("Apresentacao.pptx");

        System.out.println("Fila de impressão iniciada...");
        while (!fila.isEmpty()) {
            String documento = fila.poll();
            System.out.println("Imprimindo: " + documento);
        }
        System.out.println("Fila de impressão vazia.");
    }
}
