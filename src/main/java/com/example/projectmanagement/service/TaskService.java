package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.TaskDto;
import com.example.projectmanagement.dto.UserDto;
import com.example.projectmanagement.entity.TaskEntity;
import com.example.projectmanagement.repo.TaskRepository;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private final Mapper mapper = DozerBeanMapperBuilder.buildDefault();
    private final TaskRepository taskRepository;
    private final UserService userService;

    public TaskService(TaskRepository taskRepository,
                            UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    public TaskDto create(TaskDto taskDto) {
        TaskEntity taskEntity = mapper.map(taskDto, TaskEntity.class);
        TaskEntity savedTask = taskRepository.save(taskEntity);

        TaskDto savedTaskDto = mapper.map(savedTask, TaskDto.class);

        Integer userId = savedTask.getUser().getId();

        if(null == userId) {
            return savedTaskDto;
        }

        UserDto userDto = userService.getByUserId(userId);
        savedTaskDto.setUser(userDto);

        return savedTaskDto;
    }

    public TaskDto update(TaskDto taskDto) {
        TaskEntity taskEntity = mapper.map(taskDto, TaskEntity.class);
        TaskEntity savedTask = taskRepository.save(taskEntity);

        return mapper.map(savedTask, TaskDto.class);
    }

    public void delete(Integer id) {
        taskRepository.deleteById(id);
    }

    public TaskDto get(Integer id) {
        TaskEntity taskEntity = taskRepository.getById(id);
        TaskDto taskDto = mapper.map(taskEntity, TaskDto.class);

        Integer userId = taskDto.getUser().getId();

        if(null == userId) {
            return taskDto;
        }

        UserDto userDto = userService.getByUserId(userId);
        taskDto.setUser(userDto);

        return taskDto;
    }

}
