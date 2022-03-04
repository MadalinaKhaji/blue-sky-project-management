package com.example.projectmanagement.controller;

import com.example.projectmanagement.dto.ProjectDto;
import com.example.projectmanagement.dto.ProjectPatchNameDto;
import com.example.projectmanagement.service.ProjectService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ProjectDto createProject(@RequestBody ProjectDto projectDto) {
        return projectService.createProject(projectDto);
    }

    @PutMapping
    public ProjectDto updateProject(@RequestBody ProjectDto projectDto) {
        return projectService.updateProject(projectDto);
    }

    @PatchMapping
    public ProjectDto patchProject(@RequestBody ProjectPatchNameDto projectPatchNameDto) {
        return projectService.patchProject(projectPatchNameDto);
    }

    @GetMapping("/{id}")
    public ProjectDto getProject(@PathVariable Integer id) {
        return projectService.getProject(id);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Integer id) {
        projectService.deleteProject(id);
    }
}
