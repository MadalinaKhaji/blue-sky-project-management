package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.ProjectDto;
import com.example.projectmanagement.entity.ProjectEntity;
import com.example.projectmanagement.repo.ProjectRepository;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    private final Mapper mapper = DozerBeanMapperBuilder.buildDefault();
    private ProjectRepository projectRepository;

    private ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public ProjectDto createProject(ProjectDto projectDto) {
        ProjectEntity savedProject = projectRepository.save(mapper.map(projectDto, ProjectEntity.class));
        return mapper.map(savedProject, ProjectDto.class);
    }

    public ProjectDto updateProject(ProjectDto projectDto) {
        ProjectEntity updatedProject = projectRepository.save(mapper.map(projectDto, ProjectEntity.class));
        return mapper.map(updatedProject, ProjectDto.class);
    }

    public ProjectDto getProject(Integer id) {
        return mapper.map(projectRepository.getById(id), ProjectDto.class);
    }

    public void deleteProject(Integer id) {
        projectRepository.deleteById(id);
    }
}
