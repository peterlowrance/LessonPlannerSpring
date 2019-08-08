package com.example.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "SECTION")
public class Section {

    @Id
    @Column(name = "sectionID", updatable = false, nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer sectionID;
    @Column(unique = true)
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "section", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    Set<Project> projects;

    public int getSectionID() {
        return sectionID;
    }

    public void setSectionID(int sectionID) {
        this.sectionID = sectionID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public void removeProject(Project p){
        projects.remove(p);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Section s = (Section) obj;
        return this.getSectionID() == s.getSectionID() && this.getName().equals(s.getName());
                //&& Objects.equals(this.getProjects(), s.getProjects());
    }

    @Override
    public int hashCode() {
        return 29*sectionID + name.hashCode();
    }
}
