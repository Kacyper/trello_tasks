package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TrelloFacadeTest {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "test title", "test content");

        //When
        Task task = taskMapper.mapToTask(taskDto);

        //Then
        assertNotNull(task);
        assertEquals(1L, task.getId());
    }

    @Test
    public void testMapToTaskDto() {
        //Given
        Task task = new Task(1L, "test title", "test content");

        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        //Then
        assertNotNull(taskDto);
        assertEquals(1L, taskDto.getId());
    }

    @Test
    public void testToTaskDtoList() {
        //Given
        Task task1 = new Task(1L, "test title 1", "test content 1");
        Task task2 = new Task(2L, "test title 2", "test content 2");
        List<Task> tasks = List.of(task1, task2);

        //When
        List<TaskDto> taskDtoList = taskMapper.mapToTaskDtoList(tasks);

        //Then
        assertNotNull(taskDtoList);
        assertEquals(2, taskDtoList.size());
    }

}
