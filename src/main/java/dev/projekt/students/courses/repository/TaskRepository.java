package dev.projekt.students.courses.repository;

import dev.projekt.students.courses.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepository extends JpaRepository<Task, Integer> {


}