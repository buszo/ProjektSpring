package dev.projekt.students.courses.repository;

import dev.projekt.students.courses.model.ToDo;
import dev.projekt.students.courses.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoRepository extends JpaRepository<ToDo, Integer> {
    List<ToDo> findAllByUser(User user);
}