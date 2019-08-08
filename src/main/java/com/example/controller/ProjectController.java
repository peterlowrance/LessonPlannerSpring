package com.example.controller;

import com.example.models.Project;
import com.example.service.ProjectService;
import com.example.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    SectionService sectionService;

    // GET mappings -----------------------------

    @GetMapping("/project/{id}")
    @ResponseBody
    Optional<Project> findProjectByID(@PathVariable("id") int id) {
        return projectService.findByID(id);
    }


    // PUT mappings -----------------------------

    @PutMapping("/project/{id}")
    @ResponseBody
    public Project updateProjectDate(@PathVariable("id") int projectID, @RequestBody Project project) {
        return projectService.updateProject(project);
    }

    // POST mappings -----------------------------

    @PostMapping("/section/{sectionID}/project")
    @ResponseBody
    public Project createProject(@PathVariable("sectionID") int sectionID, @RequestBody Project project) {
        return projectService.addProject(sectionID, project);
    }

    @PostMapping("/project/{id}")
    @ResponseBody
    public int createProject(@PathVariable("id") int projectID, @RequestBody String date) {
        return projectService.updateDate(projectID, date);
    }


    // DELETE mappings -----------------------------

    @DeleteMapping("/project/{id}")
    @ResponseBody
    public void deleteProject(@PathVariable("id") int id) {
        projectService.delete(id);
    }
}
