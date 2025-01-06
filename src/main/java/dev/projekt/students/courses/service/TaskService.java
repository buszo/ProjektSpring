package dev.projekt.students.courses.service;

import dev.projekt.students.courses.model.Task;
import dev.projekt.students.courses.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task save(Task task){
        return taskRepository.save(task);
    }
}
