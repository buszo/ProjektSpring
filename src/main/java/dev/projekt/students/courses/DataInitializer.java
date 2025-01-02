import dev.projekt.students.courses.todo.Task;
import dev.projekt.students.courses.todo.TodoListRepository;
import dev.projekt.students.courses.user.User;
import dev.projekt.students.courses.todo.TodoList;
import dev.projekt.students.courses.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner dataInitializer(UserRepository userRepository, TodoListRepository todoListRepository) {
        return args -> {
            // Tworzymy przykładowych użytkowników
            User user1 = new User(null, "john_doe", "password123", List.of());
            User user2 = new User(null, "jane_doe", "password456", List.of());

            // Zapisujemy użytkowników do bazy
            userRepository.save(user1);
            userRepository.save(user2);

            Task task1 = new Task(null, "Zadanie 1 dla użytkownika 1", "asd", true);
            Task task2 = new Task(null, "asd 1 dla użytkownika 1", "asd", true);




            // Tworzymy przykładowe listy todo
            TodoList todoList1 = new TodoList(null, "Przykładowa lista 1", user1, List.of(task1, task2));
            TodoList todoList2 = new TodoList(null, "Przykładowa lista 2", user2, List.of(task3, task4));

            // Zapisujemy listy todo
            todoListRepository.save(todoList1);
            todoListRepository.save(todoList2);
        };
    }
}