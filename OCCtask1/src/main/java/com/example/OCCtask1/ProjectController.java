package com.example.OCCtask1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RestController
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepo;

    @GetMapping(value = "/api/projects")
    public List<Project> getAllProjects() {
        System.out.println("Getting all projects.");
        return projectRepo.findAll();
    }

    @GetMapping(value = "/api/projects/{projectId}")
    public Project getProjectById(@PathVariable String projectId){
        System.out.println("Getting project with ID: {}" + projectId);
        Optional OptionalProject = projectRepo.findById(projectId);
        if (OptionalProject.isPresent() && projectRepo.existsById(projectId)) {
            return (Project) OptionalProject.get();
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id Not Found", new FileNotFoundException());
    }

    @PostMapping(value = "/api/projects")
    public Project addProject(@RequestBody Project prj) {
        System.out.println("Saving project.");
        return projectRepo.save(prj);
    }

    @PutMapping(value = "/api/projects/{projectId}")
    public Project updateProject(@PathVariable String projectId, @RequestBody Project project) {
        System.out.println("Updating project with ID: {}" + projectId);
        if (!projectRepo.existsById(projectId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id Not Found", new FileNotFoundException());
        }
        project.setId(projectId);
        return projectRepo.save(project);
    }

    @DeleteMapping(value = "/api/projects/{projectId}")
    public void deleteProject(@PathVariable String projectId) {
        System.out.println("Deleting project with ID: {}" + projectId);
        if (!projectRepo.existsById(projectId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id Not Found", new FileNotFoundException());
        }
        projectRepo.deleteById(projectId);
    }
}