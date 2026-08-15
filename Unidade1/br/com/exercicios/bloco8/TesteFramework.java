package br.com.exercicios.bloco8;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Teste {
}

class MinhaClasseDeTeste {
    @Teste
    public void testeSoma() {
        System.out.println("Executando testeSoma: SUCESSO");
    }

    public void metodoComum() {
        System.out.println("Este não é um teste.");
    }

    @Teste
    public void testeLogin() {
        System.out.println("Executando testeLogin: SUCESSO");
    }
}

class ExecutorDeTestes {
    public static void executarTestes(Object obj) {
        Class<?> clazz = obj.getClass();
        Method[] metodos = clazz.getDeclaredMethods();

        int testesRodados = 0;

        for (Method method : metodos) {
            if (method.isAnnotationPresent(Teste.class)) {
                try {
                    method.invoke(obj);
                    testesRodados++;
                } catch (Exception e) {
                    System.out.println("Falha ao executar " + method.getName());
                }
            }
        }
        System.out.println(testesRodados + " testes executados.");
    }
}

public class TesteFramework {
    public static void main(String[] args) {
        MinhaClasseDeTeste testes = new MinhaClasseDeTeste();
        ExecutorDeTestes.executarTestes(testes);
    }
}
