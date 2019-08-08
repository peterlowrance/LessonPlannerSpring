package com.example.controller;

import com.example.models.Project;
import com.example.models.Section;
import com.example.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class SectionController {

    @Autowired
    SectionService sectionService;

    // GET mappings -----------------------------

    @GetMapping("/sections")
    @ResponseBody
    List<Section> findAllSections() {
        return sectionService.findAll();
    }

    @GetMapping("/{name}")
    @ResponseBody
    Section findSectionByName(@PathVariable("name") String name) {
        return sectionService.findByName(name);
    }

    @GetMapping("/{sectionName}/projects")
    @ResponseBody
    Set<Project> findAllProjects(@PathVariable("sectionName") String sectionName) {
        return sectionService.findProjectsBySection(sectionName);
    }

    @GetMapping("/{sectionID}/{name}")
    @ResponseBody
    Project findProjectInSectionByName(@PathVariable("sectionID") int sectionID, @PathVariable("name") String name) {
        return sectionService.findProjectInSectionByName(sectionID, name);
    }

    // PUT mappings -----------------------------

    @PutMapping("/{sectionName}")
    @ResponseBody
    public Section createSection(@PathVariable("sectionName") String sectionName) {
        return sectionService.create(sectionName);
    }

    // POST mappings -----------------------------

    @PostMapping("/sections")
    @ResponseBody
    public Section createSection(@RequestBody Section section) {
        return sectionService.create(section.getName());
    }

    // DELETE mappings -----------------------------

    @DeleteMapping("/sections/{id}")
    @ResponseBody
    public void deleteSection(@PathVariable("id") int id) {
        sectionService.delete(id);
    }
}
