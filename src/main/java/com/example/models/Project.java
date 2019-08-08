package com.example.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "PROJECT")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer projectID;
    private String name;
    @Basic
    private Date date;

    @OneToMany(fetch = FetchType.EAGER, mappedBy="project", cascade = CascadeType.REFRESH, orphanRemoval = true)
    @JsonManagedReference
    Set<Descriptor> descriptors;

    @ManyToOne
    @JoinColumn(name = "sectionID")
    @JsonBackReference
    private Section section;

    @OneToMany(fetch = FetchType.EAGER, mappedBy="project", cascade = CascadeType.REFRESH, orphanRemoval = true)
    @JsonManagedReference
    Set<Member> members;

    public Integer getProjectID() {
        return projectID;
    }

    public void setProjectID(Integer projectID) {
        this.projectID = projectID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<Descriptor> getDescriptors() {
        return descriptors;
    }

    public void setDescriptors(Set<Descriptor> descriptors) {
        this.descriptors = descriptors;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }

    public void removeFromSection() {
        section.removeProject(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Project p = (Project) obj;
        return this.getProjectID() == p.getProjectID() && this.getName().equals(p.getName())
        && Objects.equals(this.getDate(), p.getDate());
    }

    @Override
    public int hashCode() {
        return 7*projectID + (name != null? name.hashCode(): 0) + (date != null? date.hashCode() : 0) * 29;
    }
}
