package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.ProjectDto;
import com.example.projectmanagement.dto.ProjectPatchNameDto;
import com.example.projectmanagement.dto.UserStoryDto;
import com.example.projectmanagement.service.ProjectService;
import com.example.projectmanagement.service.UserStoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user-story")
public class UserStoryController {
    private UserStoryService userStoryService;

    public UserStoryController(UserStoryService userStoryService) {
        this.userStoryService = userStoryService;
    }

    @PostMapping
    public UserStoryDto createUserStory(@RequestBody UserStoryDto userStoryDto) {
        return userStoryService.create(userStoryDto);
    }

    @PutMapping
    public UserStoryDto updateUserStory(@RequestBody UserStoryDto userStoryDto) {
        return userStoryService.update(userStoryDto);
    }

    @GetMapping("/{id}")
    public UserStoryDto getUserStory(@PathVariable Integer id) {
        return userStoryService.get(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUserStory(@PathVariable Integer id) {
        userStoryService.delete(id);
    }
}
