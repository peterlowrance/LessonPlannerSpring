package com.example.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "DESCRIPTOR")
public class Descriptor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer descriptorID;
    @Column(length = 4000)
    private String text;
    private Boolean bigHeader;
    private String header;

    @ManyToOne
    @JoinColumn(name = "ProjectID_FK")
    @JsonBackReference
    private Project project;

    public Integer getDescriptorID() {
        return descriptorID;
    }

    public void setDescriptorID(Integer descriptorID) {
        this.descriptorID = descriptorID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setBigHeader(Boolean bigHeader) {
        this.bigHeader = bigHeader;
    }

    public Boolean getBigHeader() {
        return bigHeader;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
