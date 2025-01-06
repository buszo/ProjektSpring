package dev.projekt.students.courses.controller;

import dev.projekt.students.courses.dto.TaskDTO;
import dev.projekt.students.courses.model.Task;
import dev.projekt.students.courses.model.ToDo;
import dev.projekt.students.courses.model.User;
import dev.projekt.students.courses.repository.UserRepository;
import dev.projekt.students.courses.service.TaskService;
import dev.projekt.students.courses.service.ToDoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskController
{
    private final ToDoService toDoService;
    private final UserRepository userRepository;
    private final TaskService taskService;

    public TaskController(ToDoService toDoService, UserRepository userRepository, TaskService taskService) {
        this.toDoService = toDoService;
        this.userRepository = userRepository;
        this.taskService = taskService;
    }

    @PostMapping("/todo/{todoId}")
    public ResponseEntity<String> addTaskToToDo(
            @PathVariable Integer todoId,
            @RequestBody @Valid TaskDTO taskDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ToDo toDo = toDoService.findById(todoId)
                .orElseThrow(() -> new RuntimeException("ToDo not found"));

        if (!toDo.getUser().equals(user)) {
            throw new RuntimeException("You are not authorized to modify this ToDo");
        }

        Task task = new Task();
        task.setDescription(taskDTO.getDescription());
        task.setCompleted(false);
        task.setToDo(toDo);

        taskService.save(task);

        return ResponseEntity.status(201).body("Task added successfully");
    }
}
