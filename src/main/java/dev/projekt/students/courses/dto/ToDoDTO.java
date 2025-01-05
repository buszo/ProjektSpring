package dev.projekt.students.courses.dto;

import dev.projekt.students.courses.model.ToDo;

public class ToDoDTO {

    private Integer id;
    private String title;

    public ToDoDTO(ToDo toDo) {
        this.id = toDo.getId();
        this.title = toDo.getTitle();
    }

    // Gettery i Settery

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
}