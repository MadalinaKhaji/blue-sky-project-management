package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.ProjectDto;
import com.example.projectmanagement.dto.UserDto;
import com.example.projectmanagement.entity.ProjectEntity;
import com.example.projectmanagement.repo.ProjectRepository;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    private final Mapper mapper = DozerBeanMapperBuilder.buildDefault();
    private ProjectRepository projectRepository;
    private RestTemplate restTemplate;

    @Value("${usermanagement.user.getAllByIds}")
    private String usermanagementGetAllByIdsUrl;

    private ProjectService(ProjectRepository projectRepository,
                           RestTemplate restTemplate) {
        this.projectRepository = projectRepository;
        this.restTemplate = restTemplate;
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
        ProjectEntity projectEntity = projectRepository.getById(id);

        String userList = projectEntity.getUserList()
                .stream()
                .map(userEntity -> userEntity.getId().toString())
                .collect(Collectors.joining(","));

        UserDto[] userDtos = restTemplate.getForObject(usermanagementGetAllByIdsUrl+"/"+userList,
                UserDto[].class);

        ProjectDto projectDto = mapper.map(projectRepository.getById(id), ProjectDto.class);

        projectDto.setUserList(Arrays.asList(userDtos));

        return projectDto;
    }

    public void deleteProject(Integer id) {
        projectRepository.deleteById(id);
    }
}
