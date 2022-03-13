package com.example.OCCtask1.Controller;

import com.example.OCCtask1.Dto.ProjectDAO;
import com.example.OCCtask1.Repository.Project;
import com.example.OCCtask1.Repository.ProjectRepository;
import com.example.OCCtask1.Service.MainService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    final ModelMapper modelMapper = new ModelMapper();
    final MainService mainService = new MainService(projectRepository, modelMapper);

    @GetMapping(value = "/api/projects")
    public List<ProjectDAO> getAllProjects() {
        mainService.gettingProjectsLog();

        return mainService.mapToDTO(projectRepository.findAll());
    }

    @GetMapping(value = "/api/projects/{projectId}")
    public ProjectDAO getProjectById(@PathVariable String projectId) {
        mainService.gettingProjectLog(projectId);
        mainService.checkOptional(projectId, projectRepository.findById(projectId));
        return mainService.mapToDTO(projectRepository.findById(projectId).get());
    }

    @PostMapping(value = "/api/projects")
    public ProjectDAO addProject(@RequestBody Project project) {
        mainService.insertProjectsLog();
        mainService.checkRequestBody(project);
        return mainService.mapToDTO(projectRepository.insert(project));
    }

    @PutMapping(value = "/api/projects/{projectId}")
    public ProjectDAO updateProject(@PathVariable String projectId, @RequestBody Project project) {
        mainService.updatingProjectLog(projectId);
        mainService.checkId(projectRepository.existsById(projectId), projectId);
        mainService.checkRequestBody(project);
        return mainService.mapToDTO(projectRepository.save(mainService.setId(projectId, project)));
    }

    @DeleteMapping(value = "/api/projects/{projectId}")
    public void deleteProject(@PathVariable String projectId) {
        mainService.deleteProjectLog(projectId);
        mainService.checkId(projectRepository.existsById(projectId), projectId);
        projectRepository.deleteById(projectId);
    }

}
