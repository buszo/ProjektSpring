package dev.projekt.students.courses.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "description")
    private String description;

    @Column(name = "completed")
    private boolean completed;

    @Column(name = "completed_time")
    private LocalDateTime completed_time;

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public LocalDateTime getCompleted_time() {
        return completed_time;
    }

    public void setCompleted_time(LocalDateTime completed_time) {
        this.completed_time = completed_time;
    }

    @ManyToOne
    @JoinColumn(name = "todo_id")
    private ToDo toDo;

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

    public ToDo getToDo() {
        return toDo;
    }

    public void setToDo(ToDo toDo) {
        this.toDo = toDo;
    }
}