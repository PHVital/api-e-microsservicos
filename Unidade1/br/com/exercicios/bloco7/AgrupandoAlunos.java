package br.com.exercicios.bloco7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Aluno {
    String nome;
    double nota;

    Aluno(String nome, double nota) {
        this.nome = nome;
        this.nota = nota;
    }

    @Override
    public String toString() { return nome + " (" + nota + ")"; }
}

public class AgrupandoAlunos {
    public static void main(String[] args) {
        List<Aluno> alunos = new ArrayList<>();
        alunos.add(new Aluno("João", 8.0));
        alunos.add(new Aluno("Maria", 6.5));
        alunos.add(new Aluno("José", 4.0));
        alunos.add(new Aluno("Ana", 9.5));
        alunos.add(new Aluno("Carlos", 5.0));

        Map<String, List<Aluno>> grupos = new HashMap<>();
        grupos.put("Aprovados", new ArrayList<>());
        grupos.put("Recuperação", new ArrayList<>());
        grupos.put("Reprovados", new ArrayList<>());

        for (Aluno a : alunos) {
            if (a.nota >= 7) {
                grupos.get("Aprovados").add(a);
            } else if (a.nota >= 5 && a.nota < 7) {
                grupos.get("Recuperação").add(a);
            } else {
                grupos.get("Reprovados").add(a);
            }
        }

        for (Map.Entry<String, List<Aluno>> entry : grupos.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
