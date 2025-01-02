package dev.projekt.students.courses.todo;

import jakarta.persistence.*;

@Entity
public record Task(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id,
        String description,
        boolean completed,
        @ManyToOne
        @JoinColumn(name = "todo_list_id")
        TodoList todoList
) {
}
