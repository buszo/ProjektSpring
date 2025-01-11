package dev.projekt.students.courses.repository;

import dev.projekt.students.courses.model.Task;
import dev.projekt.students.courses.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface TaskRepository extends JpaRepository<Task, Integer> {
    Optional<Task> findById(Integer id);
}