package dev.projekt.students.courses.dto;

import dev.projekt.students.courses.model.Task;

import java.util.List;

public class ToDoDTO {
    private Integer id;
    private String title;
    private List<TaskDTO> tasks;

    public ToDoDTO(Integer id, String title, List<TaskDTO> tasks) {
        this.id = id;
        this.title = title;
        this.tasks = tasks;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<TaskDTO> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDTO> tasks) {
        this.tasks = tasks;
    }
}