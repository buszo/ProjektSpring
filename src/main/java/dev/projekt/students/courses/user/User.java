package dev.projekt.students.courses.user;

import dev.projekt.students.courses.todo.TodoList;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public record User(@Id
                   @GeneratedValue(strategy = GenerationType.IDENTITY)
                   Long id,
                   String username,
                   String password,
                   @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
                   List<TodoList> todoLists) {

    // Pusty konstruktor dla JPA
    public User() {
        this(null, null, null, null);
    }

}