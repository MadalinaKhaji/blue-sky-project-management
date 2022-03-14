package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.TaskDto;
import com.example.projectmanagement.service.TaskService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/task")
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public TaskDto createUserStory(@RequestBody TaskDto taskDto) {
        return taskService.create(taskDto);
    }

    @PutMapping
    public TaskDto updateUserStory(@RequestBody TaskDto taskDto) {
        return taskService.update(taskDto);
    }

    @GetMapping("/{id}")
    public TaskDto getUserStory(@PathVariable Integer id) {
        return taskService.get(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUserStory(@PathVariable Integer id) {
        taskService.delete(id);
    }
}
