package dev.projekt.students.courses.service;

import dev.projekt.students.courses.dto.TaskDTO;
import dev.projekt.students.courses.dto.ToDoDTO;
import dev.projekt.students.courses.model.Task;
import dev.projekt.students.courses.model.ToDo;
import dev.projekt.students.courses.model.User;
import dev.projekt.students.courses.repository.ToDoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ToDoServiceTest {
    @Mock
    private ToDoRepository toDoRepository;

    @InjectMocks
    private ToDoService toDoService;

    private User testUser;
    private ToDo testToDo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testUser = new User();
        testUser.setId(1);
        testUser.setUsername("testUser");

        Task task1 = new Task();
        Task task2 = new Task();
        task2.setDescription("test2");
        task1.setDescription("test1");
        testToDo = new ToDo();
        testToDo.setId(1);
        testToDo.setTitle("Test ToDo");
        testToDo.setUser(testUser);
        testToDo.setTasks(Arrays.asList(task1, task2));
    }




    @Test
    void save() {
        when(toDoRepository.save(testToDo)).thenReturn(testToDo);

        ToDo savedToDo = toDoService.save(testToDo);

        assertNotNull(savedToDo);
        assertEquals(testToDo.getId(), savedToDo.getId());
        verify(toDoRepository, times(1)).save(testToDo);
    }

    @Test
    void getAllByUser() {
        User user = new User();
        user.setId(1);

        List<Task> tasks = List.of(new Task(1, "Task 1", false, LocalDateTime.now()));
        List<ToDo> toDoList = List.of(new ToDo(1, "ToDo 1", user,tasks));

        when(toDoRepository.findAllByUser(user)).thenReturn(toDoList);

        List<ToDoDTO> result = toDoService.getAllByUser(user);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("ToDo 1", result.get(0).getTitle());
        verify(toDoRepository, times(1)).findAllByUser(user);
    }

    @Test
    void getToDoById_shouldThrowNotFoundWhenToDoDoesNotExist() {
        when(toDoRepository.findById(1)).thenReturn(Optional.empty());

        User user = new User();
        user.setId(1);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            toDoService.getToDoById(1, user);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("ToDo not found", exception.getReason());
    }

    @Test
    void getToDoById_shouldThrowForbiddenWhenUserDoesNotMatch() {
        User user1 = new User();
        user1.setId(1);

        User user2 = new User();
        user2.setId(2);

        List<Task> tasks = List.of(new Task(1, "Task 1", false, LocalDateTime.now()));
        ToDo toDo = new ToDo(1, "ToDo 1",user2, tasks );

        when(toDoRepository.findById(1)).thenReturn(Optional.of(toDo));

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            toDoService.getToDoById(1, user1);
        });

        assertEquals(HttpStatus.FORBIDDEN, exception.getStatusCode());
        assertEquals("You can only view your own ToDo lists", exception.getReason());
    }
    @Test
    void delete() {
        ToDo toDo = new ToDo();
        toDo.setId(1);

        doNothing().when(toDoRepository).delete(toDo);

        toDoService.delete(toDo);

        verify(toDoRepository, times(1)).delete(toDo);
    }
}