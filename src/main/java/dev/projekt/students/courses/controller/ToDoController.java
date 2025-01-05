package dev.projekt.students.courses.controller;

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
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @GetMapping("/all")
    public ResponseEntity<List<ToDoDTO>> getAllToDos() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<ToDoDTO> todos = toDoService.getAllByUser(user);
        return ResponseEntity.ok(todos);
    }
}