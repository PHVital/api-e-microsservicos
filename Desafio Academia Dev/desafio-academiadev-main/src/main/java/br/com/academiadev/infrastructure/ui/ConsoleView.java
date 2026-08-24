package main.java.br.com.academiadev.infrastructure.ui;

public class ConsoleView {
    public void showWelcome() {
        System.out.println("\n=== BEM-VINDO À ACADEMIADEV ===");
    }

    public void showLogin() {
        System.out.println("\nDigite seu e-mail para entrar (ou deixe em branco para encerrar): ");
    }

    public void showAdminMenu() {
        System.out.println("\n--- MENU ADMINISTRADOR ---");
        System.out.println("1. Gerenciar Status de Cursos");
        System.out.println("2. Gerenciar Planos de Alunos");
        System.out.println("3. Atender Próximo Ticket (Fila FIFO)");
        System.out.println("4. Relatórios e Análises");
        System.out.println("5. Exportar Dados para CSV");
        System.out.println("0. Sair");
        System.out.print("Opção: ");
    }

    public void showStudentMenu(String studentName) {
        System.out.println("\n--- MENU ALUNO: " + studentName + " ---");
        System.out.println("1. Matricular em Curso");
        System.out.println("2. Consultar Matrículas");
        System.out.println("3. Atualizar Progresso");
        System.out.println("4. Cancelar Matrícula");
        System.out.println("5. Consultar Catálogo de Cursos");
        System.out.println("6. Abrir Ticket de Suporte");
        System.out.println("0. Sair");
        System.out.print("Opção: ");
    }

    public void showMessage(String message) {
        System.out.println(">> " + message);
    }

    public void showError(String error) {
        System.out.println("!! ERRO: " + error);
    }
}
