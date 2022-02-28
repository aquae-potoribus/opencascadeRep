package com.example.testthymleef;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @Autowired
    private ProjectRepo projectRepo ;

    @GetMapping("/api/projects")
    public String BlogMain(Model model) {
        Iterable <Project> users = projectRepo.findAll();
        model.addAttribute("users", users);
        Project project = new Project("ephim");
        projectRepo.save(project);
        return "blog-main";
    }

}
