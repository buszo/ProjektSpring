package dev.projekt.students.courses.service;

import dev.projekt.students.courses.dto.TaskDTO;
import dev.projekt.students.courses.model.Task;
import dev.projekt.students.courses.model.ToDo;
import dev.projekt.students.courses.model.User;
import dev.projekt.students.courses.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final ToDoService toDoService;

    public TaskService(TaskRepository taskRepository, ToDoService toDoService) {
        this.taskRepository = taskRepository;
        this.toDoService = toDoService;
    }

    public Task save(Task task){
        return taskRepository.save(task);
    }

    public void addTaskToToDo(Integer todoId, TaskDTO taskDTO, User user) {
        ToDo toDo = toDoService.findById(todoId)
                .orElseThrow(() -> new RuntimeException("ToDo not found"));

        if (!toDo.getUser().equals(user)) {
            throw new RuntimeException("You are not authorized to modify this ToDo");
        }

        Task task = new Task();
        task.setDescription(taskDTO.getDescription());
        task.setCompleted(false);
        task.setToDo(toDo);

        taskRepository.save(task);
    }
}
