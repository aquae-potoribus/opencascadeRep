package com.example.OCCtask1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;


@RestController
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping(value = "/api/projects")
    public List<Project> getAllProjects() {
        System.out.println("Getting all projects.");
        return projectRepository.findAll();
    }

    @GetMapping(value = "/api/projects/{projectId}")
    public Project getProjectById(@PathVariable String projectId){
        System.out.println("Getting project with ID: {}" + projectId);
        checkId(projectId);
        Optional OptionalProject = projectRepository.findById(projectId);
        if (!OptionalProject.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "not find document", new FileNotFoundException());
        }
        return (Project) OptionalProject.get();
    }

    @PostMapping(value = "/api/projects")
    public Project addProject(@RequestBody Project prj) {
        System.out.println("insert project.");
        return projectRepository.insert(prj);
    }

    @PutMapping(value = "/api/projects/{projectId}")
    public Project updateProject(@PathVariable String projectId, @RequestBody Project project) {
        System.out.println("Updating project with ID: {}" + projectId);
        checkId(projectId);
        project.setId(projectId);
        return projectRepository.save(project);
    }

    @DeleteMapping(value = "/api/projects/{projectId}")
    public void deleteProject(@PathVariable String projectId) {
        System.out.println("Deleting project with ID: {}" + projectId);
        checkId(projectId);
        projectRepository.deleteById(projectId);
    }

    public void checkId(String projectId) {
        if (!projectRepository.existsById(projectId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id Not Found", new FileNotFoundException());
        }
    }

}
