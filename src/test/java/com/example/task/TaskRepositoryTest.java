package com.example.task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void testSaveAndFindTask() {
        Task task = new Task();
        task.setTitle("Complete Assignment");
        task.setDescription("Spring Boot JPA Testing");

        Task savedTask = taskRepository.save(task);

        assertThat(savedTask.getId()).isNotNull();

        entityManager.flush();
        entityManager.clear();

        Task foundTask = taskRepository.findById(savedTask.getId()).orElse(null);

        assertThat(foundTask).isNotNull();
        assertThat(foundTask.getTitle()).isEqualTo("Complete Assignment");

        System.out.println("=================================================");
        System.out.println("POSITIVE TEST PASSED: Task found with ID " + foundTask.getId());
        System.out.println("=================================================");
    }

    @Test
    void testFindTaskById_NotFound() {
        Long nonExistentId = 999L;

        Optional<Task> nonExistentTask = taskRepository.findById(nonExistentId);

        assertThat(nonExistentTask).isEmpty();

        System.out.println("=================================================");
        System.out.println("NEGATIVE TEST PASSED: No task found with ID " + nonExistentId);
        System.out.println("=================================================");
    }

    @Test
    void testDeliberateFailure_TitleMismatch() {
        Task task = new Task();
        task.setTitle("Complete Assignment");
        task.setDescription("Spring Boot JPA Testing");

        Task savedTask = taskRepository.save(task);

        Task foundTask = taskRepository.findById(savedTask.getId()).orElse(null);

        assertThat(foundTask).isNotNull();
        assertThat(foundTask.getTitle()).isEqualTo("Wrong Title Expected");
    }
}
