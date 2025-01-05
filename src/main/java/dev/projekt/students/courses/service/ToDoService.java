package dev.projekt.students.courses.service;

import dev.projekt.students.courses.dto.ToDoDTO;
import dev.projekt.students.courses.model.ToDo;
import dev.projekt.students.courses.model.User;
import dev.projekt.students.courses.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToDoService {

    @Autowired
    private ToDoRepository toDoRepository;

    public ToDo save(ToDo toDo) {
        return toDoRepository.save(toDo);
    }

    public List<ToDoDTO> getAllByUser(User user) {
        List<ToDo> todos = toDoRepository.findAllByUser(user);
        return todos.stream()
                .map(ToDoDTO::new)
                .collect(Collectors.toList());
    }
}