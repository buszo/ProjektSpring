package dev.projekt.students.courses.todo;
import dev.projekt.students.courses.todo.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoListRepository extends JpaRepository<TodoList, Long> {
}