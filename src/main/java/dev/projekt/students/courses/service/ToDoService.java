package dev.projekt.students.courses.service;

import dev.projekt.students.courses.dto.TaskDTO;
import dev.projekt.students.courses.dto.ToDoDTO;
import dev.projekt.students.courses.model.ToDo;
import dev.projekt.students.courses.model.User;
import dev.projekt.students.courses.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
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
                .map(toDo -> {
                    List<TaskDTO> taskDTOs = toDo.getTasks().stream()
                            .map(task -> new TaskDTO(
                                    task.getId(),
                                    task.getDescription(),
                                    task.isCompleted(),
                                    task.getCompleted_time()
                            ))
                            .collect(Collectors.toList());

                    return new ToDoDTO(toDo.getId(), toDo.getTitle(), taskDTOs);
                })
                .collect(Collectors.toList());
    }

    public ToDoDTO getToDoById(Integer id, User user) {
        ToDo toDo = toDoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ToDo not found"));

        if (!toDo.getUser().equals(user)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You can only view your own ToDo lists");
        }

        List<TaskDTO> taskDTOs = toDo.getTasks().stream()
                .map(task -> new TaskDTO(
                        task.getId(),
                        task.getDescription(),
                        task.isCompleted(),
                        task.getCompleted_time() // Dodaj czas zakończenia
                ))
                .collect(Collectors.toList());

        return new ToDoDTO(toDo.getId(), toDo.getTitle(), taskDTOs);
    }

    public Optional<ToDo> findById(Integer id) {
        return toDoRepository.findById(id);
    }

    public void delete(ToDo toDo) {
        toDoRepository.delete(toDo);
    }
}