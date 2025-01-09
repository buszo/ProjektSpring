package dev.projekt.students.courses.service;

import dev.projekt.students.courses.dto.TaskDTO;
import dev.projekt.students.courses.model.Task;
import dev.projekt.students.courses.model.ToDo;
import dev.projekt.students.courses.model.User;
import dev.projekt.students.courses.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {


    @Mock
    private TaskRepository taskRepository;

    @Mock
    private ToDoService toDoService;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addTaskToToDo_shouldAddTaskWhenAuthorized() {
        Integer todoId = 1;
        TaskDTO taskDTO = new TaskDTO(null, "Test task", false);
        User user = new User();
        user.setId(1);

        ToDo toDo = new ToDo();
        toDo.setId(todoId);
        toDo.setUser(user);

        when(toDoService.findById(todoId)).thenReturn(Optional.of(toDo));
        when(taskRepository.save(any(Task.class))).thenAnswer(invocation -> invocation.getArgument(0));

        taskService.addTaskToToDo(todoId, taskDTO, user);

        verify(toDoService, times(1)).findById(todoId);
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    void addTaskToToDo_shouldThrowExceptionWhenToDoNotFound() {
        Integer todoId = 1;
        TaskDTO taskDTO = new TaskDTO(null, "Test task", false);
        User user = new User();
        user.setId(1);

        when(toDoService.findById(todoId)).thenReturn(Optional.empty());


        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            taskService.addTaskToToDo(todoId, taskDTO, user);
        });

        assertEquals("ToDo not found", exception.getMessage());
        verify(toDoService, times(1)).findById(todoId);
        verify(taskRepository, never()).save(any(Task.class));
    }

    @Test
    void addTaskToToDo_shouldThrowExceptionWhenUserNotAuthorized() {
        Integer todoId = 1;
        TaskDTO taskDTO = new TaskDTO(null, "Test task", false);
        User user = new User();
        user.setId(1);

        User differentUser = new User();
        differentUser.setId(2);

        ToDo toDo = new ToDo();
        toDo.setId(todoId);
        toDo.setUser(differentUser);

        when(toDoService.findById(todoId)).thenReturn(Optional.of(toDo));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            taskService.addTaskToToDo(todoId, taskDTO, user);
        });

        assertEquals("You are not authorized to modify this ToDo", exception.getMessage());
        verify(toDoService, times(1)).findById(todoId);
        verify(taskRepository, never()).save(any(Task.class));
    }
}