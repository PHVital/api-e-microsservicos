package main.java.br.com.academiadev.domain.entities;

import main.java.br.com.academiadev.domain.exceptions.BusinessException;

public class Enrollment {
    private Student student;
    private Course course;
    private int progressPercentage;
    private boolean active;

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.progressPercentage = 0;
        this.active = true;
    }

    public void updateProgress(int newProgress) {
        if (!active) {
            throw new BusinessException("Matrícula inativa");
        }
        if (newProgress < 0 || newProgress > 100) {
            throw new BusinessException("Progresso deve ser entre 0 e 100");
        }
        this.progressPercentage = newProgress;
    }

    public void cancel() {
        this.active = false;
    }

    public boolean isActive() {
        return active;
    }

    public Course getCourse() {
        return course;
    }

    public int getProgressPercentage() {
        return progressPercentage;
    }

    public Student getStudent() {
        return student;
    }
}
