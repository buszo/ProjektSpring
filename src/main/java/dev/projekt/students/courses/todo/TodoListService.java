package dev.projekt.students.courses.todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoListService {

    @Autowired
    private TodoListRepository todoListRepository;

    public TodoList createTodoList(TodoList todoList) {
        return todoListRepository.save(todoList);
    }

    public List<TodoList> getAllTodoLists() {
        return todoListRepository.findAll();
    }

    public TodoList getTodoListById(Long id) {
        return todoListRepository.findById(id).orElse(null);
    }

    public void deleteTodoList(Long id) {
        todoListRepository.deleteById(id);
    }
}