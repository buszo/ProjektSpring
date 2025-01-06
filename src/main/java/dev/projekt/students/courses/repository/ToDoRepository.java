package dev.projekt.students.courses.repository;

import dev.projekt.students.courses.model.ToDo;
import dev.projekt.students.courses.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ToDoRepository extends JpaRepository<ToDo, Integer> {
    List<ToDo> findAllByUser(User user);
    Optional<ToDo> findById(Integer id);

}