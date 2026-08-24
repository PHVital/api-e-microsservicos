package main.java.br.com.academiadev.infrastructure.ui;

import main.java.br.com.academiadev.application.usecases.AdminServiceUseCase;
import main.java.br.com.academiadev.application.usecases.EnrollStudentUseCase;
import main.java.br.com.academiadev.application.usecases.GenerateReportsUseCase;
import main.java.br.com.academiadev.application.usecases.SupportServiceUseCase;
import main.java.br.com.academiadev.application.repositories.CourseRepository;
import main.java.br.com.academiadev.domain.entities.*;
import main.java.br.com.academiadev.domain.enums.DifficultyLevel;
import main.java.br.com.academiadev.domain.exceptions.BusinessException;
import main.java.br.com.academiadev.infrastructure.persistence.UserRepositoryInMemory;
import main.java.br.com.academiadev.infrastructure.utils.GenericCsvExporter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleController {
    private final ConsoleView view;
    private final EnrollStudentUseCase enrollStudentUseCase;
    private final GenerateReportsUseCase reportsUseCase;
    private final UserRepositoryInMemory userRepository;
    private final SupportServiceUseCase supportUseCase;
    private final AdminServiceUseCase adminServiceUseCase;
    private final CourseRepository courseRepository;
    private final Scanner scanner;

    public ConsoleController(EnrollStudentUseCase enrollStudentUseCase,
                             GenerateReportsUseCase reportsUseCase,
                             UserRepositoryInMemory userRepository,
                             SupportServiceUseCase supportUseCase,
                             AdminServiceUseCase adminServiceUseCase,
                             CourseRepository courseRepository) {
        this.view = new ConsoleView();
        this.enrollStudentUseCase = enrollStudentUseCase;
        this.reportsUseCase = reportsUseCase;
        this.userRepository = userRepository;
        this.supportUseCase = supportUseCase;
        this.adminServiceUseCase = adminServiceUseCase;
        this.courseRepository = courseRepository;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        view.showWelcome();
        while (true) {
            try {
                view.showLogin();
                String email = scanner.nextLine();
                if (email.trim().isEmpty()) break;
                login(email);
            } catch (Exception e) {
                view.showError("Erro inesperado no sistema: " + e.getMessage());
            }
        }
    }

    private void login(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);

        if (!userOpt.isPresent()) {
            view.showError("Usuário não encontrado");
            return;
        }

        User user = userOpt.get();

        if (user instanceof Student) {
            handleStudentSession((Student) user);
        } else {
            handleAdminSession(user);
        }
    }

    private void handleStudentSession(Student student) {
        boolean loggedIn = true;
        while (loggedIn) {
            view.showStudentMenu(student.getName());
            String option = scanner.nextLine();

            try {
                switch (option) {
                    case "1":
                        System.out.println("Digite o nome exato do curso: ");
                        String courseTitle = scanner.nextLine();
                        enrollStudentUseCase.execute(student.getEmail(), courseTitle);
                        view.showMessage("Matrícula realizada com sucesso!");
                        break;
                    case "2":
                        view.showMessage("Cursos matriculados:");
                        student.getEnrollments().forEach(e -> {
                            System.out.println("- " + e.getCourse().getTitle() + " (Ativo: " + e.isActive() + ", Progresso: " + e.getProgressPercentage() + "%)");
                        });
                        break;
                    case "3":
                        System.out.println("Digite o nome do curso: ");
                        String updateCourse = scanner.nextLine();
                        System.out.println("Digite o novo progresso (0 a 100): ");
                        int progress = Integer.parseInt(scanner.nextLine());
                        enrollStudentUseCase.updateProgress(student.getEmail(), updateCourse, progress);
                        view.showMessage("Progresso atualizado!");
                        break;
                    case "4":
                        System.out.println("Digite o nome do curso para cancelar matrícula: ");
                        String cancelCourse = scanner.nextLine();
                        enrollStudentUseCase.cancelEnrollment(student.getEmail(), cancelCourse);
                        view.showMessage("Matrícula cancelada!");
                        break;
                    case "5":
                        view.showMessage("Catálogo de Cursos:");
                        courseRepository.findAll().stream()
                            .filter(Course::isActive)
                            .forEach(c -> System.out.println("- " + c.getTitle() + " (" + c.getDifficultyLevel() + ")"));
                        break;
                    case "6":
                        System.out.println("Título do Problema: ");
                        String title = scanner.nextLine();
                        System.out.println("Descreva seu problema: ");
                        String message = scanner.nextLine();
                        supportUseCase.openTicket(student.getEmail(), title, message);
                        view.showMessage("Ticket de suporte criado com sucesso! Aguarde atendimento");
                        break;
                    case "0":
                        loggedIn = false;
                        break;
                    default:
                        view.showError("Opção inválida");
                }
            } catch (BusinessException e) {
                view.showError(e.getMessage());
            } catch (NumberFormatException e) {
                view.showError("Número inválido");
            }
        }
    }

    public void handleAdminSession(User admin) {
        boolean loggedIn = true;
        while (loggedIn) {
            view.showAdminMenu();
            String option = scanner.nextLine();

            try {
                switch (option) {
                    case "1":
                        System.out.println("Digite o nome exato do curso para alternar o status: ");
                        String courseTitle = scanner.nextLine();
                        adminServiceUseCase.toggleCourseStatus(courseTitle);
                        view.showMessage("Status do curso atualizado com sucesso!");
                        break;
                    case "2":
                        System.out.println("Digite o email do aluno: ");
                        String email = scanner.nextLine();
                        System.out.println("Digite o novo plano (BASIC ou PREMIUM): ");
                        String plan = scanner.nextLine();
                        SubscriptionPlan newPlan = plan.equalsIgnoreCase("PREMIUM") ? new PremiumPlan() : new BasicPlan();
                        adminServiceUseCase.changeStudentPlan(email, newPlan);
                        view.showMessage("Plano do aluno atualizado com sucesso!");
                        break;
                    case "3":
                        Optional<SupportTicket> ticketOpt = supportUseCase.processNextTicket();
                        if (ticketOpt.isPresent()) {
                            SupportTicket ticket = ticketOpt.get();
                            System.out.println("\n-------------------------------");
                            System.out.println("ATENDENDO TICKET #" + ticket.getId());
                            System.out.println("Aluno: " + ticket.getUserEmail());
                            System.out.println("Mensagem: " + ticket.getMessage());
                            System.out.println("-------------------------------");
                            view.showMessage("Ticket removido da fila com sucesso");
                        } else {
                            view.showMessage("A fila de atendimento está vazia!");
                        }
                        break;
                    case "4":
                        showReportsMenu();
                        break;
                    case "5":
                        exportDataCsv();
                        break;
                    case "0":
                        loggedIn = false;
                        break;
                    default:
                        view.showError("Opção inválida");
                }
            } catch (BusinessException e) {
                view.showError(e.getMessage());
            }
        }
    }

    private void showReportsMenu() {
        System.out.println("\n--- RELATÓRIOS ---");
        System.out.println("1. Cursos por Nível de Dificuldade");
        System.out.println("2. Instrutores Únicos de Cursos Ativos");
        System.out.println("3. Alunos por Plano de Assinatura");
        System.out.println("4. Média Geral de Progresso");
        System.out.println("5. Aluno com Mais Matrículas Ativas");
        System.out.print("Opção: ");
        
        String option = scanner.nextLine();
        switch (option) {
            case "1":
                System.out.println("Digite o nível (BEGINNER, INTERMEDIATE, ADVANCED):");
                DifficultyLevel level = DifficultyLevel.valueOf(scanner.nextLine().toUpperCase());
                List<Course> courses = reportsUseCase.getCoursesByDifficulty(level);
                courses.forEach(c -> System.out.println(c.getTitle() + " - " + c.getInstructorName()));
                break;
            case "2":
                reportsUseCase.getUniqueActiveInstructors().forEach(System.out::println);
                break;
            case "3":
                Map<String, List<Student>> grouped = reportsUseCase.groupStudentsByPlan();
                grouped.forEach((plan, students) -> {
                    System.out.println("Plano: " + plan);
                    students.forEach(s -> System.out.println(" - " + s.getName()));
                });
                break;
            case "4":
                System.out.println("Média Geral de Progresso: " + reportsUseCase.getAverageProgress() + "%");
                break;
            case "5":
                Optional<Student> topStudent = reportsUseCase.getStudentWithMostEnrollments();
                if (topStudent.isPresent()) {
                    System.out.println("Aluno: " + topStudent.get().getName() + " com " + 
                        topStudent.get().getEnrollments().stream().filter(Enrollment::isActive).count() + " matrículas ativas.");
                } else {
                    System.out.println("Nenhum aluno com matrículas ativas.");
                }
                break;
            default:
                System.out.println("Opção inválida");
        }
    }

    private void exportDataCsv() {
        System.out.println("Qual entidade deseja exportar? (1) Cursos (2) Alunos");
        String entityOpt = scanner.nextLine();
        
        System.out.println("Digite os nomes das colunas separados por vírgula (ex: title,instructorName,durationInHours): ");
        String colsInput = scanner.nextLine();
        List<String> columns = Arrays.stream(colsInput.split(",")).map(String::trim).collect(Collectors.toList());
        
        String csvOutput = "";
        
        if (entityOpt.equals("1")) {
            List<Course> data = courseRepository.findAll();
            csvOutput = GenericCsvExporter.exportToCsv(data, columns);
        } else if (entityOpt.equals("2")) {
            List<Student> data = userRepository.findAll().stream()
                .filter(u -> u instanceof Student)
                .map(u -> (Student) u)
                .collect(Collectors.toList());
            csvOutput = GenericCsvExporter.exportToCsv(data, columns);
        } else {
            System.out.println("Opção inválida");
            return;
        }

        System.out.println("\n--- CSV GERADO ---");
        System.out.println(csvOutput);
        System.out.println("-------------------");
    }
}
