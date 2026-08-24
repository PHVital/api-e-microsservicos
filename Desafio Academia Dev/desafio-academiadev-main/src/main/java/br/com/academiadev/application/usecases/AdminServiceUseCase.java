package main.java.br.com.academiadev.application.usecases;

import main.java.br.com.academiadev.application.repositories.CourseRepository;
import main.java.br.com.academiadev.application.repositories.UserRepository;
import main.java.br.com.academiadev.domain.entities.Course;
import main.java.br.com.academiadev.domain.entities.Student;
import main.java.br.com.academiadev.domain.entities.SubscriptionPlan;
import main.java.br.com.academiadev.domain.enums.CourseStatus;
import main.java.br.com.academiadev.domain.exceptions.BusinessException;

public class AdminServiceUseCase {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public AdminServiceUseCase(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    public void toggleCourseStatus(String courseTitle) {
        Course course = courseRepository.findByTitle(courseTitle)
                .orElseThrow(() -> new BusinessException("Curso não encontrado"));
        
        if (course.getStatus() == CourseStatus.ACTIVE) {
            course.setStatus(CourseStatus.INACTIVE);
        } else {
            course.setStatus(CourseStatus.ACTIVE);
        }
    }

    public void changeStudentPlan(String studentEmail, SubscriptionPlan newPlan) {
        Student student = (Student) userRepository.findByEmail(studentEmail)
                .orElseThrow(() -> new BusinessException("Aluno não encontrado"));
        
        student.setSubscriptionPlan(newPlan);
    }
}
