package dev.projekt.students.courses.model;

import jakarta.persistence.*;
import java.util.List;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "todos")
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Tytuł nie może być pusty")
    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "toDo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}