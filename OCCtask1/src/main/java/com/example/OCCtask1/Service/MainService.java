package com.example.OCCtask1.Service;

import com.example.OCCtask1.Dto.ProjectDAO;
import com.example.OCCtask1.Repository.Project;
import com.example.OCCtask1.Repository.ProjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MainService {

    @Autowired
    private ProjectRepository projectRepository;

    private final ModelMapper modelMapper;

    public MainService(ProjectRepository projectRepository, ModelMapper modelMapper) {

        this.projectRepository = projectRepository;
        this.modelMapper = modelMapper;
    }

    public ProjectDAO mapToDTO(Project project) {
        return modelMapper.map(project, ProjectDAO.class);
    }

    public List<ProjectDAO> mapToDTO(List<Project> projects) {
        List<ProjectDAO> a = new ArrayList<>();

        for (Project project : projects) {
            a.add(mapToDTO(project));
        }
        return a;
    }

    @Autowired
    public void ProjectsLog() {
        System.out.println(projectRepository.findAll());
    }

    public void gettingProjectsLog() {
        System.out.println("Getting all projects.");
    }

    public void gettingProjectLog(String projectId) {
        System.out.println("Getting project with ID: " + projectId);
    }

    public void deleteProjectLog(String projectId) {
        System.out.println("Delete project with ID: " + projectId);
    }

    public void updatingProjectLog(String projectId) {
        System.out.println("Updating project with ID: " + projectId);
    }

    public void insertProjectsLog() {
        System.out.println("insert project.");
    }

    public Project setId(String projectId, Project project) {
        project.setId(projectId);
        return project;
    }

    public void checkRequestBody(Project project) {
        String name = project.getName();
        if (name == null || name.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "the property 'name' is not defined");
        }
    }

    public void checkOptional(String projectId, Optional<Project> optionalProject) {
        if (optionalProject.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Not found project with id=%s", projectId));
        }
    }

    public void checkId(Boolean exist, String projectId) {
        if (!exist) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Not found project with id=%s", projectId));
        }
    }
}
