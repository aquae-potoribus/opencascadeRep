package com.example.testthymleef;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class MainController {

    @Autowired
    private ProjectRepo projectRepo ;

    long counter ;

    @GetMapping(value = "/api/projects")
    public List<Project> getAllMovies() {
        System.out.println("Getting all movies.");
        counter = projectRepo.findAll().stream().count() + 1;
        return projectRepo.findAll();
    }
    @GetMapping(value = "/api/projects/{movieId}")
    public Project getMovieById(@PathVariable Long movieId) {
        System.out.println("Getting movie with ID: {}"+ movieId);
        return projectRepo.findByid(movieId);
    }
    @PostMapping(value = "/api/projects")
    public Project addMovie(@RequestBody Project prj) {
        prj.setId(counter++);
        System.out.println("Saving movie.");
        return projectRepo.save(prj);
    }
    @PutMapping(value = "/api/projects/{movieId}")
    public Project updateMovie(@PathVariable Long movieId, @RequestBody Project project) {
        project.setId(movieId);
        System.out.println("Updating movie with ID: {}"+ movieId);
        return projectRepo.save(project);
    }
    @DeleteMapping(value = "/api/projects/{movieId}")
    public void deleteMovie(@PathVariable Long movieId) {
        System.out.println("Deleting movie with ID: {}"+ movieId);
        projectRepo.deleteById(movieId);
    }
}
