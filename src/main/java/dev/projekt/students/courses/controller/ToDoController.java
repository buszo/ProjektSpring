package dev.projekt.students.courses.controller;

import dev.projekt.students.courses.dto.TaskDTO;
import dev.projekt.students.courses.dto.ToDoDTO;
import dev.projekt.students.courses.dto.UserDTO;
import dev.projekt.students.courses.model.ToDo;
import dev.projekt.students.courses.model.User;
import dev.projekt.students.courses.repository.ToDoRepository;
import dev.projekt.students.courses.repository.UserRepository;
import dev.projekt.students.courses.service.ToDoService;
import dev.projekt.students.courses.service.UserDetailsServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/todo")
public class ToDoController {

    @Autowired
    private ToDoService toDoService;

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/create")
    public ResponseEntity<String> createToDo(@RequestBody @Valid ToDo newToDo) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        newToDo.setUser(user);
        toDoService.save(newToDo);
        return ResponseEntity.status(201).body("Lista utworzona");

    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDoDTO> getToDoById(@PathVariable Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ToDo toDo = toDoService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ToDo not found"));

        if (!toDo.getUser().equals(user)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can only view your own ToDo lists");
        }

        List<TaskDTO> taskDTOs = toDo.getTasks().stream()
                .map(task -> new TaskDTO(task.getId(), task.getDescription(), task.isCompleted()))
                .collect(Collectors.toList());

        ToDoDTO toDoDTO = new ToDoDTO(toDo.getId(), toDo.getTitle(), taskDTOs);

        return ResponseEntity.ok(toDoDTO);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ToDoDTO>> getAllToDos() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<ToDoDTO> todos = toDoService.getAllByUser(user);

        return ResponseEntity.ok(todos);
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<String> updateToDo(@PathVariable Integer id, @RequestBody @Valid ToDo updatedToDo) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        ToDo existingToDo = toDoService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ToDo not found"));

        if (!existingToDo.getUser().getUsername().equals(username)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can only edit your own ToDo lists");
        }

        existingToDo.setTitle(updatedToDo.getTitle());
        toDoService.save(existingToDo);

        return ResponseEntity.ok("ToDo updated successfully");
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteToDo(@PathVariable Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        ToDo existingToDo = toDoService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ToDo not found"));

        if (!existingToDo.getUser().getUsername().equals(username)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can only delete your own ToDo lists");
        }

        toDoService.delete(existingToDo);

        return ResponseEntity.ok("ToDo and all associated tasks deleted successfully");
    }
}