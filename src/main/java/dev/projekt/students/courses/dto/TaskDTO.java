package dev.projekt.students.courses.dto;

import java.time.LocalDateTime;

public class TaskDTO {
    private Integer id;
    private String description;
    private boolean completed;
    private LocalDateTime completedTime;


    public TaskDTO(Integer id, String description, boolean completed, LocalDateTime completedTime) {
        this.id = id;
        this.description = description;
        this.completed = completed;
        this.completedTime = completedTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public LocalDateTime getCompletedTime() {
        return completedTime;
    }

    public void setCompletedTime(LocalDateTime completedTime) {
        this.completedTime = completedTime;
    }
}