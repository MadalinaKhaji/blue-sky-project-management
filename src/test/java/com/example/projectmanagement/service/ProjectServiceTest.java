package com.example.projectmanagement.service;

import com.example.projectmanagement.dto.ProjectDto;
import com.example.projectmanagement.dto.ProjectPatchNameDto;
import com.example.projectmanagement.dto.UserDto;
import com.example.projectmanagement.entity.ProjectEntity;
import com.example.projectmanagement.repo.ProjectRepository;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    private final Mapper mapper = DozerBeanMapperBuilder.buildDefault();
    @Mock
    private ProjectRepository projectRepositoryMock;
    @Mock
    private UserService userServiceMock;
    private ProjectService projectService;

    private ProjectDto generateProjectDto() {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setName("some name");
        projectDto.setDescription("some description");

        UserDto userDto = new UserDto();
        userDto.setId(1);

        projectDto.setUserList(Collections.singletonList(userDto));
        return projectDto;
    }

    @BeforeEach
    public void beforeEach() {
        projectService = new ProjectService(projectRepositoryMock, userServiceMock);
    }


    @Test
    void createProject() {

        ProjectDto projectDto = generateProjectDto();

        ProjectEntity projectEntity = mapper.map(projectDto, ProjectEntity.class);

        Mockito.when(projectRepositoryMock.save(any())).thenReturn(projectEntity);

        List<Integer> userIdList = projectDto.getUserList().stream().map(UserDto::getId).collect(Collectors.toList());

        Mockito.when(userServiceMock.getByUserIdList(userIdList)).thenReturn(projectDto.getUserList());

        ProjectDto savedProject = projectService.createProject(projectDto);

        assertEquals(projectDto.getName(), savedProject.getName());
        assertEquals(projectDto.getDescription(), savedProject.getDescription());

        UserDto savedUser = savedProject.getUserList().get(0);

        assertEquals(projectDto.getUserList().get(0).getId(), savedUser.getId());
    }

    @Test
    void updateProject() {
        ProjectDto projectDto = generateProjectDto();

        ProjectEntity projectEntity = mapper.map(projectDto, ProjectEntity.class);

        Mockito.when(projectRepositoryMock.save(any())).thenReturn(projectEntity);

        List<Integer> userIdList = projectDto.getUserList().stream().map(UserDto::getId).collect(Collectors.toList());

        Mockito.when(userServiceMock.getByUserIdList(userIdList)).thenReturn(projectDto.getUserList());

        ProjectDto savedProject = projectService.updateProject(projectDto);

        assertEquals(projectDto.getName(), savedProject.getName());
        assertEquals(projectDto.getDescription(), savedProject.getDescription());

        UserDto savedUser = savedProject.getUserList().get(0);

        assertEquals(projectDto.getUserList().get(0).getId(), savedUser.getId());
    }

    @Test
    void patchProject() {
        ProjectDto projectDto = generateProjectDto();
        ProjectPatchNameDto projectPatchNameDto = new ProjectPatchNameDto();
        projectPatchNameDto.setName("some name");
        projectPatchNameDto.setId(1);

        ProjectEntity projectEntity = mapper.map(projectDto, ProjectEntity.class);

        Mockito.when(projectRepositoryMock.getById(projectPatchNameDto.getId()))
                .thenReturn(projectEntity);

        Mockito.when(projectRepositoryMock.save(any())).thenReturn(projectEntity);

        ProjectDto patchedProject = projectService.patchProject(projectPatchNameDto);

        assertEquals(projectDto.getName(), patchedProject.getName());
    }

    @Test
    void getProject() {
        ProjectDto projectDto = generateProjectDto();
        projectDto.setId(1);

        ProjectEntity projectEntity = mapper.map(projectDto, ProjectEntity.class);

        Mockito.when(projectRepositoryMock.getById(projectDto.getId()))
                .thenReturn(projectEntity);

        List<Integer> userIdList = projectDto.getUserList().stream().map(UserDto::getId).collect(Collectors.toList());

        Mockito.when(userServiceMock.getByUserIdList(userIdList))
                .thenReturn(projectDto.getUserList());

        ProjectDto getProject = projectService.getProject(1);

        assertEquals(projectDto.getName(), getProject.getName());
        assertEquals(projectDto.getDescription(), getProject.getDescription());

        UserDto savedUser = getProject.getUserList().get(0);

        assertEquals(projectDto.getUserList().get(0).getId(), savedUser.getId());
    }

    @Test
    void deleteProject() {
        projectService.deleteProject(1);
    }
}