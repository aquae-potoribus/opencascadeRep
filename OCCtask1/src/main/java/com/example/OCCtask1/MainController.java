package com.example.OCCtask1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
public class MainController {

    @Autowired
    private ProjectRepository projectRepo;

    @GetMapping(value = "/api/projects")
    public List<Project> getAllProjects() {
        System.out.println("Getting all projects.");
        return projectRepo.findAll();
    }

    @GetMapping(value = "/api/projects/{projectId}")
    public Project getProjectById(@PathVariable Long projectId) throws IOException {
        System.out.println("Getting project with ID: {}" + projectId);
        if (projectRepo.findById(projectId).isPresent()) {
            return projectRepo.findById(projectId).get();
        }
        throw new IOException();
    }

    @PostMapping(value = "/api/projects")
    public Project addProject(@RequestBody Project prj) {
        prj.setId(Integer.toString(projectRepo.findAll().stream().map(project -> Integer.parseInt(project.getId())).max(Integer::compare).get() + 1));
        System.out.println("Saving project.");
        return projectRepo.save(prj);
    }

    @PutMapping(value = "/api/projects/{projects}")
    public Project updateProject(@PathVariable String projects, @RequestBody Project project) {
        project.setId(projects);
        System.out.println("Updating project with ID: {}" + projects);
        return projectRepo.save(project);
    }

    @DeleteMapping(value = "/api/projects/{project}")
    public void deleteProjects(@PathVariable Long project) {
        System.out.println("Deleting project with ID: {}" + project);
        projectRepo.deleteById(project);
    }
}
