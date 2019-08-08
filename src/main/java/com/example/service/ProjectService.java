package com.example.service;

import com.example.models.Project;
import com.example.models.Section;
import com.example.repository.MemberRepository;
import com.example.repository.ProjectRepository;
import com.example.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;


@Service
public class ProjectService {

    @Autowired
    SectionRepository sectionRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private MemberRepository memberRepository;

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Project findByName(String name) {
        return projectRepository.findByName(name).orElse(null);
    }

    public Optional<Project> findByID(int id) {
        return projectRepository.findById(id);
    }

    public Project addProject(Section s, Project p) {
        p.setSection(s);
        if (p.getProjectID() == 0) {
            p.setProjectID((int) (Math.random() * 5000 + 1));
        }
        return projectRepository.save(p);
    }

    public Project addProject(int sectionID, Project p) {
        p.setSection(sectionRepository.findById(sectionID).orElse(null));
        /*if(p.getProjectID() == 0){
            p.setProjectID((int) (Math.random() * 5000 + 1));
        }*/
        return projectRepository.save(p);
    }

    public Project addProject(Project p) {
        return projectRepository.save(p);
    }

    public Project create(String name) {
        Project p = new Project();
        p.setName(name);
        p.setProjectID((int) (Math.random() * 5000 + 1));
        return projectRepository.save(p);
    }

    public void delete(int id) {
        projectRepository.findById(id).ifPresent(p -> {
            p.removeFromSection();
            projectRepository.delete(p);
        });
    }

    public int updateDate(int projectID, String date) {
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        try {
            System.out.println("parsed " + parser.parse(date).getTime());
            Date dateFormatted = new Date(parser.parse(date).getTime());
            System.out.println(date + "\nFormatted " + dateFormatted);
            return 0; //return projectRepository.updateDate(projectID, dateFormatted); TODO
        } catch (ParseException e) {
            System.err.println(e.getMessage());
        }
        return 0;
    }

    public Project updateProject(Project p) {
        Optional<Project> oldP = projectRepository.findById(p.getProjectID());
        if (oldP.isPresent()) {
            p.setSection(oldP.get().getSection());
            return projectRepository.save(p);
        } else return null;
    }
}
