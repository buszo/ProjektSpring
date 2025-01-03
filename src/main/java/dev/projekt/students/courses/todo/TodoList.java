package dev.projekt.students.courses.todo;

import dev.projekt.students.courses.user.User;
import jakarta.persistence.*;

import java.util.List;

@Entity
public record TodoList(@Id
                       @GeneratedValue(strategy = GenerationType.IDENTITY)
                       Long id,
                       String name,
                       @ManyToOne
                       @JoinColumn(name = "user_id")
                       User user,
                       @OneToMany(mappedBy = "todoList", cascade = CascadeType.ALL, orphanRemoval = true)
                       List<Task> tasks) {

}
