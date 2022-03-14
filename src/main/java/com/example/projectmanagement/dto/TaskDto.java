package com.example.projectmanagement.dto;

import com.example.projectmanagement.entity.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDto {
    private Integer id;

    private String title;

    private String description;

    private Integer userPoints;

    private Integer estimation;

    private Status status;

    private UserDto user;

    private UserStoryDto userStory;
}
