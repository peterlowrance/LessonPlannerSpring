package com.example.service;

import com.example.models.Project;
import com.example.models.Section;
import com.example.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class SectionService {

    @Autowired
    private SectionRepository sectionRepository;

    public List<Section> findAll() {
        return sectionRepository.findAll();
    }

    public Section findByID(int id) {
        return sectionRepository.findById(id).orElse(null);
    }

    public Section findByName(String name) {
        return sectionRepository.findByName(name).orElse(null);
    }

    public Section update(Section newSection) {
        return sectionRepository.save(newSection);
    }

    public Section create(String name) {
        Section s = new Section();
        s.setName(name);
        s.setSectionID((int) (Math.random() * 5000 + 1));
        return sectionRepository.save(s);
    }

    public void delete(int id) {
        sectionRepository.deleteById(id);
    }

    public void delete(String sectionName) {
        sectionRepository.findByName(sectionName).ifPresent(s -> sectionRepository.delete(s));
    }

    public Set<Project> findProjectsBySection(String name) {
        Optional<Section> o = sectionRepository.findByName(name);
        if (o.isPresent()) {
            return o.get().getProjects();
        } else return null;
    }

    public Project findProjectInSectionByName(int sectionID, String projectName){
        Optional<Section> sectionO = sectionRepository.findById(sectionID);
        // Return the first project in the given section with the given name
        if(sectionO.isPresent()){
            return sectionO.get().getProjects().stream().filter(p -> p.getName().equals(projectName)).findFirst().orElse(null);
        } else return null;
    }
}
