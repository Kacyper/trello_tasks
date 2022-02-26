package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DbServiceTest {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void testReturnEmptyList() {
        //Given
        List<Task> taskList = dbService.getAllTasks();

        //Then
        assertEquals(0, taskList.size());
    }

    @Test
    public void testGetAllTasks() {
        //Given
        List<Task> taskMock = List.of(new Task(1L, "title list", "test content"));
        when(taskRepository.findAll()).thenReturn(taskMock);

        //When
        List<Task> taskList = dbService.getAllTasks();

        //Then
        assertEquals(1, taskList.size());
    }

    @Test
    public void testOptionalGetTask() {
        //Given
        Optional<Task> taskOptional = dbService.getTask(1L);

        //Then
        assertTrue(taskOptional.isEmpty());
    }

    @Test
    public void testSaveTask() {
        //Given
        Task taskMock = new Task(1L, "test title", "test content");
        when(taskRepository.save(taskMock)).thenReturn(taskMock);

        //When
        Task task = dbService.saveTask(taskMock);

        //Then
        assertEquals(taskMock.getId(), task.getId());
    }

    @Test
    public void testDeleteTask() {
        //Given
        Task taskMock = new Task(1L, "test title", "test content");
        when(taskRepository.save(taskMock)).thenReturn(taskMock);
        dbService.saveTask(taskMock);
        Long taskId = taskMock.getId();

        //When
        List<Task> taskList = dbService.getAllTasks();
        dbService.deleteTask(taskId);

        //Then
        assertEquals(0, taskList.size());
    }

}
